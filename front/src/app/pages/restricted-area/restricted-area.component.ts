import {Component, OnDestroy, OnInit} from '@angular/core';
import {NavigationItem} from '../../shared/component/layout/navigation/navigation-item.model';
import {Subscription} from 'rxjs';
import {MenuService} from '../../shared/service/menu.service';
import {ActivatedRoute, Event, NavigationEnd, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-restricted-area',
  templateUrl: './restricted-area.component.html',
  styleUrls: ['./restricted-area.component.scss']
})
export class RestrictedAreaComponent implements OnInit, OnDestroy {

  public menu: Array<NavigationItem> = [];
  public isMenuOpened: boolean = true;
  public title: string = '';
  private subscriptions: Subscription = new Subscription();

  constructor(
    private menuService: MenuService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private titleService: Title
  ) {
  }

  ngOnInit(): void {
    this.configureMenu();
    this.setTitle();
    this.registerOnRouterNavigationEvents();
    this.resisterOnLeftMenu();
  }

  public ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  private configureMenu(): void {
    this.menu = this.menuService.menu;
  }

  private registerOnRouterNavigationEvents(): void {

    const subscription = this.router
      .events
      .subscribe((event: Event) => {
        if (event instanceof NavigationEnd) {
          this.setTitle();
        }
      });
    this.subscriptions.add(subscription);
  }

  private setTitle(): void {
    const data = this.activatedRoute.snapshot.firstChild.data;
    this.title = data && data['title'] || '';
    const browserTabTitle = `E-commerce - ${this.title}`;
    this.titleService.setTitle(browserTabTitle);
  }

  private resisterOnLeftMenu(): void {
    const subscription = this.menuService
      .getToggleMenuObservable()
      .subscribe(status => this.isMenuOpened = status);
    this.subscriptions.add(subscription);
  }

}

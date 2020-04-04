import {Component, OnDestroy, OnInit} from '@angular/core';
import {NavigationItem} from "../../shared/component/layout/navigation/navigation-item.model";
import {Subscription} from "rxjs";
import {MenuService} from "../../shared/service/menu.service";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-restricted-area',
  templateUrl: './restricted-area.component.html',
  styleUrls: ['./restricted-area.component.scss']
})
export class RestrictedAreaComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription = new Subscription();

  public menu: Array<NavigationItem> = [];

  public isMenuOpened: boolean = false;

  public title: string = '';

  constructor(
    private menuService: MenuService,
    private titleService: Title
  ) {
  }

  ngOnInit(): void {
    this.configureMenu();
    this.resisterOnLeftMenu();
    this.title = this.titleService.getTitle();
  }

  private configureMenu(): void {
    this.menu = this.menuService.menu;
  }

  private resisterOnLeftMenu(): void {
    const subscription = this.menuService
      .getToggleMenuObservable()
      .subscribe(status => this.isMenuOpened = status);
    this.subscriptions.add(subscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

}

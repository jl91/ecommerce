import {Component, OnInit} from '@angular/core';
import {MenuService} from '../../../service/menu.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(
    private menuService: MenuService
  ) {
  }

  ngOnInit(): void {
  }

  public toggleMenu(mouseEvent: MouseEvent): void {
    mouseEvent.preventDefault();

    const menuService = this.menuService;

    if (menuService.isMenuOpened) {
      return menuService.closeMenu();
    }

    menuService.openMenu();
  }

}

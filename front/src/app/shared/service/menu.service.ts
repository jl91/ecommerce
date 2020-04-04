import {Injectable} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {NavigationItem} from "../component/layout/navigation/navigation-item.model";

@Injectable()
export class MenuService {

  private toggleMenuSubject: Subject<boolean> = new Subject<boolean>();

  private _isMenuOpened: boolean = false;

  private _menu: Array<NavigationItem> = [
    {
      name: 'Home',
      route: ['/restricted/home']
    },
    {
      name: 'Customers',
      route: ['/restricted/customer']
    },
    {
      name: 'Products',
      route: ['/restricted/products']
    }
  ];

  constructor() {
  }

  public getToggleMenuObservable(): Observable<boolean> {
    return this.toggleMenuSubject.asObservable();
  }

  public openMenu(): void {
    this.toggleMenuSubject.next(true);
    this._isMenuOpened = true;
  }

  public closeMenu(): void {
    this.toggleMenuSubject.next(false);
    this._isMenuOpened = false;
  }

  public get isMenuOpened(): boolean {
    return this._isMenuOpened;
  }

  public get menu(): Array<NavigationItem> {
    return this._menu;
  }

}
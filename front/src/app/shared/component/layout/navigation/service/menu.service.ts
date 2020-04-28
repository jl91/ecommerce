import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {MenuItem} from '../menu-item.model';

@Injectable()
export class MenuService {

  private toggleMenuSubject: Subject<boolean> = new Subject<boolean>();

  constructor() {
  }

  private _isMenuOpened = true;

  public get isMenuOpened(): boolean {
    return this._isMenuOpened;
  }

  private _menu: Array<MenuItem> = [
    {
      name: 'Home',
      icon: 'home',
      routerLink: ['/restricted/home']
    },
    {
      name: 'Customers',
      icon: 'supervisor_account',
      routerLink: ['/restricted/customer']
    },
    {
      name: 'Products',
      icon: 'card_giftcard',
      children: [
        {
          name: 'List',
          icon: 'list',
          routerLink: ['/restricted/products'],
        },
        {
          name: 'Add',
          icon: 'add',
          routerLink: ['/restricted/products/create'],
        }
      ]
    }
  ];

  public get menu(): Array<MenuItem> {
    return this._menu;
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

}

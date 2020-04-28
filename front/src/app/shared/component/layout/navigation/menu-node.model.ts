import {MenuItem} from './menu-item.model';

export interface MenuNode {
  expandable: boolean;
  level: number;
  menuItem: MenuItem;
}

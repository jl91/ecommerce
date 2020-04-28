export interface MenuItem {
  name: string;
  icon: string;
  routerLink?: Array<string>;
  children?: Array<MenuItem>;
}

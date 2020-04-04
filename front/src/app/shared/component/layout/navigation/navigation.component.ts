import {Component, Input, OnInit} from '@angular/core';
import {NavigationItem} from "./navigation-item.model";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  @Input()
  public menu: Array<NavigationItem>

  constructor() {
  }

  ngOnInit(): void {
  }

}

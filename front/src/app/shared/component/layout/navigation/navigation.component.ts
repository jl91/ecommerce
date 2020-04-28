import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
import {MenuNode} from './menu-node.model';
import {MenuItem} from './menu-item.model';
import {FlatTreeControl} from '@angular/cdk/tree';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit, OnChanges {

  @Input()
  public menu: Array<MenuItem>;

  public treeControl = new FlatTreeControl<MenuNode>(
    node => node.level,
    node => node.expandable
  );

  public treeFlattener = new MatTreeFlattener<MenuItem, MenuNode>(
    this.transformer.bind(this),
    node => node.level,
    node => node.expandable,
    node => node.children
  );

  public dataSource = new MatTreeFlatDataSource(
    this.treeControl,
    this.treeFlattener
  );

  constructor() {
  }

  public hasChild(_: number, node: MenuNode) {
    return node.expandable;
  }

  public ngOnInit(): void {
  }

  public ngOnChanges(changes: SimpleChanges): void {
    if (changes.menu.currentValue) {
      this.dataSource.data = this.menu
    }
  }

  private transformer(node: MenuItem, level: number): MenuNode {
    return {
      expandable: node?.children?.length > 0,
      level,
      menuItem: node
    };
  }


}

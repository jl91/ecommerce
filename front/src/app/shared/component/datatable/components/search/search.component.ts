import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: 'search.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SearchComponent implements OnInit {

  constructor() {
  }

  public ngOnInit() {
  }
}

import {Component, OnInit} from '@angular/core';
import {ProductForm} from './product.form';

@Component({
  selector: 'app-save',
  templateUrl: 'save.component.html',
  styleUrls: [
    'save.component.scss'
  ]
})
export class SaveComponent implements OnInit {
  constructor(
    public productForm: ProductForm
  ) {
  }

  public ngOnInit(): void {
  }
}

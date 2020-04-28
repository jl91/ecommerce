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

  public onSubmit(event: any): void {
    console.log(event);

    if (this.productForm.invalid) {
      return this.productForm.markAllAsTouched();
    }

    const {sku, name, value, description} = this.productForm.getRawValue();

    console.table({
      sku,
      name,
      value,
      description
    });

  }
}

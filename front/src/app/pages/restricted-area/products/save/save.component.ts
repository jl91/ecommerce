import {Component, OnInit} from '@angular/core';
import {ProductForm} from './product.form';
import {ProductsHttpService} from "../../../../core/products/products-http.service";
import {Product} from "../../../../core/products/product.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-save',
  templateUrl: 'save.component.html',
  styleUrls: [
    'save.component.scss'
  ]
})
export class SaveComponent implements OnInit {
  constructor(
    public productForm: ProductForm,
    private productsHttpService: ProductsHttpService,
    private router: Router
  ) {
  }

  public ngOnInit(): void {
  }

  public onSubmit(event: any): void {
    if (this.productForm.invalid) {
      return this.productForm.markAllAsTouched();
    }

    const {sku, name, value, description} = this.productForm.getRawValue();

    const subscription = this.productsHttpService
      .save({
        sku,
        name,
        value,
        description
      })
      .subscribe((product: Product) => {
        console.log(product);
        subscription.unsubscribe();
        this.productForm.reset();
        this.redirect();
      });
  }

  private redirect(): void {
    this.router.navigate(['/restricted/products']);
  }
}

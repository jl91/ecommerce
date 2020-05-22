import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Injectable} from '@angular/core';

@Injectable()
export class ProductForm extends FormGroup {

  constructor() {
    super({
      sku: new FormControl(
        undefined,
        [
          Validators.required
        ]),
      name: new FormControl(
        undefined,
        {
          validators: [
            Validators.required
          ]
        }),
      description: new FormControl(
        undefined,
        {
          validators: [
            Validators.required
          ]
        }),
      value: new FormControl(
        undefined,
        {
          validators: [
            Validators.required
          ]
        })
    });
  }
}

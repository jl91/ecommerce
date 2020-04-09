import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Injectable} from '@angular/core';

@Injectable()
export class LoginForm extends FormGroup {

  constructor() {
    super({
      username: new FormControl(
        undefined,
        [
          Validators.required
        ]),
      password: new FormControl(
        undefined,
        {
          validators: [
            Validators.required
          ]
        })
    });
  }
}

import {Component, OnInit} from '@angular/core';
import {LoginForm} from './login.form';
import {AuthorizationService} from '../../../core/services/authorization.service';
import {AuthorizationModel} from '../../../core/model/authorization.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [
    LoginForm
  ]
})
export class LoginComponent implements OnInit {

  constructor(
    public formGroup: LoginForm,
    public authorizationService: AuthorizationService
  ) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (!this.formGroup.valid) {
      this.formGroup.markAllAsTouched();
      return false;
    }

    const {username, password} = this.formGroup.getRawValue();

    const subscription = this.authorizationService
      .login(username, password)
      .subscribe((data: AuthorizationModel) => {
          console.log(data);
          subscription.unsubscribe();
        },
        console.error.bind(console)
      );

  }

}

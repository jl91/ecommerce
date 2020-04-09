import {Component, OnInit} from '@angular/core';
import {LoginForm} from './login.form';
import {LoginService} from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [
    LoginForm,
    LoginService
  ]
})
export class LoginComponent implements OnInit {

  constructor(
    public formGroup: LoginForm,
    private loginService: LoginService
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

    this.loginService
      .login(username, password);

  }

}

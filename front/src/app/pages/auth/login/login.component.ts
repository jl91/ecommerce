import {Component, OnInit} from '@angular/core';
import {LoginForm} from "./login.form";

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
    public formGroup: LoginForm
  ) {
  }

  ngOnInit(): void {
  }

}

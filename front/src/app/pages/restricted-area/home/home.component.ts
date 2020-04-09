import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../core/authentication/authentication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {


  username: string;

  constructor(
    private authtenticationService: AuthenticationService
  ) {
  }

  ngOnInit(): void {
    const {username} = this.authtenticationService.loggerUser;
    this.username = username;
  }

}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  hide = true;

  constructor() { }

  ngOnInit() {
  }

  attemptLogin() {
    const that = this;

  }
}

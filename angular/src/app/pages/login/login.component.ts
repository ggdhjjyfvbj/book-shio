import { Component } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {log} from "node:util";
import {HttpService} from "../../service/http/http.service";
import {response} from "express";
import {AuthenticateService} from "../../response/login/authenticate-service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  title = 'Login'

  authenticate: AuthenticateService = {}

  loginForm = new FormGroup({
    username: new FormControl<string>(''),
    password: new FormControl<string>('')
  })
  constructor(
    private httpService:HttpService,
    private router: Router,
    private titleService:Title
  ) {
  }

  onNgInit() {
    this.titleService.setTitle(this.title)
  }

  onLogin() {
    this.httpService.login(this.loginForm.value)
      .subscribe(
        (response) => {
          this.authenticate = response;
          localStorage.setItem('token', this.authenticate.token as string)
          setTimeout(() => {
            this.router.navigate(['main']);
          }, 1000)
        }
      )
  }

}

import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Title} from "@angular/platform-browser";
import {HttpService} from "../../service/http/http.service";
import {response} from "express";
import {Router} from "@angular/router";
import {UserNameResponse} from "../../response/main/UserNameResponse";

let token: string | null
@Component({
  selector: 'app-main',
  standalone: true,
  imports: [],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {

  title = 'Main';
  authorization: string = '';
  userResponse: UserNameResponse = {};

  constructor(
    private titleService: Title,
    private router: Router,
    private httpService:HttpService
  ) {
  }

  ngOnInit() {
    this.titleService.setTitle(this.title)
    if (typeof localStorage !== "undefined") {
      token = localStorage.getItem('token');
    }
    this.hello();
    this.redirect();
  }

  redirect() {
    if (!token) {
      this.authorization = 'Авторизация';
    }
  }

  redirectToLoginOrProfile() {
    if (!token) {
      this.router.navigate(['login'])
    } else {
      this.router.navigate(['profile'])
    }
  }

  hello() {
    if (token) {
      this.httpService.main(token).subscribe(
        (response) => {
          this.userResponse = response;
          this.authorization = this.userResponse.username as string;
        }
      )
    }
  }
}

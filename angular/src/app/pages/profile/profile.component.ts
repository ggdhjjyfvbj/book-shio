import { Component } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {HttpService} from "../../service/http/http.service";
import {TokenService} from "../../service/token/toke.service";
import {response} from "express";
import {UserResponse} from "../../response/profile/UserResponse";
import {Router} from "@angular/router";
import {NgStyle} from "@angular/common";

let token: string | null;
let colors = ['#e95b2b', '#ff4100', '#00f87e', '#7dfb00']
@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    NgStyle
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {
  title = 'profile';
  userResponse: UserResponse = {};
  username: string = ''
  email: string = ''
  constructor(
    private titleService:Title,
    private router:Router,
    private tokenService:TokenService,
    private httpService:HttpService
  ) {
  }

  ngOnInit() {
    this.titleService.setTitle(this.title);
    if (!this.tokenService.getToken()) {
      this.router.navigate(['login'])
    } else {
      this.profile();
    }
  }

  logout() {
    this.tokenService.clearToken('token');
    setTimeout(() => {
      this.router.navigate(['login']);
    }, 1000)
  }

  style() {
    let random = Math.floor(Math.random() * colors.length)
    return colors[random];
  }

  profile() {
    this.httpService.profile(this.tokenService.getToken())
      .subscribe(
        ( response ) => {
          this.userResponse = response;
          this.email = this.userResponse.email as string;
          this.username = this.userResponse.username as string;
        }
      )
  }

  protected readonly colors = colors;
}

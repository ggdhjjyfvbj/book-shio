import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as http from "http";

const defaultURL = "http://localhost:8082/"
@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(
    private httpClient:HttpClient
  ) {

  }

  public register(registerRequest:any) {
    return this.httpClient.post(defaultURL + 'api/users/register', registerRequest)
  }

  public login(loginRequest:any) {
    return this.httpClient.post(defaultURL + 'api/users/login', loginRequest)
  }

  public main(token: string | null) {
    return this.httpClient.get(defaultURL + "main", {
      headers: new HttpHeaders().set('Authorization', 'Bearer ' + token)
    })
  }

  public profile(token: string | null) {
    return this.httpClient.get(defaultURL + "profile", {
      headers: new HttpHeaders().set('Authorization', 'Bearer ' + token)
    })
  }
}

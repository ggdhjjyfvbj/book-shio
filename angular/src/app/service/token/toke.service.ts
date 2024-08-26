import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private token(key: string) {
    let token: string | null = null;
    if (typeof localStorage) {
      token = localStorage.getItem(key)
    }
    return token;
  }
  getToken() {
    return this.token('token');
  }

  clearToken(token: string) {
    localStorage.removeItem(token);
  }
}

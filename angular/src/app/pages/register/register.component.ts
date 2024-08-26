import { Component } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {timeoutProvider} from "rxjs/internal/scheduler/timeoutProvider";
import {Router} from "@angular/router";
import {HttpService} from "../../service/http/http.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  title = 'Register'

  registerForm = new FormGroup({
    username: new FormControl<string>(''),
    password: new FormControl<string>(''),
    confirmPassword: new FormControl<string>(''),
    email: new FormControl<string>('')
  })
  constructor(
    private httpService: HttpService,
    private titleService:Title) {
  }

  ngOnInit() {
    this.titleService.setTitle(this.title)
  }

  onRegister() {
    this.httpService.register(this.registerForm.value)
      .subscribe(
        (response) => {
          console.log(response);
        }
      )
  }
}

import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  credentials = {
    username: '',
    password: '',
    confirmPassword: '',
    email: '',
    admin: false,
    executor: false
  };
  massage: string;

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
    if (!this.app.authenticated) {
      app.rout = '/reg';
      this.router.navigateByUrl('/login');
      return;
    }
  }

  register() {
    this.massage = this.app.message;
    this.app.register(this.credentials, () => {
      this.massage = this.app.message;
    });
  }

  ngOnInit(): void {
  }
}



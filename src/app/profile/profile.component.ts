import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  greeting = {};
  login = AppService.log;

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
    http.get('http://localhost:1985').subscribe(data => {this.greeting = data;
                                                         console.log(data); });
  }

  authenticated() { return this.app.authenticated; }



  ngOnInit(): void {
  }

  edit() {
    if (!this.app.authenticated) {
      this.router.navigateByUrl('/login');
      return;
    }
    this.router.navigateByUrl('/profile/edit');
  }
}

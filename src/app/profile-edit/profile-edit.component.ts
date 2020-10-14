import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';

class User {
  id: string;
  username: string;
  email: string;
  admin: boolean;
  img: string;
  firstName: string;
  lastName: string;
  middleName: string;
  password: string;
}

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {
  userEd: User = {admin: false, email: '', firstName: '', id: '', img: '', lastName: '', middleName: '', password: '', username: ''} ;
  user: User = AppService.log.user;
  credentials = {username: '', password: ''};
  constructor(private app: AppService, private http: HttpClient, private router: Router, private cookieService: CookieService) {
    if (!this.app.authenticated) {
      this.router.navigateByUrl('/login');
      return;
    }
  }

  ngOnInit(): void {
  }

  edit() {
    this.userEd.id = AppService.log.user.id;
    this.http.put(AppService.servrURL + 'edit', this.userEd).subscribe((userEd: User) => {
      console.log(userEd);
      this.credentials.username = this.cookieService.get('us');
      this.credentials.password = this.cookieService.get('ps');
      this.app.authenticate(this.credentials, () => {
        this.router.navigateByUrl('/');
      });
    });
  }
}

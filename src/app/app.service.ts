import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';

class Reg {
  error: string;
}

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

class Log {
  id: string;
  name: string;
  roles: string;
  user: User;

}

@Injectable()
export class AppService {

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
    if (!('' === this.cookieService.get('ps'))) {
      this.credentials.username = this.cookieService.get('us');
      this.credentials.password = this.cookieService.get('ps');
      this.authenticate(this.credentials, () => {
      });
    }
  }

  static log: Log;
  static servrURL = '//localhost:1985/';
  credentials = {username: '', password: ''};

  authenticated = false;
  reg: Reg = {error: ''};

  message = '';


  static get_Roles() {
    return AppService.log.roles;
  }

  authenticate(credentials, callback) {
    this.authenticated = false;
    let headers;
    headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    this.http.get(AppService.servrURL + 'user', {headers}).subscribe((response: Log) => {
      AppService.log = response;

      if (AppService.log.name) {
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }


      this.cookieService.set('ps', credentials.password);
      this.cookieService.set('us', credentials.username);
      console.log(response);
      return callback && callback();
    });

  }


  register(credentials, callback) {
    if (!this.authenticated) {
      this.router.navigateByUrl('/login');
      return;
    }
    this.message = null;
    this.http.post(AppService.servrURL + 'reg', credentials).subscribe((response: Reg) => {
      this.message = response.error;
      if (response.error == null) {
        this.message = 'Пользователь зарегестрирован';
      }
      return callback && callback();
    });

  }

  projectCrate(credentials, callback) {
    if (!this.authenticated) {
      this.router.navigateByUrl('/login');
      return;
    }
    this.message = null;
    this.http.post(AppService.servrURL + 'project/create', credentials).subscribe(next => console.log(next),
      error => console.log(error));

  }
}


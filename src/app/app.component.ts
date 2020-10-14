import {Component} from '@angular/core';
import {HttpClient, HttpClientXsrfModule} from '@angular/common/http';
import {AppService} from './app.service';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'tehSystem';

  greeting = {};
  credentials = {username: '', password: ''};

  constructor(private app: AppService, private http: HttpClient, private router: Router, private cookieService: CookieService) {
    this.app.authenticate(this.credentials, undefined);
    http.get('//localhost:1985/resource').subscribe(data => this.greeting = data);
  }


  logout() {

    this.http.post('logout', {}).subscribe(() => {
      this.app.authenticated = false;
      this.router.navigateByUrl('/login');
    });
  }
}

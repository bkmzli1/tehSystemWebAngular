import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    credentials = {username: '', password: ''};
    error;

    constructor(private app: AppService, private http: HttpClient, private router: Router, private cookieService: CookieService) {
        if (!('' === this.cookieService.get('ps'))) {
            this.credentials.username = this.cookieService.get('us');
            this.credentials.password = this.cookieService.get('ps');
            this.app.authenticate(this.credentials, () => {
                this.router.navigateByUrl(app.rout);
            });
        }
        if (this.app.authenticated) {
            this.router.navigateByUrl('/profile');
            return;
        }
    }

    login() {
        this.app.authenticate(this.credentials, () => {
            this.router.navigateByUrl('/');
        });

    }


    ngOnInit(): void {
    }

}

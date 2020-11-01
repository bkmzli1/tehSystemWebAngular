import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
    selector: 'app-my-orders',
    templateUrl: './my-orders.component.html',
    styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {

    constructor(private app: AppService, private http: HttpClient, private router: Router) {
        if (!this.app.authenticated) {
            app.rout = '/orders';
            this.router.navigateByUrl('/login');
            return;
        }
    }

    ngOnInit(): void {
    }

}

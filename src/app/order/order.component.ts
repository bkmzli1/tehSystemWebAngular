import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {AppService} from '../app.service';

class Project {
    id: string;
    name: string;
    userCrate: User;
    userExecutor: User;
    img: Img[];
    text: string;
    creationDate: string;
    completionDate: string;
    done: boolean;
}

class Img {
    img: string;
    name: string;
    id: string;
}

class User {
    id: Img;
    name: string;
    img: string;
    email: string;
    telephone: string;
    lastName: string;
    firstName: string;
    middleName: string;
}

@Component({
    selector: 'app-order',
    templateUrl: './order.component.html',
    styleUrls: ['./order.component.css']
})
export class OrderComponent {
    orders: Project;
    sservrURL: string;

    constructor(private app: AppService, private router: Router, private http: HttpClient) {
        if (!this.app.authenticated) {
            app.rout = '/orders';
            this.sservrURL = app.sservrURL;
            this.router.navigateByUrl('/login');
            return;
        }
        this.http.post(AppService.servrURL + 'project/get/' + app.idProject, AppService.log.user.id).subscribe((response: Project) => {
            console.log(response);
            this.orders = response;
        });
    }


    href(s: string) {
        console.log(s);

    }
}

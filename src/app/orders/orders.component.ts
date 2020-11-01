import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

class User {
    id: string;
    name: string;
    img: string;
    email: string;
    telephone: string;
}


class Project {
    id: string;
    userCrate: User;
    userExecutor: User;
    text: string;
    creationDate: string;
    completionDate: string;
    done: boolean;
}

@Component({
    selector: 'app-orders',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
    orders: Project[];
    log = AppService.log;
    i = 0;
    AppService: any;


    constructor(private app: AppService, private router: Router, private http: HttpClient) {
        this.http.post(AppService.servrURL + 'project/get', AppService.log.user.id).subscribe((response: Project[]) => {
            console.log(response);
            this.orders = response;
        });
    }

    ngOnInit(): void {
    }

    id(i: number) {
        console.log(i);
        i++;
        this.i = i;
        return i;
    }

    text(id: string) {
        this.app.idProject = id;
        console.log(this.app.idProject);
        this.router.navigateByUrl('/order');
    }
}

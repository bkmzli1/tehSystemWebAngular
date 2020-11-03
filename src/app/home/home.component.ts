import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    private greeting;

    constructor(private app: AppService, private http: HttpClient, private router: Router) {

    }

    ngOnInit(): void {
    }

}

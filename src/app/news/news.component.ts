import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
    if (!this.app.authenticated) {
      app.rout = '/news';
      this.router.navigateByUrl('/login');
      return;
    } }

  ngOnInit(): void {
  }

}

import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  orders: any;
  i = 0;


  constructor(private app: AppService, private http: HttpClient) {
    this.http.post(AppService.servrURL + 'project/get',  AppService.log.user.id ).subscribe((response ) => {
      console.log(response);
      this.orders = response;
    });
  }
  ngOnInit(): void {
  }

  id(i: number) {
    console.log(i);
    i ++;
    this.i = i ;
    return i;
  }
}

import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {AppService} from '../app.service';
class Project {
  id: string;
  userCrate: User;
  userExecutor: User;
  text: string;
  creationDate: string;
  completionDate: string;
  done: boolean;
}
class User {
  id: string;
  name: string;
  img: string;
  email: string;
  telephone: string;
}

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orders: Project;

  constructor(private app: AppService, private router: Router, private http: HttpClient) {
    this.http.post(AppService.servrURL + 'project/get/' + app.idProject, AppService.log.user.id).subscribe((response: Project) => {
      console.log(response);
      this.orders = response;
    });
  }

  ngOnInit(): void {
  }

}

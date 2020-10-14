import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {AppService} from '../app.service';

class User {
  id: string;
  name: string;
  img: string;
  email: string;
}

@Component({
  selector: 'app-orders-create',
  templateUrl: './orders-create.component.html',
  styleUrls: ['./orders-create.component.css']
})

export class OrdersCreateComponent implements OnInit {
  user: User = null;

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
    this.http.get(AppService.servrURL + 'executor').subscribe((response: User[]) => {
      this.users = response;
      console.log(this.users);
    });

  }

  selectedValue;
  users: User[];
  text: string;
  error = false;
  errorText: string;


  ngOnInit(): void {
  }


  test(item: string) {
    console.log(item);
    this.http.get(AppService.servrURL + 'id/' + item).subscribe((response: User) => {
      this.user = response;
    });
  }

  print(img: string) {
    console.log(img);
    return img;
  }

  create() {
    if (!!this.text) {
      this.error = false;
      const project = {userCrate: AppService.log.user.id, userExecutor: this.user.id, text: this.text};
      this.app.projectCrate(project, () => null);
      this.router.navigateByUrl('/orders');
    } else {
      this.errorText = 'Заполните все поля!';
      this.error = true;
    }
  }
}

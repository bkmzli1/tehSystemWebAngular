import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';

@Component({
  selector: 'app-head',
  templateUrl: './head.component.html',
  styleUrls: ['./head.component.css']
})
export class HeadComponent implements OnInit {
  admin = false;


  constructor(private app: AppService) {
  }

  authenticated() {
    return this.app.authenticated;
  }

  adminIs() {
    if (this.app.authenticated) {
     return AppService.get_Roles() != 'User';
    } else {
      return false;
    }
  }

  ngOnInit(): void {
  }

  userIs() {
   return this.app.authenticated;
  }
}

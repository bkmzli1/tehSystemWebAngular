import { BrowserModule } from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {
  HTTP_INTERCEPTORS,
  HttpClientModule,
  HttpClientXsrfModule, HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpXsrfTokenExtractor
} from '@angular/common/http';
import {AppService} from './app.service';
import {RouterModule, Routes} from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import {FormsModule} from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import {Observable} from 'rxjs';
import { HeadComponent } from './head/head.component';
import { BordComponent } from './bord/bord.component';
import { NewsComponent } from './news/news.component';
import { OrdersComponent } from './orders/orders.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { ProfileComponent } from './profile/profile.component';
import { OrdersCreateComponent } from './orders-create/orders-create.component';
import { ProfileEditComponent } from './profile-edit/profile-edit.component';
import {CookieService} from 'ngx-cookie-service';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home'},
  { path: 'home', component: HomeComponent},
  { path: 'reg', component: RegisterComponent},
  { path: 'news', component: NewsComponent},
  { path: 'orders', component: OrdersComponent},
  { path: 'ordersCreate', component: OrdersCreateComponent},
  { path: 'myOrders', component: MyOrdersComponent},
  { path: 'profile', component: ProfileComponent},
  { path: 'profile/edit', component: ProfileEditComponent},
  { path: 'login', component: LoginComponent}
];
@Injectable()
export class HttpXsrfInterceptor implements HttpInterceptor {

  constructor(private tokenExtractor: HttpXsrfTokenExtractor) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const headerName = 'X-XSRF-TOKEN';
    const token = this.tokenExtractor.getToken() as string;
    if (token !== null && !req.headers.has(headerName)) {
      req = req.clone({ headers: req.headers.set(headerName, token) });
    }
    return next.handle(req);
  }
}


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    HeadComponent,
    BordComponent,
    NewsComponent,
    OrdersComponent,
    MyOrdersComponent,
    ProfileComponent,
    OrdersCreateComponent,
    ProfileEditComponent,

  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'My-Xsrf-Cookie', // this is optional
      headerName: 'My-Xsrf-Header' // this is optional
    }),
    FormsModule,
  ],
  providers: [AppService, { provide: HTTP_INTERCEPTORS, useClass: HttpXsrfInterceptor, multi: true },  HttpClientModule, CookieService ],
  bootstrap: [AppComponent],
})
export class AppModule { }




import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {AppService} from '../app.service';

class User {
    id: string;
    name: string;
    img: string;
    email: string;
    telephone: string;
}

class Img {
    img: string;
}

class Project {
    id: string;
    userCrate: User;
    userExecutor: User;
    text: string;
    img = {};
}

@Component({
    selector: 'app-orders-create',
    templateUrl: './orders-create.component.html',
    styleUrls: ['./orders-create.component.css']
})

export class OrdersCreateComponent implements OnInit {
    user: User = null;

    constructor(private app: AppService, private http: HttpClient, private router: Router) {
        if (!this.app.authenticated) {
            app.rout = '/ordersCreate';
            this.router.navigateByUrl('/login');
            return;
        }
        this.http.get(AppService.servrURL + 'executor').subscribe((response: User[]) => {
            this.users = response;

        });

    }

    fileToUpload: FormData = null;
    selectedValue;
    users: User[];
    text = '';
    error = false;
    errorText: string;


    ngOnInit(): void {
    }


    test(item: string) {

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
            this.app.projectCrate(project, () => null, 'orders', this.fileToUpload);

        } else {
            this.errorText = 'Заполните все поля!';
            this.error = true;
        }
    }

    handleFileInput(fileList: FileList) {
        console.log(fileList);
        const uploadData = new FormData();
        for (let i = 0; i < fileList.length; i++) {
            uploadData.append('img', fileList[i], fileList.item(i).name);
        }
        this.fileToUpload = uploadData;

    }
}

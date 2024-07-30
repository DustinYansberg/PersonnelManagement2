import { Component } from '@angular/core';
import { User } from '../models/user';
import { HttpService } from '../services/http.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-user-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-user-form.component.html',
  styleUrl: './update-user-form.component.css'
})
export class UpdateUserFormComponent {

    user: User = new User(0, "username", "firstname 2", "lastname 2", "displayname","email",0,"type")

    constructor(private httpService: HttpService){
      this.getUserById();
    }

    getUserById(){
      this.user = this.httpService.getUserById();
    }

    updatedUser: User = new User(0, "", "", "", "","",0,"")

    updateUser(){
      this.httpService.updateUser(this.updatedUser)
    }
}

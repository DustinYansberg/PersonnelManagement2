import { Component } from '@angular/core';
import { UserCardComponent } from '../user-card/user-card.component';
import { HttpService } from '../services/http.service';
import { User } from '../models/user';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [UserCardComponent],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
users: User[] = [];

  constructor(private httpService: HttpService){
    this.getAllUsers();
  }
  getAllUsers(){
    //this.users = this.httpService.getAllUsers();
    this.httpService.getAllUsers().subscribe(resp=>{
      this.users = [];
      for (let item of resp.body['Resources']){
        console.log
        if(item.name.givenName){
        this.users.push(
          new User(item.id, item.userName, item.name.givenName, item.name.familyName, item.displayName, item.emails.value, item.managerId, item.type)
        );
     }
      }
  })
  }

  deleteUser(userId: number){
    this.httpService.deleteUser(userId)
    //.subscribe(response =>
    this.getAllUsers();
  //)
  }

  processDeleteEvent(userId: number){
    this.deleteUser(userId);
  }
}

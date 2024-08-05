import { Component, NgModule } from '@angular/core';
import { UserCardComponent } from '../user-card/user-card.component';
import { HttpService } from '../services/http.service';
import { User } from '../models/user';
import { Button } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { FilterService } from 'primeng/api';


@Component({
  selector: 'app-users',
  standalone: true,
  imports: [UserCardComponent, Button, FormsModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
users: User[] = [];
searchStr: string = '';

  constructor(private httpService: HttpService, private filterService: FilterService){
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

  //Sorting
  sortLast(){
    this.users = this.users.sort((a,b) =>
      a.lastName.localeCompare(b.lastName)
    );
  }

  sortFirst(){
    this.users = this.users.sort((a,b) =>
      a.firstName.localeCompare(b.firstName)
    );
  }


  search(){
    let str = this.searchStr
    this.users =this.users.filter(user => {
      return user.firstName.toLocaleLowerCase().startsWith(str.toLocaleLowerCase()) 
      || user.lastName.toLocaleLowerCase().startsWith(str.toLocaleLowerCase()) 
    });
  }
  reset(){
    this.getAllUsers();
    this.searchStr ='';
  }
}

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
    this.users = this.httpService.getAllUsers()
    console.log(this.users);
  }
}

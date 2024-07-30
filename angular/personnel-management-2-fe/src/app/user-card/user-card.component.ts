import { Component, Input } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { HttpService } from '../services/http.service';

@Component({
  selector: 'app-user-card',
  standalone: true,
  imports: [CardModule, ButtonModule],
  templateUrl: './user-card.component.html',
  styleUrl: './user-card.component.css'
})
export class UserCardComponent {

  constructor(private router:Router, private htttpService: HttpService){}

  @Input() user: User = new User(0, "", "","","", "",0,"");

  editUser(){
    console.log("edit")
  }
  deleteUser(){
    
  }
}

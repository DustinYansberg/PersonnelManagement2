import { Component, EventEmitter, Input, Output } from '@angular/core';
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

  @Input() user: User = new User('', '', '', '', '', '', '', '', '', '', '','','',true,'');

  @Output() deleteUserEvent = new EventEmitter<string>();

  editUser(){
    this.router.navigate(['update/'+this.user.userId]);
  }
  deleteUser(){
    this.deleteUserEvent.emit(this.user.userId);
    this.router.navigate(['users'])
  }
}

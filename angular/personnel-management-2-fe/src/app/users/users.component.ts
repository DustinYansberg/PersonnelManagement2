import { Component } from '@angular/core';
import { UserCardComponent } from '../user-card/user-card.component';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [UserCardComponent],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
users = ['', '', '', '', ''];
}

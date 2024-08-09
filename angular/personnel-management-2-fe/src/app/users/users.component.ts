import { Component, NgModule } from '@angular/core';
import { UserCardComponent } from '../user-card/user-card.component';
import { HttpService } from '../services/http.service';
import { User } from '../models/user';
import { Button } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { FilterService, PrimeIcons } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [UserCardComponent, Button, FormsModule],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css',
})
export class UsersComponent {
  users: User[] = [];
  searchStr: string = '';

  constructor(
    private httpService: HttpService,
    private filterService: FilterService,
    private router: Router
  ) {
    this.getAllUsers();
  }
  getAllUsers() {
    this.httpService.getAllUsers().subscribe((resp) => {
      this.users = [];
      for (let item of resp.body['Resources']) {
        if (item.displayName) {
          const dynamicKey =
            'urn:ietf:params:scim:schemas:sailpoint:1.0:User' as keyof typeof item;
          const adminValue = item[dynamicKey];
          const dynamicKey2 =
            'urn:ietf:params:scim:schemas:extension:enterprise:2.0:User' as keyof typeof item;
          const managerValue = item[dynamicKey2];
          this.users.push(
            new User(
              item.id,
              item.userName,
              'password',
              item.name.givenName,
              item.name.familyName,
              item.displayName,
              item.emails[0].value,
              managerValue.manager.displayId,
              managerValue.manager.value,
              item.userType,
              item.meta.version,
              adminValue.administrator.displayName,
              adminValue.administrator.value,
              true,
              'department'
            )
          );
        }
      }
    });
    this.sortLastAZ();
  }

  deleteUser(userId: string) {
    this.httpService.deleteUser(userId).subscribe((response) => {
      this.getAllUsers();
    });
  }

  processDeleteEvent(userId: string) {
    this.deleteUser(userId);
  }

  //Sorting
  sortLastAZ() {
    this.users = this.users.sort((a, b) =>
      a.lastName?.localeCompare(b.lastName)
    );
  }

  sortLastZA() {
    this.users = this.users.sort((a, b) =>
      b.lastName?.localeCompare(a.lastName)
    );
  }

  sortFirstAZ() {
    this.users = this.users.sort((a, b) =>
      a.firstName?.localeCompare(b.firstName)
    );
  }

  sortFirstZA() {
    this.users = this.users.sort((a, b) =>
      b.firstName?.localeCompare(a.firstName)
    );
  }

  search() {
    // let str = this.searchStr
    // this.users =this.users.filter(user => {
    //   return user.firstName.toLocaleLowerCase().startsWith(str.toLocaleLowerCase())
    //   || user.lastName.toLocaleLowerCase().startsWith(str.toLocaleLowerCase())
    // });
  }
  reset() {
    this.getAllUsers();
    this.searchStr = '';
  }

  clicked(id: string) {
    this.router.navigate(['user/' + id]);
  }
}

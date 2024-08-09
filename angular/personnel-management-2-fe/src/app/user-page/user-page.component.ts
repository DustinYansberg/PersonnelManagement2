import { Component } from '@angular/core';
import { HttpService } from '../services/http.service';
import { ActivatedRoute } from '@angular/router';
import { User } from '../models/user';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { Account } from '../models/account';

@Component({
  selector: 'app-user-page',
  standalone: true,
  imports: [CardModule, ButtonModule],
  templateUrl: './user-page.component.html',
  styleUrl: './user-page.component.css'
})
export class UserPageComponent {

  constructor(private httpService:HttpService, private route: ActivatedRoute){
    this.getUserById()
  }

  user: User = new User('', '', '', '', '', '', '', '', '', '', '','','',true,'');
  userAccounts: Account[]=[];
  getUserById() {
    this.httpService.getUserById(this.route.snapshot.params['id']).subscribe(resp => {
      let item =resp.body;
      const dynamicKey = "urn:ietf:params:scim:schemas:sailpoint:1.0:User" as keyof typeof item;
      const adminValue = item[dynamicKey];
      const dynamicKey2 = "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User" as keyof typeof item;
      const managerValue = item[dynamicKey2];
      console.log(item)
      this.user =new User(item.id, item.userName, 'password', item.name.givenName, item.name.familyName, item.displayName, item.emails[0].value, managerValue.manager.displayId, managerValue.manager.value, item.meta.resourceType, item.meta.version, adminValue.administrator.displayName, adminValue.administrator.value, true, 'department');
      const dynamicKey3 = "urn:ietf:params:scim:schemas:sailpoint:1.0:User" as keyof typeof item;
      const accountValue = item[dynamicKey3];
      this.getUserAccountIds(accountValue.accounts);
    });
  }

  getUserAccountIds(accounts:any){
      for (let account of accounts){
        console.log("account "+account.value)
        this.httpService.getAccountById(account.value).subscribe(
          {
            next: resp => {
              let item = resp.body;
              console.log(item)
              const dynamicKey5 = "urn:ietf:params:scim:schemas:sailpoint:1.0:Application:Schema:Salesforce:account" as keyof typeof item;
      const accountNestedInfo = item[dynamicKey5];
      console.log(accountNestedInfo)
          this.userAccounts.push(new Account(item.application.value, item.identity.value, item.nativeIdentity, item.identity.displayName, item.id, 'password','password', item.active, item.locked, item.manuallyCorrelated, item.hasEntitlements, item.application.displayName, accountNestedInfo.Username, accountNestedInfo.FirstName, accountNestedInfo.LastName, accountNestedInfo.CommunityNickname, accountNestedInfo.Alias, accountNestedInfo.Email));
            },
            error: err => {
              console.log(err);
            }
          });
      }
      console.log(this.userAccounts);
  }
}

import { Component } from '@angular/core';
import { HttpService } from '../services/http.service';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { User } from '../models/user';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { Account } from '../models/account';

@Component({
  selector: 'app-user-page',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CardModule, ButtonModule],
  templateUrl: './user-page.component.html',
  styleUrl: './user-page.component.css',
})
export class UserPageComponent {
  newAccountForm: FormGroup;
  constructor(
    private httpService: HttpService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
    this.getUserById();
    this.newAccountForm = this.formBuilder.group({
      accountAppId: [
        '',
        Validators.compose([Validators.required, Validators.minLength(4)]),
      ],
      accountUserId: [
        '',
        Validators.compose([Validators.required, Validators.minLength(4)]),
      ],
      nativeIdentity: [''],
      displayName: ['', Validators.required],
      instanceId: [''],
      password: [''],
      currentPassword: ['', Validators.required],
      active: [true],
      locked: [false],
      manuallyCorrelated: [false],
      hasEntitlements: [true],
      accountAppName: ['', Validators.required],
      salesforceUsername: ['', Validators.required],
      salesforceFirstName: ['', Validators.required],
      salesforceLastName: ['', Validators.required],
      salesforceCommunityNickname: ['', Validators.required],
      salesforceAlias: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(8),
          Validators.maxLength(8),
        ]),
      ],
      salesforceEmail: ['', Validators.required],
    });
  }
  isAccountBlockOpen: boolean = false;
  account: Account = new Account(
    '0a03000490e613798190f06722171485',
    '',
    '',
    '',
    '',
    '',
    '',
    true,
    false,
    false,
    false,
    '',
    '',
    '',
    '',
    '',
    '',
    ''
  );
  user: User = new User(
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    true,
    ''
  );
  userAccounts: Account[] = [];
  getUserById() {
    this.httpService
      .getUserById(this.route.snapshot.params['id'])
      .subscribe((resp) => {
        let item = resp.body;
        const dynamicKey =
          'urn:ietf:params:scim:schemas:sailpoint:1.0:User' as keyof typeof item;
        const adminValue = item[dynamicKey];
        const dynamicKey2 =
          'urn:ietf:params:scim:schemas:extension:enterprise:2.0:User' as keyof typeof item;
        const managerValue = item[dynamicKey2];
        console.log(item);
        this.user = new User(
          item.id,
          item.userName,
          'password',
          item.name.givenName,
          item.name.familyName,
          item.displayName,
          item.emails[0].value,
          managerValue.manager.displayId,
          managerValue.manager.value,
          item.meta.resourceType,
          item.meta.version,
          adminValue.administrator.displayName,
          adminValue.administrator.value,
          true,
          'department'
        );
        const dynamicKey3 =
          'urn:ietf:params:scim:schemas:sailpoint:1.0:User' as keyof typeof item;
        const accountValue = item[dynamicKey3];
        this.getUserAccountIds(accountValue.accounts);
      });
  }
  ngOnInit() {
    this.newAccountForm.valueChanges.subscribe((formValues) => {
      this.account = { ...this.account, ...formValues };
    });
    this.newAccountForm.patchValue(this.account);
  }

  openAccountBlock() {
    console.log('account block opened');
    this.account.accountUserId = this.user.userId;
    this.account.password = 'password';
    this.account.currentPassword = 'password';
    this.account.accountAppName = 'Salesforce';
    this.newAccountForm.patchValue(this.account);
    this.isAccountBlockOpen = !this.isAccountBlockOpen;
    // open a new block with a form for creating a new account with this user
  }
  createAccount() {
    this.httpService.createAccount(this.newAccountForm.value).subscribe({});
  }

  deleteAccount(accountId: string) {
    this.httpService.deleteAccount(accountId).subscribe({
      next: (resp) => {
        console.log(resp);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  getUserAccountIds(accounts: any) {
    for (let account of accounts) {
      console.log('account ' + account.value);
      this.httpService.getAccountById(account.value).subscribe({
        next: (resp) => {
          let item = resp.body;
          console.log(item);
          let accountNestedInfo;
          if (item.application.displayName == 'Azure Entra ID') {
            const dynamicKey6 =
              'urn:ietf:params:scim:schemas:sailpoint:1.0:Application:Schema:Azure Entra ID:account' as keyof typeof item;
            accountNestedInfo = item[dynamicKey6];
          }
          if (item.application.displayName == 'Salesforce') {
            const dynamicKey5 =
              'urn:ietf:params:scim:schemas:sailpoint:1.0:Application:Schema:Salesforce:account' as keyof typeof item;

            accountNestedInfo = item[dynamicKey5];
          }
          console.log(accountNestedInfo);
          this.userAccounts.push(
            new Account(
              item.application.value,
              item.identity.value,
              item.nativeIdentity,
              item.identity.displayName,
              item.id,
              'password',
              'password',
              item.active,
              item.locked,
              item.manuallyCorrelated,
              item.hasEntitlements,
              item.application.displayName,
              accountNestedInfo.Username,
              accountNestedInfo.FirstName,
              accountNestedInfo.LastName,
              accountNestedInfo.CommunityNickname,
              accountNestedInfo.Alias,
              accountNestedInfo.Email
            )
          );
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
    console.log(this.userAccounts);
  }
}

import { Component } from '@angular/core';
import { User } from '../models/user';
import { HttpService } from '../services/http.service';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ErrorPipe } from '../pipes/error.pipe';
import { Button } from 'primeng/button';
import { timeout } from 'rxjs';
import { Checkbox } from 'primeng/checkbox';

@Component({
  selector: 'app-update-user-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule, ErrorPipe, Button],
  templateUrl: './update-user-form.component.html',
  styleUrl: './update-user-form.component.css',
})
export class UpdateUserFormComponent {
  updateUserForm: FormGroup;
  user: User = new User('', '', '', '', '', '', '', '', '', '', '','','',true,'');

  constructor(
    private httpService: HttpService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.getUserById();
    
    this.updateUserForm = this.formBuilder.group({
      firstName: ['',Validators.compose([Validators.required, Validators.minLength(4)]),],
      lastName: ['', Validators.compose([Validators.required, Validators.minLength(4)])],
      username: [{value:''}, Validators.required],
      displayName: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      managerId: ['', Validators.required],
      manager: ['', Validators.required],
      userId: [{value: ''}, Validators.required],
      password: ['', Validators.required],
      softwareVersion: ['', Validators.required],
      administrator: ['', Validators.required],
      administratorId: ['', Validators.required],
      active: [true, Validators.required]
    });
  }

  ngOnInit() {
    this.getUserById();
    this.updateUserForm.valueChanges.subscribe((formValues) => {
      this.user = { ...this.user, ...formValues };
    });
  }
  get firstName(){
    return this.updateUserForm.get('firstName');
  }
  get lastName(){
    return this.updateUserForm.get('lastName');
  }
  get username(){
    return this.updateUserForm.get('username');
  }
  get displayName(){
    return this.updateUserForm.get('displayName');
  }
  get email(){
    return this.updateUserForm.get('email');
  }
  get managerId(){
    return this.updateUserForm.get('managerId');
  }
  get manager(){
    return this.updateUserForm.get('manager');
  }
 
  get userId(){
    return this.updateUserForm.get('userId');
  }
  get password(){
    return this.updateUserForm.get('password');
  }
  get softwareVersion(){
    return this.updateUserForm.get('softwareVersion');
  }
  get aministrator(){
    return this.updateUserForm.get('administrator');
  }
  get aministratorId(){
    return this.updateUserForm.get('administratorId');
  }
  get active(){
    return this.updateUserForm.get('active');
  }
  get department(){
    return this.updateUserForm.get('department');
  }

  
  getUserById() {
    this.httpService.getUserById(this.route.snapshot.params['id']).subscribe(resp => {
      let item =resp.body;
      const dynamicKey = "urn:ietf:params:scim:schemas:sailpoint:1.0:User" as keyof typeof item;
      const adminValue = item[dynamicKey];
      const dynamicKey2 = "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User" as keyof typeof item;
      const managerValue = item[dynamicKey2];
      this.user =new User(item.id, item.userName, 'password', item.name.givenName, item.name.familyName, item.displayName, item.emails[0].value, managerValue.manager.displayId, managerValue.manager.value, item.meta.resourceType, item.meta.version, adminValue.administrator.displayName, adminValue.administrator.value, true, 'department');
      this.updateUserForm.patchValue(this.user);
    });
  }

  updateUser() {
    console.log(this.updateUserForm.value)
    
    this.httpService.updateUser(this.updateUserForm.value).subscribe(resp =>{
      this.router.navigate(['user/'+this.user.userId])
    }
    );
  }
}

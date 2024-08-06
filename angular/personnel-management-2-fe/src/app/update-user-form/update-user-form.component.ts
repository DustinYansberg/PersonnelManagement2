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
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ErrorPipe } from '../pipes/error.pipe';
import { Button } from 'primeng/button';

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
    private formBuilder: FormBuilder
  ) {
    this.getUserById();
    console.log(this.user);
    this.updateUserForm = this.formBuilder.group({
      firstName: ['',Validators.compose([Validators.required, Validators.minLength(4)]),],
      lastName: ['', Validators.compose([Validators.required, Validators.minLength(4)])],
      username: ['', Validators.required],
      displayName: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      managerId: ['', Validators.required],
      manager: ['', Validators.required],
      type: ['', Validators.required],
      userId: ['', Validators.required],
      password: ['', Validators.required],
      softwareVersion: ['', Validators.required],
      administrator: ['', Validators.required],
      administratorId: ['', Validators.required],
      active: [true, Validators.required],
      department: ['', Validators.required],
    });
    
  }

  ngOnInit() {
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
  get type(){
    return this.updateUserForm.get('type');
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
      this.user =new User(item.id, item.userName, 'password', item.name.givenName, item.name.familyName, item.displayName, item.emails[0].value, item.managerId, 'manager', item.meta.resourceType, 'software version', 'administrator', 'adminId', true, 'department');
    });
  }

  updateUser() {
    // console.log(this.user);
    this.httpService.updateUser(this.updateUserForm.value);
  }
}

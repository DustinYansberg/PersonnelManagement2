import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { HttpService } from '../services/http.service';
import { User } from '../models/user';
import { ErrorPipe } from '../pipes/error.pipe';
import { Button, ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-new-user-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule, ErrorPipe, Button],
  templateUrl: './new-user-form.component.html',
  styleUrl: './new-user-form.component.css',
})
export class NewUserFormComponent {
  newUserForm: FormGroup;
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

  constructor(
    private httpService: HttpService,
    private formBuilder: FormBuilder
  ) {
    this.newUserForm = this.formBuilder.group({
      firstName: [
        '',
        Validators.compose([Validators.required, Validators.minLength(4)]),
      ],
      lastName: [
        '',
        Validators.compose([Validators.required, Validators.minLength(4)]),
      ],
      username: ['', Validators.required],
      displayName: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      managerId: [''],
      type: [''],
      userId: [''],
      password: ['', Validators.required],
      softwareVersion: [''],
      administratorId: ['', Validators.required],
      active: [true, Validators.required],
    });
  }
  get firstName() {
    return this.newUserForm.get('firstName');
  }
  get lastName() {
    return this.newUserForm.get('lastName');
  }
  get username() {
    return this.newUserForm.get('username');
  }
  get displayName() {
    return this.newUserForm.get('displayName');
  }
  get email() {
    return this.newUserForm.get('email');
  }
  get managerId() {
    return this.newUserForm.get('managerId');
  }
  get type() {
    return this.newUserForm.get('type');
  }
  get userId() {
    return this.newUserForm.get('userId');
  }
  get password() {
    return this.newUserForm.get('password');
  }
  get softwareVersion() {
    return this.newUserForm.get('softwareVersion');
  }
  get aministratorId() {
    return this.newUserForm.get('administratorId');
  }
  get active() {
    return this.newUserForm.get('active');
  }

  ngOnInit() {
    this.newUserForm.valueChanges.subscribe((formValues) => {
      this.user = { ...this.user, ...formValues };
    });
  }

  createUser() {
    this.httpService.createUser(this.newUserForm.value).subscribe({
      next: (resp) => {
        console.log(resp.body);
      },
      error: (err) => {
        console.log(err);
        console.log(this.newUserForm.value);
      },
    });
  }
}

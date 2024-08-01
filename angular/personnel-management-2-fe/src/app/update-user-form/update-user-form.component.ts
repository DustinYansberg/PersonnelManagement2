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

@Component({
  selector: 'app-update-user-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './update-user-form.component.html',
  styleUrl: './update-user-form.component.css',
})
export class UpdateUserFormComponent {
  updateUserForm: FormGroup;
  user: User = new User(0, '', '', '', '', '', 0, '');

  constructor(
    private httpService: HttpService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
    this.updateUserForm = this.formBuilder.group({
      firstName: [
        '',
        Validators.compose([Validators.required, Validators.minLength(4)]),
      ],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      displayName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      managerId: [0, Validators.required],
      type: ['', Validators.required],
      userId: [0, Validators.required],
    });
    this.getUserById();
  }

  ngOnInit() {
    this.updateUserForm.valueChanges.subscribe((formValues) => {
      this.user = { ...this.user, ...formValues };
    });
  }
  get firstName() {
    return this.updateUserForm.get('firstName');
  }
  get lastName() {
    return this.updateUserForm.get('lastName');
  }
  get username() {
    return this.updateUserForm.get('username');
  }
  get displayName() {
    return this.updateUserForm.get('displayName');
  }
  get email() {
    return this.updateUserForm.get('email');
  }
  get managerId() {
    return this.updateUserForm.get('managerId');
  }
  get type() {
    return this.updateUserForm.get('type');
  }
  get userId() {
    return this.updateUserForm.get('userId');
  }

  getUserById() {
    this.user = this.httpService.getUserById(this.route.snapshot.params['id']);
  }

  updateUser() {
    // console.log(this.user);
    this.httpService.updateUser(this.user);
  }
}

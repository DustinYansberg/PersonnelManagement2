import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormControl,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { HttpService } from '../services/http.service';
import { User } from '../models/user';

@Component({
  selector: 'app-new-user-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './new-user-form.component.html',
  styleUrl: './new-user-form.component.css',
})
export class NewUserFormComponent {
  newUserForm: FormGroup;
  user: User = new User(0, '', '', '', '', '', 0, '');

  constructor(
    private httpService: HttpService,
    private formBuilder: FormBuilder
  ) {
    this.newUserForm = this.formBuilder.group({
      firstName: [
        '',
        Validators.compose([Validators.required, Validators.minLength(4)]),
      ],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      displayName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      managerId: ['', Validators.required],
      type: ['', Validators.required],
      userId: ['', Validators.required],
    });
  }

  // Subscribe to form changes
  ngOnInit() {
    this.newUserForm.valueChanges.subscribe((formValues) => {
      this.user = { ...this.user, ...formValues };
    });
  }

  createUser() {
    this.httpService.createUser(this.user);
  }
}

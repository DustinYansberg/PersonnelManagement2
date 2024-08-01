import { CommonModule } from '@angular/common';
import { Component} from '@angular/core';
import { FormBuilder, FormGroup,  FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpService } from '../services/http.service';
import { User } from '../models/user';
import { ErrorPipe } from '../pipes/error.pipe';


@Component({
  selector: 'app-new-user-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule, ErrorPipe],
  templateUrl: './new-user-form.component.html',
  styleUrl: './new-user-form.component.css'
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
      managerId: [0, Validators.required],
      type: ['', Validators.required],
      userId: [0, Validators.required],
    });
  }
  get firstName(){
    return this.newUserForm.get('firstName');
  }
  get lastName(){
    return this.newUserForm.get('lastName');
  }
  get username(){
    return this.newUserForm.get('username');
  }
  get displayName(){
    return this.newUserForm.get('displayName');
  }
  get email(){
    return this.newUserForm.get('email');
  }
  get managerId(){
    return this.newUserForm.get('managerId');
  }
  get type(){
    return this.newUserForm.get('type');
  }
  get userId(){
    return this.newUserForm.get('userId');
  }

  ngOnInit() {
    this.newUserForm.valueChanges.subscribe((formValues) => {
      this.user = { ...this.user, ...formValues };
    });
  }

  createUser(){
    this.httpService.createUser(this.newUserForm.value);
  }

}

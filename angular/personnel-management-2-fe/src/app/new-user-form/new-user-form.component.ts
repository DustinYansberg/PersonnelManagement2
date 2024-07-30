import { CommonModule } from '@angular/common';
import { Component} from '@angular/core';
import { FormBuilder, FormGroup, FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpService } from '../services/http.service';
import { User } from '../models/user';


@Component({
  selector: 'app-new-user-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './new-user-form.component.html',
  styleUrl: './new-user-form.component.css'
})
export class NewUserFormComponent {

  constructor(private httpService: HttpService){}

user: User = new User(0, "", "", "", "","",0,"")

  createUser(){
      this.httpService.createUser(this.user);
    }

// newUserForm: FormGroup = this.formBuilder.group({
//   // firstName: ['', Validators.compose([Validators.required, Validators.minLength(4)])]
// }
// );

}

import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UsersComponent } from './users/users.component';
import { NewUserFormComponent } from './new-user-form/new-user-form.component';
import { UpdateUserFormComponent } from './update-user-form/update-user-form.component';
import { UserPageComponent } from './user-page/user-page.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'users',
        component: UsersComponent
    },
    {
        path: 'create',
        component: NewUserFormComponent
    },
    {
        path:'update/:id',
        component: UpdateUserFormComponent
    },
    {
        path: 'user/:id',
        component: UserPageComponent
    }
];

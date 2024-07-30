import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  url: String ='http://localhost:8080/';

  //get all
  getAllUsers(){
    return [new User(0, "username", "firstname 1", "lastname 1", "displayname","email",0,"type"),
      new User(0, "username", "firstname 2", "lastname 2", "displayname","email",0,"type")
    ]
  }

  //get by id
  getUserById(id: number){
    console.log(id);
    return new User(0, "username", "firstname 2", "lastname 2", "displayname","email",0,"type")
  }

  //create user
  createUser(user: User){
    console.log("create user request", user)
  }

  updateUser(user: User){
    console.log("update user request", user)
  }

  deleteUser(id: number){
    console.log("delete user")
  }
}

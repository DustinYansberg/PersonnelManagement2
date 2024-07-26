import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  //get all
  getAllUsers(){
    return [new User(0, "username", "firstname 1", "lastname 1", "displayname","email",0,"type"),
      new User(0, "username", "firstname 2", "lastname 2", "displayname","email",0,"type")
    ]
  }

  //get by id
  getUserById(){
    return new User(0, "username", "firstname 2", "lastname 2", "displayname","email",0,"type")
  }
}

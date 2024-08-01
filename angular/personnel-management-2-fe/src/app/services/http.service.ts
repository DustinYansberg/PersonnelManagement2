import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  url: String ='http://localhost:8081/';

  //get all
  getAllUsers(): Observable<HttpResponse<any>>{
    return this.http.get(this.url+'identity', {observe: 'response'});
  }

  //get by id
  getUserById(id: number): Observable<HttpResponse<any>>{
    return this.http.get(this.url+'identity/'+id, {observe: 'response'});
  }

  //create user
  createUser(user: User){
    console.log("create user request", user)
  }

  updateUser(user: User){
    console.log("update user request", user)
  }

  deleteUser(id: number){
    console.log("delete user " + id)
  }
}

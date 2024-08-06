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

  //get all users
  getAllUsers(): Observable<HttpResponse<any>>{
    return this.http.get(this.url+'identity', {observe: 'response'});
  }

  //get user by id
  getUserById(id: string): Observable<HttpResponse<any>>{
    return this.http.get(this.url+'identity/'+id, {observe: 'response'});
  }

  //create user
  createUser(user: User){
    console.log("create user request", user)
  }

  //Edit user
  updateUser(user: User){
    console.log("update user request", user)
  }

  //delete user by ID
  deleteUser(id: string): Observable<HttpResponse<any>>{
    console.log("delete user " + id)
    return this.http.delete(this.url +'identity/' + id, {observe: 'response'});
  }
}

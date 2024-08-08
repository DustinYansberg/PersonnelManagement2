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

  //get account
  getAccountById(id: string){
      return this.http.get(this.url +'account/'+id, {observe: 'response'})
  }
  //create user
  createUser(user: User){
    console.log("create user request", user);
    return this.http.post(this.url+'identity', {
      "userName": user.username,
      "password": user.password,
      "firstName": user.firstName,
      "lastName": user.lastName,
      "email": user.email,
      "administratorId": user.administratorId,
      "displayName": user.displayName,
      "active": user.active,
      "userType": user.type,
      "department": user.department
  }, {observe: 'response'});
  }

  //Edit user
  updateUser(user: User){
    console.log("update user request", user)
    return this.http.post(this.url+'identity/'+ user.userId, {
      "userId": user.userId,
      "userName": user.username,
      "password": user.password,
      "firstName": user.firstName,
      "lastName": user.lastName,
      "email": user.email,
      "administratorId": user.administratorId,
      "displayName": user.displayName,
      "active": user.active,
      "userType": user.type,
      "department": user.department,
      "managerId": user.managerId,
  }, {observe: 'response'});
  }

  //delete user by ID
  deleteUser(id: string): Observable<HttpResponse<any>>{
    console.log("delete user " + id)
    return this.http.delete(this.url +'identity/' + id, {observe: 'response'});
  }
}

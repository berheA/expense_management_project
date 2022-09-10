import { Injectable } from '@angular/core';
import { catchError, Observable, map, tap, of } from 'rxjs';
import { User } from '../models/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EmailValidator } from '@angular/forms';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private url = 'http://18.208.181.129:7000/login';
  users:User[]=[
    new User(1, "bAmare", "Berhe", "Amare", "BAmare@email.com", "manager", "password"),
    new User(2, "mdegaga", "Merry", "Degaga", "MDegaga@email.com", "manager", "password"),
    new User(3, "jDoe", "John", "Doe", "jdoe@email", "employee", "password"),
    new User(4, "jDoe2", "Jane", "Doe", "jdoe2@email", "employee", "password"),
    new User(5, "mCarter", "Moe", "Carter", "jdoe@email", "employee", "password"),
    new User(6, "lMoore", "Lisa", "Moore", "lMoore@email", "employee", "password"),
  ];
  currentUser:any;

  constructor(private http:HttpClient) {
   }

  // login(username:string, password:string):Observable<User>{
  //  login(username:string, password:string):User{ 
    login(username:string, password:string):any{ 
    let body = {
      "username":username,
      "password":password
    }; 
    this.currentUser = this.users.find(u => (username.toLowerCase() === u.username.toLowerCase()) && password === u.password);
    return this.currentUser;
    // return this.http.post<User>(this.url, body).pipe(
    //   catchError(this.handleError<User>('login', undefined))
    // );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      return of(result as T);
    }
  }
    resetUser():void{
      this.currentUser=null;
    }
}

import { Injectable } from '@angular/core';
import { Reimb } from '../models/reimb';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  users:User[]=[
    new User(3, "jDoe", "John", "Doe", "jdoe@email", "employee", "password"),
    new User(4, "jDoe2", "Jane", "Doe", "jdoe2@email", "employee", "password"),
    new User(5, "mCarter", "Moe", "Carter", "jdoe@email", "employee", "password"),
    new User(6, "lMoore", "Lisa", "Moore", "lMoore@email", "employee", "password"),
  ];

  reimbs:Reimb[]=[
    new Reimb(1, 42.00 ,new Date().toDateString(),"", "","",3,0,"pending","food"),
    new Reimb(2, 60.00 ,new Date().toDateString(),"", "","",3,0,"pending","other"),
    new Reimb(3, 100.00 ,'Thu Feb 01 2022',"Thu Feb 03 2022", "","",3,1,"approved","food"),
    new Reimb(4, 56.00 ,new Date().toDateString(),"", "","",4,0,"pending","food"),
    new Reimb(5, 200.00 ,"Mon Jan 24 2022",new Date().toDateString(), "","",5,0,"denied","lodging"),
    new Reimb(6, 350.00 ,"Sat Dec 18 2021",new Date().toDateString(), "","",5,0,"denied","other"),


  ];
  


  constructor() { }

  getUsers():User[]{
    return this.users;
  }

  getReimbs():Reimb[]{
    return this.reimbs;
  }

  
  getReimbsEmp(userID:number):Reimb[]{
    return this.reimbs.filter(r => r.author == userID);
  }

  editRequest(reimbID:any, status:string, resolver:number):void{
    reimbID = Number.parseInt(reimbID);
    this.reimbs.forEach(element => {
      console.log("current element:");
      console.log(element);
      if(element.id === reimbID) {
        element.statusID = status;
        element.resolver = resolver;
        element.timeResolved =  new Date().toDateString();
        console.log("changed element:");
        console.log(element);
      }
    });
  }




}

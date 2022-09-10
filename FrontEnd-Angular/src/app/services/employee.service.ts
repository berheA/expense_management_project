import { Injectable } from '@angular/core';
import { Reimb } from '../models/reimb';
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {


  reimbs:Reimb[]=[
    new Reimb(1, 42.00 ,new Date().toDateString(),"", "","",3,0,"pending","food"),
    new Reimb(2, 60.00 ,new Date().toDateString(),"", "","",3,0,"pending","other"),
    new Reimb(3, 100.00 ,'Thu Feb 01 2022',"Thu Feb 03 2022", "","",3,1,"approved","food"),
    new Reimb(4, 56.00 ,new Date().toDateString(),"", "","",4,0,"pending","food"),
    new Reimb(5, 200.00 ,"Mon Jan 24 2022",new Date().toDateString(), "","",5,0,"denied","lodging"),
    new Reimb(6, 350.00 ,"Sat Dec 18 2021",new Date().toDateString(), "","",5,0,"denied","other"),


  ];
  constructor() { }

  getReimbs(userID:number):Reimb[]{
    return this.reimbs.filter(r => r.author == userID);
  }

  addReimbs(r:Reimb):void{
    this.reimbs.push(r);
  }


}

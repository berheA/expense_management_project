import { Component, OnInit, ViewEncapsulation} from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/app/services/employee.service';
import { Reimb } from 'src/app/models/reimb';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
})
export class EmployeeComponent implements OnInit {

  name:string=this.loginservice.currentUser.firstname;
  userID:number=this.loginservice.currentUser.userID;
  reimbs:Reimb[] = [];
  tempReimbs:Reimb[]=[];
  pending:number = 0; 
  approved:number = 0;
  denied:number = 0; 

  constructor(private router:Router, private service:EmployeeService, private loginservice:LoginService) { }

  ngOnInit(): void {
   this.reimbs = this.service.getReimbs(this.userID);
   this.pending = this.reimbs.filter(r => r.statusID.toLowerCase() == "pending").length;
   this.approved = this.reimbs.filter(r => r.statusID.toLowerCase() == "approved").length;
   this.denied = this.reimbs.filter(r => r.statusID.toLowerCase() == "denied").length;
  }

  background(status:string):any{
    if(status.toLowerCase()=='pending'){
      return {backgroundColor:'lightblue'}
    }else if(status.toLowerCase() == 'approved'){
      return {backgroundColor:'#90ee90'}
    }else{
      return {backgroundColor:'#f08080'}
    }
  }

  viewPending():void{
    this.reimbs = this.service.getReimbs(this.userID).filter(r => r.statusID.toLowerCase() == "pending");
  }

  viewApproved():void{
    this.reimbs = this.service.getReimbs(this.userID).filter(r => r.statusID.toLowerCase() == "approved");
  }

  viewDenied():void{
    this.reimbs =this.service.getReimbs(this.userID).filter(r => r.statusID.toLowerCase() == "denied");
  }

  signout():void{
    this.router.navigateByUrl("login");
  }

}

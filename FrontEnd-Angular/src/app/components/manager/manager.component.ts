import { Component, OnInit, ViewEncapsulation} from '@angular/core';
import { Reimb } from 'src/app/models/reimb';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { ManagerService } from 'src/app/services/manager.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css'],
})
export class ManagerComponent implements OnInit {

  users:User[]=[];
  reimbs:Reimb[]=[];
  name:string=this.loginservice.currentUser.firstname;
  pending:number = 0; 
  approved:number = 0;
  denied:number = 0; 
  tempEmpID:number=0;
  query:string="";

  constructor(private service:ManagerService, private loginservice:LoginService) { }


  ngOnInit(): void {
    this.users = this.service.getUsers();
    this.reimbs = this.service.getReimbs();
    this.tempEmpID = 0;
    this.updatefields();
  }

  updatefields(){
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
    if(this.tempEmpID) {
      this.query="pending";
      this.reimbs = this.service.getReimbsEmp(this.tempEmpID).filter(r => r.statusID.toLowerCase() == "pending");
    } else {
    this.reimbs = this.service.getReimbs().filter(r => r.statusID.toLowerCase() == "pending");
  }
}

  viewApproved():void{
    if(this.tempEmpID) {
      this.query="approved";
      this.reimbs = this.service.getReimbsEmp(this.tempEmpID).filter(r => r.statusID.toLowerCase() == "approved");
    } else {
    this.reimbs = this.service.getReimbs().filter(r => r.statusID.toLowerCase() == "approved");
  }
}

  viewDenied():void{
    if(this.tempEmpID) {
      this.query="denied";
      this.reimbs = this.service.getReimbsEmp(this.tempEmpID).filter(r => r.statusID.toLowerCase() == "denied");
    } else {
    this.reimbs =this.service.getReimbs().filter(r => r.statusID.toLowerCase() == "denied");
  }
}
  
  validateFilter():void{
    const empID = (<HTMLInputElement>document.getElementById("empID"));
    
    // console.log(empID.value);
    // console.log(Number.parseInt(empID.value))
  
    if(empID.validity.patternMismatch || empID.validity.valueMissing){
      empID.classList.add("is-invalid");
    }
    else {
      empID.classList.remove("is-invalid");
      //submit the request
      this.tempEmpID = Number.parseInt(empID.value);
      this.reimbs = this.service.getReimbsEmp(this.tempEmpID);
      this.updatefields();
      
      (<HTMLFormElement>document.getElementById("filterForm")).reset();
    }
  }

  approve(event:MouseEvent):void{
    const approve = <HTMLButtonElement>event.target;
    const reimbID = approve.parentElement?.children[0].childNodes[1].childNodes[1].textContent?.toString();
    this.service.editRequest(reimbID,"approved", this.loginservice.currentUser.userID);
    
    if(this.tempEmpID){
      if(this.query ==="pending") {
          this.viewPending();
      }
      else if(this.query === "denied") {
        this.viewDenied();
      }
      else if(this.query === "approved"){
        this.viewApproved();
      }
      else {
        this.reimbs = this.service.getReimbsEmp(this.tempEmpID);
      }
      this.pending=this.service.getReimbsEmp(this.tempEmpID).filter(r => r.statusID.toLowerCase() == 'pending').length;
      this.approved=this.service.getReimbsEmp(this.tempEmpID).filter(r => r.statusID.toLowerCase() == 'approved').length;
      this.denied=this.service.getReimbsEmp(this.tempEmpID).filter(r => r.statusID.toLowerCase() == 'denied').length;
      

    }
    else {
      this.ngOnInit();
    }
  }

  deny(event:MouseEvent):void{
    const deny = <HTMLButtonElement>event.target;
    const reimbID = deny.parentElement?.children[0].childNodes[1].childNodes[1].textContent?.toString();
    this.service.editRequest(reimbID,"denied", this.loginservice.currentUser.userID);
    if(this.tempEmpID){
      if(this.query ==="pending") {
          this.viewPending();
      }
      else if(this.query === "denied") {
        this.viewDenied();
      }
      else if(this.query === "approved"){
        this.viewApproved();
      }
      else {
        this.reimbs = this.service.getReimbsEmp(this.tempEmpID);
      }
      this.pending=this.service.getReimbsEmp(this.tempEmpID).filter(r => r.statusID.toLowerCase() == 'pending').length;
      this.approved=this.service.getReimbsEmp(this.tempEmpID).filter(r => r.statusID.toLowerCase() == 'approved').length;
      this.denied=this.service.getReimbsEmp(this.tempEmpID).filter(r => r.statusID.toLowerCase() == 'denied').length;

    }
    else {
      this.ngOnInit();
    }
  }


}

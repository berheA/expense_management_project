import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/app/services/employee.service';
import { Reimb } from 'src/app/models/reimb';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-add-request',
  templateUrl: './add-request.component.html',
  styleUrls: ['./add-request.component.css']
})
export class AddRequestComponent implements OnInit {

  size:number=0;
  userID:number=this.login.currentUser.userID;

  constructor(private router:Router, private service:EmployeeService, private login:LoginService) { }

  ngOnInit(): void {
    this.size = this.service.getReimbs(this.userID).length; 
  }

  trigger(event:MouseEvent):void {

    const amount = <HTMLInputElement>document.getElementById("amount");
    const type = <HTMLSelectElement>document.getElementById("reimType");
    const receipt = <HTMLInputElement>document.getElementById("receipt");

    
    if((!amount.validity.patternMismatch && !amount.validity.valueMissing) && !type.validity.valueMissing) {
     amount.classList.remove("is-invalid");
     type.classList.remove("is-invalid");

     if(receipt.value){
       if(this.fileValidation(receipt)) {
        this.service.addReimbs(new Reimb(this.size+1, Number.parseFloat(amount.value),new Date().toDateString(),"", "","",this.userID,0,"pending",type.value));
        (<HTMLFormElement>document.getElementById("addRequest")).reset();
        receipt.classList.remove("is-invalid");
        this.router.navigateByUrl("/employee");
       }
         receipt.classList.add("is-invalid");
     }
     else {
         //submit w/o image
         this.service.addReimbs(new Reimb(this.size+1, Number.parseFloat(amount.value),new Date().toDateString(),"", "","",this.userID,0,"pending",type.value));
         (<HTMLFormElement>document.getElementById("addRequest")).reset();
        receipt.classList.remove("is-invalid");
        this.router.navigateByUrl("/employee");
     }
     
    } else {
    if(amount.validity.patternMismatch || amount.validity.valueMissing){
        amount.classList.add("is-invalid");
    }else {
        amount.classList.remove("is-invalid");
    }
 
    if(type.validity.valueMissing) {
        type.classList.add("is-invalid");
    } else {
         type.classList.remove("is-invalid");
    }
 }


  }


   fileValidation(receipt:any):boolean {
    const filePath = receipt.value;            
    // Allowing file type
    var allowedExtensions =
            /(\.pdf|\.jpg|\.jpeg|\.png|\.gif)$/i;
    if (!allowedExtensions.exec(filePath)) {
        return false;
    }
        return true;
                   
}
}

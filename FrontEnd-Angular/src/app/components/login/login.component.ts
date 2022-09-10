import { Component, OnInit, ViewEncapsulation} from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  constructor(private loginService:LoginService, private router:Router) { 
    
  }

  ngOnInit(): void {
  }


  trigger(event:MouseEvent):void{
    const username = <HTMLInputElement>document.getElementById("username");
    const password = <HTMLInputElement>document.getElementById("password");

  
    if(username.checkValidity() && password.checkValidity()) {
      let user = this.loginFunc(username.value, password.value);

      username.classList.remove("is-invalid");
      password.classList.remove("is-invalid");
      if(user){
        this.router.navigateByUrl("/"+user.role);
      }
      (<HTMLFormElement>document.getElementById("loginForm")).reset();

    } 
    else {
  
    if(!username.checkValidity()) {
      username.classList.add("is-invalid");
    }
    else{
      username.classList.remove("is-invalid");
    }
  
    if(!password.checkValidity()){
      password.classList.add("is-invalid");
    }else {
      password.classList.remove("is-invalid");
    }
  }
    
}

  loginFunc(username:string, password:string):any{
    let welcome = <HTMLHeadingElement>document.getElementById("welcome");

      let user;
      // this.loginService.login(username, password).subscribe(out => user = out);
      user = this.loginService.login(username, password);
      if(user){
        welcome.classList.remove("is-invalid")
      }else {
        welcome.classList.add("is-invalid");
      }
      return user;
    }

}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AmplifyUIAngularModule } from '@aws-amplify/ui-angular';


import { BackButtonDisableModule } from 'angular-disable-browser-back-button'; // to disable browser back button


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { ManagerComponent } from './components/manager/manager.component';
import { AddRequestComponent } from './components/add-request/add-request.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EmployeeComponent,
    ManagerComponent,
    AddRequestComponent,
    PageNotFoundComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AmplifyUIAngularModule,
     BackButtonDisableModule.forRoot( {
        preserveScrollPosition: true
     })
 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

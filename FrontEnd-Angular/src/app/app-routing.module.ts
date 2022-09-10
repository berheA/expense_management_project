import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddRequestComponent } from './components/add-request/add-request.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { LoginComponent } from './components/login/login.component';
import { ManagerComponent } from './components/manager/manager.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

const routes: Routes = [

  {path:"", component:LoginComponent},
  {path:"employee", component:EmployeeComponent},
  {path:"manager", component:ManagerComponent},
  {path:"addRequest", component:AddRequestComponent},
  {path:"login", component:LoginComponent},
  {path:"makeRequest", component:AddRequestComponent},
  
  {path: '**', component: PageNotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

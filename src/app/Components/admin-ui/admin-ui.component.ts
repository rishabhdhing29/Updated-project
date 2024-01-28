import { Component,OnInit} from '@angular/core';
import { Admin } from '../../Model/admin.model';
import { AdminService } from '../../Service/admin.service';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';
import { Router } from '@angular/router';

import { OperatorService } from 'src/app/Service/operator-jwt/operator.service';

@Component({
  selector: 'app-admin-ui',
  templateUrl: './admin-ui.component.html',
  styleUrls: ['./admin-ui.component.css']
})
export class AdminUIComponent {
  token: any; // Define the token property
  admin!: Admin ;
 


  constructor(private router:Router,private adminService: AdminJwtClientService,private tokenservice:TokenServiceService,private busService:OperatorService ) {}
  ngOnInit(): void {
    let id=Number(sessionStorage.getItem("adminId"));
    console.log(id);
    this.adminService.getAdminById(id).subscribe((Response)=>{this.admin=Response; 
      console.log(this.admin.firstName);})
  }
  
  getalladmins() {
    this.token=this.tokenservice.getToken();
    this.adminService.alladmins(this.token).subscribe(
      (response) => {
         // Display the response in the console
      
        this.router.navigate(['/display-all-admin']);

      }
     
    );
  }
  onUpdateUser() {
  
    this.router.navigate(['/update-admin']);
  }
  
 
  
  
       

  createBus(){
  
        this.router.navigate(['/add-bus']);
    
  }
  
  logout() {
    // Clear the token when logging out
    this.tokenservice.clearToken();
    this.router.navigate(['/admin']);
  }
  getallUsers() {
    this.token=this.tokenservice.getToken();
    this.adminService.allUsers(this.token).subscribe(
      (response) => {
       // Display the response in the console
      
        this.router.navigate(['/getAllUsers']);
  
      }
     
    );
  }
  getallBuses() {
    this.token=this.tokenservice.getToken();
    this.adminService.allBus(this.token).subscribe(
      (response) => {
         // Display the response in the console
      
        this.router.navigate(['/all-buses']);
  
      }
     
    );
  }
  getallOperator() {
    this.token=this.tokenservice.getToken();
    this.adminService.allBusOperators(this.token).subscribe(
      (response) => {
        // Display the response in the console
      
        this.router.navigate(['/getAllOperator']);
  
      }
     
    );
  }

}

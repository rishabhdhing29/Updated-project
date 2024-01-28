import { Component, OnInit } from '@angular/core';
import { AuthRequest } from '../../Model/AuthRequest';
import { AdminJwtClientService } from '../../Service/admin-jwt-client.service';
import { Router } from '@angular/router';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';
import { AdminService } from 'src/app/Service/admin.service';

@Component({
  selector: 'app-admin-management',
  templateUrl: './admin-management.component.html',
  styleUrls: ['./admin-management.component.css']
})
export class AdminManagementComponent implements OnInit {
  response: any;
  token: any;
  authRequest: AuthRequest = new AuthRequest();

 
  constructor(private service:AdminService,private jwtService: AdminJwtClientService, private router: Router,private tokenService:TokenServiceService) {}

  ngOnInit(): void {
    // Perform initialization logic if needed
  }

  readFormData(formData: any) {
    if (formData.valid) {
      this.authRequest.username = formData.value.username;
      this.authRequest.password = formData.value.password;

      this.getAccessToken(this.authRequest);
    } else {
      
      alert('Please fill in the required fields.');
    }
  }

  public getAccessToken(authRequest: any) {
    let response = this.jwtService.getGeneratedToken(authRequest);
  
    response.subscribe((genToken) => {
      this.token = genToken;
      this.tokenService.setToken(this.token);
  
      // Fetch the admin ID and store it in sessionStorage
      this.service.getAdminIdByFirstName(authRequest.username).subscribe(
        (admin: any) => {
          if (admin && admin.adminId) {
            sessionStorage.setItem('adminId', admin.adminId.toString());
            const storedAdminId = sessionStorage.getItem('adminId');
            console.log('Stored Admin ID:', storedAdminId);
            this.router.navigate(['/adminUI']);
          } else {
            console.error('Admin ID not found in the response.');
            // Handle the case where the admin ID is not available
          }
        },
        (error) => {
          console.error('Error fetching admin ID:', error);
          // Handle errors appropriately
        }
      );
  
    
    });
  }
  
  
}

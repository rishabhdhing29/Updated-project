import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthRequest } from 'src/app/Model/AuthRequest';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';
import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';
import { UserService } from 'src/app/Service/user.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  

  response: any;
  token: any;
  authRequest: AuthRequest = new AuthRequest();

  constructor(private jwtService:UserJwtClientService, private service:UserService,private router:Router,private tokenService:TokenServiceService){}

  ngOnInit(): void {
   
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
  
      // Fetch the user ID and store it in sessionStorage
      this.service.getUserIdByFirstName(authRequest.username).subscribe(
        (user: any) => {
          if (user && user.userId) {
            sessionStorage.setItem('id', user.userId.toString());
            const storedId = sessionStorage.getItem('id');
console.log('Stored User ID:', storedId);
            this.router.navigate(['/userUI']);
          } else {
            console.error('User ID not found in the response.');
            // Handle the case where the user ID is not available
          }
        },
        (error) => {
          console.error('Error fetching user ID:', error);
          // Handle errors appropriately
        }
      );
  
      this.jwtService.loggedInUsername = authRequest.username;
    });
  }
  

  
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthRequest } from '../../Model/AuthRequest';
import { OperatorJwtClientService } from '../../Service/operator-jwt/operator-jwt-client.service';
import { TokenServiceService } from '../../Service/token/token-service.service';
import { OperatorService } from 'src/app/Service/operator-jwt/operator.service';

@Component({
  selector: 'app-bus-operator-login',
  templateUrl: './bus-operator-login.component.html',
  styleUrls: ['./bus-operator-login.component.css']
})
export class BusOperatorLoginComponent implements OnInit {
  response: any;
  token: any;
  authRequest: AuthRequest = new AuthRequest();

  constructor(private service:OperatorService,private jwtService: OperatorJwtClientService, private router: Router, private tokenService: TokenServiceService) {}

  ngOnInit(): void {}

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

      // Fetch the operator ID and store it in sessionStorage
      // Assuming there's a method `getOperatorIdByUsername` in your OperatorService
      this.service.getOperatorIdByFirstName(authRequest.username).subscribe(
        (operator: any) => {
          if (operator && operator.operatorId) {
            sessionStorage.setItem('operatorId', operator.operatorId.toString());
            const storedOperatorId = sessionStorage.getItem('operatorId');
            console.log('Stored Operator ID:', storedOperatorId);
            this.router.navigate(['/operator-ui']);
          } else {
            console.error('Operator ID not found in the response.');
            // Handle the case where the operator ID is not available
          }
        },
        (error) => {
          console.error('Error fetching operator ID:', error);
          // Handle errors appropriately
        }
      );
    });
  }
}

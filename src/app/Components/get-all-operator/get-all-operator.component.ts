import { Component } from '@angular/core';
import { Operator } from 'src/app/Model/busoperator.model';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-get-all-operator',
  templateUrl: './get-all-operator.component.html',
  styleUrls: ['./get-all-operator.component.css']
})
export class GetAllOperatorComponent {

  operatorList: Operator[] = [];

  constructor(private adminService:AdminJwtClientService,private tokenService: TokenServiceService) { }

  ngOnInit() {
    this.fetchOperators();
  }
  fetchOperators() {
    const token = this.tokenService.getToken();
    this.adminService.allBusOperators(token).subscribe(
      (operators: Operator[]) => {
        this.operatorList= operators;
      }
      
    );
  }
  deleteOperator(operatorId: number) {
    const token = this.tokenService.getToken(); 
    this.adminService.deleteOperator(operatorId, token).subscribe(
      (response) => {
        console.log('Operator deleted:', response);
     
      },
      (error) => {
        console.error('Error deleting operator:', error);
      
      }
    );
  }

}

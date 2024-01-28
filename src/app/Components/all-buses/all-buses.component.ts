import { Component } from '@angular/core';
import { Bus } from 'src/app/Model/bus.model';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-all-buses',
  templateUrl: './all-buses.component.html',
  styleUrls: ['./all-buses.component.css']
})
export class AllBusesComponent {
  busList: Bus[] = [];

  constructor(private operatorService:AdminJwtClientService,private tokenService: TokenServiceService , private adminService:AdminJwtClientService ) { }

  ngOnInit() {
    this.fetchBuses();
  }
  fetchBuses() {
    const token = this.tokenService.getToken();
    this.operatorService.allBus(token).subscribe(
      (buses: Bus[]) => {
        this.busList = buses;
      }
      
    );
  }
  deleteBus(busId: number) {
    const token = this.tokenService.getToken(); 
    this.operatorService.deleteBus(busId, token).subscribe(
      (response) => {
        console.log('Bus deleted:', response);
        
      },
      (error) => {
        console.error('Error deleting Bus:', error);
       
      }
    );
}
}
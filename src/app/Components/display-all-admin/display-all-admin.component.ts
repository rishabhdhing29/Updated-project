import { Component } from '@angular/core';
import { Admin } from 'src/app/Model/admin.model';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-display-all-admin',
  templateUrl: './display-all-admin.component.html',
  styleUrls: ['./display-all-admin.component.css']
})
export class DisplayAllAdminComponent {

  adminList: Admin[] = [];

  constructor(private adminService:AdminJwtClientService,private tokenService: TokenServiceService) { }

  ngOnInit() {
    this.fetchAdmins();
  }
  fetchAdmins() {
    const token = this.tokenService.getToken();
    this.adminService.alladmins(token).subscribe(
      (admins: Admin[]) => {
        this.adminList = admins;
      }
      
    );
  }
  deleteAdmin(adminId: number) {
    const token = this.tokenService.getToken(); 
    this.adminService.delete(adminId, token).subscribe(
      (response) => {
        console.log('Admin deleted:', response);
 
      },
      (error) => {
        console.error('Error deleting admin:', error);
        
      }
    );
  }
  
}

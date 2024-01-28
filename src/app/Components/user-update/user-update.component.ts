import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Model/user.model';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';
import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';
import { UserService } from 'src/app/Service/user.service';
//import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent {
 
  user: User = new User();
  userId!:number ;

  constructor(private userService: UserJwtClientService,private tokenService:TokenServiceService) { }

  ngOnInit(): void {
    this.userId=Number(sessionStorage.getItem("id"));
    this.userService. getUserById(this.userId).subscribe((data)=>{
    this.user=data;
  });
  }
 
  updateUserDetails() {
    const token = this.tokenService.getToken()
    console.log(token);
    this.userService.updateUser(this.user,token).subscribe(() => {
      console.log('User updated');
      
    }, (error) => {
     
      console.error(error);
    });

  }
}

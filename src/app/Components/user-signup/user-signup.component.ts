import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Model/user.model';
import { UserService } from 'src/app/Service/user.service';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent {

  user:User=new User();
  constructor(private userService:UserService,private router: Router){}

  createUser(data:User){
    this.userService.createUser(data).subscribe(
      (response) => {
        console.log('User created successfully!', response);
        this.router.navigateByUrl('/user-login');

       
      })
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Booking } from 'src/app/Model/booking.model';
import { Bus } from 'src/app/Model/bus.model';
import { BookingService } from 'src/app/Service/booking/booking.service';
import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';
import { User } from 'src/app/Model/user.model';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css']
})
export class ViewBookingComponent implements OnInit {
  booking: Booking = new Booking();
  bus: Bus = new Bus(0, '', 0, '', '', 0, 0, '', 0, new Date(), 0);
  user: User=new User();

  amount!: number; // Declare amount variable
  bookingId!: number;
  busId!: number;

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private userJwtClientService: UserJwtClientService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((data) => {
      this.bookingId = data['bookingId'];
      this.busId = data['busId'];
      this.amount = data['amount'];

      this.bookingService.getBookingById(this.bookingId).subscribe((response1) => {
        this.booking = response1;
        console.log(this.booking);

        this.bookingService.getBusById(this.busId).subscribe((response) => {
          this.bus = response;
          console.log(this.bus);

          let storedId = sessionStorage.getItem('id');
          if (storedId) {
            // Fetch user details by ID
            this.userJwtClientService.getUserById(+storedId).subscribe(
              (userData) => {
                this.user = userData;
                console.log('User Details:', this.user);
                // Use this.user in your template to display user information
              },
              (error) => {
                console.error('Error fetching user details:', error);
                // Handle errors accordingly
              }
            );
          } else {
            console.error('User ID not found in sessionStorage');
            // Handle case where user ID is not available in sessionStorage
          }
        });
      });
    });
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { Booking } from 'src/app/Model/booking.model';
import { Customer } from 'src/app/Model/customer.model';
import { User } from 'src/app/Model/user.model';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  apiUrl: string = 'http://localhost:8181/api/v1/bookings/';
  baseUrl: string = 'http://localhost:8181/api/v1/buses/';
  cusUrl: string = 'http://localhost:8181/api/v1/usercustomers/';

  constructor(private http: HttpClient) { }

  createBooking( book:Booking , busId: number,userId: number): Observable<Booking> {
    return this.http.post<Booking>(`${this.apiUrl}add/${busId}/${userId}`, book);
  }
  getBusById(busId: number): Observable<any> {
    const url = `${this.baseUrl}getBusById/${busId}`;
    return this.http.get<any>(url)
     
  }
  getBookingById(bookingId: number): Observable<Booking> {
    const url = `${this.apiUrl}getById/${bookingId}`;
    return this.http.get<Booking>(url);
  }
  getCustomersByBooking(bookingId: number): Observable<User[]> {
    const url = `${this.cusUrl}booking/${bookingId}`;
    return this.http.get<User[]>(url);
  }
  occupiedSeats(date:Date,busId:number):Observable<string []>{
    
    return this.http.get<string []>(this.apiUrl+`fetchbookedseats/${date}/${busId}`);
}

 
}

import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
 
  constructor(private router: Router) {}

  onSubmit(sourceCity: string, destinationCity: string, date: string) {
    const formattedDate = this.formatDate(date);

    this.router.navigate(['/searchbus'], { queryParams: { sourceCity, destinationCity, date: formattedDate } });
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); 
    const day = date.getDate().toString().padStart(2, '0'); 

    return `${year}-${month}-${day}`;
  }
}

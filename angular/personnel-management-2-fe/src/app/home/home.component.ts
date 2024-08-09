import { Component } from '@angular/core';
import { HttpService } from '../services/http.service';
import { RouterLink } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { CommonModule } from '@angular/common';
import { BadgeModule } from 'primeng/badge';
import { MenubarModule } from 'primeng/menubar';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [TagModule, RouterLink, MenubarModule, BadgeModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
items: MenuItem[] = [];
  numOfUsers:number = 0;
  constructor(private service:HttpService) { 
  }
  ngOnInit(){
    this.getAccounts();
    this.items = [{
      label: 'Home',
      // icon:'pi pi-fw pi-home',
      route: ['/']
    }, {
      label: 'Users',
      // icon:'pi pi-fw pi-home',
      route: ['/users']
    }, 
    {
      label: 'Create',
      // icon:'pi pi-fw pi-home',
      route: ['/create']
    }]
      }

  getAccounts() {
    this.service.getAllUsers().subscribe(resp => {
      this.numOfUsers = resp.body.totalResults;
      
    });
  }


}

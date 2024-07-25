import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AvatarModule } from 'primeng/avatar';
import { BadgeModule } from 'primeng/badge';
import { MenuModule } from 'primeng/menu';
import { MenubarModule } from 'primeng/menubar';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [TagModule, RouterLink, MenubarModule, BadgeModule, CommonModule],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})

export class NavComponent {
  items: MenuItem[] = [];
  ngOnInit(){
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
},{
  label: 'Update',
  route:['/update']
}]
  }
}

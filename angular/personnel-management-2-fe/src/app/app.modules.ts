import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { InputText } from 'primeng/inputtext';
import { PrimeIcons } from 'primeng/api';
import { RadioButton } from 'primeng/radiobutton';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    InputText,
    PrimeIcons,
    RadioButton
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
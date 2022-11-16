import { Component, OnInit, OnDestroy } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { Producto } from './producto';
import { HeaderService } from '../header/header.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-rbp',
  templateUrl: './rbp.component.html',
  styleUrls: ['./productos.component.css']
})
export class RbpComponent implements OnInit {

  public matches: Producto[] = []; // this.headerService.getMessage()[0];
  public input: string = ""; //this.headerService.getMessage()[1];
  public subscription: Subscription;

  constructor(private headerService: HeaderService) { 
    this.subscription = this.headerService.getMessage().subscribe(message => {
      this.matches = message[0];
      this.input = message[1];
    })
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {

  }

}

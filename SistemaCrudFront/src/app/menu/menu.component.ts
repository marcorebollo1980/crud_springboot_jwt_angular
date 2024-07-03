import { Component } from '@angular/core';
import { TokenService } from '../service/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: []
})
export class MenuComponent {
  
  //Atributos y variables
 isLogged: boolean = false; 


 constructor(private tokenService: TokenService) { }

 ngOnInit() {
  if (this.tokenService.getToken()) {
    this.isLogged = true;
  } else {
    this.isLogged = false;
  }
 }

 onLogOut(): void {
  this.tokenService.logOut();
  window.location.reload();
 }




}

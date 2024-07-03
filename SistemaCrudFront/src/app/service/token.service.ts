import { Injectable } from '@angular/core';


const TOKEN_KEY = "AuthToken";
const USERNAME_KEY = "AuthUserName";
const AUTHORITIES_KEY = "AuthAuthorities";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  //Atributos y variables
  roles: Array<string> = [];

  constructor() { }


  public setToken(token: string): void{
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string  {
    return sessionStorage.getItem(TOKEN_KEY) || '';
  }

  public setUserName(userName: string): void{
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, userName);
  }
  
  public getUserName(): string {
    const userName = sessionStorage.getItem(USERNAME_KEY);
    if(userName === null){
      throw new Error('El userName not found');  
    }else{
      return userName;
    }
  }

  public setAuthorities(authorities: string[]): void {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];
    const authorities = sessionStorage.getItem(AUTHORITIES_KEY);
    if (authorities) {
      try {
        JSON.parse(authorities).forEach((authority: any) => {
          this.roles.push(authority.authority);
        });
      } catch (e) {
        console.error('Error al momento de realizar el parseo de las authorities del session storage', e);
      }
    }
    return this.roles;
  }


  public logOut(): void {
    window.sessionStorage.clear();
  }

}

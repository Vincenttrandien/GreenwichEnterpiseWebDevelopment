import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CommandURL } from 'src/shared/manageURL';
import { SIGNIN } from './login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor( private http : HttpClient ) { }

  login(body: SIGNIN){
    return this.http.post<any>(CommandURL.SIGNIN , {username : body.username, password: body.password})
  }
}

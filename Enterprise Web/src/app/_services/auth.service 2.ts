import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommandURL } from 'src/shared/manageURL';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post(CommandURL.SIGNIN, {
      username: credentials.username,
      password: credentials.password
    });
  }

  register(username: string, email: string, password: string, roles: any): Observable<any> {
    return this.http.post(CommandURL.SIGNUP, {
      username,
      email,
      password,
      roles
    });
  }
}

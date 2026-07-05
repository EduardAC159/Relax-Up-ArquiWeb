import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from '../models/UserDTO';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class Userservice {
  private url = `${base_url}/api/User`;
  constructor(private http: HttpClient) {}

  registrar(u: UserDTO) {
    return this.http.post(`${this.url}/nuevo`, u);
  }
}
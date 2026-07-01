import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Comunidad } from '../models/Comunidad';
import { Usuario } from '../models/usuario';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class Usuarioservice {
     private url = `${base_url}/api/Usuario`;
      constructor(private http: HttpClient) {}
    
      list() {
        return this.http.get<Usuario[]>(`${this.url}/listar`);
      }
      insert(u: Usuario) {
        return this.http.post(`${this.url}/nuevo`, u);
      }
      delete(id: number) {
        return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
      }
}

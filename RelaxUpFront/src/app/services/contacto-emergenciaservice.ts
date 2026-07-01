import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Comunidad } from '../models/Comunidad';
import { ContactoEmergencia } from '../models/contacto-emergencia';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class ContactoEmergenciaservice {
    private url = `${base_url}/api/ContactoEmergencia`;
      constructor(private http: HttpClient) {}
    
      list() {
        return this.http.get<ContactoEmergencia[]>(`${this.url}/listar`);
      }
      insert(co: ContactoEmergencia) {
        return this.http.post(`${this.url}/nuevo`, co);
      }
      delete(id: number) {
        return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
      }
}

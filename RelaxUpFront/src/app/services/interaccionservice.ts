import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Interaccion } from '../models/Interaccion';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class Interaccionservice {
    private url = `${base_url}/api/Interaccion`;
          constructor(private http: HttpClient) {}
        
          list() {
            return this.http.get<Interaccion[]>(`${this.url}/listar`);
          }
          insert(e: Interaccion) {
            return this.http.post(`${this.url}/nuevo`, e);
          }
          delete(id: number) {
            return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
          }
}

import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Emergencia } from '../models/emergencia';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class Emergenciaservice {
     private url = `${base_url}/api/Emergencias`;
      constructor(private http: HttpClient) {}
    
      list() {
        return this.http.get<Emergencia[]>(`${this.url}/listar`);
      }
      insert(e: Emergencia) {
        return this.http.post(`${this.url}/nuevo`, e);
      }
      update(e: Emergencia) {
        return this.http.put(`${this.url}/actualiza`, e, { responseType: 'text' });
      }
      listId(id: number) {
        return this.http.get<Emergencia>(`${this.url}/${id}`);
      }
      delete(id: number) {
        return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
      }
}
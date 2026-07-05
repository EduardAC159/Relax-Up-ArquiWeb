import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Comunidad } from '../models/Comunidad';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class Comunidadservice {
  private url = `${base_url}/api/Comunidad`;
  constructor(private http: HttpClient) {}

  list() {
    return this.http.get<Comunidad[]>(`${this.url}/listar`);
  }
  insert(c: Comunidad) {
    return this.http.post(`${this.url}/nuevo`, c);
  }
  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
  update(c: Comunidad) {
    return this.http.put(`${this.url}/actualiza`, c, { responseType: 'text' });
  }
  listId(id: number) {
    return this.http.get<Comunidad>(`${this.url}/${id}`);
  }
}

import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Recursos } from '../models/Recursos';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class Recursosservice {
  private url = `${base_url}/api/Recursos`;
  constructor(private http: HttpClient) {}

  // El backend expone GET en la raíz del recurso (sin "/listar")
  list() {
    return this.http.get<Recursos[]>(`${this.url}`);
  }
  insert(r: Recursos) {
    return this.http.post(`${this.url}/nuevo`, r);
  }
  update(r: Recursos) {
    return this.http.put(`${this.url}/actualiza`, r, { responseType: 'text' });
  }
  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
  // No existe GET /{id} en el backend, así que no hay listId() aquí.
}
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Progreso } from '../models/Progreso';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class Progresoservice {
  private url = `${base_url}/api/Progreso`;
  constructor(private http: HttpClient) {}

  // El backend expone GET en la raíz del recurso (sin "/listar")
  list() {
    return this.http.get<Progreso[]>(`${this.url}`);
  }
  insert(p: Progreso) {
    return this.http.post(`${this.url}/nuevo`, p);
  }
  update(p: Progreso) {
    return this.http.put(`${this.url}/actualiza`, p, { responseType: 'text' });
  }
  // Requiere el endpoint GET /{id} agregado arriba en ProgresoController
  listId(id: number) {
    return this.http.get<Progreso>(`${this.url}/${id}`);
  }
  // Requiere el endpoint DELETE /{id} agregado arriba en ProgresoController
  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
}
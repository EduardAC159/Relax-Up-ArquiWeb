import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Recordatorio } from '../models/Recordatorio';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class Recordatorioservice {
  private url = `${base_url}/api/Recordatorio`;
  constructor(private http: HttpClient) {}

  // El backend expone GET en la raíz del recurso (sin "/listar")
  list() {
    return this.http.get<Recordatorio[]>(`${this.url}`);
  }
  insert(r: Recordatorio) {
    return this.http.post(`${this.url}/nuevo`, r);
  }
  update(r: Recordatorio) {
    return this.http.put(`${this.url}/actualiza`, r, { responseType: 'text' });
  }
  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
  // No existe GET /{id} en el backend, así que no hay método listId() aquí.
}
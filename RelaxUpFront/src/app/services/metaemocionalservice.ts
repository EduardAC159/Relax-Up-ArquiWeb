import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { MetaEmocional } from '../models/meta-emocional';
import { QuantityMetaEmocional } from '../models/QuantityMetaEmocional';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class MetaEmocionalservice {
  private url = `${base_url}/api/MetaEmocional`;
  constructor(private http: HttpClient) {}

  list() {
    return this.http.get<MetaEmocional[]>(`${this.url}/listar`);
  }
  insert(m: MetaEmocional) {
    return this.http.post(`${this.url}/nuevo`, m);
  }
  update(m: MetaEmocional) {
    return this.http.put(`${this.url}/actualiza`, m, { responseType: 'text' });
  }
  listId(id: number) {
    return this.http.get<MetaEmocional>(`${this.url}/${id}`);
  }
  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
  cantidadMetaEmocionalUsuario() {
    return this.http.get<QuantityMetaEmocional[]>(`${this.url}/CantidadMetaEmocionalUsuario`);
  }
}
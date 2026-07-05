import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { ContactoEmergencia } from '../../../models/contacto-emergencia';
import { ContactoEmergenciaservice } from '../../../services/contacto-emergenciaservice';
import { Usuarioservice } from '../../../services/usuarioservice';

@Component({
  selector: 'app-contacto-emergencia-list',
  imports: [MatTableModule,MatIconModule,MatButtonModule],
  templateUrl: './contacto-emergencia-list.html',
  styleUrl: './contacto-emergencia-list.css',
})
export class ContactoEmergenciaList implements OnInit {
  dataSource: MatTableDataSource<ContactoEmergencia> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5', 'c6'];

  constructor(
    private ceS: ContactoEmergenciaservice,
    private router: Router,
    private uS: Usuarioservice,
  ) {}

  ngOnInit(): void {
    this.cargar();
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.cargar();
      }
    });
  }
  cargar() {
    this.ceS.list().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
    });
  }
  eliminar(id: number) {
    this.ceS.delete(id).subscribe((data) => {
      this.ceS.list().subscribe((data) => {
        this.dataSource.data = data;
      });
    });
  }
  init() {

    this.uS.list(this.id).subscribe((data) => {


    });
  }
  
}





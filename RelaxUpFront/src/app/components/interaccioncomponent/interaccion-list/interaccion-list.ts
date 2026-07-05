import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Usuario } from '../../../models/usuario';
import { Interaccion } from '../../../models/Interaccion';
import { Interaccionservice } from '../../../services/interaccionservice';
import { Comunidad } from '../../../models/Comunidad';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { CommonModule } from '@angular/common';
import { Comunidadservice } from '../../../services/comunidadservice';

@Component({
  selector: 'app-interaccion-list',
  imports: [MatTableModule, MatIconModule, CommonModule, RouterLink, MatPaginatorModule],
  templateUrl: './interaccion-list.html',
  styleUrl: './interaccion-list.css',
})
export class InteraccionList implements OnInit, AfterViewInit {
  dataSource: MatTableDataSource<Interaccion> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c4', 'c5'];
  user: Usuario[] = [];
  comu: Comunidad[] = [];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private uS: Usuarioservice,
    private iS: Interaccionservice,
    private cS: Comunidadservice,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.uS.list().subscribe((data) => {
      this.user = data;
    });
    this.cS.list().subscribe((data) => {
      this.comu = data;
    });

    this.cargar();

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.cargar();
      }
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  cargar() {
    this.iS.list().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
    });
  }
  getUser(id: number): string {
    return this.user.find((pro) => pro.idUsuario === id)?.nombres || 'Sin proyecto';
  }
  getComunidad(id: number): string {
    return this.comu.find((pro) => pro.idComunidad === id)?.nombre || 'Sin proyecto';
  }

  eliminar(id: number) {
    this.iS.delete(id).subscribe((data) => {
      this.iS.list().subscribe((data) => {
        this.dataSource.data = data;
      });
    });
  }
}
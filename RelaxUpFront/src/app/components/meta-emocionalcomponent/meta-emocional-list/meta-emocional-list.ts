import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MetaEmocional } from '../../../models/meta-emocional';
import { MetaEmocionalservice } from '../../../services/metaemocionalservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Usuario } from '../../../models/usuario';

@Component({
  selector: 'app-meta-emocional-list',
  imports: [MatTableModule, MatIconModule, MatButtonModule, MatPaginatorModule],
  templateUrl: './meta-emocional-list.html',
  styleUrl: './meta-emocional-list.css',
})
export class MetaEmocionalList implements OnInit, AfterViewInit {
  dataSource: MatTableDataSource<MetaEmocional> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5', 'c6', 'c7', 'c8'];
  usuarios: Usuario[] = [];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private meS: MetaEmocionalservice,
    private uS: Usuarioservice,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.uS.list().subscribe((data) => {
      this.usuarios = data;
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
    this.meS.list().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
    });
  }

  getUsuario(id: number): string {
    return this.usuarios.find((u) => u.idUsuario === id)?.nombres || 'Sin usuario';
  }

  editar(id: number) {
    this.router.navigate(['/meta-emocional/editar', id]);
  }

  eliminar(id: number) {
    this.meS.delete(id).subscribe(() => {
      this.cargar();
    });
  }
}
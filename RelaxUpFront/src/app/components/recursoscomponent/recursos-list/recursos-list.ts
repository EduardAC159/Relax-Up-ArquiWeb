import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Recursosservice } from '../../../services/recursosservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Usuario } from '../../../models/usuario';
import { Recursos } from '../../../models/Recursos';

@Component({
  selector: 'app-recursos-list',
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatPaginatorModule,
  ],
  templateUrl: './recursos-list.html',
  styleUrl: './recursos-list.css',
})
export class RecursosList implements OnInit, AfterViewInit {
  dataSource: MatTableDataSource<Recursos> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5', 'c6', 'c7'];
  usuarios: Usuario[] = [];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private recS: Recursosservice,
    private uS: Usuarioservice,
    private router: Router,
    private snackBar: MatSnackBar,
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
    this.recS.list().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
      error: (error) => {
        console.error('❌ Error al cargar recursos:', error);
        this.snackBar.open('Error al cargar recursos', 'Cerrar', {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'top',
        });
      },
    });
  }

  getUsuario(id: number): string {
    return this.usuarios.find((u) => u.idUsuario === id)?.nombres || 'Sin usuario';
  }

  editar(id: number) {
    this.router.navigate(['/recursos/editar', id]);
  }

  eliminar(id: number) {
    if (!id || isNaN(id) || id <= 0) {
      this.snackBar.open('Error: ID inválido', 'Cerrar', { duration: 3000 });
      return;
    }
    if (confirm('¿Estás seguro de eliminar este recurso?')) {
      this.recS.delete(id).subscribe({
        next: () => {
          this.snackBar.open('Recurso eliminado correctamente', 'Cerrar', {
            duration: 3000,
          });
          this.cargar();
        },
        error: (error) => {
          console.error('❌ Error al eliminar:', error);
          this.snackBar.open('Error al eliminar recurso', 'Cerrar', {
            duration: 3000,
          });
        },
      });
    }
  }
}
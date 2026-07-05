import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Progresoservice } from '../../../services/progresoservice';
import { MetaEmocionalservice } from '../../../services/metaemocionalservice';
import { MetaEmocional } from '../../../models/meta-emocional';
import { Progreso } from '../../../models/Progreso';

@Component({
  selector: 'app-progreso-list',
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatPaginatorModule,
  ],
  templateUrl: './progreso-list.html',
  styleUrl: './progreso-list.css',
})
export class ProgresoList implements OnInit, AfterViewInit {
  dataSource: MatTableDataSource<Progreso> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5', 'c6', 'c7'];
  metas: MetaEmocional[] = [];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private pS: Progresoservice,
    private meS: MetaEmocionalservice,
    private router: Router,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    this.meS.list().subscribe((data) => {
      this.metas = data;
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
    this.pS.list().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
      error: (error) => {
        console.error('❌ Error al cargar progresos:', error);
        this.snackBar.open('Error al cargar progresos', 'Cerrar', {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'top',
        });
      },
    });
  }

  getMeta(id: number): string {
    return this.metas.find((m) => m.idMeta === id)?.descripcion || 'Sin meta';
  }

  editar(id: number) {
    this.router.navigate(['/progreso/editar', id]);
  }

  eliminar(id: number) {
    if (!id || isNaN(id) || id <= 0) {
      this.snackBar.open('Error: ID inválido', 'Cerrar', { duration: 3000 });
      return;
    }
    if (confirm('¿Estás seguro de eliminar este progreso?')) {
      this.pS.delete(id).subscribe({
        next: () => {
          this.snackBar.open('Progreso eliminado correctamente', 'Cerrar', {
            duration: 3000,
          });
          this.cargar();
        },
        error: (error) => {
          console.error('❌ Error al eliminar:', error);
          this.snackBar.open('Error al eliminar progreso', 'Cerrar', {
            duration: 3000,
          });
        },
      });
    }
  }
}
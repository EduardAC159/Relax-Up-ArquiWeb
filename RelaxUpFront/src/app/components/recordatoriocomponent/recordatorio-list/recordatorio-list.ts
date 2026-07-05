import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Recordatorioservice } from '../../../services/recordatorioservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Usuario } from '../../../models/usuario';
import { Recordatorio } from '../../../models/Recordatorio';

@Component({
  selector: 'app-recordatorio-list',
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    MatPaginatorModule,
  ],
  templateUrl: './recordatorio-list.html',
  styleUrl: './recordatorio-list.css',
})
export class RecordatorioList implements OnInit, AfterViewInit {
  dataSource: MatTableDataSource<Recordatorio> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5', 'c6', 'c7'];
  usuarios: Usuario[] = [];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private reS: Recordatorioservice,
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
    this.reS.list().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
      error: (error) => {
        console.error('❌ Error al cargar recordatorios:', error);
        this.snackBar.open('Error al cargar recordatorios', 'Cerrar', {
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
    this.router.navigate(['/recordatorio/editar', id]);
  }

  eliminar(id: number) {
    if (!id || isNaN(id) || id <= 0) {
      this.snackBar.open('Error: ID inválido', 'Cerrar', { duration: 3000 });
      return;
    }
    if (confirm('¿Estás seguro de eliminar este recordatorio?')) {
      this.reS.delete(id).subscribe({
        next: () => {
          this.snackBar.open('Recordatorio eliminado correctamente', 'Cerrar', {
            duration: 3000,
          });
          this.cargar();
        },
        error: (error) => {
          console.error('❌ Error al eliminar:', error);
          this.snackBar.open('Error al eliminar recordatorio', 'Cerrar', {
            duration: 3000,
          });
        },
      });
    }
  }
}
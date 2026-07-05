import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { CommonModule } from '@angular/common'; // ✅ IMPORTAR CommonModule
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Usuario } from '../../../models/usuario';
import { Usuarioservice } from '../../../services/usuarioservice';

@Component({
  selector: 'app-usuario-list',
  imports: [
    CommonModule, // ✅ AGREGAR CommonModule
    MatTableModule,
    MatIconModule,
    MatButtonModule,
  ],
  templateUrl: './usuario-list.html',
  styleUrl: './usuario-list.css',
})
export class UsuarioList implements OnInit {
  dataSource: MatTableDataSource<Usuario> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5', 'c6', 'c7', 'c8'];

  constructor(
    private uS: Usuarioservice,
    private router: Router,
    private snackBar: MatSnackBar,
    private cdr: ChangeDetectorRef,
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
    console.log('📥 Cargando usuarios...');
    this.uS.list().subscribe({
      next: (data) => {
        console.log('✅ Datos recibidos:', data);
        this.dataSource.data = data;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('❌ Error al cargar usuarios:', error);
        this.snackBar.open('Error al cargar usuarios', 'Cerrar', {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'top',
        });
      },
    });
  }

  eliminar(id: number) {
    console.log('🗑️ Eliminar usuario - ID:', id);

    if (!id || isNaN(id) || id <= 0) {
      console.error('❌ ID inválido');
      this.snackBar.open('Error: ID inválido', 'Cerrar', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
      return;
    }

    if (confirm('¿Estás seguro de eliminar este usuario?')) {
      this.uS.delete(id).subscribe({
        next: () => {
          console.log('✅ Usuario eliminado');
          this.snackBar.open('Usuario eliminado correctamente', 'Cerrar', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
          });
          this.cargar();
        },
        error: (error) => {
          console.error('❌ Error al eliminar:', error);
          this.snackBar.open('Error al eliminar usuario', 'Cerrar', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top',
          });
        },
      });
    }
  }

  // usuario-list.ts
  verContactos(id: number) {
    console.log('🔍 ===== verContactos INICIO =====');
    console.log('🔍 ID recibido:', id);
    console.log('🔍 Tipo del ID:', typeof id);
    console.log('🔍 ¿Es undefined?', id === undefined);
    console.log('🔍 ¿Es null?', id === null);
    console.log('🔍 ¿Es NaN?', isNaN(id));

    // ✅ Validación estricta
    if (id === undefined || id === null || isNaN(id) || id <= 0) {
      console.error('❌ ID inválido para navegar a contactos:', id);

      // ✅ Mostrar el elemento completo para depuración
      console.log('📊 Datos del dataSource:', this.dataSource.data);
      if (this.dataSource.data.length > 0) {
        console.log('📊 Primer usuario:', this.dataSource.data[0]);
        console.log('📊 ID del primer usuario:', this.dataSource.data[0].idUsuario);
      }

      this.snackBar.open('Error: ID de usuario inválido', 'Cerrar', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top',
      });
      return;
    }

    const idNumber = Number(id);
    console.log('✅ ID válido:', idNumber);
    console.log('🚀 Navegando a: /contacto-emergencia/lista/' + idNumber);

    // ✅ Navegar con el ID validado
    this.router.navigate(['/contacto-emergencia/lista', idNumber]).then(
      (success) => {
        console.log('✅ Navegación exitosa:', success);
        if (!success) {
          console.warn('⚠️ Navegación fallida, usando window.location');
          window.location.href = `/contacto-emergencia/lista/${idNumber}`;
        }
      },
      (error) => {
        console.error('❌ Error en navegación:', error);
        window.location.href = `/contacto-emergencia/lista/${idNumber}`;
      },
    );
  }
  editar(id: number) {
    this.router.navigate(['/usuario/editar', id]);
  }
}

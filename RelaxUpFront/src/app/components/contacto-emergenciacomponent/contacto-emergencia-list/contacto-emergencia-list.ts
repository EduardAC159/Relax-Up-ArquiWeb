// contacto-emergencia-list.ts
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ContactoEmergencia } from '../../../models/contacto-emergencia';
import { ContactoEmergenciaservice } from '../../../services/contacto-emergenciaservice';
import { Usuarioservice } from '../../../services/usuarioservice';

@Component({
  selector: 'app-contacto-emergencia-list',
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './contacto-emergencia-list.html',
  styleUrl: './contacto-emergencia-list.css',
})
export class ContactoEmergenciaList implements OnInit {
  dataSource: MatTableDataSource<ContactoEmergencia> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5'];
  id: number = 0;
  nombreUsuario: string = 'Cargando...';

  constructor(
    private ceS: ContactoEmergenciaservice,
    private route: ActivatedRoute,
    private uS: Usuarioservice,
    private router: Router,
    private snackBar: MatSnackBar,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    console.log('🔄 ===== INICIO ContactoEmergenciaList =====');
    console.log('🔄 URL actual:', window.location.href);
    
    // ✅ Suscribirse a los parámetros de la ruta
    this.route.params.subscribe({
      next: (params: Params) => {
        console.log('📋 Parámetros de la URL:', params);
        console.log('📋 ID de la URL:', params['id']);
        
        // ✅ Obtener el ID
        const idParam = params['id'];
        
        // ✅ Si no hay ID en la URL, intentar obtenerlo de otra forma
        if (!idParam) {
          console.warn('⚠️ No hay ID en los parámetros, intentando desde la URL...');
          const urlParts = window.location.pathname.split('/');
          const lastPart = urlParts[urlParts.length - 1];
          console.log('📋 Última parte de la URL:', lastPart);
          
          if (lastPart && !isNaN(Number(lastPart)) && Number(lastPart) > 0) {
            this.procesarId(Number(lastPart));
            return;
          }
          
          console.error('❌ No se encontró ID en la URL');
          this.snackBar.open('Error: ID de usuario no encontrado', 'Cerrar', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
          this.router.navigate(['/usuario/lista']);
          return;
        }
        
        this.procesarId(idParam);
      },
      error: (error) => {
        console.error('❌ Error al obtener parámetros:', error);
        this.router.navigate(['/usuario/lista']);
      }
    });
  }

  procesarId(idParam: any) {
    console.log('📋 Procesando ID:', idParam);
    console.log('📋 Tipo:', typeof idParam);
    
    // ✅ Verificar si el ID es válido
    if (idParam === undefined || idParam === null || idParam === 'undefined' || idParam === 'null') {
      console.error('❌ ID no existe');
      this.snackBar.open('Error: ID de usuario inválido', 'Cerrar', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
      this.router.navigate(['/usuario/lista']);
      return;
    }
    
    // ✅ Convertir a número y validar
    const idNumber = Number(idParam);
    console.log('🔍 ID convertido a número:', idNumber);
    
    if (isNaN(idNumber) || idNumber <= 0) {
      console.error('❌ ID no es un número válido:', idParam);
      this.snackBar.open('Error: ID de usuario inválido', 'Cerrar', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
      this.router.navigate(['/usuario/lista']);
      return;
    }
    
    // ✅ ID válido
    this.id = idNumber;
    console.log('✅ ID de usuario válido:', this.id);
    
    // Cargar datos
    this.cargarUsuario();
    this.init();
  }

  cargarUsuario() {
    if (!this.id || isNaN(this.id) || this.id <= 0) {
      this.nombreUsuario = 'Usuario no encontrado';
      this.cdr.detectChanges();
      return;
    }
    
    console.log('📥 Cargando usuario con ID:', this.id);
    this.uS.listId(this.id).subscribe({
      next: (usuario) => {
        console.log('✅ Usuario cargado:', usuario);
        this.nombreUsuario = usuario?.nombres || 'Usuario sin nombre';
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('❌ Error al cargar usuario:', error);
        this.nombreUsuario = 'Usuario no encontrado';
        this.cdr.detectChanges();
        if (error.status === 401) {
          this.router.navigate(['/login']);
        }
      }
    });
  }

  init() {
    if (!this.id || isNaN(this.id) || this.id <= 0) {
      console.error('❌ ID inválido para cargar contactos');
      this.dataSource.data = [];
      this.cdr.detectChanges();
      return;
    }
    
    console.log('📥 Cargando contactos para usuario ID:', this.id);
    this.ceS.listByUsuario(this.id).subscribe({
      next: (data) => {
        console.log('✅ Contactos cargados:', data);
        this.dataSource.data = data || [];
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('❌ Error al cargar contactos:', error);
        this.dataSource.data = [];
        this.cdr.detectChanges();
        this.snackBar.open('Error al cargar contactos', 'Cerrar', {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'top'
        });
        if (error.status === 401) {
          this.router.navigate(['/login']);
        }
      }
    });
  }

  eliminar(id: number) {
    console.log('🗑️ Eliminar contacto - ID:', id);
    
    if (!id || isNaN(id) || id <= 0) {
      console.error('❌ ID de contacto inválido');
      this.snackBar.open('Error: ID de contacto inválido', 'Cerrar', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
      return;
    }
    
    if (confirm('¿Estás seguro de eliminar este contacto?')) {
      this.ceS.delete(id).subscribe({
        next: () => {
          console.log('✅ Contacto eliminado');
          this.snackBar.open('Contacto eliminado correctamente', 'Cerrar', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
          this.init();
        },
        error: (error) => {
          console.error('❌ Error al eliminar contacto:', error);
          this.snackBar.open('Error al eliminar contacto', 'Cerrar', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
        }
      });
    }
  }

  irRegistrar() {
    if (!this.id || isNaN(this.id) || this.id <= 0) {
      console.error('❌ ID inválido para registrar');
      this.snackBar.open('Error: ID de usuario inválido', 'Cerrar', {
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
      return;
    }
    
    console.log('🚀 Navegando a registrar contacto para usuario:', this.id);
    this.router.navigate(['/contacto-emergencia/news', this.id]);
  }
  

  volver() {
    console.log('🔙 Volviendo a lista de usuarios');
    this.router.navigate(['/usuario/lista']);
  }
}
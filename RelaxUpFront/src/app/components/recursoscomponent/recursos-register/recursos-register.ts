import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Params, Router, RouterLink } from '@angular/router';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Recursosservice } from '../../../services/recursosservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Usuario } from '../../../models/usuario';
import { Recursos } from '../../../models/Recursos';

@Component({
  selector: 'app-recursos-register',
  imports: [
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './recursos-register.html',
  styleUrl: './recursos-register.css',
})
export class RecursosRegister implements OnInit {
  form: FormGroup = new FormGroup({});
  recurso: Recursos = new Recursos();
  editMode: boolean = false;
  usuarios: Usuario[] = [];
  tipos: string[] = ['Video', 'Articulo', 'Audio', 'Ejercicio guiado'];

  constructor(
    private recS: Recursosservice,
    private uS: Usuarioservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    this.uS.list().subscribe((data) => {
      this.usuarios = data;
    });

    this.form = this.formBuilder.group({
      Titulo: ['', Validators.required],
      Tipo: ['', Validators.required],
      Enlace: ['', [Validators.required, Validators.pattern('https?://.+')]],
      IdUsuario: ['', Validators.required],
    });

    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      if (id) {
        this.editMode = true;
        // No existe GET /api/Recursos/{id} en el backend:
        // se busca el registro dentro del listado completo.
        this.recS.list().subscribe({
          next: (data) => {
            const encontrado = data.find((r) => r.idRecursos === id);
            if (!encontrado) {
              this.snackBar.open('Recurso no encontrado', 'Cerrar', {
                duration: 3000,
              });
              this.router.navigate(['/recursos/lista']);
              return;
            }
            this.recurso = encontrado;
            this.form.patchValue({
              Titulo: encontrado.titulo,
              Tipo: encontrado.tipo,
              Enlace: encontrado.enlace,
              IdUsuario: encontrado.idUsuario,
            });
          },
          error: () => {
            this.snackBar.open('Error al cargar el recurso', 'Cerrar', {
              duration: 3000,
            });
          },
        });
      }
    });
  }

  aceptar(): void {
    if (this.form.valid) {
      this.recurso.titulo = this.form.value.Titulo;
      this.recurso.tipo = this.form.value.Tipo;
      this.recurso.enlace = this.form.value.Enlace;
      this.recurso.idUsuario = this.form.value.IdUsuario;

      if (this.editMode) {
        this.recS.update(this.recurso).subscribe({
          next: () => {
            this.snackBar.open('Recurso actualizado correctamente', 'Cerrar', {
              duration: 3000,
            });
            this.router.navigate(['/recursos/lista']);
          },
          error: () => {
            this.snackBar.open('Error al actualizar el recurso', 'Cerrar', {
              duration: 3000,
            });
          },
        });
      } else {
        this.recS.insert(this.recurso).subscribe({
          next: () => {
            this.snackBar.open('Recurso registrado correctamente', 'Cerrar', {
              duration: 3000,
            });
            this.router.navigate(['/recursos/lista']);
          },
          error: () => {
            this.snackBar.open('Error al registrar el recurso', 'Cerrar', {
              duration: 3000,
            });
          },
        });
      }
    }
  }
}
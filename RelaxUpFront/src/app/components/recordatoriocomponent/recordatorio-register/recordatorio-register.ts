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
import {
  MatNativeDateModule,
  provideNativeDateAdapter,
} from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Recordatorioservice } from '../../../services/recordatorioservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Usuario } from '../../../models/usuario';
import { Recordatorio } from '../../../models/Recordatorio';

@Component({
  selector: 'app-recordatorio-register',
  providers: [provideNativeDateAdapter()],
  imports: [
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    RouterLink,
  ],
  templateUrl: './recordatorio-register.html',
  styleUrl: './recordatorio-register.css',
})
export class RecordatorioRegister implements OnInit {
  form: FormGroup = new FormGroup({});
  recordatorio: Recordatorio = new Recordatorio();
  editMode: boolean = false;
  usuarios: Usuario[] = [];
  tipos: string[] = ['Alerta', 'Recordatorio diario', 'Motivacional', 'Seguimiento'];

  constructor(
    private reS: Recordatorioservice,
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
      Mensaje: ['', Validators.required],
      FechaHora: ['', Validators.required],
      Tipo: ['', Validators.required],
      IdUsuario: ['', Validators.required],
    });

    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      if (id) {
        this.editMode = true;
        // No existe GET /api/Recordatorio/{id} en el backend:
        // se busca el registro dentro del listado completo.
        this.reS.list().subscribe({
          next: (data) => {
            const encontrado = data.find((r) => r.idRecordatorio === id);
            if (!encontrado) {
              this.snackBar.open('Recordatorio no encontrado', 'Cerrar', {
                duration: 3000,
              });
              this.router.navigate(['/recordatorio/lista']);
              return;
            }
            this.recordatorio = encontrado;
            this.form.patchValue({
              Mensaje: encontrado.mensaje,
              FechaHora: new Date(encontrado.fechaHora),
              Tipo: encontrado.tipo,
              IdUsuario: encontrado.idUsuario,
            });
          },
          error: () => {
            this.snackBar.open('Error al cargar el recordatorio', 'Cerrar', {
              duration: 3000,
            });
          },
        });
      }
    });
  }

  aceptar(): void {
    if (this.form.valid) {
      this.recordatorio.mensaje = this.form.value.Mensaje;
      this.recordatorio.fechaHora = this.form.value.FechaHora;
      this.recordatorio.tipo = this.form.value.Tipo;
      this.recordatorio.idUsuario = this.form.value.IdUsuario;

      if (this.editMode) {
        this.reS.update(this.recordatorio).subscribe({
          next: () => {
            this.snackBar.open('Recordatorio actualizado correctamente', 'Cerrar', {
              duration: 3000,
            });
            this.router.navigate(['/recordatorio/lista']);
          },
          error: () => {
            this.snackBar.open('Error al actualizar el recordatorio', 'Cerrar', {
              duration: 3000,
            });
          },
        });
      } else {
        this.reS.insert(this.recordatorio).subscribe({
          next: () => {
            this.snackBar.open('Recordatorio registrado correctamente', 'Cerrar', {
              duration: 3000,
            });
            this.router.navigate(['/recordatorio/lista']);
          },
          error: () => {
            this.snackBar.open('Error al registrar el recordatorio', 'Cerrar', {
              duration: 3000,
            });
          },
        });
      }
    }
  }
}
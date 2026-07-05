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
import { Progresoservice } from '../../../services/progresoservice';
import { MetaEmocionalservice } from '../../../services/metaemocionalservice';
import { MetaEmocional } from '../../../models/meta-emocional';
import { Progreso } from '../../../models/Progreso';

@Component({
  selector: 'app-progreso-register',
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
  templateUrl: './progreso-register.html',
  styleUrl: './progreso-register.css',
})
export class ProgresoRegister implements OnInit {
  form: FormGroup = new FormGroup({});
  progreso: Progreso = new Progreso();
  editMode: boolean = false;
  metas: MetaEmocional[] = [];

  constructor(
    private pS: Progresoservice,
    private meS: MetaEmocionalservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    this.meS.list().subscribe((data) => {
      this.metas = data;
    });

    this.form = this.formBuilder.group({
      NivelControlIra: ['', [Validators.required, Validators.min(0), Validators.max(10)]],
      Fecha: ['', Validators.required],
      Observaciones: ['', Validators.required],
      IdMetaEmocional: ['', Validators.required],
    });

    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      if (id) {
        this.editMode = true;
        // Este módulo sí tiene GET /api/Progreso/{id} (lo agregamos en el backend)
        this.pS.listId(id).subscribe({
          next: (data) => {
            this.progreso = data;
            this.form.patchValue({
              NivelControlIra: data.nivelControlIra,
              Fecha: new Date(data.fecha),
              Observaciones: data.observaciones,
              IdMetaEmocional: data.idMetaEmocional,
            });
          },
          error: () => {
            this.snackBar.open('Progreso no encontrado', 'Cerrar', {
              duration: 3000,
            });
            this.router.navigate(['/progreso/lista']);
          },
        });
      }
    });
  }

  aceptar(): void {
    if (this.form.valid) {
      this.progreso.nivelControlIra = this.form.value.NivelControlIra;
      this.progreso.fecha = this.form.value.Fecha;
      this.progreso.observaciones = this.form.value.Observaciones;
      this.progreso.idMetaEmocional = this.form.value.IdMetaEmocional;

      if (this.editMode) {
        this.pS.update(this.progreso).subscribe({
          next: () => {
            this.snackBar.open('Progreso actualizado correctamente', 'Cerrar', {
              duration: 3000,
            });
            this.router.navigate(['/progreso/lista']);
          },
          error: () => {
            this.snackBar.open('Error al actualizar el progreso', 'Cerrar', {
              duration: 3000,
            });
          },
        });
      } else {
        this.pS.insert(this.progreso).subscribe({
          next: () => {
            this.snackBar.open('Progreso registrado correctamente', 'Cerrar', {
              duration: 3000,
            });
            this.router.navigate(['/progreso/lista']);
          },
          error: () => {
            this.snackBar.open('Error al registrar el progreso', 'Cerrar', {
              duration: 3000,
            });
          },
        });
      }
    }
  }
}
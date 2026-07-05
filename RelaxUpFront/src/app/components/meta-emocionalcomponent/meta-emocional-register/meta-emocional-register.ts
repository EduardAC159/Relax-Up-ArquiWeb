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
import { MatNativeDateModule, provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatButtonModule } from '@angular/material/button';
import { MetaEmocional } from '../../../models/meta-emocional';
import { MetaEmocionalservice } from '../../../services/metaemocionalservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Usuario } from '../../../models/usuario';

@Component({
  selector: 'app-meta-emocional-register',
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
  templateUrl: './meta-emocional-register.html',
  styleUrl: './meta-emocional-register.css',
})
export class MetaEmocionalRegister implements OnInit {
  form: FormGroup = new FormGroup({});
  meta: MetaEmocional = new MetaEmocional();
  editMode: boolean = false;
  usuarios: Usuario[] = [];
  estados: string[] = ['Pendiente', 'En progreso', 'Completada'];

  constructor(
    private meS: MetaEmocionalservice,
    private uS: Usuarioservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.uS.list().subscribe((data) => {
      this.usuarios = data;
    });

    this.form = this.formBuilder.group({
      Descripcion: ['', Validators.required],
      FechaInicio: ['', Validators.required],
      FechaFin: ['', Validators.required],
      Estado: ['', Validators.required],
      IdUsuario: ['', Validators.required],
    });

    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      if (id) {
        this.editMode = true;
        this.meS.listId(id).subscribe({
          next: (data) => {
            this.meta = data;
            this.form.patchValue({
              Descripcion: data.descripcion,
              FechaInicio: new Date(data.fechaInicio),
              FechaFin: new Date(data.fechaFin),
              Estado: data.estado,
              IdUsuario: data.idUsuario,
            });
          },
        });
      }
    });
  }

  aceptar(): void {
    if (this.form.valid) {
      this.meta.descripcion = this.form.value.Descripcion;
      this.meta.fechaInicio = this.form.value.FechaInicio;
      this.meta.fechaFin = this.form.value.FechaFin;
      this.meta.estado = this.form.value.Estado;
      this.meta.idUsuario = this.form.value.IdUsuario;

      if (this.editMode) {
        this.meS.update(this.meta).subscribe({
          next: () => {
            this.router.navigate(['/meta-emocional/lista']);
          },
        });
      } else {
        this.meS.insert(this.meta).subscribe({
          next: () => {
            this.router.navigate(['/meta-emocional/lista']);
          },
        });
      }
    }
  }
}
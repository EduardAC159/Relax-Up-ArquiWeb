import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule, provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, Params, Router, RouterLink } from '@angular/router';
import { Emergencia } from '../../../models/emergencia';
import { Emergenciaservice } from '../../../services/emergenciaservice';

@Component({
  selector: 'app-emergencia-register',
  providers: [provideNativeDateAdapter()],
  imports: [
    MatSelectModule,
    MatInputModule,
    MatRadioModule,
    MatDatepickerModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatIconModule,
    RouterLink,
  ],
  templateUrl: './emergencia-register.html',
  styleUrl: './emergencia-register.css',
})
export class EmergenciaRegister implements OnInit {
  form: FormGroup = new FormGroup({});
  eme: Emergencia = new Emergencia();
  editMode: boolean = false;

  constructor(
    private eS: Emergenciaservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      Tipo: ['', Validators.required],
      Descripcion: ['', Validators.required],
      Fecha: ['', Validators.required],
    });

    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      if (id) {
        this.editMode = true;
        this.eS.listId(id).subscribe({
          next: (data) => {
            this.eme = data;
            this.form.patchValue({
              Tipo: data.tipo,
              Descripcion: data.descripcion,
              Fecha: new Date(data.fecha),
            });
          },
        });
      }
    });
  }
  aceptar(): void {
    if (this.form.valid) {
      this.eme.tipo = this.form.value.Tipo;
      this.eme.descripcion = this.form.value.Descripcion;
      this.eme.fecha = this.form.value.Fecha;

      if (this.editMode) {
        this.eS.update(this.eme).subscribe({
          next: () => {
            this.router.navigate(['/emergencia/lista']);
          },
        });
      } else {
        this.eS.insert(this.eme).subscribe({
          next: () => {
            this.router.navigate(['/emergencia/lista']);
          },
        });
      }
    }
  }
}
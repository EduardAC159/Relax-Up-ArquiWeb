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
import { ActivatedRoute, Params, Router, RouterLink } from '@angular/router';
import { Interaccion } from '../../../models/Interaccion';
import { Interaccionservice } from '../../../services/interaccionservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Comunidadservice } from '../../../services/comunidadservice';
import { Usuario } from '../../../models/usuario';
import { Comunidad } from '../../../models/Comunidad';

@Component({
  selector: 'app-interaccion-register',
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
  templateUrl: './interaccion-register.html',
  styleUrl: './interaccion-register.css',
})
export class InteraccionRegister implements OnInit {
  form: FormGroup = new FormGroup({});
  inte: Interaccion = new Interaccion();
  editMode: boolean = false;
  usuarios: Usuario[] = [];
  comunidades: Comunidad[] = [];

  constructor(
    private iS: Interaccionservice,
    private uS: Usuarioservice,
    private cS: Comunidadservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.uS.list().subscribe((data) => {
      this.usuarios = data;
    });
    this.cS.list().subscribe((data) => {
      this.comunidades = data;
    });

    this.form = this.formBuilder.group({
      Mensaje: ['', Validators.required],
      Fecha: ['', Validators.required],
      IdUsuario: ['', Validators.required],
      IdComunidad: ['', Validators.required],
    });

    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      if (id) {
        this.editMode = true;
        this.iS.listId(id).subscribe({
          next: (data) => {
            this.inte = data;
            this.form.patchValue({
              Mensaje: data.mensaje,
              Fecha: new Date(data.fecha),
              IdUsuario: data.idUsuario,
              IdComunidad: data.idComunidad,
            });
          },
        });
      }
    });
  }

  aceptar(): void {
    if (this.form.valid) {
      this.inte.mensaje = this.form.value.Mensaje;
      this.inte.fecha = this.form.value.Fecha;
      this.inte.idUsuario = this.form.value.IdUsuario;
      this.inte.idComunidad = this.form.value.IdComunidad;

      if (this.editMode) {
        this.iS.update(this.inte).subscribe({
          next: () => {
            this.router.navigate(['/interaccion/lista']);
          },
        });
      } else {
        this.iS.insert(this.inte).subscribe({
          next: () => {
            this.router.navigate(['/interaccion/lista']);
          },
        });
      }
    }
  }
}
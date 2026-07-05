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
import { Usuario } from '../../../models/usuario';
import { Usuarioservice } from '../../../services/usuarioservice';

@Component({
  selector: 'app-usuario-register',
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
    RouterLink
],
  templateUrl: './usuario-register.html',
  styleUrl: './usuario-register.css',
})
export class UsuarioRegister implements OnInit {
  form: FormGroup = new FormGroup({});
  us: Usuario = new Usuario();
  editMode: boolean = false;

  constructor(
    private uS: Usuarioservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {

    this.form = this.formBuilder.group({
      nombre: ['', Validators.required],
      correo: ['', Validators.required],
      direccion: ['', Validators.required],
      celular: ['', Validators.required],
    });

    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      if (id) {
        this.editMode = true;
        this.uS.listId(id).subscribe({
          next: (data) => {
            this.us = data;
            this.form.patchValue({
              nombre: data.nombres,
              correo: data.email,
              direccion: data.direccion,
              celular: data.celular,
            });
          },
        });
      }
    });
  }
  aceptar(): void {

    if (this.form.valid) {
      this.us.nombres = this.form.value.nombre;
      this.us.email = this.form.value.correo;
      this.us.direccion = this.form.value.direccion;
      this.us.celular = this.form.value.celular;

      if (this.editMode) {
        this.uS.update(this.us).subscribe({
          next: () => {
            this.router.navigate(['/usuario/lista']);
          }
        });
      } else {
        this.uS.insert(this.us).subscribe({
          next: () => {
            this.router.navigate(['/usuario/lista']);
          }
        });
      }
    }

  }


}
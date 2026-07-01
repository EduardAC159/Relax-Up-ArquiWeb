import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
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
import { Router, RouterLink } from '@angular/router';
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

  constructor(
    private uS: Usuarioservice,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    this.form = this.formBuilder.group({
      nombre: ['', Validators.required],
      correo: ['', Validators.required],
      direccion: ['', Validators.required],
      celular: ['', Validators.required],
    });
  }
  aceptar(): void {

    if (this.form.valid) {
      this.us.Nombres = this.form.value.nombre;
      this.us.Email = this.form.value.correo;
      this.us.Direccion = this.form.value.direccion;
      this.us.Celular = this.form.value.celular;
      console.log(JSON.stringify(this.us));
      this.uS.insert(this.us).subscribe({
        next: () => {
          this.router.navigate(['/usuario/lista']);
        }
      }
      );
    }

  }


}


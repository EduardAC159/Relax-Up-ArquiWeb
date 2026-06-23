import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { Comunidad } from '../../../models/Comunidad';
import { Comunidadservice } from '../../../services/comunidadservice';

@Component({
  selector: 'app-comunidad-register',
  imports: [
    CommonModule,
    MatInputModule,
    MatDatepickerModule,
    MatRadioModule,
    ReactiveFormsModule,
    MatButtonModule,
  ],
  templateUrl: './comunidad-register.html',
  providers: [provideNativeDateAdapter()],
  styleUrl: './comunidad-register.css',
})
export class ComunidadRegister implements OnInit{
  form: FormGroup = new FormGroup({});
  com: Comunidad = new Comunidad();

  constructor(
    private cS: Comunidadservice,
    private router: Router,
    private formBuilder: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      comunidad: ['', Validators.required],
      descripcion:['',Validators.required]
    });
  }
  aceptar() {
    if (this.form.valid) {
      this.com.nombre = this.form.value.comunidad;
      this.com.descripcion = this.form.value.descripcion;
      this.cS.insert(this.com).subscribe({
        next: () => {
          this.router.navigate(['/comunidad/lista']);
        },
      });
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
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
  editMode: boolean = false;

  constructor(
    private cS: Comunidadservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      comunidad: ['', Validators.required],
      descripcion:['',Validators.required]
    });

    this.route.params.subscribe((params: Params) => {
      const id = +params['id'];
      if (id) {
        this.editMode = true;
        this.cS.listId(id).subscribe({
          next: (data) => {
            this.com = data;
            this.form.patchValue({
              comunidad: data.nombre,
              descripcion: data.descripcion,
            });
          },
        });
      }
    });
  }
  aceptar() {
    if (this.form.valid) {
      this.com.nombre = this.form.value.comunidad;
      this.com.descripcion = this.form.value.descripcion;

      if (this.editMode) {
        this.cS.update(this.com).subscribe({
          next: () => {
            this.router.navigate(['/comunidad/lista']);
          },
        });
      } else {
        this.cS.insert(this.com).subscribe({
          next: () => {
            this.router.navigate(['/comunidad/lista']);
          },
        });
      }
    }
  }
}
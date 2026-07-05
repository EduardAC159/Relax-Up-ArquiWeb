import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router, RouterLink } from '@angular/router';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioModule } from '@angular/material/radio';
import { MatNativeDateModule, provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { ContactoEmergencia } from '../../../models/contacto-emergencia';
import { Usuario } from '../../../models/usuario';
import { Usuarioservice } from '../../../services/usuarioservice';
import { ContactoEmergenciaservice } from '../../../services/contacto-emergenciaservice';

@Component({
  selector: 'app-contacto-emergencia-register',
  imports: [
    MatSelectModule,
    MatDatepickerModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatRadioModule,
    MatNativeDateModule,
    RouterLink,
  ],
  templateUrl: './contacto-emergencia-register.html',
  providers: [provideNativeDateAdapter()],
  styleUrl: './contacto-emergencia-register.css',
})
export class ContactoEmergenciaRegister implements OnInit {
  form: FormGroup = new FormGroup({});
  ce: ContactoEmergencia = new ContactoEmergencia();
  idUsuario: number = 0;
  nombreUsuario: string = '';

  constructor(
    private uS: Usuarioservice,
    private ceS: ContactoEmergenciaservice,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.idUsuario = +params['id'];

      this.uS.listId(this.idUsuario).subscribe(data => {
      this.nombreUsuario = data.Nombres; 
    });
    });

    this.form = this.formBuilder.group({
      Nombre: ['', Validators.required],
      Celular: ['', Validators.required],
      Relacion: ['', Validators.required],
    });
  }
  aceptar(): void {
  if (this.form.valid) {

    this.ce.nombre = this.form.value.Nombre;
    this.ce.celular = this.form.value.Celular;
    this.ce.relacion = this.form.value.Relacion;

    this.ce.idUsuario = this.idUsuario;

    this.ceS.insert(this.ce).subscribe({
      next: () => {
        this.router.navigate(['/contacto-emergencia/lista', this.idUsuario]);
      },
    });
  }
}
}

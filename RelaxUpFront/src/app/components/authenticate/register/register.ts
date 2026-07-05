import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { UserDTO } from '../../../models/UserDTO';
import { Userservice } from '../../../services/userservice';

@Component({
  selector: 'app-register',
  imports: [
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    RouterLink,
  ],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  username: string = '';
  password: string = '';
  confirmPassword: string = '';

  constructor(
    private userService: Userservice,
    private router: Router,
    private snackBar: MatSnackBar,
  ) {}

  registrar() {
    if (!this.username || !this.password) {
      this.snackBar.open('Completa usuario y contraseña', 'Cerrar', { duration: 3000 });
      return;
    }
    if (this.password !== this.confirmPassword) {
      this.snackBar.open('Las contraseñas no coinciden', 'Cerrar', { duration: 3000 });
      return;
    }

    const dto = new UserDTO();
    dto.username = this.username;
    dto.password = this.password;

    this.userService.registrar(dto).subscribe({
      next: () => {
        this.snackBar.open('Cuenta creada. Ahora inicia sesión', 'Cerrar', {
          duration: 3000,
        });
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error(error);
        const msg =
          error.status === 409
            ? 'Ese usuario ya existe'
            : 'No se pudo crear la cuenta';
        this.snackBar.open(msg, 'Cerrar', { duration: 3000 });
      },
    });
  }
}
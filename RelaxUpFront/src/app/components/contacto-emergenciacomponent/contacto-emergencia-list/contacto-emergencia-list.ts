import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Params, Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { ContactoEmergencia } from '../../../models/contacto-emergencia';
import { ContactoEmergenciaservice } from '../../../services/contacto-emergenciaservice';
import { Usuarioservice } from '../../../services/usuarioservice';
import { Usuario } from '../../../models/usuario';


@Component({
  selector: 'app-contacto-emergencia-list',
  imports: [MatTableModule,MatIconModule,MatButtonModule],
  templateUrl: './contacto-emergencia-list.html',
  styleUrl: './contacto-emergencia-list.css',
})
export class ContactoEmergenciaList implements OnInit {
  dataSource: MatTableDataSource<ContactoEmergencia> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5'];
  id:number = 0;
  nombreUsuario: string = '';
  listaU: Usuario[] = [];

  constructor(
    private ceS: ContactoEmergenciaservice,
    private route: ActivatedRoute,
    private uS: Usuarioservice,
    private router: Router,
  ) {}

  ngOnInit(): void {

    this.route.params.subscribe((params: Params) => {
      this.id = +params['id'];

      this.uS.listId(this.id).subscribe(usuario => {
      this.nombreUsuario = usuario.Nombres; 
    });
      this.init();
    });

  }

  init() {
    this.ceS.listByUsuario(this.id).subscribe({
      next: (data) => {
        this.dataSource.data = data;
      }
    });
  }

  eliminar(id: number) {
    this.ceS.delete(id).subscribe(() => {
      this.init();
    });
  }

  irRegistrar() {
  this.router.navigate(['/contacto-emergencia/news',this.id]);
}
}
  
  






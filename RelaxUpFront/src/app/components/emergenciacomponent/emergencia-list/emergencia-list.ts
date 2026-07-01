import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { Emergencia } from '../../../models/emergencia';
import { Emergenciaservice } from '../../../services/emergenciaservice';


@Component({
  selector: 'app-emergencia-list',
  imports: [MatTableModule,MatIconModule,MatButtonModule],
  templateUrl: './emergencia-list.html',
  styleUrl: './emergencia-list.css',
})
export class EmergenciaList implements OnInit {
  dataSource: MatTableDataSource<Emergencia> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4', 'c5'];

  constructor(
    private eS: Emergenciaservice,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.cargar();
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.cargar();
      }
    });
  }
  cargar() {
    this.eS.list().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
    });
  }
  eliminar(id: number) {
    this.eS.delete(id).subscribe((data) => {
      this.eS.list().subscribe((data) => {
        this.dataSource.data = data;
      });
    });
  }
}




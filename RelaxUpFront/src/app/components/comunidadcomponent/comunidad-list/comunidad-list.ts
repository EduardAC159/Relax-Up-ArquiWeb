import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { Comunidad } from '../../../models/Comunidad';
import { Comunidadservice } from '../../../services/comunidadservice';


@Component({
  selector: 'app-comunidad-list',
  imports: [MatTableModule,MatIconModule,MatButtonModule],
  templateUrl: './comunidad-list.html',
  styleUrl: './comunidad-list.css',
})
export class ComunidadList implements OnInit {
  dataSource: MatTableDataSource<Comunidad> = new MatTableDataSource();
  displayedColumns: string[] = ['c1', 'c2', 'c3', 'c4'];

  constructor(
    private cS: Comunidadservice,
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
    this.cS.list().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
    });
  }
  eliminar(id: number) {
    this.cS.delete(id).subscribe((data) => {
      this.cS.list().subscribe((data) => {
        this.dataSource.data = data;
      });
    });
  }
}


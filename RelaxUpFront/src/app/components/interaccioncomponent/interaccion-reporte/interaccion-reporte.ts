import { Component, OnInit } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration, ChartData } from 'chart.js';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { Interaccionservice } from '../../../services/interaccionservice';
import { QuantityInteracciones } from '../../../models/QuantityInteracciones';

@Component({
  selector: 'app-interaccion-reporte',
  imports: [BaseChartDirective, MatCardModule, MatTableModule],
  templateUrl: './interaccion-reporte.html',
  styleUrl: './interaccion-reporte.css',
})
export class InteraccionReporte implements OnInit {
  reporte: QuantityInteracciones[] = [];
  displayedColumns: string[] = ['nombre', 'interacciones'];
  cargando = true;
  sinDatos = false;

  chartType: ChartConfiguration<'bar'>['type'] = 'bar';

  chartData: ChartData<'bar'> = {
    labels: [],
    datasets: [
      {
        label: 'Interacciones',
        data: [],
        backgroundColor: '#d32f2f',
        borderRadius: 6,
      },
    ],
  };

  chartOptions: ChartConfiguration<'bar'>['options'] = {
    responsive: true,
    plugins: {
      legend: { display: false },
      title: { display: true, text: 'Cantidad de interacciones por usuario' },
    },
    scales: {
      y: { beginAtZero: true, ticks: { precision: 0 } },
    },
  };

  constructor(private iS: Interaccionservice) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar() {
    this.cargando = true;
    this.iS.cantidadInteraccionesUsuario().subscribe({
      next: (data) => {
        this.reporte = data;
        this.sinDatos = data.length === 0;
        this.chartData = {
          labels: data.map((d) => d.nombre),
          datasets: [
            {
              label: 'Interacciones',
              data: data.map((d) => d.interacciones),
              backgroundColor: '#d32f2f',
              borderRadius: 6,
            },
          ],
        };
        this.cargando = false;
      },
      error: () => {
        this.sinDatos = true;
        this.cargando = false;
      },
    });
  }
}
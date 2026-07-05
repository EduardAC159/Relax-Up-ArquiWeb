import { Component, OnInit } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration, ChartData } from 'chart.js';
import { MatCardModule } from '@angular/material/card';
import { MetaEmocionalservice } from '../../../services/metaemocionalservice';
import { Usuarioservice } from '../../../services/usuarioservice';

@Component({
  selector: 'app-meta-emocional-reporte',
  imports: [BaseChartDirective, MatCardModule],
  templateUrl: './meta-emocional-reporte.html',
  styleUrl: './meta-emocional-reporte.css',
})
export class MetaEmocionalReporte implements OnInit {
  cargando = true;
  totalCompletaron = 0;
  totalUsuarios = 0;

  chartType: ChartConfiguration<'doughnut'>['type'] = 'doughnut';

  chartData: ChartData<'doughnut'> = {
    labels: ['Completaron al menos una meta', 'No completaron ninguna'],
    datasets: [
      {
        data: [0, 0],
        backgroundColor: ['#2e7d32', '#e0e0e0'],
      },
    ],
  };

  chartOptions: ChartConfiguration<'doughnut'>['options'] = {
    responsive: true,
    plugins: {
      title: { display: true, text: 'Usuarios que completaron una Meta Emocional' },
    },
  };

  constructor(
    private meS: MetaEmocionalservice,
    private uS: Usuarioservice,
  ) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar() {
    this.cargando = true;

    this.meS.cantidadMetaEmocionalUsuario().subscribe({
      next: (data) => {
        this.totalCompletaron = data[0]?.cantidad_Meta_Emocional_Usuario ?? 0;
        this.actualizarChart();
      },
    });

    this.uS.list().subscribe({
      next: (usuarios) => {
        this.totalUsuarios = usuarios.length;
        this.actualizarChart();
        this.cargando = false;
      },
      error: () => (this.cargando = false),
    });
  }

  private actualizarChart() {
    const noCompletaron = Math.max(this.totalUsuarios - this.totalCompletaron, 0);
    this.chartData = {
      labels: ['Completaron al menos una meta', 'No completaron ninguna'],
      datasets: [
        {
          data: [this.totalCompletaron, noCompletaron],
          backgroundColor: ['#2e7d32', '#e0e0e0'],
        },
      ],
    };
  }
}
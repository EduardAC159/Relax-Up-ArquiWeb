import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { RecordatorioList } from './recordatorio-list/recordatorio-list';

@Component({
  selector: 'app-recordatoriocomponent',
  imports: [RouterOutlet, RecordatorioList],
  templateUrl: './recordatoriocomponent.html',
  styleUrl: './recordatoriocomponent.css',
})
export class Recordatoriocomponent {
  constructor(public route: ActivatedRoute) {}
}
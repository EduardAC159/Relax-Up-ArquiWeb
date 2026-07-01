import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { EmergenciaList } from './emergencia-list/emergencia-list';


@Component({
  selector: 'app-emergenciacomponent',
  imports: [RouterOutlet, EmergenciaList],
  templateUrl: './emergenciacomponent.html',
  styleUrl: './emergenciacomponent.css',
})
export class Emergenciacomponent {
  constructor(public route:ActivatedRoute) {}
}

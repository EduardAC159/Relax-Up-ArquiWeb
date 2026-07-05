import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { ProgresoList } from './progreso-list/progreso-list';

@Component({
  selector: 'app-progresocomponent',
  imports: [RouterOutlet, ProgresoList],
  templateUrl: './progresocomponent.html',
  styleUrl: './progresocomponent.css',
})
export class Progresocomponent {
  constructor(public route: ActivatedRoute) {}
}
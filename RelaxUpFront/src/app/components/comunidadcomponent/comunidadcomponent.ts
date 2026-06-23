import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { ComunidadList } from './comunidad-list/comunidad-list';


@Component({
  selector: 'app-comunidadcomponent',
  imports: [RouterOutlet, ComunidadList],
  templateUrl: './comunidadcomponent.html',
  styleUrl: './comunidadcomponent.css',
})
export class Comunidadcomponent {
  constructor(public route:ActivatedRoute) {}
}

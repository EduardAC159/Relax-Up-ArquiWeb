import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { RecursosList } from './recursos-list/recursos-list';

@Component({
  selector: 'app-recursoscomponent',
  imports: [RouterOutlet, RecursosList],
  templateUrl: './recursoscomponent.html',
  styleUrl: './recursoscomponent.css',
})
export class Recursoscomponent {
  constructor(public route: ActivatedRoute) {}
}
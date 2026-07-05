import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { InteraccionList } from './interaccion-list/interaccion-list';

@Component({
  selector: 'app-interaccioncomponent',
  imports: [RouterOutlet, InteraccionList],
  templateUrl: './interaccioncomponent.html',
  styleUrl: './interaccioncomponent.css',
})
export class Interaccioncomponent {
  constructor(public route: ActivatedRoute) {}
}
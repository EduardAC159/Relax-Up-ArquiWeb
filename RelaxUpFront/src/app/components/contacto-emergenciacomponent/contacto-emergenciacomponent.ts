import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { ContactoEmergenciaList } from './contacto-emergencia-list/contacto-emergencia-list';


@Component({
  selector: 'app-contacto-emergenciacomponent',
  imports: [RouterOutlet, ContactoEmergenciaList],
  templateUrl: './contacto-emergenciacomponent.html',
  styleUrl: './contacto-emergenciacomponent.css',
})
export class ContactoEmergenciacomponent {
  constructor(public route:ActivatedRoute) {}
}

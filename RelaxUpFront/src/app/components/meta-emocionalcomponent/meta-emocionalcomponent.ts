import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { MetaEmocionalList } from './meta-emocional-list/meta-emocional-list';

@Component({
  selector: 'app-meta-emocionalcomponent',
  imports: [RouterOutlet, MetaEmocionalList],
  templateUrl: './meta-emocionalcomponent.html',
  styleUrl: './meta-emocionalcomponent.css',
})
export class MetaEmocionalcomponent {
  constructor(public route: ActivatedRoute) {}
}
import { Routes } from '@angular/router';
import { Homecomponent } from './components/homecomponent/homecomponent';
import { Comunidadcomponent } from './components/comunidadcomponent/comunidadcomponent';
import { LiteralMapSpreadAssignment } from '@angular/compiler';
import { ComunidadList } from './components/comunidadcomponent/comunidad-list/comunidad-list';
import { ComunidadRegister } from './components/comunidadcomponent/comunidad-register/comunidad-register';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'homes',
    pathMatch: 'full',
  },
  {
    path: 'homes',
    component: Homecomponent,
  },
  {
    path: 'comunidad',
    component:Comunidadcomponent,
    children:[
        {
            path:'lista',
            component:ComunidadList
        },
        {
            path:'news',
            component:ComunidadRegister
        }
    ]
  },
];

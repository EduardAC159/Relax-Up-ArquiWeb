import { Routes } from '@angular/router';
import { Homecomponent } from './components/homecomponent/homecomponent';
import { Comunidadcomponent } from './components/comunidadcomponent/comunidadcomponent';
import { LiteralMapSpreadAssignment } from '@angular/compiler';
import { ComunidadList } from './components/comunidadcomponent/comunidad-list/comunidad-list';
import { ComunidadRegister } from './components/comunidadcomponent/comunidad-register/comunidad-register';
import { Usuariocomponent } from './components/usuariocomponent/usuariocomponent';
import { UsuarioList } from './components/usuariocomponent/usuario-list/usuario-list';
import { UsuarioRegister } from './components/usuariocomponent/usuario-register/usuario-register';
import { Emergenciacomponent } from './components/emergenciacomponent/emergenciacomponent';
import { EmergenciaList } from './components/emergenciacomponent/emergencia-list/emergencia-list';
import { EmergenciaRegister } from './components/emergenciacomponent/emergencia-register/emergencia-register';

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
    component: Comunidadcomponent,
    children: [
      {
        path: 'lista',
        component: ComunidadList,
      },
      {
        path: 'news',
        component: ComunidadRegister,
      },
    ],
  },
  {
    path: 'usuario',
    component: Usuariocomponent,
    children: [
      {
        path: 'lista',
        component: UsuarioList,
      },
      {
        path: 'news',
        component: UsuarioRegister,
      },
    ],
  },
  {
    path: 'emergencia',
    component: Emergenciacomponent,
    children: [
      {
        path: 'lista',
        component: EmergenciaList,
      },
      {
        path: 'news',
        component: EmergenciaRegister,
      },
    ],
  },
];

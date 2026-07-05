import { Routes } from '@angular/router';
import { Homecomponent } from './components/homecomponent/homecomponent';
import { Comunidadcomponent } from './components/comunidadcomponent/comunidadcomponent';
import { ComunidadList } from './components/comunidadcomponent/comunidad-list/comunidad-list';
import { ComunidadRegister } from './components/comunidadcomponent/comunidad-register/comunidad-register';
import { Usuariocomponent } from './components/usuariocomponent/usuariocomponent';
import { UsuarioList } from './components/usuariocomponent/usuario-list/usuario-list';
import { UsuarioRegister } from './components/usuariocomponent/usuario-register/usuario-register';
import { Emergenciacomponent } from './components/emergenciacomponent/emergenciacomponent';
import { EmergenciaList } from './components/emergenciacomponent/emergencia-list/emergencia-list';
import { EmergenciaRegister } from './components/emergenciacomponent/emergencia-register/emergencia-register';
import { ContactoEmergenciaList } from './components/contacto-emergenciacomponent/contacto-emergencia-list/contacto-emergencia-list';
import { ContactoEmergenciacomponent } from './components/contacto-emergenciacomponent/contacto-emergenciacomponent';
import { ContactoEmergenciaRegister } from './components/contacto-emergenciacomponent/contacto-emergencia-register/contacto-emergencia-register';
import { Authenticate } from './components/authenticate/authenticate/authenticate';
import { seguridadGuard } from './guard/seguridad-guard';
import { Interaccioncomponent } from './components/interaccioncomponent/interaccioncomponent';
import { InteraccionList } from './components/interaccioncomponent/interaccion-list/interaccion-list';
import { InteraccionRegister } from './components/interaccioncomponent/interaccion-register/interaccion-register';
import { MetaEmocionalcomponent } from './components/meta-emocionalcomponent/meta-emocionalcomponent';
import { MetaEmocionalList } from './components/meta-emocionalcomponent/meta-emocional-list/meta-emocional-list';
import { MetaEmocionalRegister } from './components/meta-emocionalcomponent/meta-emocional-register/meta-emocional-register';
import { Recordatoriocomponent } from './components/recordatoriocomponent/recordatoriocomponent';
import { RecordatorioList } from './components/recordatoriocomponent/recordatorio-list/recordatorio-list';
import { RecordatorioRegister } from './components/recordatoriocomponent/recordatorio-register/recordatorio-register';
import { Recursoscomponent } from './components/recursoscomponent/recursoscomponent';
import { RecursosList } from './components/recursoscomponent/recursos-list/recursos-list';
import { RecursosRegister } from './components/recursoscomponent/recursos-register/recursos-register';
import { Progresocomponent } from './components/progresocomponent/progresocomponent';
import { ProgresoList } from './components/progresocomponent/progreso-list/progreso-list';
import { ProgresoRegister } from './components/progresocomponent/progreso-register/progreso-register';
import { Register } from './components/authenticate/register/register';
import { InteraccionReporte } from './components/interaccioncomponent/interaccion-reporte/interaccion-reporte';
import { MetaEmocionalReporte } from './components/meta-emocionalcomponent/meta-emocional-reporte/meta-emocional-reporte';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: Authenticate,
  },
  {
    path: 'registro',
    component: Register,
  },
  {
    path: 'homes',
    component: Homecomponent,
    canActivate: [seguridadGuard],
  },
  {
    path: 'comunidad',
    component: Comunidadcomponent,
    canActivate: [seguridadGuard],
    canActivateChild: [seguridadGuard],
    children: [
      {
        path: 'lista',
        component: ComunidadList,
      },
      {
        path: 'news',
        component: ComunidadRegister,
      },
      {
        path: 'editar/:id',
        component: ComunidadRegister,
      },
    ],
  },
  {
    path: 'usuario',
    component: Usuariocomponent,
    canActivate: [seguridadGuard],
    canActivateChild: [seguridadGuard],
    children: [
      {
        path: 'lista',
        component: UsuarioList,
      },
      {
        path: 'news',
        component: UsuarioRegister,
      },
      {
        path: 'editar/:id',
        component: UsuarioRegister,
      },
    ],
  },
  {
    path: 'emergencia',
    component: Emergenciacomponent,
    canActivate: [seguridadGuard],
    canActivateChild: [seguridadGuard],
    children: [
      {
        path: 'lista',
        component: EmergenciaList,
      },
      {
        path: 'news',
        component: EmergenciaRegister,
      },
      {
        path: 'editar/:id',
        component: EmergenciaRegister,
      },
    ],
  },
  {
    path: 'contacto-emergencia',
    component: ContactoEmergenciacomponent,
    canActivate: [seguridadGuard],
    canActivateChild: [seguridadGuard],
    children: [
      {
        path: 'lista/:id',
        component: ContactoEmergenciaList,
      },
      {
        path: 'news/:id',
        component: ContactoEmergenciaRegister,
        data: { mode: 'create' },
      },
      {
        path: 'editar/:id',
        component: ContactoEmergenciaRegister,
        data: { mode: 'edit' },
      },
    ],
  },
  {
    path: 'interaccion',
    component: Interaccioncomponent,
    canActivate: [seguridadGuard],
    canActivateChild: [seguridadGuard],
    children: [
      {
        path: 'lista',
        component: InteraccionList,
      },
      {
        path: 'news',
        component: InteraccionRegister,
      },
      {
        path: 'editar/:id',
        component: InteraccionRegister,
      },
      {
        path: 'reporte',
        component: InteraccionReporte,
      },
    ],
  },
  {
    path: 'meta-emocional',
    component: MetaEmocionalcomponent,
    canActivate: [seguridadGuard],
    canActivateChild: [seguridadGuard],
    children: [
      {
        path: 'lista',
        component: MetaEmocionalList,
      },
      {
        path: 'news',
        component: MetaEmocionalRegister,
      },
      {
        path: 'editar/:id',
        component: MetaEmocionalRegister,
      },
      {
        path: 'reporte',
        component: MetaEmocionalReporte,
      },
    ],
  },
  {
    path: 'recordatorio',
    component: Recordatoriocomponent,
    canActivate: [seguridadGuard],
    canActivateChild: [seguridadGuard],
    children: [
      {
        path: 'lista',
        component: RecordatorioList,
      },
      {
        path: 'news',
        component: RecordatorioRegister,
      },
      {
        path: 'editar/:id',
        component: RecordatorioRegister,
      },
    ],
  },
  {
    path: 'recursos',
    component: Recursoscomponent,
    canActivate: [seguridadGuard],
    canActivateChild: [seguridadGuard],
    children: [
      {
        path: 'lista',
        component: RecursosList,
      },
      {
        path: 'news',
        component: RecursosRegister,
      },
      {
        path: 'editar/:id',
        component: RecursosRegister,
      },
    ],
  },
  {
    path: 'progreso',
    component: Progresocomponent,
    canActivate: [seguridadGuard],
    canActivateChild: [seguridadGuard],
    children: [
      { path: 'lista', component: ProgresoList },
      { path: 'news', component: ProgresoRegister },
      { path: 'editar/:id', component: ProgresoRegister },
    ],
  },
];

// guard/seguridad-guard.ts
import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { Loginservice } from '../services/loginservice';

export const seguridadGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const loginService = inject(Loginservice);

  console.log('🛡️ Verificando acceso a:', state.url);
  
  const isAuthenticated = loginService.verificar();
  console.log('🛡️ ¿Autenticado?', isAuthenticated);
  
  if (isAuthenticated) {
    console.log('✅ Acceso permitido');
    return true;
  }

  console.warn('⚠️ Acceso denegado - redirigiendo al login');
  router.navigate(['/login']);
  return false;
};
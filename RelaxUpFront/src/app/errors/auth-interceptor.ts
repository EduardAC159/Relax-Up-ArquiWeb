// interceptors/auth-interceptor.ts
import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // ✅ Excluir el endpoint de login
  if (req.url.includes('/login')) {
    console.log('🔓 Login endpoint - No se agrega token');
    return next(req);
  }

  const token = localStorage.getItem('token');
  console.log('🔍 Interceptor - URL:', req.url);
  console.log('🔍 Interceptor - Token existe?', !!token);
  
  if (token) {
    // ✅ Asegurar que el token tenga el formato correcto
    const authToken = token.startsWith('Bearer ') ? token : `Bearer ${token}`;
    console.log('🔑 Token enviado:', authToken.substring(0, 30) + '...');
    
    const authReq = req.clone({
      headers: req.headers.set('Authorization', authToken)
    });
    
    return next(authReq);
  }
  
  console.warn('⚠️ No hay token disponible');
  return next(req);
};
// auth.interceptor.ts
import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // ✅ Excluir el endpoint de login
  if (req.url.includes('/login')) {
    console.log('🔓 Login endpoint - No se agrega token');
    return next(req);
  }

  const token = localStorage.getItem('token');
  
  console.log('🔍 AuthInterceptor - URL:', req.url);
  console.log('🔍 AuthInterceptor - Token existe?', !!token);
  
  if (token) {
    const authToken = token.startsWith('Bearer ') ? token : `Bearer ${token}`;
    
    const authReq = req.clone({
      headers: req.headers.set('Authorization', authToken)
    });
    
    console.log('🔑 Token enviado:', authToken.substring(0, 30) + '...');
    return next(authReq);
  }
  
  console.warn('⚠️ No hay token disponible');
  return next(req);
};
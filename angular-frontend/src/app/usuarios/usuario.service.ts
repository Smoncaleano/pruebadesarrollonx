import { Injectable } from '@angular/core';
//import { DatePipe, formatDate } from '@angular/common';
import { Usuario } from './usuario';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import swal from 'sweetalert2';

import { Router } from '@angular/router';

@Injectable()
export class UsuarioService {
  private urlAPI: string = 'http://localhost:8090/apiClientes/clientes';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router: Router) { }

  getUsuarios(): Observable<Usuario[]> {
    return this.http.get(this.urlAPI).pipe(
      tap(response => {
        let clientes = response as Usuario[];
        console.log('ClienteService: tap 1');
        clientes.forEach(cliente => {
          console.log(cliente.nombre);
        });
      }),
      map(response => {
        let clientes = response as Usuario[];
        return clientes.map(cliente => {
          cliente.nombre = cliente.nombre.toUpperCase();
          //let datePipe = new DatePipe('es');
          //cliente.createAt = datePipe.transform(cliente.createAt, 'EEEE dd, MMMM yyyy');
          //cliente.createAt = formatDate(cliente.createAt, 'dd-MM-yyyy', 'es');
          return cliente;
        });
      }
      ),
      tap(response => {
        console.log('ClienteService: tap 2');
        response.forEach(cliente => {
          console.log(cliente.nombre);
        });
      })
    );
  }

  create(usuario: Usuario): Observable<Usuario> {
    return this.http.post(this.urlAPI, usuario, { headers: this.httpHeaders }).pipe(
      map((response: any) => response.usuario as Usuario),
      catchError(e => {

        if (e.status == 400) {
          return throwError(e);
        }

        console.error(e.error.mensaje);
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  getUsuario(id): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.urlAPI}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/clientes']);
        console.error(e.error.mensaje);
        swal('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  update(cliente: Usuario): Observable<any> {
    return this.http.put<any>(`${this.urlAPI}/${cliente.id}`, cliente, { headers: this.httpHeaders }).pipe(
      catchError(e => {

        if (e.status == 400) {
          return throwError(e);
        }

        console.error(e.error.mensaje);
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

  delete(id: number): Observable<Usuario> {
    return this.http.delete<Usuario>(`${this.urlAPI}/${id}`, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

}

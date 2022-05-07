import { Injectable } from '@angular/core';
//import { DatePipe, formatDate } from '@angular/common';
import { Cargo } from './cargo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import swal from 'sweetalert2';

import { Router } from '@angular/router';

@Injectable()
export class CargoService {
  private urlAPI: string = 'http://localhost:8090/apiCargos/cargos';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router: Router) { }

  getCargos(): Observable<Cargo[]> {
    return this.http.get(this.urlAPI).pipe(
      tap(response => {
        let cargos = response as Cargo[];
        console.log('ClienteService: tap 1');
        cargos.forEach(cargo => {
          console.log(cargo.nombre);
        });
      }),
      map(response => {
        let cargos = response as Cargo[];
        return cargos.map(cargo => {
          cargo.nombre = cargo.nombre.toUpperCase();
          //let datePipe = new DatePipe('es');
          //cliente.createAt = datePipe.transform(cliente.createAt, 'EEEE dd, MMMM yyyy');
          //cliente.createAt = formatDate(cliente.createAt, 'dd-MM-yyyy', 'es');
          return cargo;
        });
      }
      ),
      tap(response => {
        console.log('ClienteService: tap 2');
        response.forEach(cargo => {
          console.log(cargo.nombre);
        });
      })
    );
  }

  create(cargo: Cargo): Observable<Cargo> {
    return this.http.post(this.urlAPI, cargo, { headers: this.httpHeaders }).pipe(
      map((response: any) => response.usuario as Cargo),
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

  getCargo(id): Observable<Cargo> {
    return this.http.get<Cargo>(`${this.urlAPI}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/cargos']);
        console.error(e.error.mensaje);
        swal('Error al crear', 'por favor');
        return throwError(e);
      })
    );
  }

  update(cargo: Cargo): Observable<any> {
    return this.http.put<any>(`${this.urlAPI}/${cargo.id}`, cargo, { headers: this.httpHeaders }).pipe(
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

  delete(id: number): Observable<Cargo> {
    return this.http.delete<Cargo>(`${this.urlAPI}/${id}`, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

}

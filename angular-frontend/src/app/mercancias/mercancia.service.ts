import { Injectable } from '@angular/core';
//import { DatePipe, formatDate } from '@angular/common';
import { Mercancia } from './mercancia';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import swal from 'sweetalert2';

import { Router } from '@angular/router';

@Injectable()
export class MercanciaService {
  private urlAPI: string = 'http://localhost:8090/apiMercancia/mercancias';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router: Router) { }

  getUsuarios(): Observable<Mercancia[]> {
    return this.http.get(this.urlAPI).pipe(
      tap(response => {
        let clientes = response as Mercancia[];
        console.log('ClienteService: tap 1');
        clientes.forEach(cliente => {
          console.log(cliente.nombre);
        });
      }),
      map(response => {
        let clientes = response as Mercancia[];
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

  create(mercancia: Mercancia, fkUser: number): Observable<Mercancia> {
    return this.http.post(this.urlAPI+"/"+fkUser, mercancia, { headers: this.httpHeaders }).pipe(
      map((response: any) => response.mercancia as Mercancia),
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

  getUsuario(id): Observable<Mercancia> {
    return this.http.get<Mercancia>(`${this.urlAPI+"Id"}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/mercancias']);
        console.error(e.error.mensaje);
        swal('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  getMercanciaByDate(fecha: Date): Observable<Mercancia[]> {
    return this.http.get(`${ 'http://localhost:8090/apiMercancia/mercanciasDate'}/${fecha}`).pipe(
      catchError(e => {
        this.router.navigate(['/mercancias']);
        console.error(e.error.mensaje);
        swal('Busqueda sin resultados', e.error.mensaje, 'error');
        return throwError(e);
      }),
      tap(response => {
        let clientes = response as Mercancia[];
        console.log('ClienteService: tap 1');
        clientes.forEach(cliente => {
          console.log(cliente.nombre);
        });
      }),
      map(response => {
        let clientes = response as Mercancia[];
        return clientes.map(cliente => {
          cliente.nombre = cliente.nombre;
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

  getMercanciaByUser(fkUser: number): Observable<Mercancia[]> {
    return this.http.get(`${ 'http://localhost:8090/apiMercancia/mercanciasUser'}/${fkUser}`).pipe(
      catchError(e => {
        this.router.navigate(['/mercancias']);
        console.error(e.error.mensaje);
        swal('Busqueda sin resultados', e.error.mensaje, 'error');
        return throwError(e);
      }),
      tap(response => {
        let clientes = response as Mercancia[];
        console.log('ClienteService: tap 1');
        clientes.forEach(cliente => {
          console.log(cliente.nombre);
        });
      }),
      map(response => {
        let clientes = response as Mercancia[];
        return clientes.map(cliente => {
          cliente.nombre = cliente.nombre;
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


  getMercanciaByName(name: String): Observable<Mercancia[]> {
    return this.http.get(this.urlAPI+"/"+name).pipe(
      catchError(e => {
        this.router.navigate(['/mercancias']);
        console.error(e.error.mensaje);
        swal('Busqueda sin resultados', e.error.mensaje, 'error');
        return throwError(e);
      }),
      tap(response => {
        let clientes = response as Mercancia[];
        console.log('ClienteService: tap 1');
        clientes.forEach(cliente => {
          console.log(cliente.nombre);
        });
      }),
      map(response => {
        let clientes = response as Mercancia[];
        return clientes.map(cliente => {
          cliente.nombre = cliente.nombre;
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

  update(mercancia: Mercancia): Observable<any> {
    return this.http.put<any>(`${this.urlAPI}/${mercancia.id}`, mercancia, { headers: this.httpHeaders }).pipe(
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

  delete(id: number, fkUser: number): Observable<Mercancia> {
    return this.http.delete<Mercancia>(`${this.urlAPI}/${id}/${fkUser}`, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        swal(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }

}

import { Component, OnInit } from '@angular/core';
import { Mercancia } from './mercancia';
import { MercanciaService } from './mercancia.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2/dist/sweetalert2.js';  
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-mercancias',
  templateUrl: './mercancias.component.html'
})
export class MercanciasComponent implements OnInit {

  mercancias: Mercancia[];
  nameToFind: String;
  userToFind: String;
  dateToFind: Date;
  constructor(private mercanciaService: MercanciaService, private router: Router) { }

  ngOnInit() {
    if(this.nameToFind == undefined){
      this.cargarMercancias();
    }
   
  }

  cargarMercancias(): void{
    this.mercanciaService.getUsuarios().pipe(
      tap(mercancias => {
        console.log('ClientesComponent: tap 3');
        mercancias.forEach(usuarios => {
          console.log(usuarios.nombre);
        });
      })
    ).subscribe(mercancias => this.mercancias = mercancias);
    
  }

  findByName(name: String): void{
    this.mercanciaService.getMercanciaByName(name).pipe(
      
      tap(mercancias => {
        
        console.log('ClientesComponent: tap 3');
        mercancias.forEach(usuarios => {
          console.log(usuarios.nombre);
        });
      })
    ).subscribe(mercancias => this.mercancias = mercancias);
    
  }

  findByDate(fecha: Date): void{
    this.mercanciaService.getMercanciaByDate(fecha).pipe(
      
      tap(mercancias => {
        
        console.log('ClientesComponent: tap 3');
        mercancias.forEach(usuarios => {
          console.log(usuarios.nombre);
        });
      })
    ).subscribe(mercancias => this.mercancias = mercancias);
    
  }
  
  findByUser(fkUser: number): void{
    this.mercanciaService.getMercanciaByUser(fkUser).pipe(
      
      tap(mercancias => {
        
        console.log('ClientesComponent: tap 3');
        mercancias.forEach(usuarios => {
          console.log(usuarios.nombre);
        });
      })
    ).subscribe(mercancias => this.mercancias = mercancias);
    
  }

  delete(mercancia: Mercancia): void {
    Swal.fire({
      title: `¿Seguro que desea eliminar la mercancía ${mercancia.nombre}?`,
  
      html: `<input type="number" id="login" class="swal2-input" placeholder="id de usuario">
      <p>Por favor ingrese el id del usuario creador de la mercancia</p>`,
      confirmButtonText: 'Aceptar',
      focusConfirm: false,
      preConfirm: () => {
        const login = Swal.getPopup().querySelector('#login').value
        
        if (!login ) {
          Swal.showValidationMessage(`Por favor ingrese el id del usuario creador de la mercancia`)
        }
        return { login: login }
      }
    }).then((result) => {
      if (result.value) {
      
        this.mercanciaService.delete(mercancia.id, result.value.login).subscribe(
          () => {
            this.mercancias = this.mercancias.filter(cli => cli !== mercancia)
            Swal(
              'Mercancía Eliminada!',
              `Mercancía ${mercancia.nombre} eliminado con éxito.`,
              'success'
            )
          }
        )

      }
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import swal from 'sweetalert2';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html'
})
export class UsuariosComponent implements OnInit {

  usuarios: Usuario[];

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit() {
    this.usuarioService.getUsuarios().pipe(
      tap(usuarios => {
        console.log('ClientesComponent: tap 3');
        usuarios.forEach(usuarios => {
          console.log(usuarios.nombre);
        });
      })
    ).subscribe(usuarios => this.usuarios = usuarios);
  }

  delete(usuario: Usuario): void {
    swal({
      title: 'Está seguro?',
      text: `¿Seguro que desea eliminar al cliente ${usuario.nombre} ${usuario.apellido}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: false,
      reverseButtons: true
    }).then((result) => {
      if (result.value) {

        this.usuarioService.delete(usuario.id).subscribe(
          () => {
            this.usuarios = this.usuarios.filter(cli => cli !== usuario)
            swal(
              'Cliente Eliminado!',
              `Cliente ${usuario.nombre} eliminado con éxito.`,
              'success'
            )
          }
        )

      }
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { Cargo } from '../cargos/cargo';
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { CargoService } from '../cargos/cargo.service';

import { tap } from 'rxjs/operators';
import { NgForm } from '@angular/forms';
import { resolve } from 'url';
@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

   usuario: Usuario = new Usuario();
  titulo: string = "Crear Usuario";
  cargos: Cargo[];
  errores: string[];
  selectedCargo: number;



  constructor(private usuarioService: UsuarioService, private cargoService: CargoService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.cargarCargos();
    this.cargarUsuario();
    
      }

  cargarCargos(): void{
    this.cargoService.getCargos().pipe().subscribe(cargos => this.cargos = cargos);
  }

  onCargoSelected(){
    console.log("se activa" + this.selectedCargo);    
  }



  cargarUsuario(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this.usuarioService.getUsuario(id).subscribe((cliente) => this.usuario = cliente);
      }
    })
  }

  create(): void {
    console.log(this.selectedCargo);
  
    this.cargoService.getCargo(this.selectedCargo).pipe().subscribe((cargof) => {

      this.usuario.cargo = cargof
      console.log(this.usuario.cargo)
      this.usuarioService.create(this.usuario)
      .subscribe(
        cliente => {
          swal('Nuevo cliente', `El cliente ${this.usuario.nombre} ha sido creado con éxito`, 'success');
          this.router.navigate(['/usuarios']);
          
        },
        err => {
          this.errores = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
    } );
  
  
  })

    
  }

  update(): void {
    this.cargoService.getCargo(this.selectedCargo).pipe().subscribe((cargof) => {
      this.usuario.cargo = cargof
      this.usuarioService.update(this.usuario)
      .subscribe(
        json => {
          
          this.router.navigate(['/usuarios']);
          swal('Cliente Actualizado', `${json.mensaje}: ${json.cliente.nombre}`, 'success');
          
        },
        err => {
          this.errores = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }
      )
    })
    
  }

}

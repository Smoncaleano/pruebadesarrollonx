import { Component, OnInit } from '@angular/core';
import { Mercancia } from './mercancia';
import { MercanciaService } from './mercancia.service';
import { UsuarioService } from '../usuarios/usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2'

import { Usuario } from '../usuarios/usuario';
@Component({
  selector: 'app-form',
  templateUrl: './MercanciaForm.component.html'
})
export class MercanciaFormComponent implements OnInit {

   mercancia: Mercancia = new Mercancia();
   user: Usuario = new Usuario();
  titulo: String = "Crear mercancia";
  usuarios: Usuario[];
  errores: string[];
  selectedUser: number;
 

  

  constructor(private mercanciaService: MercanciaService, private usuarioService:UsuarioService, 
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.cargarUsuarios();
    this.cargarUsuario();
    
      }

  cargarUsuarios(): void{
    this.usuarioService.getUsuarios().pipe().subscribe(usuarios => this.usuarios = usuarios);
  }

  onCargoSelected(){
    console.log("se activa" + this.selectedUser);    
  }



  cargarUsuario(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this.mercanciaService.getUsuario(id).subscribe((mercancia) => this.mercancia = mercancia);
      }
    })
  }

  create(): void {
    console.log(this.selectedUser);
  
    this.usuarioService.getUsuario(this.selectedUser).pipe().subscribe((cargof) => {

      this.mercancia.usuario = cargof
      console.log(this.mercancia.usuario)
      this.mercanciaService.create(this.mercancia, this.selectedUser)
      .subscribe(
        cliente => {
          Swal('Nueva mercancía', `La mercancía ${this.mercancia.nombre} ha sido creado con éxito`, 'success');
          this.router.navigate(['/mercancias']);
          
        },
        err => {
          this.errores = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
    } );
  
  
  })

    
  }

  update(): void {
   
    this.usuarioService.getUsuario(this.selectedUser).pipe().subscribe((usuariof) => {
      
      this.mercancia.usuario = usuariof;
      console.log(this.mercancia.usuario);
      this.mercanciaService.update(this.mercancia)
      .subscribe(
        json => {
          
          this.router.navigate(['/mercancias']);
          Swal('Mercancía Actualizada', `${json.mensaje}: ${json.cliente.nombre}`, 'success');
          
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

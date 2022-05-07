import { Component, OnInit } from '@angular/core';
import { Cargo } from './cargo';
import { CargoService } from './cargo.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './Cargoform.component.html'
})
export class CargoFormComponent implements OnInit {

  private cargo: Cargo = new Cargo();
  titulo: string = "Crear cargo";

  errores: string[];

  constructor(private cargoService: CargoService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.cargarUsuario();
  }

  cargarUsuario(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this.cargoService.getCargo(id).subscribe((cargo) => this.cargo = cargo);
      }
    })
  }

  create(): void {
    this.cargoService.create(this.cargo)
      .subscribe(
        cargo => {
          swal('Nuevo cargo', `El cargo ${this.cargo.nombre} ha sido creado con éxito`, 'success');

          this.router.navigate(['/cargos']);
        },
        err => {
          this.errores = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }
      );
  }

  update(): void {
    this.cargoService.update(this.cargo)
      .subscribe(
        json => {
          swal('Actualizado', `El cargo ${this.cargo.nombre} ha sido actualizado`, 'success');
          this.router.navigate(['/cargos']);
          
        },
        err => {
          this.errores = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }
      )
  }

}

import { Component, OnInit } from '@angular/core';
import { Cargo } from './cargo';
import { CargoService } from './cargo.service';
import swal from 'sweetalert2';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-cargos',
  templateUrl: './cargos.component.html'
})
export class CargoComponent implements OnInit {

  cargos: Cargo[];

  constructor(private cargoService: CargoService) { }

  ngOnInit() {
    this.cargoService.getCargos().pipe(
      tap(cargos => {
        console.log('ClientesComponent: tap 3');
        cargos.forEach(cargos => {
          console.log(cargos.nombre);
        });
      })
    ).subscribe(cargos => this.cargos = cargos);
  }


}

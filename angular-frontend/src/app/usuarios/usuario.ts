import {Cargo} from '../cargos/cargo'; 
export class Usuario {
  id: number;
  nombre: string;
  apellido:string;
  createAt:Date;
  email: string;
  cargo: Cargo;
}

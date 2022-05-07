import {Usuario} from '../usuarios/usuario'; 
export class Mercancia {
  id: number;
  nombre: string;
  cantidad:number;
  createAt:Date;
  usuario: Usuario;
}

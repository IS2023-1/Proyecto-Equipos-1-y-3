import { Role } from './roles';

export class Usuario {

    id_usuario: number;

    username: string;

    cuenta: number;

    nombre: string;

    apellidoPaterno: string;

    apellidoMaterno: string;

    numeroCel: number;

    correo: string;

    carrera: string;

    password: string;

    esActivo: boolean;

    pumapuntos: number;

    roles: string[];
}

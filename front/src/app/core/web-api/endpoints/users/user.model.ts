import {Role} from './roles/role.model';

export interface User {

  id: number;

  role: Role;

  name: string;

  username: string;

  password: string;

  createdAt: Date;

}

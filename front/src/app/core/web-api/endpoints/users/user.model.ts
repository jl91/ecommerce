import {Role} from './roles/role.model';
import {Resource} from '../../model/resource/resource.model';
import {Serializer} from '../../model/serializer/serializer.model';
import {ClassTransformer} from 'class-transformer';

export class User implements Resource, Serializer<User> {

  id: number;

  role: Role;

  name: string;

  username: string;

  password: string;

  createdAt: Date;

  public toJson(data): any {
    const classTransformer = new ClassTransformer();
    return classTransformer.classToPlain(data);
  }

  public toModel(data: object): User {
    const classTransformer = new ClassTransformer();
    return classTransformer.plainToClass(User, data);
  }

}

import {Resource} from '../../model/resource/resource.model';
import {Serializer} from '../../model/serializer/serializer.model';
import {ClassTransformer} from 'class-transformer';

export class Product implements Resource, Serializer<Product> {

  public id?: number;

  public sku: string;

  public name: string;

  public description: string;

  public value: number;

  public createdAt?: string;

  public toJson(data): any {
    const classTransformer = new ClassTransformer();
    return classTransformer.classToPlain(data);
  }

  public toModel(data: object): Product {
    const classTransformer = new ClassTransformer();
    return classTransformer.plainToClass(Product, data);
  }

}

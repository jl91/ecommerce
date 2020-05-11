import {Resource} from '../../model/resource/resource.model';
import {Serializer} from '../../model/serializer/serializer.model';

export class Product implements Resource, Serializer<Product> {

  public id?: number;

  public sku: string;

  public name: string;

  public description: string;

  public value: number;

  public createdAt?: string;

  toModel(data: any): Product {
    return undefined;
  }

  toJson(data): any {
    const keys = Object.keys(this);
    const json = {};

    keys.forEach((key: string) => {
      json[key] = this[key];
    });

    return json;
  }

}

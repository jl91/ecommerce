import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Product} from './product.model';
import {BaseRepositoryService} from '../../repository/base-repository.service';
import {WebApiConfiguration} from '../../model/configuration/web-api.configuration';

@Injectable()
export class ProductsHttpService extends BaseRepositoryService<Product> {

  constructor(
    protected httpClient: HttpClient,
    public webApiConfiguration: WebApiConfiguration
  ) {
    super(
      httpClient,
      webApiConfiguration.basePath,
      'products',
      new Product()
    );
  }
}

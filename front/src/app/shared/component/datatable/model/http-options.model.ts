import {Repository} from '../../../../core/web-api/model/repository/repository.model';

export interface HttpOptions {
  service: Repository<any>;
  fetchAllMethod: string;
}

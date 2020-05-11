export interface Serializer<T> {
  toModel(data: any): T;
  toJson(data): any;
}

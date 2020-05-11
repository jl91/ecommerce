export interface Serializer<T> {
  toModel(data: object): T;
  toJson(data): any;
}

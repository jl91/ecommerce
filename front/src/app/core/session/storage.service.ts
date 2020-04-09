import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {StorageTypeEnum} from './storage-type.enum';

@Injectable()
export class StorageService implements Storage {

  private namespace: string;
  private storage: Storage = null;
  private storageType: StorageTypeEnum;
  private _storageSubject: Subject<boolean> = new Subject<boolean>();

  constructor() {
    this.setStorageType(StorageTypeEnum.LOCAL_STORAGE);
  }

  public get length(): number {
    return this.storage.length;
  }

  public setStorageType(storageType: StorageTypeEnum): StorageService {
    this.storageType = storageType;

    this.storage = (
      storageType === StorageTypeEnum.LOCAL_STORAGE
        ? localStorage
        : sessionStorage
    );

    return this;
  }

  public storageObservable(): Observable<boolean> {
    return this._storageSubject.asObservable();
  }

  public setItem(key: string, value: any): StorageService {
    const namespacedKey = this.getKey(key);

    if (typeof value === 'object') {
      this.storage.setItem(namespacedKey, JSON.stringify(value));
      return this;
    }

    this.storage.setItem(namespacedKey, `${value}`);
    return this;
  }

  public getItem(key: string): any {

    if (!this.hasItem(key)) {
      return undefined;
    }

    const namespacedKey = this.getKey(key);
    const data = this.storage.getItem(namespacedKey);

    if (this.isJson(data)) {
      return JSON.parse(data);
    }

    return data;
  }

  public removeItem(key: string): StorageService {

    if (!this.hasItem(key)) {
      return this;
    }

    const namespacedKey = this.getKey(key);

    this.storage.removeItem(namespacedKey);
    return this;
  }

  public hasItem(key: string): boolean {
    const namespacedKey = this.getKey(key);
    return this.storage.getItem(namespacedKey) !== null;
  }

  public clear(): StorageService {
    this.storage.clear();
    return this;
  }

  public logout(): StorageService {
    sessionStorage.clear();
    localStorage.clear();
    return this;
  }

  public clearNamespace(namespace: string): StorageService {
    const keyNamespace = this.getNamespace(namespace);
    for (let i = 0; i < this.storage.length; i++) {
      const key = this.storage.key(i);
      if (key.indexOf(keyNamespace) !== -1) {
        this.removeItem(key);
      }
    }

    return this;
  }

  public setNamespace(namespace: string): StorageService {
    this.namespace = namespace;
    return this;
  }

  public notify(): void {
    this._storageSubject.next(true);
  }

  public key(index: number): string | null {
    return this.storage.key(index);
  }

  private isJson(data: string | object): boolean {

    if (typeof data === 'object') {
      return true;
    }

    try {
      JSON.parse(data);
    } catch (e) {
      return false;
    }
    return true;
  }

  private getKey(key: string): string {
    return `${this.getNamespace(this.namespace)}.${key}`;
  }

  private getNamespace(namespace: string): string {
    return `${this.storageType}-${namespace}`;
  }

}

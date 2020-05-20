import {OverlayContainer} from '@angular/cdk/overlay';
import {Injectable} from '@angular/core';

@Injectable()
export class DynamicOverlayContainerService extends OverlayContainer {

  public setContainerElement(element: HTMLElement): void {
    this._containerElement = element;
  }

}

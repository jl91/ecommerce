import {
  Overlay,
  OverlayKeyboardDispatcher,
  OverlayPositionBuilder,
  ScrollStrategyOptions,
  OverlayRef
} from '@angular/cdk/overlay';

import {
  ComponentFactoryResolver,
  Inject,
  Injector,
  NgZone,
  Renderer2,
  RendererFactory2,
  Injectable
} from '@angular/core';

import {DynamicOverlayContainerService} from './dynamic-overlay-container.service';

import {Directionality} from '@angular/cdk/bidi';

import {DOCUMENT} from '@angular/common';

@Injectable()
export class DynamicOverlayService extends Overlay {

  private dynamicOverlayContainer: DynamicOverlayContainerService;

  private renderer: Renderer2;

  constructor(
    scrollStrategies: ScrollStrategyOptions,
    overlayContainer: DynamicOverlayContainerService,
    componentFactoryResolver: ComponentFactoryResolver,
    positionBuilder: OverlayPositionBuilder,
    keyboardDispatcher: OverlayKeyboardDispatcher,
    injector: Injector,
    ngZone: NgZone,
    @Inject(DOCUMENT) document: any,
    directionality: Directionality,
    rendererFactory: RendererFactory2
  ) {
    super(
      scrollStrategies,
      overlayContainer,
      componentFactoryResolver,
      positionBuilder,
      keyboardDispatcher,
      injector,
      ngZone,
      document,
      directionality
    );

    this.renderer = rendererFactory.createRenderer(null, null);
    this.dynamicOverlayContainer = overlayContainer;
  }

  private setContainerElement(containerElement: HTMLElement): void {
    this.renderer.setStyle(containerElement, 'transform', 'translateZ(0)');
    this.dynamicOverlayContainer.setContainerElement(containerElement);
  }

  public createWithDefaultConfig(containerElement: HTMLElement): OverlayRef {
    this.setContainerElement(containerElement);
    return super.create({
      positionStrategy: this.position()
        .global()
        .centerHorizontally()
        .centerVertically(),
      hasBackdrop: true
    });
  }
}

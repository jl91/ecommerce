import {Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ComponentPortal} from '@angular/cdk/portal';
import {LoaderContentComponent} from './component/loader-content.component';
import {DynamicOverlayService} from './overlay/dynamic-overlay.service';
import {DynamicOverlayContainerService} from './overlay/dynamic-overlay-container.service';
import {OverlayRef} from '@angular/cdk/overlay';

@Component({
  selector: 'app-loader',
  templateUrl: 'loader.component.html',
  providers: [
    DynamicOverlayService,
    DynamicOverlayContainerService
  ]
})
export class LoaderComponent implements OnInit, OnChanges {

  @Input()
  private connectedTo: ElementRef;

  @Input()
  private isLoading = false;

  private overlayRef: OverlayRef;

  constructor(private dynamicOverlayService: DynamicOverlayService) {
  }

  public ngOnInit() {
  }

  public openLoader(): void {
    const portal = new ComponentPortal(LoaderContentComponent);
    this.overlayRef = this.dynamicOverlayService.createWithDefaultConfig(this.connectedTo.nativeElement);
    this.overlayRef.attach(portal);
  }

  public closeLoader(): void {
    this.overlayRef?.detach();
  }

  public ngOnChanges(changes: SimpleChanges): void {

    if (!changes?.isLoading) {
      return;
    }

    if (this.isLoading) {
      return this.openLoader();
    }

    this.closeLoader();
  }

}

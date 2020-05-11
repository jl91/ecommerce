import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ProductsListComponent} from './products-list.component';
import {ProductsHttpService} from '../../../../core/web-api/endpoints/products/products-http.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('ProductsListComponent', () => {
  let component: ProductsListComponent;
  let fixture: ComponentFixture<ProductsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        ProductsHttpService
      ],
      declarations: [
        ProductsListComponent
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

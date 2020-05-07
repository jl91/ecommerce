import {async, TestBed} from '@angular/core/testing';
import {PrintHeaderPipe} from './print-header.pipe';

describe('PrintHeaderPipe', () => {
  let pipe: PrintHeaderPipe;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        PrintHeaderPipe
      ]
    })
      .compileComponents();
  }));

  beforeAll(() => {
    pipe = new PrintHeaderPipe();
  });

  it('should create', () => {
    expect(pipe).toBeTruthy();
  });

  it('should test if pipe return null when there is no column', () => {
    const expected = pipe.transform('header', []);
    expect(expected).toBeNull();
  });


});

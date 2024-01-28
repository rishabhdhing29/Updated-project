import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusOperatorLoginComponent } from './bus-operator-login.component';

describe('BusOperatorLoginComponent', () => {
  let component: BusOperatorLoginComponent;
  let fixture: ComponentFixture<BusOperatorLoginComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BusOperatorLoginComponent]
    });
    fixture = TestBed.createComponent(BusOperatorLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

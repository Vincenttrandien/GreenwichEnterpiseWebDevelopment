import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MmLayoutComponent } from './mm-layout.component';

describe('MmLayoutComponent', () => {
  let component: MmLayoutComponent;
  let fixture: ComponentFixture<MmLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MmLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MmLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

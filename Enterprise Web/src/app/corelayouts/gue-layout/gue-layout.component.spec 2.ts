import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GueLayoutComponent } from './gue-layout.component';

describe('GueLayoutComponent', () => {
  let component: GueLayoutComponent;
  let fixture: ComponentFixture<GueLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GueLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GueLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

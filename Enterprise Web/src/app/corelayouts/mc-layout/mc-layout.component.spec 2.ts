import { ComponentFixture, TestBed } from '@angular/core/testing';

import { McLayoutComponent } from './mc-layout.component';

describe('McLayoutComponent', () => {
  let component: McLayoutComponent;
  let fixture: ComponentFixture<McLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ McLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(McLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GueDashboardComponent } from './gue-dashboard.component';

describe('GueDashboardComponent', () => {
  let component: GueDashboardComponent;
  let fixture: ComponentFixture<GueDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GueDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GueDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StuLayoutComponent } from './stu-layout.component';

describe('StuLayoutComponent', () => {
  let component: StuLayoutComponent;
  let fixture: ComponentFixture<StuLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StuLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StuLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

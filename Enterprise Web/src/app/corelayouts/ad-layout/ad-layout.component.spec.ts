import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdLayoutComponent } from './ad-layout.component';

describe('AdLayoutComponent', () => {
  let component: AdLayoutComponent;
  let fixture: ComponentFixture<AdLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StdContributionlistComponent } from './std-contributionlist.component';

describe('StdContributionlistComponent', () => {
  let component: StdContributionlistComponent;
  let fixture: ComponentFixture<StdContributionlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StdContributionlistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StdContributionlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

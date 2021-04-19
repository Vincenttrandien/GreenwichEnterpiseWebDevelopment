import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StdChatComponent } from './std-chat.component';

describe('StdChatComponent', () => {
  let component: StdChatComponent;
  let fixture: ComponentFixture<StdChatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StdChatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StdChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

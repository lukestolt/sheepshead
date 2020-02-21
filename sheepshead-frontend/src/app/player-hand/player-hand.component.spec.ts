import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerHand } from './player-hand.component';

describe('PlayerHandComponent', () => {
  let component: PlayerHand;
  let fixture: ComponentFixture<PlayerHand>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayerHand ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayerHand);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

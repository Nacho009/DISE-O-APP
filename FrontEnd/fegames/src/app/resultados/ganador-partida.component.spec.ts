import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GanadorPartidaComponent } from './ganador-partida.component';

describe('GanadorPartidaComponent', () => {
  let component: GanadorPartidaComponent;
  let fixture: ComponentFixture<GanadorPartidaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GanadorPartidaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GanadorPartidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

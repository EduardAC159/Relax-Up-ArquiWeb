import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComunidadList } from './comunidad-list';

describe('ComunidadList', () => {
  let component: ComunidadList;
  let fixture: ComponentFixture<ComunidadList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComunidadList],
    }).compileComponents();

    fixture = TestBed.createComponent(ComunidadList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

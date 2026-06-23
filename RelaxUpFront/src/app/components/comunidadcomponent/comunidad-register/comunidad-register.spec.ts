import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComunidadRegister } from './comunidad-register';

describe('ComunidadRegister', () => {
  let component: ComunidadRegister;
  let fixture: ComponentFixture<ComunidadRegister>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComunidadRegister],
    }).compileComponents();

    fixture = TestBed.createComponent(ComunidadRegister);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { Injectable } from '@angular/core';
import {Router} from '@angular/router';

@Injectable()
export class UIContext {


  public title: string;
  public showToolbar: boolean;

  constructor(private router: Router) {
    this.setDefaultContext();
  }

  public setDefaultContext() {
    this.title = 'Reclamos';
    this.showToolbar = true;
  }

  public setShowToolbar(value) {
    this.showToolbar = value;
  }

  public setTittle(tittle) {
    this.title = tittle;
  }
}

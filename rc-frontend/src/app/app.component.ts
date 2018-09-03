import { Component, ComponentFactoryResolver } from '@angular/core';
import {UIContext} from "./ui.context";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(public uiContext: UIContext) { }
}

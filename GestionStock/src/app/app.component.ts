import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ColorModesComponent } from './composants/color-modes/color-modes.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ColorModesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'GestionStock';
}

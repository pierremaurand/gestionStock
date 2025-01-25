import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuComponent } from '../../composants/menu/menu.component';
import { HeaderComponent } from '../../composants/header/header.component';
import { FooterComponent } from '../../composants/footer/footer.component';

@Component({
  selector: 'app-main',
  imports: [RouterOutlet, MenuComponent, HeaderComponent, FooterComponent],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {

}

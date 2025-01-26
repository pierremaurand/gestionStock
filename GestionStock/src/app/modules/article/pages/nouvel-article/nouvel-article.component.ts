import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nouvel-article',
  imports: [],
  templateUrl: './nouvel-article.component.html',
  styleUrl: './nouvel-article.component.scss'
})
export class NouvelArticleComponent {

  constructor(private router: Router){}


  onCancel(): void {
    this.router.navigate(['home/articles']);
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-details-client-fournisseur',
  imports: [],
  templateUrl: './details-client-fournisseur.component.html',
  styleUrl: './details-client-fournisseur.component.scss'
})
export class DetailsClientFournisseurComponent implements OnInit{

  origin = '';

  constructor(private activateRoute: ActivatedRoute) {

  }
  ngOnInit(): void {
    this.activateRoute.data.subscribe( data => {
      this.origin = data['origin'];
    });
  }

}

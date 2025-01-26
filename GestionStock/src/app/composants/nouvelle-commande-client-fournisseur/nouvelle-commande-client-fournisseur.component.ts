import { Component, OnInit } from '@angular/core';
import { DetailsLigneCommandeClientFournisseurComponent } from "../details-ligne-commande-client-fournisseur/details-ligne-commande-client-fournisseur.component";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-nouvelle-commande-client-fournisseur',
  imports: [DetailsLigneCommandeClientFournisseurComponent],
  templateUrl: './nouvelle-commande-client-fournisseur.component.html',
  styleUrl: './nouvelle-commande-client-fournisseur.component.scss'
})
export class NouvelleCommandeClientFournisseurComponent implements OnInit{
  origin = '';

  constructor(private activatedRoute: ActivatedRoute){}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.origin = data['origin'];
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-nouveau-client-fournisseur',
  imports: [],
  templateUrl: './nouveau-client-fournisseur.component.html',
  styleUrl: './nouveau-client-fournisseur.component.scss'
})
export class NouveauClientFournisseurComponent implements OnInit{

  origin = '';

  constructor(private activatedRoute: ActivatedRoute, private router: Router){}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe( data => {
      this.origin = data['origin'];
    })
  }

  onCancel(): void {
    if(this.origin == 'client'){
      this.router.navigate(['home/clients']);
    } else {
      this.router.navigate(['home/fournisseurs']);
    }
  }

}

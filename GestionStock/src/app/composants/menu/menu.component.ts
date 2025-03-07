import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgFor } from '@angular/common';
import { Menu } from './menu';

@Component({
  selector: 'app-menu',
  imports: [NgFor],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss',
})
export class MenuComponent {
  menuProperties: Array<Menu> = [
    {
      id: '1',
      titre: 'Tableau de bord',
      icon: 'fa-solid fa-gauge',
      url: '',
      sousMenu: [
        {
          id: '11',
          titre: "Vue d'ensemble",
          icon: 'fa-solid fa-chart-pie',
          url: 'home/dashboard/vueensemble',
        },
        {
          id: '12',
          titre: 'Statistiques',
          icon: 'fa-solid fa-chart-line',
          url: 'home/dashboard/statistiques',
        },
      ],
    },
    {
      id: '2',
      titre: 'Articles',
      icon: 'fa-solid fa-boxes-stacked',
      url: '',
      sousMenu: [
        {
          id: '21',
          titre: 'Articles',
          icon: 'fa-solid fa-boxes-stacked',
          url: 'home/articles/articles',
        },
        {
          id: '22',
          titre: 'Mouvements de stock',
          icon: 'fa-brands fa-stack-overflow',
          url: 'home/articles/mouvements-stock',
        },
      ],
    },
    {
      id: '3',
      titre: 'Clients',
      icon: 'fa-solid fa-users',
      url: '',
      sousMenu: [
        {
          id: '31',
          titre: 'Clients',
          icon: 'fa-solid fa-users',
          url: 'home/clients/clients',
        },
        {
          id: '32',
          titre: 'Commandes clients',
          icon: 'fa-solid fa-basket-shopping',
          url: 'home/clients/commandes-client',
        },
      ],
    },
    {
      id: '4',
      titre: 'Fournisseurs',
      icon: 'fa-solid fa-users',
      url: '',
      sousMenu: [
        {
          id: '41',
          titre: 'Fournisseurs',
          icon: 'fa-solid fa-users',
          url: 'home/fournisseurs/fournisseurs',
        },
        {
          id: '42',
          titre: 'Commandes fournisseurs',
          icon: 'fa-solid fa-truck',
          url: 'home/fournisseurs/commandes-fournisseur',
        },
      ],
    },
    {
      id: '5',
      titre: 'Paramètres',
      icon: 'fa-solid fa-gears',
      url: '',
      sousMenu: [
        {
          id: '51',
          titre: 'Catégories',
          icon: 'fa-solid fa-screwdriver-wrench',
          url: 'home/parametres/categories',
        },
        {
          id: '52',
          titre: 'Utilisateurs',
          icon: 'fa-solid fa-users-gear',
          url: 'home/parametres/utilisateurs',
        },
      ],
    },
  ];

  private lastSelectedMenu: Menu | undefined;

  constructor(private router: Router) {}

  navigate(menu: Menu): void {
    if (this.lastSelectedMenu) {
      this.lastSelectedMenu.active = false;
    }
    menu.active = true;
    this.lastSelectedMenu = menu;
    this.router.navigate([menu.url]);
  }

  isFirstMenu(id: string|undefined): boolean {
    if(id && id == '1')
      return true;
    return false;
  }
}

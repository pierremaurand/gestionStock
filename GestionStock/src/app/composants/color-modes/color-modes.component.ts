import { Component, OnInit } from '@angular/core';
import { ColorMode } from './color-mode';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-color-modes',
  imports: [CommonModule],
  templateUrl: './color-modes.component.html',
  styleUrl: './color-modes.component.scss'
})
export class ColorModesComponent implements OnInit{
  modes: Array<ColorMode> = [
    {value: 'light', title: 'Light', icon: '#sun-fill', isActive: false},
    {value: 'dark', title: 'Dark', icon: '#moon-stars-fill', isActive: false},
    {value: 'auto', title: 'Auto', icon: '#circle-half', isActive: true},
  ];

  private _mode: ColorMode|undefined;

  ngOnInit(): void {
    this.setTheme(this.getPreferredTheme());
  }

  get activatedMode(): ColorMode {
    if(!this._mode) {
      this._mode = this.modes.filter(mode => mode.isActive)[0];
    }
    return this._mode;
  }

  isActive(mode: ColorMode): string {
    return mode.isActive ? 'true': 'false';
  }

  getStoredTheme(): string|null {
    return localStorage.getItem('theme');
  }

  setStoredTheme(theme: string) {
    localStorage.setItem('theme', theme);
  }

  getPreferredTheme(): string {
    const storedTheme = this.getStoredTheme();
    if(storedTheme) {
      return storedTheme;
    }
    return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
  }

  setTheme(theme: string) {
    if (theme === 'auto') {
      document.documentElement.setAttribute('data-bs-theme', (window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light'))
    } else {
      document.documentElement.setAttribute('data-bs-theme', theme)
    }
  }

  showActiveTheme(mode: ColorMode): void{
    if(this._mode) {
      this._mode.isActive = false;
    }
    mode.isActive = true;
    this._mode = mode;
    this.setTheme(mode.value as string);
  }

}

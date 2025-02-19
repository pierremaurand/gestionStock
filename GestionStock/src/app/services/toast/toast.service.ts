import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(
    private toastr: ToastrService
  ) { }

  saveSuccess(name: string, id?: number) {
    let message = name + " ";
    message += id? "modifié(e) ": "ajouté(e) ";
    message += "avec succès";
    this.toastr.success(message, "Success");
  }

  showSuccess(message: string) {
    this.toastr.success(message, "Success");
  }

  showError(message: string) {
    this.toastr.error(message, "Error");
  }

  showInfo(message: string) {
    this.toastr.info(message, "Info");
  }

  showWarning(message: string) {
    this.toastr.warning(message, "Warning");
  }
}

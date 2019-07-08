import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';
import {OperacaoService} from '../../services/operacao.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-entrada',
  templateUrl: './entrada.component.html'
})
export class EntradaComponent implements OnInit {

  tipos =  [
    {value: 'CARRO', modelo: 'Carro', valor: 15},
    {value: 'MOTO', modelo: 'Moto', valor: 10},
    {value: 'CAMINHAO', modelo: 'Caminhão', valor: 35},
    {value: 'CAMINHONETE', modelo: 'Caminhonete', valor: 20}
  ];

  form: FormGroup;

  constructor(private api: OperacaoService, private formBuilder: FormBuilder, private router: Router) { }
  ngOnInit() {
    this.form = this.formBuilder.group({
      placa : [null, Validators.required],
      modelo : [null, Validators.required]
    });
  }

  adicionar(form: NgForm) {
    this.api.entrada(form).subscribe(response => {
      this.router.navigate(['/operacao']);
    }, (error) => {
      console.log(error);
    });
  }
}

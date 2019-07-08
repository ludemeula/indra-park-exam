import { Component, OnInit } from '@angular/core';
import {OperacaoService} from '../../services/operacao.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Operacao} from '../../models/operacao';

@Component({
  selector: 'app-saida',
  templateUrl: './saida.component.html'
})
export class SaidaComponent implements OnInit {

  constructor(private api: OperacaoService, private route: ActivatedRoute, private formBuilder: FormBuilder, private router: Router) { }
  private operacao: Operacao;
  private _ID = this.route.snapshot.params.id;
  form: FormGroup;

  ngOnInit() {
    this.getOperacao(this._ID);
    this.form = this.formBuilder.group({
      placa: [null, Validators.required],
      valorTotal: ''
    });
  }

  getOperacao(id) {
    this.api.getById(id).subscribe(request => {
      this.operacao = request;
      this.operacao.valorTotal = 0;
      this.form.setValue({placa: this.operacao.placa, valorTotal: 'R$ 0,00'});
    });
  }

  calcular() {
    this.api.calculo(this.operacao.modelo, this.operacao.dataHoraEntrada).subscribe(request => {
      this.operacao.valorTotal = request;
      this.form.setValue({placa: this.operacao.placa, valorTotal: Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(this.operacao.valorTotal)});
    });
  }

  sair() {
    this.api.saida(this._ID).subscribe(request => {
      this.router.navigate(['/operacao']);
    });
  }
}

import { Component, OnInit } from '@angular/core';
import {OperacaoService} from '../../services/operacao.service';
import {Operacao} from '../../models/operacao';
import {FormBuilder, FormGroup, NgForm} from '@angular/forms';

@Component({
  selector: 'app-operacao',
  templateUrl: './operacao.component.html'
})
export class OperacaoComponent implements OnInit {

  displayedColumns: string[] = ['placa', 'modelo', 'dataHoraEntrada', 'dataHoraSaida', 'totalPago', 'acao'];
  dataSource: Operacao[];

  form: FormGroup;

  constructor(private _api: OperacaoService, private formBuilder: FormBuilder) {}

  ngOnInit() {

    this.form = this.formBuilder.group({
      placa: '',
      dataHoraEntrada: '',
      dataHoraSaida: ''
    });

    this._api.getAll()
      .subscribe(response => {

        this.dataSource = response;
        console.log(this.dataSource);
      }, err => {
        console.log(err);
      });
  }

  pesquisar(form: NgForm) {
    console.log(form);
  }
}

import { Component, OnInit } from '@angular/core';
import {OperacaoService} from '../../services/operacao.service';
import {Operacao} from '../../models/operacao';

@Component({
  selector: 'app-operacao',
  templateUrl: './operacao.component.html'
})
export class OperacaoComponent implements OnInit {

  displayedColumns: string[] = ['placa', 'modelo', 'horario', 'acao'];
  dataSource: Operacao[];

  constructor(private _api: OperacaoService) {}

  ngOnInit() {
    this._api.getAll()
      .subscribe(res => {

        this.dataSource = res;
        console.log(this.dataSource);
      }, err => {
        console.log(err);
      });
  }

}

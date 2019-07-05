import { Component, OnInit } from '@angular/core';
import {OperacaoService} from '../../services/operacao.service';
import {Veiculo} from '../../models/veiculo';

@Component({
  selector: 'app-operacao',
  templateUrl: './operacao.component.html',
  styleUrls: ['./operacao.component.css']
})
export class OperacaoComponent implements OnInit {

  displayedColumns: string[] = ['placa', 'modelo', 'horario', 'acao'];
  dataSource: Veiculo[];

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

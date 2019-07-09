import { Component, OnInit } from '@angular/core';
import {OperacaoService} from '../../services/operacao.service';
import {build$} from 'protractor/built/element';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  chartLabels = [];
  chartData = [];

  constructor(private _api: OperacaoService) { }

  ngOnInit() {
    this.buildLabel();
    this._api.dashboard().subscribe(response => {
      this.chartData = this.buildData(response);
      // this.buildData(response);
    }, err => {
      console.log(err);
    });
  }

  chartOptions = {
    elements: {
      line: {
        fill: false
      }
    }
  };

  buildLabel() {
    let _dataInicial = new Date();
    this.chartLabels.push(_dataInicial.toLocaleDateString());
    for (let i = 1; i <= 7; i++) {
      _dataInicial.setDate(_dataInicial.getDate() - 1);
      this.chartLabels.push(_dataInicial.toLocaleDateString());
    }

    this.chartLabels.sort();
  }

  buildData(response) {
    let resultados = [];

    let modelos = ['CARRO', 'MOTO', 'CAMINHAO', 'CAMINHONETE'];
    modelos.forEach(modelo => {
      let dash = {};
      let data = [];
      this.chartLabels.forEach(label => {
        let result = response.filter(el => el['data'] === label && el['modelo'] === modelo);
        if (result.length > 0) {
          data.push(result.map(el => el['quantidade']).reduce( (sum, current) => sum + current, 0 ));
        } else {
          data.push(0);
        }
      });
      dash['data'] = data;
      dash['label'] = modelo;

      resultados.push(dash);

    });
    console.log(resultados)

    return resultados;

  }
}

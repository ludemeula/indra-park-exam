import { Routes } from '@angular/router';

import { FullComponent } from './layouts/full/full.component';
import {TestComponent} from './view/test/test.component';
import {DashboardComponent} from './view/dashboard/dashboard.component';
import {OperacaoComponent} from './view/operacao/operacao.component';

export const AppRoutes: Routes = [
  {
    path: '',
    component: FullComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'test',
        component: TestComponent
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'operacao',
        component: OperacaoComponent
      }
    ]
  }
];

import { Routes } from '@angular/router';

import { FullComponent } from './layouts/full/full.component';
import {TestComponent} from './view/test/test.component';

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
      }
    ]
  }
];

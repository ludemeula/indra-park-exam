import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FullComponent} from './layouts/full/full.component';
import {AppRoutes} from './app.routing';
import {RouterModule} from '@angular/router';
import {MAT_DATE_FORMATS, MatMenuModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DemoMaterialModule} from './demo-material-module';
import {TestComponent} from './view/test/test.component';
import {TestService} from './services/test.service';
import {HttpClientModule} from '@angular/common/http';
import {DashboardComponent} from './view/dashboard/dashboard.component';
import {OperacaoComponent} from './view/operacao/operacao.component';
import {SaidaComponent} from './view/saida/saida.component';
import {EntradaComponent} from './view/entrada/entrada.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ChartsModule} from 'ng2-charts';
import localePt from '@angular/common/locales/pt';
import {registerLocaleData} from '@angular/common';


export const MY_FORMATS = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MM YYYY',
  },
};

registerLocaleData(localePt, 'pt-BR');
@NgModule({
  declarations: [
    AppComponent,
    FullComponent,
    TestComponent,
    DashboardComponent,
    OperacaoComponent,
    SaidaComponent,
    EntradaComponent,
  ],
  imports: [
    MatMenuModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    DemoMaterialModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(AppRoutes),
    ChartsModule
  ],
  providers: [TestService,
    { provide: LOCALE_ID, useValue: 'pt-BR' },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS }
    ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

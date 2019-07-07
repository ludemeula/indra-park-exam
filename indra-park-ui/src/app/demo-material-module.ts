/**
 * @license
 * Copyright Google LLC All Rights Reserved.
 *
 * Use of this source code is governed by an MIT-style license that can be
 * found in the LICENSE file at https://angular.io/license
 */

import {NgModule} from '@angular/core';
import {
  MatButtonModule, MatButtonToggleModule, MatDivider, MatIconModule, MatInputModule, MatMenuModule, MatSelectModule, MatSidenavContainer,
  MatSidenavModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';

/**
 * NgModule that includes all Material modules that are required to serve the demo-app.
 */
@NgModule({
  exports: [
    MatToolbarModule,
    MatMenuModule,
    MatSidenavModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatIconModule,
    MatTableModule,
    MatInputModule,
    MatSelectModule
  ]
})
export class DemoMaterialModule {}

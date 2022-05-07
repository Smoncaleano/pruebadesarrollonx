import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { CargoComponent } from './cargos/cargos.component';
import { MercanciasComponent } from './mercancias/mercancias.component';
import { UsuarioService } from './usuarios/usuario.service';
import { MercanciaService } from './mercancias/mercancia.service';
import { CargoService } from './cargos/cargo.service';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormComponent } from './usuarios/form.component';
import { CargoFormComponent } from './cargos/CargoForm.component';
import { MercanciaFormComponent} from './mercancias/MercanciaFormComponent';
import { FormsModule } from '@angular/forms';

import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es';
registerLocaleData(localeES, 'es');

const routes: Routes = [
  { path: '', redirectTo: '/usuarios', pathMatch: 'full' },
  { path: '', redirectTo: '/cargos', pathMatch: 'full' },
  { path: '', redirectTo: '/mercancias', pathMatch: 'full' },
  { path: 'directivas', component: DirectivaComponent },
  { path: 'usuarios', component: UsuariosComponent },
  { path: 'cargos', component: CargoComponent },
  { path: 'mercancias', component: MercanciasComponent },
  { path: 'usuarios/form', component: FormComponent },
  { path: 'cargos/Cargoform', component: CargoFormComponent },
  { path: 'mercancias/MercanciaForm', component: MercanciaFormComponent },
  { path: 'cargos/Cargoform/:id', component: CargoFormComponent },
  { path: 'usuarios/form/:id', component: FormComponent },
  { path: 'mercancias/MercanciaForm/:id', component: MercanciaFormComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    DirectivaComponent,
    UsuariosComponent,
    FormComponent,
    CargoComponent,
    CargoFormComponent,
    MercanciasComponent,
    MercanciaFormComponent 
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [UsuarioService, CargoService, MercanciaService, { provide: LOCALE_ID, useValue: 'es' }],
  bootstrap: [AppComponent]
})
export class AppModule { }

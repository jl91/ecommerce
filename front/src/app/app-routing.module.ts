import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from './core/guards/authentication.guard';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./pages/auth/auth.module')
      .then(m => m.AuthModule),
  },
  {
    path: 'restricted',
    loadChildren: () => import('./pages/restricted-area/restricted-area.module')
      .then(m => m.RestrictedAreaModule),
    canLoad: [AuthenticationGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

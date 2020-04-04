import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

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
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

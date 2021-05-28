import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CarComponent} from "./car/car.component";
import {NewsComponent} from "./news/news.component";

const routes: Routes = [
  {
    path: 'car',
    component: CarComponent
  },
  {
    path: 'news',
    component: NewsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

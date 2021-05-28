import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

export interface Car {
  carId: number;
  mark: string;
  model: string;
  color: string;
  productionDate: string;
}


@Injectable({
  providedIn: 'root'
})
export class CarService {

  private REST_API_CAR = 'http://localhost:8080/api/car'

  constructor(
    private http: HttpClient
  ) {
  }

  public getCars(): Observable<any> {
    return this.http.get<Car>(this.REST_API_CAR + '/all');
  }

  public getCarsByDate(dateFrom: String, dateTo: String): Observable<any> {
    return this.http.get<Car>(this.REST_API_CAR + `/date/${dateFrom}/${dateTo}`)
  }

  public saveCar(value: any): Observable<any> {
    const car: Car = {carId : value.car_id, mark: value.car_mark, model: value.car_model, color: value.car_color, productionDate: value.car_production_date };
    return this.http.post<any>(this.REST_API_CAR + '/save', car);
  }
}

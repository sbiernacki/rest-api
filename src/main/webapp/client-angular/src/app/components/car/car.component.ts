import {Component, OnInit} from '@angular/core';
import {Car, CarService} from "../../service/car/car.service";
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.scss']
})
export class CarComponent implements OnInit {
  public cars: Car[] = [];

  constructor(
    private carService: CarService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.getCars()
  }

  getCars(): void {
    this.carService.getCars().subscribe((resp: any) => {
      this.cars = resp;
      console.log(this.cars)
    });
  }

  getCarsByDate(myForm: NgForm): void {
    const dateFrom = myForm.value.date_from;
    const dateTo = myForm.value.date_to;
    this.carService.getCarsByDate(dateFrom, dateTo).subscribe((resp: any) => {
      this.cars = resp;
      console.log(resp);
    })
    myForm.resetForm();
    this.router.navigateByUrl('/car');
  }

  save(myform: NgForm): void {

    this.carService.saveCar(myform.value).subscribe((resp: any) => {
      console.log(resp);
    })
    myform.resetForm();
    this.getCars()
  }

}

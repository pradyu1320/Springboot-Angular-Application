import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'search-employee',
  templateUrl: './search-employee.component.html',
  styleUrls: ['./search-employee.component.css']
})
export class SearchEmployeeComponent implements OnInit {
  age:number;
  employees:Employee[];
  constructor(private dataService:EmployeeService) { }

  ngOnInit() {
    this.age=0;
  }
  private searchEmployee(){
    this.dataService.getEmployeeByAge(this.age)
    .subscribe(employees => this.employees=employees);
  }
  onSubmit(){
    this.searchEmployee();
  }

}

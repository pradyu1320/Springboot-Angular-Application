import { Component, OnInit, Input } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeListComponent } from '../employee-list/employee-list.component';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {
  @Input() employee:Employee;


  constructor(private employeeService:EmployeeService,private listComponent:EmployeeListComponent) { }

  ngOnInit() {
  }
  updateActive(isActive:boolean){
    this.employeeService.updateEmployee(this.employee.id,
      {firstName:this.employee.firstname,lastName:this.employee.lastname,age:this.employee.age,active:isActive})
      .subscribe(
        data=>{
          console.log(data);
          this.employee=data as Employee;
        },
        error => console.log(error)
      );
  }
  deleteEmployee(){
    this.employeeService.deleteEmployee(this.employee.id)
    .subscribe(
      data => {
        console.log(data);
        this.listComponent.reloadData();
      },
      error => console.log(error)
    );
  }

}

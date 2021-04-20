import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-std-dashboard',
  templateUrl: './std-dashboard.component.html',
  styleUrls: ['./std-dashboard.component.css']
})
export class StdDashboardComponent implements OnInit {

  constructor() { }

  topYear : any;
  middleYear : any;
  botYear : any;

  yearList : string[] = [];
  yearBefore = new Date().getFullYear()+1;

  ngOnInit(): void {
    this.makeYearList();
    console.log(this.yearList)
    this.topYear = this.middleYear = this.botYear = this.yearList[2]
    // this.topYear = this.yearList[2] ;
    // this.middleYear = this.yearList[2] ;
    // this.botYear = this.yearList[2] ;


  }

  makeYearList() {
    for (let i = 0; i < 7; i++) {
        this.yearList.push(this.yearBefore.toString());
        this.yearBefore--;
    }
  }

  title = 'Contributions by each Faculty for academic year';
  type = 'PieChart';
  data = [
    ['Information Technology', 45.0],
    ['Bussiness', 26.8],
    ['Event Manager', 12.8],
    ['Graphic Design', 15.4],
  ];
  options = {
    colors: ['#e0440e', '#e6693e', '#ec8f6e', '#f3b49f', '#f6c7b6'], is3D: true
  };

  width1 = 650;
  height1 = 260;

  width2 = 450;
  height2 = 210;


}

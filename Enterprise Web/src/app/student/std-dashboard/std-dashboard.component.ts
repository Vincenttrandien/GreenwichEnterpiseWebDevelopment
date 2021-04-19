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

  title = 'Browser market shares at a specific website, 2014';
  type = 'PieChart';
  data = [
    ['Firefox', 45.0],
    ['IE', 26.8],
    ['Chrome', 12.8],
    ['Safari', 8.5],
    ['Opera', 6.2],
    ['Others', 0.7]
  ];
  options = {
    colors: ['#e0440e', '#e6693e', '#ec8f6e', '#f3b49f', '#f6c7b6'], is3D: true
  };

  width1 = 650;
  height1 = 625;

  width2 = 450;
  height2 = 175;

}

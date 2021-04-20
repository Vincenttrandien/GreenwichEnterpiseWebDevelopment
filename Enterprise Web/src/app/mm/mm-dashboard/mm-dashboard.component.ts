import { Component, OnInit } from '@angular/core';
import { AppGlobals } from 'src/app/app-variable';

@Component({
  selector: 'app-mm-dashboard',
  templateUrl: './mm-dashboard.component.html',
  styleUrls: ['./mm-dashboard.component.css']
})
export class MmDashboardComponent implements OnInit {

  constructor( private globalMemor: AppGlobals) { }

  topYear : any;
  middleYear : any;
  botYear : any;

  yearList : string[] = [];
  yearBefore = new Date().getFullYear()+1;

  ngOnInit(): void {
    this.makeYearList();
    console.log(this.yearList)
    this.topYear = this.middleYear = this.botYear = this.yearList[2];

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


  // title2 = 'Contributions by each Faculty for academic year';
  // type2 = 'PieChart';
  // data2 = [
  //   ['Information Technology', 45.0],
  //   ['Bussiness', 26.8],
  //   ['Event Manager', 12.8],
  //   ['Graphic Design', 8.5],
  // ];
  // options2 = {
  //   colors: ['#e0440e', '#e6693e', '#ec8f6e', '#f3b49f', '#f6c7b6'], is3D: true
  // };



}

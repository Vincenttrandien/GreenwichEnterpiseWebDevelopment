import { Component, OnInit } from '@angular/core';
import { AppGlobals } from 'src/app/app-variable';
import * as $ from 'jquery';


@Component({
  selector: 'app-gue-dashboard',
  templateUrl: './gue-dashboard.component.html',
  styleUrls: ['./gue-dashboard.component.css']
})
export class GueDashboardComponent implements OnInit {

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

  // width2 = 50;
  // height2 = 210;


  // google.load("visualization", "1", {packages:["corechart"]});
  // google.setOnLoadCallback(drawChart1);
  // function drawChart1() {
  //   var data = google.visualization.arrayToDataTable([
  //     ['Year', 'Sales', 'Expenses'],
  //     ['2004',  1000,      400],
  //     ['2005',  1170,      460],
  //     ['2006',  660,       1120],
  //     ['2007',  1030,      540]
  //   ]);

  //   var options = {
  //     title: 'Company Performance',
  //     hAxis: {title: 'Year', titleTextStyle: {color: 'red'}}
  //  };

  // var chart = new google.visualization.ColumnChart(document.getElementById('chart_div1'));
  //   chart.draw(data, options);
  // }

  // google.load("visualization", "1", {packages:["corechart"]});
  // google.setOnLoadCallback(drawChart2);
  // function drawChart2() {
  //   var data = google.visualization.arrayToDataTable([
  //     ['Year', 'Sales', 'Expenses'],
  //     ['2013',  1000,      400],
  //     ['2014',  1170,      460],
  //     ['2015',  660,       1120],
  //     ['2016',  1030,      540]
  //   ]);

  //   var options = {
  //     title: 'Company Performance',
  //     hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
  //     vAxis: {minValue: 0}
  //   };

  //   var chart = new google.visualization.AreaChart(document.getElementById('chart_div2'));
  //   chart.draw(data, options);
  // }

  // $(window).resize(function(){
  //   drawChart1();
  //   drawChart2();
  // });

  // Reminder: you need to put https://www.google.com/jsapi in the head of your document or as an external resource on codepen //

  width1 = 650;
  height1 = 260;

  width2 = 450;
  height2 = 210;

}

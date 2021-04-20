import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-mm-contributions',
  templateUrl: './mm-contributions.component.html',
  styleUrls: ['./mm-contributions.component.css']
})
export class MmContributionsComponent implements OnInit {

  constructor(
    private modalService : NgbModal,
    private router : Router
  ) { }

  yearBefore = new Date().getFullYear()+1;
  yearList : string[] = [];

  falList = ["IT", "Marketing", "Business", "Graphic Design"];

  ngOnInit(): void {
    this.makeYearList();
  }

  makeYearList() {
    for (let i = 0; i < 7; i++) {
        this.yearList.push(this.yearBefore.toString());
        this.yearBefore--;
    }
  }

  changeToDTB() {
    this.router.navigateByUrl('/mc/contributionslist');
  }



  createModal(create: any){
    this.modalService.open(create, { centered: true, size: "lg" });
  }

  editModal(edit: any){
    this.modalService.open(edit, { centered: true, size: "lg" });
  }

  closeModal(){
    this.modalService.dismissAll();
  }

}

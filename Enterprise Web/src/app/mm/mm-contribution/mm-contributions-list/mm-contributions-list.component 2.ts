import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-mm-contributions-list',
  templateUrl: './mm-contributions-list.component.html',
  styleUrls: ['./mm-contributions-list.component.css']
})
export class MmContributionsListComponent implements OnInit {

  constructor( private modalService : NgbModal ) { }

  roleList = ["Admin", "Marketing Manager", "Marketing Coordinator", "Student", "Guest"];

  ngOnInit(): void {
  }

  viewModal(view: any){
    this.modalService.open( view, { centered: true, size: 'lg' });
  }

}

import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';
import { CONTRIBUTION } from 'src/app/student/std-contribution/std-contribution';
import { StdContributionService } from 'src/app/student/std-contribution/std-contribution.service';

@Component({
  selector: 'app-mc-history',
  templateUrl: './mc-history.component.html',
  styleUrls: ['./mc-history.component.css']
})
export class McHistoryComponent implements OnInit {

  constructor( private modalService: NgbModal,
               private cookie : CookieService,
               private submissionService : StdContributionService ) { }

  id : any;
  nam = 2020;
  pageNum = 1;
  pageSize = 10;
  keyword = '';
  totalElements : any;

  recommendList = [];

  ngOnInit(): void {


    this.getCategoryList();
  }

  getCategoryList(){
    this.keyword = this.cookie.get('userId').toString();
    this.submissionService.getCategoryList(this.pageNum, this.pageSize, this.nam, this.keyword).subscribe( data => {
      if (data) {
        this.recommendList = data.content;
        this.totalElements = data.totalElements;
      }
      console.log(this.recommendList);
    })
  }

  downloadFile(item: CONTRIBUTION){
    if (item) {
      this.id = item.id;
      window.open("http://localhost:8080/greenwich/auth/Submission/files/" + this.id);
    }
  }



  viewModal(view: any){
    this.modalService.open( view, { centered: true, size: 'xl'});
  }
  downloadModal(download: any){
    this.modalService.open( download, { centered: true, size: 'lg'});
  }

  sendModal(send: any){
    this.modalService.open( send, { centered: true, size: 'lg'});
  }

}

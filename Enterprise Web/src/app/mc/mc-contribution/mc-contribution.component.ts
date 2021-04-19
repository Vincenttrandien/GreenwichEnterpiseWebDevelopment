import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';
import { TOPIC } from 'src/app/admin/ad-topics/ad-topics';
import { AdTopicsService } from 'src/app/admin/ad-topics/ad-topics.service';

@Component({
  selector: 'app-mc-contribution',
  templateUrl: './mc-contribution.component.html',
  styleUrls: ['./mc-contribution.component.css']
})
export class McContributionComponent implements OnInit {

  constructor( private modalService: NgbModal,
               private router : Router,
               private cookie : CookieService,
               private topicService : AdTopicsService) { }

  id: any;
  pageNum = 1;
  pageSize = 10;
  nam = 2020;
  keyword = '';
  totalElements : any;

  dataList = [];
  topicList = [];


  ngOnInit(): void {
    this.getCategoryList();
  }

  getCategoryList(){
    this.keyword = this.cookie.get('facultyName').toString();
    this.topicService.getCategoryList(this.pageNum, this.pageSize, this.nam, this.keyword).subscribe( data => {
      if (data) {
        this.topicList = data.content;
        this.totalElements = data.totalElements;
      }
    })
  }


  showTopic(item: TOPIC){
    if (item) {
      this.id = item.id;
      this.dataList = [item];
      this.keyword = this.dataList[0].codeTopic;
      this.cookie.set('codeTopic', this.keyword);
      this.router.navigate(['/mc/contributionList']);
      this.getCategoryList();
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

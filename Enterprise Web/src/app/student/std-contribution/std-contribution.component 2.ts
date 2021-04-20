import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { TOPIC } from 'src/app/admin/ad-topics/ad-topics';
import { AdTopicsService } from 'src/app/admin/ad-topics/ad-topics.service';
import { AppGlobals } from 'src/app/app-variable';
import { validationUtil } from 'src/shared/validation.util';
import { CONTRIBUTION } from './std-contribution';
import { StdContributionService } from './std-contribution.service';

@Component({
  selector: 'app-std-contribution',
  templateUrl: './std-contribution.component.html',
  styleUrls: ['./std-contribution.component.css']
})
export class StdContributionComponent implements OnInit {

  constructor( private formBuilder : FormBuilder,
               private cookie : CookieService,
               private toaster : ToastrService,
               private modalService : NgbModal,
               private topicService : AdTopicsService,
               private contributionService : StdContributionService ) { }

  falList = ["IT", "Marketing", "Business", "Graphic Design"];

  id: any;
  pageNum = 1;
  pageSize = 10;
  nam = 2020;
  keyword = '';
  totalElements : any;

  currentMonth = new Date().getMonth()+1;

  updated = false;
  submitted = false;

  topicList = [];

  dataList = [];

  commentForm :FormGroup
  topicForm : FormGroup;
  contributionForm : FormGroup;

  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';

  currentDate : any;
  codeTopic : string;

  fileInfos: Observable<any>;

  utils: any = validationUtil;

  ooo: any;
  ngOnInit(): void {
    this.contributionForm = this.formBuilder.group({
      codeSubmission : ['', Validators.required],
      codeUser : [''],
      comment : [''],
      comment2 : [''],
      dateSubmit : [''],
      name : [''],
      status: [],
      codeUserRecommend : [],
      nameFaculty: [],
      codeTopic : [],
      files : [],
      type : [],
      data : []
    })

    this.topicForm = this.formBuilder.group({
      codeTopic : [''],
      name : [''],
      dateUpdate : [''],
      dateUpdate2 : [''],
      nam : [''],
      nameFaculty : [''],
      nameFile: [],
      data : [],
      desc : [''],
    })

    this.commentForm = this.formBuilder.group({
      content : [],
      userName : [],
      nameRole : [],
      dateUpdate : [],
      commentDesc : []
    })

    this.currentDate = (new Date().getDate().toString() + '-'
                        + this.currentMonth.toString() + '-'
                          + new Date().getFullYear().toString());

    this.getCategoryList();
  }

  get f (){
    return this.contributionForm.controls;
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

  showTopic(show: any, item: TOPIC){
    if (item) {
      this.id = item.id;
      this.dataList = [item];
      this.topicForm.patchValue(item);
      this.codeTopic = this.dataList[0].codeTopic;
    }
    this.modalService.open( show , {centered: true, size: 'xl'})
  }

  submitForm(submit: any){
    this.modalService.open( submit , {centered: true, size: 'xl'})
  }

  onSubmit(body : CONTRIBUTION){
    this.submitted = true;

    if ( this.contributionForm.invalid ) {
      return;
    }

    this.f.dateSubmit.setValue( new String ( this.f.dateSubmit.value.day.toString() + '-'
                                  + this.f.dateSubmit.value.month.toString() + '-'
                                  + this.f.dateSubmit.value.year.toString() ));
    body.dateSubmit  = this.f.dateSubmit.value.toString();
    body.codeUser = this.cookie.get('Currentcode');
    body.codeTopic = this.codeTopic;
    body.nameFaculty = this.cookie.get("facultyName").toString();
    body.codeUserRecommend = this.cookie.get("Currentcode").toString();

    if (this.updated){
      this.id = this.ooo;
      this.contributionService.updateSubmission(this.id, body).subscribe( data => {{
        if (data) {
          this.toaster.success('Success','Success');
          this.modalService.dismissAll;
        }
      }})
    } else {
      this.contributionService.createSubmission(body).subscribe( data=> {
        if (data) {
          this.toaster.success('Success','Success');
          this.modalService.dismissAll;
          this.updated = true;
          this.ooo = data.id;
        }
      })
    }
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    if (this.selectFile) {
      this.upload();
    } else {
      this.toaster.error('failure','failure');
    }
  }

  upload() {
    this.currentFile = this.selectedFiles.item(0);
    this.contributionService.uploadFile(this.currentFile, this.ooo).subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.toaster.success("File upload successfully");
          this.contributionService.getFiles();
          this.modalService.dismissAll();
          this.getCategoryList();
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.contributionService.getFiles();
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }

  downloadFile(){
    window.open("http://localhost:8080/greenwich/auth/Topic/files/" + this.id);
  }


}

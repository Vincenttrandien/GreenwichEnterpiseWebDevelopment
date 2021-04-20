import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { validationUtil } from 'src/shared/validation.util';
import Swal from 'sweetalert2';
import { TOPIC } from './ad-topics';
import { AdTopicsService } from './ad-topics.service';

@Component({
  selector: 'app-ad-topics',
  templateUrl: './ad-topics.component.html',
  styleUrls: ['./ad-topics.component.css']
})
export class AdTopicsComponent implements OnInit {

  constructor( private formBuilder : FormBuilder,
              private modalService : NgbModal,
              private topicService : AdTopicsService,
              private toaster : ToastrService
    ) { }

  yearBefore = new Date().getFullYear()+1;
  yearList : string[] = [];

  falList = ["Information Technology", "Marketing", "Business", "Graphic Design"];

  id: any;

  idmemor: any;

  ooo: any;

  nam = 2020;
  pageNum = 1;
  pageSize = 10;
  keyword = '';
  totalElements : any;

  currentFile : File;
  selectedFiles: FileList;
  fileInfos: Observable<any>;

  submitted = false;
  updated = false;

  topicList = [];
  topicForm : FormGroup;

  topicId : any;

  utils : any = validationUtil;

  limitPat : any = '/^[a-zA-Z0-9!@#$%^&*()]+$/';

  ngOnInit(): void {
    this.topicForm = this.formBuilder.group({
      codeTopic : ['', [Validators.required, Validators.maxLength(this.utils.CODE_MAXLENGTH)]],
      name : ['', [Validators.required, Validators.maxLength(this.utils.CODE_MAXLENGTH)]],
      dateUpdate : [''],
      dateUpdate2 : [''],
      nam : ['', Validators.required],
      nameFaculty : [''],
      fileName: [],
      data : [],
      desc : [''],
    })

    this.getCategoryList();
    this.makeYearList();
  }
  onReset(){
    this.updated = false;
    this.submitted = false;
    this.topicForm.reset();
  }

  get f () {
    return this.topicForm.controls;
  }

  makeYearList() {
    for (let i = 0; i < 7; i++) {
        this.yearList.push(this.yearBefore.toString());
        this.yearBefore--;
    }
  }

  createModal(create: any){
    this.topicForm.reset();
    this.modalService.open(create, { centered: true, size: "lg" });
  }

  updateModal(update: any, item: TOPIC){
    if (item) {
      this.id = item.id;
      this.idmemor = item.id;
      this.updated = true;
      this.topicForm.patchValue(item);
    }
    this.modalService.open(update, { centered: true, size: "lg" });
  }

  getCategoryList(){
    this.topicService.getCategoryList(this.pageNum, this.pageSize, this.nam, this.keyword).subscribe( data => {
      if (data) {
        this.topicList = data.content;
        this.totalElements = data.totalElements;
      }
    })
  }

  onSubmit( body : TOPIC ){
    this.submitted = true;

    if ( this.topicForm.invalid) {
      return;
    }
    this.selectFile(event)

    this.f.dateUpdate.setValue( new String ( this.f.dateUpdate.value.day.toString() + '-' + this.f.dateUpdate.value.month.toString() + '-' + this.f.dateUpdate.value.year.toString() ));
    this.f.dateUpdate2.setValue( new String ( this.f.dateUpdate2.value.day.toString() + '-' + this.f.dateUpdate2.value.month.toString() + '-' + this.f.dateUpdate2.value.year.toString() ));

    body.dateUpdate  = this.f.dateUpdate.value.toString();
    body.dateUpdate2  = this.f.dateUpdate2.value.toString();

    if( this.updated ){
      this.topicService.updateCategory( this.id, body ).subscribe( data => {
        if (data) {
          this.toaster.success('Topic information have been updated','Success');
          this.onReset();
          this.getCategoryList();
          this.modalService.dismissAll();
          this.topicForm.reset();
        }
      })
    } else {
      this.topicService.createNewCategory( body ).subscribe( data => {
        if (data) {
          this.toaster.success('Create new topic successfully','Success')
          this.onReset();
          this.modalService.dismissAll();
          this.getCategoryList();
          this.topicForm.reset();
          this.topicId = data.id;
        }
      })
    }
  }

  deleteCategory(item: TOPIC){
    item =  this.topicList.find(x => x.id == this.idmemor);
    const textConfirm = '<span style="color: #ff0000;">Warning : Deleted data cannot be recovered</span>';
    Swal.fire({
      title: '<span style="color: #2d8dc7;"> DO YOU REALLY WANT TO DELETE THIS TOPIC <strong style="color: #f1556c; font-weight:bold;">'
           + '</strong> ?</span>',
      html: textConfirm,
      imageHeight: 150,
      imageWidth: 320,
      imageClass: 'img-responsive',
      animation: false,
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '',
      cancelButtonText: 'Cancel',
      confirmButtonText: 'Agree'
    }).then((result) => {
      if (result.value) {
        console.log(item.id);
        this.topicService.deleteCategory(item.id).subscribe(data => {
          console.log(item.id);
          if (data) {
            this.toaster.success('Topic have been deleted', 'Success');
            this.getCategoryList();
            this.onReset();
            this.modalService.dismissAll();
          }
        });
      } (err) => {
          this.toaster.error('Failed to delete topic', 'Failure');
      }
    })
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
    this.topicService.uploadFile(this.currentFile, this.topicId).subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.toaster.success("File upload successfully");

          this.topicForm.reset();
          this.modalService.dismissAll();
          this.getCategoryList();
        }
      },
      err => {
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }

  closeModal(){
    this.modalService.dismissAll();
  }
}

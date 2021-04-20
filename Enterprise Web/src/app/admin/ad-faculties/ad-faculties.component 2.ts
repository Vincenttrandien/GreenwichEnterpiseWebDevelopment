import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Toast, ToastrService } from 'ngx-toastr';
import { AppGlobals } from 'src/app/app-variable';
import { validationUtil } from 'src/shared/validation.util';
import Swal from 'sweetalert2';
import { FACULTY } from './ad-faculties';
import { AdFacultiesService } from './ad-faculties.service';

@Component({
  selector: 'app-ad-faculties',
  templateUrl: './ad-faculties.component.html',
  styleUrls: ['./ad-faculties.component.css']
})
export class AdFacultiesComponent implements OnInit {

  constructor( private facultyService : AdFacultiesService,
               private modalService : NgbModal,
               private toastr : ToastrService,
               private formBuilder : FormBuilder) { }

layoutStatus = 1;

  id : any;
  nam = 2020;
  pageNum = 1;
  pageSize = 10;
  keyword = "";

  yearBefore = new Date().getFullYear()+1;

  facultyList = [];
  yearList : string[] = [];
  totalElements : any;

  submitBtnName = 'Add';

  updated = false;
  submitted = false;

  facultyForm : FormGroup;

  idmemor : any;

  utils : any = validationUtil


  ngOnInit(): void {

    this.facultyForm = this.formBuilder.group({
      codeFaculty : ['',[Validators.required, Validators.maxLength(this.utils.CODE_MAXLENGTH)]],
      name : ['',[Validators.required, Validators.maxLength(this.utils.CODE_MAXLENGTH)]],
      nam : [this.yearList[0], Validators.required],
      desc : [''],
      topic : []
    })

    this.makeYearList();
    this.getCategoryList();
  }

  get f (){
    return this.facultyForm.controls;
  }

  onReset(){
    this.updated = false;
    this.submitted = false;
    this.facultyForm.reset();
  }

  getCategoryList(){
    this.facultyService.getCategoryList(this.pageNum, this.pageSize, this.nam, this.keyword).subscribe( data => {
      if (data) {
        this.facultyList = data.content;
        this.totalElements = data.totalElements;
      }
    })
  }

  makeYearList() {
    for (let i = 0; i < 7; i++) {
        this.yearList.push(this.yearBefore.toString());
        this.yearBefore--;
    }
  }

  createLayout(){
    this.facultyForm.reset();
    this.layoutStatus = 1;
    this.submitBtnName = 'Add';
  }

  viewInfo(item : FACULTY){
    if (item) {
      this.layoutStatus = 2;
      this.updated = true;
      this.idmemor = item.id;
      this.id = item.id;
      this.submitBtnName = 'Update'
      this.facultyForm.patchValue(item);
    }
  }

  onSubmit(body : FACULTY){
    this.submitted = true;

    if (this.facultyForm.invalid){
      return;
    }

    if (this.updated){
      this.facultyService.updateCategory(this.id, body).subscribe( data => {
        if (data) {
          this.toastr.success('Thành công','Cập nhật thông tin faculty thành công');
          this.onReset();
          this.getCategoryList();
        } else {
          this.toastr.error('Thất bại','Cập nhật thông tin faculty thất bại');
          this.onReset();
        }
      })
    } else {
      this.facultyService.createNewCategory(body).subscribe( data => {
        if (data) {
          this.toastr.success('Thành công', 'Tạo mới thông tin faculty thành công');
          this.onReset();
          this.getCategoryList();
        } else {
          this.toastr.error('Thất bại','Tạo mới thông tin faculty chưa thành công');
          this.onReset();
        }
      })
    }
  }

  deleteCategory(item: FACULTY){
    item =  this.facultyList.find(x => x.id == this.idmemor);
    const textConfirm = 'Dữ liệu bị xóa sẽ không thể khôi phục.';
    Swal.fire({
      title: '<span style="color: #2d8dc7;"> BẠN MUỐN XÓA THÔNG TIN FACULTY <strong style="color: #f1556c; font-weight:bold;">' + '</strong> ?</span>',
      html: textConfirm,
      imageHeight: 150,
      imageWidth: 320,
      imageClass: 'img-responsive',
      animation: false,
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '',
      cancelButtonText: 'Bỏ qua',
      confirmButtonText: 'Đồng ý'
    }).then((result) => {
      if (result.value) {
        this.facultyService.deleteCategory(item.id).subscribe(data => {
          if (data) {
            this.toastr.success('Thành công', 'Xóa thông tin faculty thành công');
            this.getCategoryList();
            this.onReset();
            this.modalService.dismissAll();
          }
        }, (err) => {
          this.toastr.error('Thất bại', 'Xóa thông tin faculty thất bại');
      });
      }
    })
  }

}

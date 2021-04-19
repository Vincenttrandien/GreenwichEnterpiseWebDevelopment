import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { ACCOUNTs } from 'src/app/admin/ad-accounts/ad-accounts';
import { AdAccountsService } from 'src/app/admin/ad-accounts/ad-accounts.service';


@Component({
  selector: 'app-mm-managemc',
  templateUrl: './mm-managemc.component.html',
  styleUrls: ['./mm-managemc.component.css']
})
export class MmManagemcComponent implements OnInit {

  constructor( private modalService : NgbModal,
               private toast : ToastrService,
               private formBuilder : FormBuilder,
               private accountService : AdAccountsService) { }

  id: any;
  nam = 2020;
  pageNum = 1;
  pageSize = 10;
  searchKey = "GCC";

  yearBefore = new Date().getFullYear()+1;

  totalElements: any;

  mcList = [];
  yearList = [];
  mcForm : FormGroup;

  updated = false;
  submitted = false;

  alooo : any;

  btnSubmitName = 'Create';

  roleList = ["Admin", "Marketing Manager", "Marketing Coordinator", "Student", "Guest"];

  ngOnInit(): void {
    this.mcForm = this.formBuilder.group({
      codeUser : ['', Validators.required],
      name: ['', [Validators.required]],
      email : ['', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      dob : ['', Validators.required],
      address : ['', Validators.required],
      phoneNumber :  ['', Validators.required],
      username : ['', Validators.required],
      password : ['', Validators.required],
      roles : [''],
      retypePassword : [''],
      facultyName : ['', Validators.required],
      nam : [ this.yearList[1] ,Validators.required],
    })
    this.getCategoryList();
  }

  get f (){
    return this.mcForm.controls;
  }

  onReset(){
    this.updated = false;
    this.submitted = false;
    this.mcForm.reset();
  }

  makeYearList() {
    for (let i = 0; i < 7; i++) {
        this.yearList.push(this.yearBefore.toString());
        this.yearBefore--;
    }
  }

  getCategoryList(){
    this.accountService.getCategoryList(this.pageNum, this.pageSize, this.nam, this.searchKey).subscribe( data => {
      if (data) {
        this.mcList = data.content;
        this.totalElements = data.totalElements;
      } else {}
      this.onReset();
      this.updated = false;
      this.btnSubmitName = 'Create';
    })
  }



  viewModal(item: ACCOUNTs, view: any){
    if (item) {
      this.id = item.id;
      this.updated = true;
      this.mcForm.patchValue(item);
    } else {
      this.onReset();
      this.updated = false;
      this.btnSubmitName = 'Create';
    }
    this.modalService.open( view, { centered: true, size: 'lg' });
  }

  editModal(item: ACCOUNTs, edit: any){
    if (item) {
      this.id = item.id;
      this.updated = true;
      this.mcForm.patchValue(item);
      this.alooo = [item.roles[0]];
      console.log(this.alooo)
    } else {
      this.onReset();
      this.updated = false;
      this.btnSubmitName = 'Create';
    }
    this.modalService.open( edit, { centered: true, size: 'lg' });
  }

  onSubmit( body: ACCOUNTs ){
    body.roles = this.alooo;
    this.accountService.updateCategory(this.id, body).subscribe( data => {
      if (data) {
        this.toast.success('Update user information successfully', 'Success')
        this.mcForm.reset();
        this.modalService.dismissAll();
        this.getCategoryList();
      } else {
        this.toast.error('Update user information process have an error', 'Failure');
        this.mcForm.reset();
        this.modalService.dismissAll();
        this.getCategoryList();
      }

    })
  }


  editModall( edit: any){
    this.modalService.open( edit, { centered: true, size: 'lg' });
  }

}

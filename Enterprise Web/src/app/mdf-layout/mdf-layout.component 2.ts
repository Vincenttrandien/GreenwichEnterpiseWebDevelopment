import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import * as $ from 'jquery';
import { CookieService } from 'ngx-cookie-service';
import { ToastrService } from 'ngx-toastr';
import { ACCOUNTs } from '../admin/ad-accounts/ad-accounts';
import { AdAccountsService } from '../admin/ad-accounts/ad-accounts.service';
import { AppGlobals } from '../app-variable';


@Component({
  selector: 'app-mdf-layout',
  templateUrl: './mdf-layout.component.html',
  styleUrls: ['./mdf-layout.component.css']
})
export class MdfLayoutComponent implements OnInit {

  constructor( private router: Router,
               private cookie : CookieService,
               private toast : ToastrService,
               private modalService: NgbModal,
               private formBuilder : FormBuilder,
               private globalMemor : AppGlobals,
               private accountService : AdAccountsService){}

  id : any;

  basedLayout : number;

  currentName : any;
  currentRole : any;

  profileList = [];
  profileForm : FormGroup;

  ngOnInit(): void {

    this.profileForm = this.formBuilder.group({
      name: ['', Validators.required] ,
      email: ['', Validators.required] ,
      dob: ['', Validators.required] ,
      address: ['', Validators.required] ,
      phoneNumber: ['', Validators.required] ,
      nam: ['', Validators.required] ,
      retypePassword: [],
      id: [],
      topic: [],
      roles: [],
      facultyName: [],
      codeUser: [],
      username: [],
      password: [],
      submissions: [],
    })

    $("#menu-toggle1").click(function(e) {
      e.preventDefault();
      $("#sidebar").toggleClass("active");
      $("#menu").toggleClass("active");
      $("#content").toggleClass("active");
      $("#header").toggleClass("active");
      $("#footer").toggleClass("active");
    });

    $("#menu-toggle2").click(function(e) {
      e.preventDefault();
      $("#sidebar").toggleClass("active");
      $("#menu").toggleClass("active");
      $("#content").toggleClass("active");
      $("#header").toggleClass("active");
      $("#footer").toggleClass("active");
    });

    this.basedLayout = this.globalMemor.basedlayout;
    this.id = this.globalMemor.currentId
    this.findById();
  }


  profileModal(profile: any ){
    this.findById();
    // body = this.profileForm.value;
    this.modalService.open( profile, { centered: true, size: 'lg' });


    // if (body) {
    //   body.id = this.cookie.get('id');
    //   this.id = body.id;
    //   this.profileForm.patchValue(body);
    //   this.modalService.open( profile, { centered: true, size: 'lg' });
    // }
  }

  repassModal(repass: any){
    this.modalService.open( repass, { centered: true, size: 'md' });
  }

  logoutModal(logout: any){
    this.modalService.open( logout, { centered: true, size: 'md' });
  }

  findById(){
    this.id = this.cookie.get('userId')
    this.accountService.findById( this.id ).subscribe( data => {
      if (data) {
        console.log(data);
        this.profileForm.setValue(data);
        this.currentName = data.name;
        this.currentRole = data.roles[0].name;
        this.globalMemor.currentCode = data.codeUser;
        this.globalMemor.currentFaculty = data.facultyName;
        this.globalMemor.currentName = data.name;

        switch (this.currentRole) {
          case "ROLE_ADMIN" : {
            this.currentRole = "Admin";
            this.basedLayout = 1;
            break;
          }
          case "ROLE_MM" : {
            this.currentRole = "Marketing Manager";
            this.basedLayout = 3;
            break;
          }
          case "ROLE_MC" : {
            this.currentRole = "Marketing Coordinator";
            this.basedLayout = 2;
            break;
          }
          case "ROLE_STUDENT" : {
            this.currentRole = "Student";
            this.basedLayout = 4;
            break;
          }
          case "ROLE_GUEST" : {
            this.currentRole = "Guest";
            this.basedLayout = 5;
            break;
          }
        }

        this.cookie.set('userCode',this.globalMemor.currentCode);
        this.cookie.set('facultyName',this.globalMemor.currentFaculty);
        this.cookie.set('Name',this.globalMemor.currentName);
        this.cookie.set('Role',this.globalMemor.currentRole);
      }
    })
  }

  updateProfile(body : ACCOUNTs){
    this.accountService.updateCategory(this.id, body).subscribe( data => {
      if (data) {
        this.toast.success('Thành công', 'Cập nhật thông tin người dùng thành công');
        this.modalService.dismissAll();
      }
    }, (err) => {
      this.toast.error('Thất bại', 'Cập nhật thông tin cá nhân thất bại vui lòng kiểm tra')
    })
  }

  logOut(){
    this.modalService.dismissAll();
    this.router.navigate(['']);
    this.globalMemor.basedlayout = null;
    this.globalMemor.currentCode = null;
    this.globalMemor.currentId = null;
    this.globalMemor.currentName = null;
    this.globalMemor.currentRole = null;
    this.globalMemor.currentEmail = null;
    this.globalMemor.currentDOB = null;
    this.globalMemor.currentAdd = null;
    this.globalMemor.currentPhone = null;
    this.globalMemor.currentNam = null;
    this.globalMemor.currentImage = null;
    this.globalMemor.currentTopic = null;
    this.globalMemor.currentFaculty = null;
    this.globalMemor.currentUsername = null;
  }


  notify(){
    this.router.navigate(['/notify']);
  }

  // Admin
  mngAccount(){
    this.router.navigate(['admin/account']);
  }

  mngFaculty(){
    this.router.navigate(['admin/faculty']);
  }

  mngSubmit(){
    this.router.navigate(['/admin/topic']);
  }

  // Student
  stdDash(){
    this.router.navigate(['student/dashboard']);
  }

  stdChat(){
    this.router.navigate(['/student/chat']);
  }

  stdContribution(){
    this.router.navigate(['/student/contribution']);
  }


  // Marketing Manager
  mmDashboard(){
    this.router.navigate(['/mm/dashboard']);
  }

  mmManage(){
    this.router.navigate(['/mm/managemc']);
  }

  mmChat(){
    this.router.navigate(['/mm/chat']);
  }

  mmContributions(){
    this.router.navigate(['/mm/contributions']);
  }

  mmContributionList(){
    this.router.navigate(['/mm/contributionslist']);
  }

  // Marketing Coordinator
  mcDashboard(){
    this.router.navigate(['/mc/dashboard']);
  }

  mcContribution(){
    this.router.navigate(['/mc/contribution']);
  }

  mcHistory(){
    this.router.navigate(['/mc/history']);
  }

  closeModal(){
    this.modalService.dismissAll();
  }
}

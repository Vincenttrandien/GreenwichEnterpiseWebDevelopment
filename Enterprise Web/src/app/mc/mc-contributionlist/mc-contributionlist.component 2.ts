import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { ToastrService } from 'ngx-toastr';
import { AdTopicsService } from 'src/app/admin/ad-topics/ad-topics.service';
import { CONTRIBUTION } from 'src/app/student/std-contribution/std-contribution';
import { StdContributionService } from 'src/app/student/std-contribution/std-contribution.service';

@Component({
  selector: 'app-mc-contributionlist',
  templateUrl: './mc-contributionlist.component.html',
  styleUrls: ['./mc-contributionlist.component.css']
})
export class McContributionlistComponent implements OnInit {

  constructor( private cookie : CookieService,
               private toast : ToastrService,
               private formBuilder : FormBuilder,
               private submissionService : StdContributionService) { }

  id: any;
  pageNum = 1;
  pageSize = 10;
  nam = 2020;
  keyword = '';
  totalElements : any;

  submissionForm : FormGroup;

  contriList = [];

  ngOnInit(): void {

    this.submissionForm = this.formBuilder.group({
      codeSubmission : [''],
      codeUser : [''],
      comment : [''],
      comment2 : [''],
      dateSubmit : [''],
      name : [''],
      status: [],
      nameFaculty: [],
      codeTopic : [],
      files : [],
      type : [],
      data : []
    })

    this.getCategoryList();
  }

  getCategoryList(){
    this.keyword = this.cookie.get('codeTopic').toString();
    this.submissionService.getCategoryList(this.pageNum, this.pageSize, this.nam, this.keyword).subscribe( data => {
      if (data) {
        this.contriList = data.content;
        this.totalElements = data.totalElements;
      }
    })
  }

  // getContriStatus(item: CONTRIBUTION){
  //   if (item) {
  //     this.id = item.id;
  //     this.submissionForm.patchValue(item);
  //     console.log(this.submissionForm.value);
  //   }
  // }

  recommendContri(item: CONTRIBUTION){
    if (item) {
      this.id = item.id;
      if (item.status){
        item.status = false;
        this.submissionService.updateSubmission(this.id, item).subscribe(data => { })
        this.toast.success('This contribution has been de-recommend', 'Notice')
      } else {
        item.status = true;
        this.submissionService.updateSubmission(this.id, item).subscribe(data => { })
        this.toast.success('This submission has been recommended', 'Success')
      }
    }
  }

  downloadFile(item: CONTRIBUTION){
    if (item) {
      this.id = item.id;
      window.open("http://localhost:8080/greenwich/auth/Submission/files/" + this.id);
    }
  }
}

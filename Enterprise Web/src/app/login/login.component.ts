import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ToastrService } from 'ngx-toastr';
import { AppGlobals } from '../app-variable';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { SIGNIN } from './login';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor( private formBuilder : FormBuilder,
               private loginService : LoginService,
               private toast : ToastrService,
               private router : Router,
               private cookie : CookieService,
               private globalMemor : AppGlobals,
               private tokenStorage: TokenStorageService
  ) { }

  signinForm : FormGroup;
  account = [];
  userRole : any;
  id : any;

  ngOnInit(): void {
    this.router.navigate['/'];
    this.signinForm = this.formBuilder.group({
      username : ['', Validators.required],
      password : ['', Validators.required],
      roles : ['', Validators.required],
      id : []
    })

    if (this.tokenStorage.getToken()) {
      this.userRole = this.tokenStorage.getUser().roles;
    }

  }

  onSubmit(value: SIGNIN) {
    this.loginService.login(value).subscribe ( data => {
      if ( data ) {
        this.toast.success("đăng nhập thành công");
        this.id = data.id ;
        this.signinForm.patchValue(data);

        this.globalMemor.currentId = data.id;
        this.globalMemor.currentUsername = data.username;
        this.globalMemor.currentRole = data.roles[0];
        this.userRole = this.signinForm.value.roles;

        this.cookie.set('userId',this.globalMemor.currentId);

        switch (this.userRole[0]) {
          case ("ROLE_ADMIN"): {
            this.globalMemor.basedlayout = 1;
            this.globalMemor.currentRole = "Admin"
            this.router.navigate(['/admin/account']);
            break;
          } case ("ROLE_MC"): {
            this.globalMemor.basedlayout = 2;
            this.globalMemor.currentRole = "Marketing Coordinator";
            this.router.navigate(['/mc/dashboard']);
            break;
          } case ("ROLE_MM"): {
            this.globalMemor.basedlayout = 3;
            this.globalMemor.currentRole = "Marketing Manager";
            this.router.navigate(['/mm/dashboard']);
            break;
          } case ("ROLE_STUDENT"): {
            this.globalMemor.basedlayout = 4;
            this.globalMemor.currentRole = "Student";
            this.router.navigate(['/student/dashboard']);
            break;
          } case ("ROLE_GUEST"): {
            this.globalMemor.basedlayout = 5;
            this.globalMemor.currentRole = "Guest";
            this.router.navigate(['/guest/dashboard']);
            break;
          } default: {
            this.toast.error('Thất bại','Chưa lấy được data đâu');
            break;
          }
        }
      }
    }, (err) => {
      this.toast.error('Username or password is not correct','Failure');
    } )
  }

}

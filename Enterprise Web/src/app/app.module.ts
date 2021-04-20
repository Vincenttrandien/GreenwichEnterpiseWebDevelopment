import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { DatePipe } from '@angular/common';
import { CommonModule } from '@angular/common';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdAccountsComponent } from './admin/ad-accounts/ad-accounts.component';
import { AdFacultiesComponent } from './admin/ad-faculties/ad-faculties.component';
import { MdfLayoutComponent } from './mdf-layout/mdf-layout.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { StdDashboardComponent } from './student/std-dashboard/std-dashboard.component';
import { StdChatComponent } from './student/std-chat/std-chat.component';
import { StdContributionComponent } from './student/std-contribution/std-contribution.component';
import { NotifyComponent } from './notify/notify.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { McContributionComponent } from './mc/mc-contribution/mc-contribution.component';
import { McDashboardComponent } from './mc/mc-dashboard/mc-dashboard.component';
import { McHistoryComponent } from './mc/mc-history/mc-history.component';
import { MmContributionsComponent } from './mm/mm-contribution/mm-contributions/mm-contributions.component';
import { MmContributionsListComponent } from './mm/mm-contribution/mm-contributions-list/mm-contributions-list.component';
import { MmManagemcComponent } from './mm/mm-managemc/mm-managemc.component';
import { PrivacyPolicyComponent } from './website-backgrounds/privacy-policy/privacy-policy.component';
import { TermsConditionsComponent } from './website-backgrounds/terms-conditions/terms-conditions.component';
import { AdTopicsComponent } from './admin/ad-topics/ad-topics.component';
import { McLayoutComponent } from './corelayouts/mc-layout/mc-layout.component';
import { MmLayoutComponent } from './corelayouts/mm-layout/mm-layout.component';
import { StuLayoutComponent } from './corelayouts/stu-layout/stu-layout.component';
import { AdLayoutComponent } from './corelayouts/ad-layout/ad-layout.component';
import { GueLayoutComponent } from './corelayouts/gue-layout/gue-layout.component';
import { AppGlobals } from './app-variable';
import { AppRoutingModule } from './app-routing.module';
import { CookieService } from 'ngx-cookie-service';
import { NgbDateCustomParserFormatter } from 'src/shared/NgbDateCustomParserFormatter';
import { GoogleChartsModule } from 'angular-google-charts';
import { MmDashboardComponent } from './mm/mm-dashboard/mm-dashboard.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { CommentComponent } from './comment/comment.component';
import { McContributionlistComponent } from './mc/mc-contributionlist/mc-contributionlist.component';
import { StdContributionlistComponent } from './student/std-contributionlist/std-contributionlist.component';
import { GueDashboardComponent } from './guest/gue-dashboard/gue-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdAccountsComponent,
    AdFacultiesComponent,
    MdfLayoutComponent,
    StdDashboardComponent,
    StdChatComponent,
    StdContributionComponent,
    NotifyComponent,
    McContributionComponent,
    McDashboardComponent,
    McHistoryComponent,
    MmContributionsComponent,
    MmContributionsListComponent,
    MmManagemcComponent,
    PrivacyPolicyComponent,
    TermsConditionsComponent,
    AdTopicsComponent,
    AdLayoutComponent,
    McLayoutComponent,
    MmLayoutComponent,
    StuLayoutComponent,
    GueLayoutComponent,
    MmDashboardComponent,
    CommentComponent,
    McContributionlistComponent,
    StdContributionlistComponent,
    GueDashboardComponent

  ],
  imports: [
    CommonModule,

    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgbModule,
    NgSelectModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    GoogleChartsModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
      preventDuplicates: false
    })
  ],
  providers: [authInterceptorProviders, DatePipe, AppGlobals, CookieService, NgbDateCustomParserFormatter],
  bootstrap: [AppComponent]
})
export class AppModule { }

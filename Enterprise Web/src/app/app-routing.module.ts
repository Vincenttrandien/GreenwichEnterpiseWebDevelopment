import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdAccountsComponent } from './admin/ad-accounts/ad-accounts.component';
import { AdFacultiesComponent } from './admin/ad-faculties/ad-faculties.component';
import { AdTopicsComponent } from './admin/ad-topics/ad-topics.component';
import { GueDashboardComponent } from './guest/gue-dashboard/gue-dashboard.component';
import { LoginComponent } from './login/login.component';
import { McContributionComponent } from './mc/mc-contribution/mc-contribution.component';
import { McContributionlistComponent } from './mc/mc-contributionlist/mc-contributionlist.component';
import { McDashboardComponent } from './mc/mc-dashboard/mc-dashboard.component';
import { McHistoryComponent } from './mc/mc-history/mc-history.component';
import { MdfLayoutComponent } from './mdf-layout/mdf-layout.component';
import { MmContributionsListComponent } from './mm/mm-contribution/mm-contributions-list/mm-contributions-list.component';
import { MmContributionsComponent } from './mm/mm-contribution/mm-contributions/mm-contributions.component';
import { MmDashboardComponent } from './mm/mm-dashboard/mm-dashboard.component';
import { MmManagemcComponent } from './mm/mm-managemc/mm-managemc.component';
import { NotifyComponent } from './notify/notify.component';
import { StdChatComponent } from './student/std-chat/std-chat.component';
import { StdContributionComponent } from './student/std-contribution/std-contribution.component';
import { StdDashboardComponent } from './student/std-dashboard/std-dashboard.component';
import { PrivacyPolicyComponent } from './website-backgrounds/privacy-policy/privacy-policy.component';
import { TermsConditionsComponent } from './website-backgrounds/terms-conditions/terms-conditions.component';

const routes: Routes = [
    { path:'', component: LoginComponent, pathMatch:'full' },
    { path:'login', component: LoginComponent, pathMatch:'full' },
    { path:'mdflayout', component: MdfLayoutComponent },

    // Main
    {
      path: 'notify',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: NotifyComponent }
      ]
    },

    // Admin
    {
      path: 'admin/account',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: AdAccountsComponent }
      ]
    },

    // {
    //   path: 'admin/submission',
    //   component: MdfLayoutComponent,
    //   children: [
    //     { path: '', component: AdSubmissionsComponent }
    //   ]
    // },

    {
      path: 'admin/faculty',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: AdFacultiesComponent }
      ]
    },


    {
      path: 'admin/topic',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: AdTopicsComponent }
      ]
    },

    // Student
    {
      path: 'student/dashboard',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: StdDashboardComponent }
      ]
    },

    {
      path: 'student/chat',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: StdChatComponent }
      ]
    },

    {
      path: 'student/contribution',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: StdContributionComponent }
      ]
    },


    //Marketing Coordinator
    {
      path: 'mc/contribution',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: McContributionComponent }
      ]
    },
    {
      path: 'mc/contributionList',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: McContributionlistComponent }
      ]
    },
    {
      path: 'mc/history',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: McHistoryComponent }
      ]
    },
    {
      path: 'mc/dashboard',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: McDashboardComponent }
      ]
    },


    // Marketing Manager
    {
      path: 'mm/managemc',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: MmManagemcComponent }
      ]
    },

    {
      path: 'mm/dashboard',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: MmDashboardComponent }
      ]
    },

    {
      path: 'mm/contributions',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: MmContributionsComponent }
      ]
    },

    {
      path: 'mm/contributionslist',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: MmContributionsListComponent }
      ]
    },

    // Guest
    {
      path: 'guest/dashboard',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: GueDashboardComponent }
      ]
    },

    // Backgrounds
    {
      path: 'privacy-policy',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: PrivacyPolicyComponent }
      ]
    },

    {
      path: 'terms-conditions',
      component: MdfLayoutComponent,
      children: [
        { path: '', component: TermsConditionsComponent}
      ]
    },

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }

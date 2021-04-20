import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommandURL } from 'src/shared/manageURL';
import { PagingResponse } from 'src/shared/paging-response';
import { CONTRIBUTION } from './std-contribution';

@Injectable({
  providedIn: 'root'
})
export class StdContributionService {

  constructor( private http : HttpClient ) { }

  // http://localhost:8080/greenwich/auth/Submisson/searchByKey?pageSize=1&pageNumber=10&nam=2020&keyword=


  getCategoryList(pageNum: number, pageSize: number, nam: number, keyword: any):Observable<PagingResponse<CONTRIBUTION>>{
    const params = new HttpParams() .set('pageNumber', pageNum.toString())
                                    .set('pageSize', pageSize.toString())
                                    .set('nam', nam.toString())
                                    .set('keyword', keyword)
    return this.http.get<any>(CommandURL.SUBMISSION + 'searchByKey' ,{params, headers : {'Content-Type':'application/json'}});
  }

  createSubmission(body : CONTRIBUTION){
    return this.http.post<any>(CommandURL.SUBMISSION + 'insert' , body);
  }

  updateSubmission(id : any, body: CONTRIBUTION){
    return this.http.put<any>(CommandURL.SUBMISSION + id, body);
  }

  deleteCategory(id : any){
    return this.http.delete<any>(CommandURL.SUBMISSION + id);
  }

  uploadFile(body: File, id: string): Observable<HttpEvent<any>> {
      const formData: FormData = new FormData();
      formData.append('file', body);
      const req = new HttpRequest('PUT', `${CommandURL.SUBMISSION}upload/` + id, formData, {reportProgress: true, responseType: 'json'});
      return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${CommandURL.SUBMISSION}files`);
  }
}

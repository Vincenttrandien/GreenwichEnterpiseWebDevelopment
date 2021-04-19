import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { title } from 'process';
import { Observable } from 'rxjs';
import { CommandURL } from 'src/shared/manageURL';
import { PagingResponse } from 'src/shared/paging-response';
import { TOPIC } from './ad-topics';

@Injectable({
  providedIn: 'root'
})
export class AdTopicsService {

  constructor( private http :  HttpClient ) { }

  getCategoryList(pageNum: number, pageSize: number, nam: number, keyword: any):Observable<PagingResponse<TOPIC>>{
    const params = new HttpParams() .set('pageNumber', pageNum.toString())
                                    .set('pageSize', pageSize.toString())
                                    .set('nam', nam.toString())
                                    .set('keyword', keyword)
    return this.http.get<any>(CommandURL.TOPIC + 'searchByKey' ,{params, headers : {'Content-Type':'application/json'}});
  }

  uploadPics( title: any ){
    const params = new HttpParams() .set('title', title.toString());
    return this.http.post<any>(CommandURL.PICS + '/add' ,params );
  }

  findById(id: string){
    return this.http.get<any>(CommandURL.TOPIC + id);
  }

  createNewCategory(body: TOPIC){
    return this.http.post<any>(CommandURL.TOPIC + 'insert' , body , {headers : {'Content-Type':'application/json'}});
  }

  updateCategory(id: string, body: TOPIC){
    return this.http.put<any>(CommandURL.TOPIC + id , body);
  }

  deleteCategory(id: string){
    return this.http.delete<any>(CommandURL.TOPIC + id);
  }


  uploadFile(body: File, id: string): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', body);
    const req = new HttpRequest('PUT', `${CommandURL.TOPIC}upload/` + id, formData, {reportProgress: true, responseType: 'json'});
    return this.http.request(req);
  }

  downloadFile(id: string){
    return this.http.get<any>(CommandURL.TOPIC + 'files/' + id);
  }

  // getFiles(): Observable<any> {
  //   return this.http.get(`${CommandURL.TOPIC}files`);
  // }


}


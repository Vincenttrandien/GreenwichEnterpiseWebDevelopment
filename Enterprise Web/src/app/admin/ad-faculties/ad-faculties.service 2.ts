import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommandURL } from 'src/shared/manageURL';
import { PagingResponse } from 'src/shared/paging-response';
import { FACULTY } from './ad-faculties';

@Injectable({
  providedIn: 'root'
})
export class AdFacultiesService {

  constructor( private http : HttpClient ) { }

  getCategoryList(pageNum: number, pageSize: number, nam: number, keyword: any) : Observable<PagingResponse<FACULTY>>{
    const params = new HttpParams () .set('pageNumber', pageNum.toString())
                                     .set('pageSize', pageSize.toString())
                                     .set('nam', nam.toString())
                                     .set('keyword', keyword)
    return this.http.get<PagingResponse<FACULTY>>(CommandURL.FACULTY + 'getAll', {params, headers: {'Content-Type': 'application/json'}})
  }

  createNewCategory( body : FACULTY ){
    return this.http.post<any>(CommandURL.FACULTY + 'insert' , body);
  }

  updateCategory ( id : any , body : FACULTY ) {
    return this.http.put<any>(CommandURL.FACULTY + id, body);
  }

  deleteCategory ( id : string ) {
    return this.http.delete<FACULTY>(CommandURL.FACULTY + id);
  }

  findCategory (id : any) {
    return this.http.get<any>(CommandURL.FACULTY + id);
  }

}

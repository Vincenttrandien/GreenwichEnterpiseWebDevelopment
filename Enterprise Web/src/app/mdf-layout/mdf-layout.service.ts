import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommandURL } from 'src/shared/manageURL';
import { PagingResponse } from 'src/shared/paging-response';
import { ACCOUNTs } from '../admin/ad-accounts/ad-accounts';

@Injectable({
  providedIn: 'root'
})
export class MdfLayoutService {

  constructor( private http : HttpClient) { }

  getCategoryList(pageNum: number, pageSize: number, nam: number,  searchKey: string) : Observable<PagingResponse<ACCOUNTs>>{
    const params = new HttpParams() .set('pageNumber', pageNum.toString())
                                    .set('pageSize', pageSize.toString())
                                    .set('nam', nam.toString())
                                    .set('keyword', searchKey)
      return this.http.get<PagingResponse<ACCOUNTs>>(CommandURL.ACCOUNTS + '/searchByKey/' , {params, headers: {'Content-Type': 'application/json'}})
  }

  updateFaculty( id: any, codeFal : any, nameFal : any) : Observable<PagingResponse<ACCOUNTs>>{
    const params = new HttpParams() .set('codeFaculty', codeFal.toString())
                                    .set('nameFaculty', nameFal.toString())
      return this.http.get<PagingResponse<ACCOUNTs>>(CommandURL.ACCOUNTS + '/addFacultyforUser/' + id + '/' , {params, headers: {'Content-Type': 'application/json'}})
  }

  createNewCategory(body: ACCOUNTs){
    return this.http.post<ACCOUNTs>(CommandURL.ACCOUNTS + '/insert', body);
  }

  updateCategory(id: string, body: ACCOUNTs){
    return this.http.put<ACCOUNTs>(CommandURL.ACCOUNTS + '/' + body.id, body);
  }

  deleteCategory(id: string){
    return this.http.delete<ACCOUNTs>(CommandURL.ACCOUNTS + '/' + id);
  }



}


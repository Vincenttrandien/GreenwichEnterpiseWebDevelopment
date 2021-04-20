import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommandURL } from 'src/shared/manageURL';
import { PagingResponse } from 'src/shared/paging-response';
import { ACCOUNTs} from './ad-accounts';

@Injectable({
  providedIn: 'root'
})
export class AdAccountsService {

  constructor( private http : HttpClient) { }

  getCategoryList(pageNum: number, pageSize: number, nam: number,  searchKey: string) : Observable<PagingResponse<ACCOUNTs>>{
    const params = new HttpParams() .set('pageNumber', pageNum.toString())
                                    .set('pageSize', pageSize.toString())
                                    .set('nam', nam.toString())
                                    .set('keyword', searchKey)
    return this.http.get<PagingResponse<ACCOUNTs>>(CommandURL.ACCOUNTS + '/searchByKey/' , {params, headers: {'Content-Type': 'application/json'}})
  }

  signUp(body: ACCOUNTs){
    return this.http.post<ACCOUNTs>(CommandURL.SIGNUP, body, {headers: {'Content-Type': 'application/json'}});
  }

  createNewCategory(body: ACCOUNTs){
    return this.http.post<ACCOUNTs>(CommandURL.ACCOUNTS + '/insert', body);
  }

  updateCategory(id: string, body: ACCOUNTs){
    return this.http.put<ACCOUNTs>(CommandURL.ACCOUNTS + '/' + id, body);
  }

  deleteCategory(id: string){
    return this.http.delete<ACCOUNTs>(CommandURL.ACCOUNTS + '/' + id);
  }

  findById(id: any){
    return this.http.get<any>(CommandURL.ACCOUNTS + '/' + id);
  }
}

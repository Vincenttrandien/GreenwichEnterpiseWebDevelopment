import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CommandURL } from 'src/shared/manageURL';
import { COMMENT } from './comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor( private http : HttpClient ) { }

  findById(id: any){
    return this.http.get<COMMENT>(CommandURL.COMMENT + id);
  }

  addComment(body: COMMENT){
    return this.http.post<COMMENT>(CommandURL.COMMENT + 'insert', body);
  }

  editComment(id: any,body: COMMENT){
    return this.http.put<COMMENT>(CommandURL.COMMENT + id, body);
  }

  deleteComment(id: any){
    return this.http.delete<COMMENT>(CommandURL.COMMENT + id);
  }
}

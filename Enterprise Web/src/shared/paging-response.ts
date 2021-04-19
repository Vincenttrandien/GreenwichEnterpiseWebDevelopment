export class PagingResponse<T> {
  content: Array<T>;
  totalElements: number;
  totalUnRead: number;
}
export class PagedData<T> {
  items: Array<T>;
  totalItems: number;
  pageNumber: number;
  pageSize: number;
}

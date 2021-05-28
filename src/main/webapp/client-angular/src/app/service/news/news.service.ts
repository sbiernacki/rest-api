import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

export interface News {
  id: number;
  author: string;
  title: string;
  description: string;
  content: string;
}

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  private REST_API_NEWS = "http://localhost:8080/api/news"

  constructor(private http: HttpClient) {
  }

  public getNews(): Observable<News> {
    return this.http.get<News>(this.REST_API_NEWS + '/all');
  }

}

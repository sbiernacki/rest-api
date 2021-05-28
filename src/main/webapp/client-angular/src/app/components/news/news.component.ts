import {Component, OnInit} from '@angular/core';
import {News, NewsService} from "../../service/news/news.service";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  public news: News[] = [];

  constructor(
    private newsService: NewsService
  ) {
  }

  ngOnInit(): void {
    this.getNews();
  }

  public getNews(): void {
    this.newsService.getNews().subscribe((resp: any) => {
      this.news = resp;
      console.log(resp);
    });
  }

}

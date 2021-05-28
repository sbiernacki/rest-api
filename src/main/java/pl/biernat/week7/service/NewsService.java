package pl.biernat.week7.service;

import pl.biernat.week7.model.News;

import java.util.List;

public interface NewsService {

    void fetchNews();

    List<News> findAllNews();

    void updateNews(News news);
}

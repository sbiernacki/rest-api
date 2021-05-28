package pl.biernat.week7.dao.impl;

import pl.biernat.week7.integration.model.Article;
import pl.biernat.week7.model.News;

import java.util.List;

public interface NewsDao {

    void saveNews(Article article);

    List<News> findAllNews();

    void updateNews(News news);
}

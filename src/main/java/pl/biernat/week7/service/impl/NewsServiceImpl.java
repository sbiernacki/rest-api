package pl.biernat.week7.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.biernat.week7.integration.CallExternalNewsApi;
import pl.biernat.week7.integration.model.Article;
import pl.biernat.week7.dao.impl.NewsDao;
import pl.biernat.week7.model.News;
import pl.biernat.week7.service.NewsService;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsDao newsDao;
    private final CallExternalNewsApi callExternalNewsApi;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao, CallExternalNewsApi callExternalNewsApi) {
        this.newsDao = newsDao;
        this.callExternalNewsApi = callExternalNewsApi;
    }

    @Override
    public void fetchNews() {
        List<Article> articles = callExternalNewsApi.fetchNews().getArticles();

        for (int i = 1; i < articles.size(); i++) {
            Article article = articles.get(i);
            article.setId((long) i);
            newsDao.saveNews(article);
        }
    }

    @Override
    public List<News> findAllNews() {
        return newsDao.findAllNews();
    }

    @Override
    public void updateNews(News news) {
        newsDao.updateNews(news);
    }
}

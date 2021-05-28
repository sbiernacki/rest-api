package pl.biernat.week7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pl.biernat.week7.dao.impl.NewsDao;
import pl.biernat.week7.integration.model.Article;
import pl.biernat.week7.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NewsDaoImpl implements NewsDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveNews(Article article) {
        String sql = "INSERT INTO news VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getId(), article.getAuthor(), article.getTitle(), article.getDescription(), article.getContent());
    }

    @Override
    public List<News> findAllNews() {
        List<News> news = new ArrayList<>();
        String sql = "SELECT * FROM news";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        results.forEach(element -> news.add(new News(
                Long.parseLong(String.valueOf(element.get("news_id"))),
                String.valueOf(element.get("author")),
                String.valueOf(element.get("title")),
                String.valueOf(element.get("description")),
                String.valueOf(element.get("content"))
        )));
        return news;
    }

    @Override
    public void updateNews(News news) {
        String sql = "UPDATE news SET news.author = ?, news.title = ?, news.description = ?, news.content = ? WHERE news_id = ?";
        jdbcTemplate.update(sql, news.getAuthor(), news.getTitle(), news.getDescription(), news.getContent(), news.getId());
    }
}

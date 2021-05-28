package pl.biernat.week7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.biernat.week7.model.News;
import pl.biernat.week7.service.NewsService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/news")
public class NewsApi {

    private final NewsService newsService;

    @Autowired
    public NewsApi(NewsService newsService) {
        this.newsService = newsService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void prepareNews() {
        newsService.fetchNews();
    }

    @GetMapping("/all")
    public ResponseEntity<List<News>> getArticles() {
        List<News> response = newsService.findAllNews();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<News> updateCar(@RequestBody News news) {
        newsService.updateNews(news);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}

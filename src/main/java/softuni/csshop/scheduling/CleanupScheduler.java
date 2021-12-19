package softuni.csshop.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import softuni.csshop.repository.ArticleRepository;
import softuni.csshop.service.ProductService;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class CleanupScheduler {

    private final ProductService productService;
    private final ArticleRepository articleRepository;


    public CleanupScheduler(ProductService productService, ArticleRepository articleRepository) {
        this.productService = productService;

        this.articleRepository = articleRepository;
    }

    @Scheduled(cron = "0 1 * * * ?")
    public void deleteNonActiveProducts() {
       this.productService.deleteNonActiveProducts();
    }

    @Scheduled(cron = "0 2 * * * ?")
    public void deleteArticleAfterMonth() {
        LocalDate date = LocalDate.now().minus(30, DAYS);
        this.articleRepository.deleteByDateIsBefore(date);    }
}

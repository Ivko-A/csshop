package softuni.csshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.csshop.model.Article;

import java.time.LocalDate;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
void deleteByDateIsBefore(LocalDate date);
}

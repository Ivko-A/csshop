package softuni.csshop.service;

import softuni.csshop.model.binding.ArticleAddBindingModel;
import softuni.csshop.model.view.ArticleViewModel;

import java.util.List;

public interface ArticleService {
    void addArticle(ArticleAddBindingModel articleAddBindingModel);

    List<ArticleViewModel> findAllArticles();

    void delete(String id);
}

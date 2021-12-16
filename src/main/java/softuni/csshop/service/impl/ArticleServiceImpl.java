package softuni.csshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.csshop.model.Article;
import softuni.csshop.model.binding.ArticleAddBindingModel;
import softuni.csshop.model.view.ArticleViewModel;
import softuni.csshop.repository.ArticleRepository;
import softuni.csshop.service.ArticleService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addArticle(ArticleAddBindingModel articleAddBindingModel) {
        Article article = modelMapper.map(articleAddBindingModel, Article.class);
        article.setDate(LocalDate.now());

        this.articleRepository.save(article);
    }

    @Override
    public List<ArticleViewModel> findAllArticles() {

        return this.articleRepository
                .findAll()
                .stream()
                .map(article -> {

                    return this.modelMapper
                            .map(article, ArticleViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.articleRepository.deleteById(id);
    }
}

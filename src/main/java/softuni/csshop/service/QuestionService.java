package softuni.csshop.service;

import softuni.csshop.model.binding.QuestionAddBindingModel;
import softuni.csshop.model.view.QuestionViewModel;

import java.util.List;

public interface QuestionService {

    void saveQuestion(QuestionAddBindingModel questionAddBindingModel);
    List<QuestionViewModel> findAllQuestions();
    void delete(String id);
}

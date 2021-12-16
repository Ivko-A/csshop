package softuni.csshop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.csshop.model.Question;
import softuni.csshop.model.binding.QuestionAddBindingModel;
import softuni.csshop.model.view.QuestionViewModel;
import softuni.csshop.repository.QuestionRepository;
import softuni.csshop.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(ModelMapper modelMapper, QuestionRepository questionRepository) {
        this.modelMapper = modelMapper;
        this.questionRepository = questionRepository;
    }

    @Override
    public void saveQuestion(QuestionAddBindingModel questionAddBindingModel) {

        Question question = modelMapper.map(questionAddBindingModel, Question.class);

        this.questionRepository.save(question);



    }

    @Override
    public List<QuestionViewModel> findAllQuestions() {
        return this.questionRepository
                .findAll()
                .stream()
                .map(question -> {

                    return this.modelMapper
                            .map(question, QuestionViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.questionRepository.deleteById(id);
    }
}

package softuni.csshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.csshop.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
}

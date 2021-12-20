package softuni.csshop.model.binding;

import org.hibernate.validator.constraints.Length;
import softuni.csshop.model.UserEntity;

public class QuestionAddBindingModel {
    private String email;
    private String textContent;

    public QuestionAddBindingModel() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 9, message = "Question must be at least 10 character")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}

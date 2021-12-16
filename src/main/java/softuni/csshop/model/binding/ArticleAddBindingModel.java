package softuni.csshop.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ArticleAddBindingModel {
    private String name;
    private String picture;
    private String textContent;

    public ArticleAddBindingModel() {
    }

    @Length(min = 5, message = "Name must at least 6 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    @Length(min = 20, message = "Article must at least 21 characters")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}

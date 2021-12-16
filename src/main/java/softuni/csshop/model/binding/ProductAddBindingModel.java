package softuni.csshop.model.binding;

import org.hibernate.validator.constraints.Length;
import softuni.csshop.model.Category;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductAddBindingModel {
    private String name;
    private Category category;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String picture;

    public ProductAddBindingModel() {
    }

    @Length(min = 4, message = "Name must at least 5 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Product must have a category")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Length(min = 9, message = "Name must at least 10 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @DecimalMin(value = "0", message = "Price must be a positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @DecimalMin(value = "0", message = "Quantity must be a positive number")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @NotNull
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

package softuni.csshop.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    private String email;
    private String password;
    private List<RoleEntity> roles = new ArrayList<>();
//    private List<Product> cart = new ArrayList<>();
//    private List<Product> orders = new ArrayList<>();

    public UserEntity() {
    }
    @Email(message = "Please enter a valid email address")
    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

//    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
//    @JoinColumn(name = "product_id")
//    public List<Product> getCart() {
//        return cart;
//    }
//
//    public void setCart(List<Product> cart) {
//        this.cart = cart;
//    }
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
//    @JoinColumn(name = "products_id")
//    public List<Product> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Product> orders) {
//        this.orders = orders;
//    }
}

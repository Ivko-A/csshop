package softuni.csshop.model.view;

import softuni.csshop.model.RoleEntity;

import java.util.List;

public class UserViewModel {
    private String id;
    private String email;
    private List<RoleEntity> roles;

    public UserViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}

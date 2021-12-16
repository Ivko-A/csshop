package softuni.csshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    private String role;

    public RoleEntity() {
    }

    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }

    public RoleEntity setRole(String role) {
        this.role = role;
        return this;
    }
}

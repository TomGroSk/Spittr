package pl.gromadzki.spittr.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLE", uniqueConstraints = {@UniqueConstraint(columnNames = { "ROLE", "USER_NAME" })})
public class UserRole {
    private Integer userRoleId;
    private User user;
    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    public Integer getUserRoleId() {
        return userRoleId;
    }
    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NAME", nullable = false)
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "ROLE", nullable = false)
    public String getRole() {
        return this.role;
    }
    public void setRole(String role) {
        this.role = role;

    }
}

package com.hobin.crm.model;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity()
@Table(name="users")
@TableGenerator(name = "user-id-generator",
        table = "sequences", pkColumnName = "sequence", pkColumnValue = "users", valueColumnName = "last_used",
        initialValue = 10000, allocationSize = 1)
public class User implements Serializable {
    private static final long serialVersionUID = 4485360885440977225L;

    /**
     * Define a DTO view without the password.
     */
    public interface WithoutPasswordView {}

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user-id-generator")
    @JsonView(WithoutPasswordView.class)
    private Long id;

    @Column(unique=true, length=64, nullable=false)
    @JsonView(WithoutPasswordView.class)
    private String username;

    @Column(length=128, nullable=false)
    private String password;

    @Column(name = "nosalt_password", length = 128, nullable = false)
    private String noSaltPassword;

    @Column(length = 16, nullable = false)
    private String salt;

    @Column(length=128, nullable=false, unique = true)
    @JsonView(WithoutPasswordView.class)
    private String phone;

    @Column(length=8, nullable = false)
    @JsonView(WithoutPasswordView.class)
    private UserType type;

    @Column(length=8)
    @JsonView(WithoutPasswordView.class)
    private UserStatus status;

    @Column(name = "registered_at", nullable = false)
    @JsonView(WithoutPasswordView.class)
    private Timestamp registeredAt;

    @Column(length = 512)
    @JsonView(WithoutPasswordView.class)
    private String avatar;

    @Column(name = "im_token", length = 256)
    @JsonView(WithoutPasswordView.class)
    private String imToken;

    public User() {

    }

    public User(String username, String password, String phone, UserType type) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.type = type;
        this.status = UserStatus.REGISTERED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoSaltPassword() {
        return noSaltPassword;
    }

    public void setNoSaltPassword(String noSaltPassword) {
        this.noSaltPassword = noSaltPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }
}

package com.github.leonroy.datademo.user;

import com.github.leonroy.datademo.user.role.Role;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "my_user") // postgres reserved word "com.github.leonroy.datademo.user" not allowed
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true) // enforce email is not re-used by another com.github.leonroy.datademo.user
    @Email(message = "Malformed email")
    @NotBlank(message = "Email required")
    private String email;

    public String getEmail() {
        return email.toLowerCase();
    }

    public User setEmail(String email) {
        this.email = email.toLowerCase();
        return this;
    }

    @NotBlank(message = "Password is required.")
    private String password;

    private String fullName;

    @Column
    private Boolean enabled = false;

    @Column
    private Instant deletedAt;

    @NotNull
    private Instant createdAt = Instant.now();

    @LastModifiedDate
    private Instant updatedAt;

    @ManyToMany //(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = Collections.unmodifiableSet(new HashSet<>());

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private HashMap<String, Object> properties = new HashMap<>();

}

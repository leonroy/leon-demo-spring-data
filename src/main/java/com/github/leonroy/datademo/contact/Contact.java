package com.github.leonroy.datademo.contact;

import com.github.leonroy.datademo.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@CompoundIndexes({
        @CompoundIndex(name = "user_state_idx", def = "{'ownerId' : 1, 'state': 1}"),
        @CompoundIndex(name = "id_user_idx", def = "{'id' : 1, 'ownerId': 1}")
})
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "com/github/leonroy/datademo/user")
@Document(collection = "contacts")
public class Contact {

    @Id
    private String id;

    @TextIndexed
    private String firstName;

    @TextIndexed
    private String lastName;

    @TextScore
    private Float score;

    private String role;

    @Setter(AccessLevel.NONE)
    @Transient
    private User user;

    @NotNull
    private Instant createdAt = Instant.now();

    /*
     * Needed for MongoDB since it can't persist methods only fields.
     */
    @NotBlank(message = "Used ID required")
    @Indexed
    private String userId;

    public Contact setUser(User user) {
        this.user = user;
        this.setUserId(String.valueOf(user.getId())); // needed for Mongo since it can't persist methods only fields
        return this;
    }

}

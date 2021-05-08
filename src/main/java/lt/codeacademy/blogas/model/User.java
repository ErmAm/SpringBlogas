package lt.codeacademy.blogas.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // 05-07 TODO šitas  kels bėdų ir vėliau. Time objektas nešneka su hiberneitu
//    @NotBlank
//    private Timestamp dateCreated;


//    TODO sąryšiai kiek suprantu vienas vartotojas gali turėti daug įrašų ir gali atlikti komentarus

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<BlogRecord> userBlogRecordsList;

//    Taip pat useris kuria komentarus


//    private BlogRecord blogRecord;


}

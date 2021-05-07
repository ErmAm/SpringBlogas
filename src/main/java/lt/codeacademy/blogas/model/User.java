package lt.codeacademy.blogas.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String name;

    @NotBlank
    private String surname;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @NotBlank
    private LocalDateTime dateCreated;


//    TODO sąryšiai kiek suprantu vienas vartotojas gali turėti daug įrašų ir gali atlikti komentarus

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<BlogRecord> userBlogRecordsList;

//    Taip pat useris kuria komentarus


//    private BlogRecord blogRecord;


}

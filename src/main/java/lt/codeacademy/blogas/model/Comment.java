package lt.codeacademy.blogas.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="comment_id")
    private BlogRecord blogRecord;
}

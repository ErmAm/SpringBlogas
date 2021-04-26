package lt.codeacademy.blogas.model;


import lombok.Data;

import java.util.UUID;

@Data
public class BlogRecord {

    private UUID id;
    private String name;
    private String username;
    private String text;

}

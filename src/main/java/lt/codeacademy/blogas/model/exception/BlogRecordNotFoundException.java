package lt.codeacademy.blogas.model.exception;

public class BlogRecordNotFoundException extends RuntimeException{

    private final String blogID;

    public BlogRecordNotFoundException(String blogID) {
        this.blogID = blogID;
    }

}

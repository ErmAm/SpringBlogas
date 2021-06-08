package lt.codeacademy.blogas.model.exception;

public class CommentNotFoundException extends RuntimeException{

    private final String commentID;

    public CommentNotFoundException(String commentID) {
        this.commentID = commentID;
    }
}

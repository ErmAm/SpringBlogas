package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.Comment;
import lt.codeacademy.blogas.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService{


    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getComment(UUID id) {
        return null;
    }

    /** TODO Nereikia pamiršti suimplimentinti errora jei nebus rastas komentaras.
     *
     *     @Override
     *     public Product getProduct(UUID id) {
     *         return jpaProductRepository.findById(id)
     *         return productRepository.findById(id)
     *                 .orElseThrow(ProductNotFoundException::new);
     * */

    @Override
    public List<Comment> getComments() {
        return null;
    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void delete(UUID id) {

    }
}

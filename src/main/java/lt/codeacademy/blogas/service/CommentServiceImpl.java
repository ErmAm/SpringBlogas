package lt.codeacademy.blogas.service;

import lt.codeacademy.blogas.model.Comment;
import lt.codeacademy.blogas.model.exception.BlogRecordNotFoundException;
import lt.codeacademy.blogas.model.exception.CommentNotFoundException;
import lt.codeacademy.blogas.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{


    private CommentRepository commentRepository;
    private UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public void addComment(Comment comment) {

        commentRepository.save(comment);
    }

    @Override
    public Comment getComment(UUID id) {
        return commentRepository.findById(id).orElseThrow(() -> new BlogRecordNotFoundException(id.toString()));
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
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void delete(UUID id) {
         Optional<Comment> blogForDelete = commentRepository.findById(id);
        if (blogForDelete.isPresent()){
            commentRepository.deleteById(id);
        }else {
            throw new CommentNotFoundException(id.toString());
        }

    }

// 05-25 Filtruojam pagal paduota blogo id iš visų kommentarų ir grąžinam pageidaujamą įrašą.

    public List<Comment> filteredByBlogComments(UUID id){

//        Kažką riekia sugalvoti su erroru jei turime tuščia listą.
        if (!getAllComments().isEmpty()){
            List<Comment> filteredByBlogCommentList = getAllComments()
                    .stream()
                    .filter(com -> com.getBlogRecord().getId().equals(id))
                    .collect(Collectors.toList());
            return filteredByBlogCommentList;
        }else {
            return null;
        }

    }


//  List<Customer> charlesWithMoreThan100Points = customers
//  .stream()
//  .filter(c -> c.getPoints() > 100 && c.getName().startsWith("Charles"))
//  .collect(Collectors.toList());
//
//  assertThat(charlesWithMoreThan100Points).hasSize(1);
//  assertThat(charlesWithMoreThan100Points).contains(charles);

}

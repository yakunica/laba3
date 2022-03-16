package com.irinushirka.operators;
import com.irinushirka.users.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
public class OperatorsController {

    @Autowired
    OperatorsRepository operatorsRepository;

    // Get All Notes
    @GetMapping("/operators")
    public List<Operators> getAllNotes() {
        return operatorsRepository.findAll();
    }

    @PostMapping("/operators")
    public Operators createNote(@RequestBody Operators book) {
        return operatorsRepository.save(book);
    }

    @DeleteMapping("/operators/{id}")
    public Boolean deleteBook(@PathVariable(value = "id") Integer bookId) throws UserNotFoundException {
        Operators book = operatorsRepository.findById(bookId)
                .orElseThrow(() -> new UserNotFoundException(bookId));

        operatorsRepository.delete(book);

        return true;//ResponseEntity.ok().build();
    }

    @PutMapping("/operators/{id}")
    public Operators updateNote(@PathVariable(value = "id") Integer bookId,
                                @RequestBody Operators bookDetails) throws UserNotFoundException {

        Operators book = operatorsRepository.findById(bookId)
                .orElseThrow(() -> new UserNotFoundException(bookId));

        book.setFirst_name(bookDetails.getFirst_name());
        book.setAccount(bookDetails.getAccount());
        book.setLast_name(bookDetails.getLast_name());
        book.setSecond_name(bookDetails.getSecond_name());
        book.setAccumulated_tenure(bookDetails.getAccumulated_tenure());

        Operators updatedBook = operatorsRepository.save(book);

        return updatedBook;
    }
}
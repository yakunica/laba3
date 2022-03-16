package com.irinushirka.document;



import com.irinushirka.users.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentControllers {

    @Autowired
    DocumentRepository tasksRepository;

    // Get All Notes
    @GetMapping("/documents")
    public List<Documents> getAllNotes() {
        return tasksRepository.findAll();
    }

    @PostMapping("/documents")
    public Documents createNote(@RequestBody Documents book) {
        return tasksRepository.save(book);
    }

    @DeleteMapping("/documents/{id}")
    public Boolean deleteBook(@PathVariable(value = "id") Integer bookId) throws UserNotFoundException {
        Documents book = tasksRepository.findById(bookId)
                .orElseThrow(() -> new UserNotFoundException(bookId));

        tasksRepository.delete(book);
        return true;//ResponseEntity.ok().build();
    }

    @PutMapping("/documents/{id}")
    public Documents updateNote(@PathVariable(value = "id") Integer bookId,
                                @RequestBody Documents bookDetails) throws UserNotFoundException {

        Documents book = tasksRepository.findById(bookId)
                .orElseThrow(() -> new UserNotFoundException(bookId));

book.setClient(bookDetails.getClient());
book.setDate_uploaded(bookDetails.getDate_uploaded());
book.setDate_verified(bookDetails.getDate_verified());
book.setDocument_id(bookDetails.getDocument_id());
book.setDocument_issuer(bookDetails.getDocument_issuer());
book.setDocument_number(bookDetails.getDocument_number());
book.setDocumentType(bookDetails.getDocumentType());
book.setExpiry_date(bookDetails.getExpiry_date());


        Documents updatedBook = tasksRepository.save(book);

        return updatedBook;
    }
}
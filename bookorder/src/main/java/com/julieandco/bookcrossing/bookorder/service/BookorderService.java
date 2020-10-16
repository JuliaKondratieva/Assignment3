package com.julieandco.bookcrossing.bookorder.service;

import com.julieandco.bookcrossing.bookorder.entity.Bookorder;
import com.julieandco.bookcrossing.bookorder.entity.dto.BookDTO;
import com.julieandco.bookcrossing.bookorder.entity.dto.CustomerDTO;
import com.julieandco.bookcrossing.bookorder.entity.dto.SubmitBookorderDTO;
import com.julieandco.bookcrossing.bookorder.repo.BookorderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookorderService {
    //private final BookRepository bookRepository;
    private final BookorderRepo bookorderRepo;
    //private final UserRepository userRepository;

    @Autowired
    public BookorderService(BookorderRepo bookorderRepo) {
        //this.bookRepository = bookRepository;
        this.bookorderRepo = bookorderRepo;
    }

    @Transactional
    public void addOrder(BookDTO orderedBook, CustomerDTO user){
        //Book bookel=bookRepository.findBookByTitle(orderedBook.getTitle());
        //User user1=userRepository.findByUsername(user.getUsername());
        Bookorder newOrder;
        System.out.println("BOKID\n");/////null

        System.out.println("BOOKID: "+orderedBook.getId().toString());/////null

        newOrder = null;
        List<Bookorder> findById;
        findById = bookorderRepo.findByBookId(orderedBook.getId());
        System.out.println("----------BOOKORDERSERVICE--------");
        //System.out.println("FINDBYIDLIST: 1 "+bookorderRepo.findByBookId(orderedBook.getId()).get(0).toString() +" 2- "+bookorderRepo.findByBookId(orderedBook.getId()).get(1));
        if(findById.contains(orderedBook)){
            newOrder=new Bookorder();
            newOrder.setBookId(orderedBook.getId());
            newOrder.setCustomerId(user.getId());
            newOrder.setSubmitted(false);
            System.out.println("SAVING TO REPO");
            bookorderRepo.save(newOrder);
            System.out.println("You are in waiting list \n");
        }

        else {
            System.out.println("ELSE LOOP SAVING nEW ORDER");
            newOrder = new Bookorder();
            newOrder.setBookId(orderedBook.getId());
            newOrder.setCustomerId(user.getId());
            bookorderRepo.save(newOrder);
        }
        //bookel.setAvailable(false);
        //bookRepository.save(bookel);

    }

    @Transactional
    public List<Bookorder> findByBookId(UUID id){
        return bookorderRepo.findByBookId(id);
    }


    @Transactional(readOnly = true)
    public List<Bookorder> getAllOrders() {
        return bookorderRepo.findAll();
    }

    @Transactional
    public void saveOrder(Bookorder bookorder){
        //if(bookorderRepo.findByBookId(bookorder.getBookId()) == null){
            bookorderRepo.save(bookorder);
        //}
    }

    @Transactional
    public void deleteOrder(Bookorder bookorder){
        List<Bookorder> ordersById = new ArrayList<>();
        LocalDateTime min = LocalDateTime.now();
        Bookorder nextOrder=null;
        ordersById = bookorderRepo.findByBookId(bookorder.getBookId());
        if(ordersById!= null){
            for(Bookorder o: ordersById){
                if(o.getFromDate().isBefore(min)&&o!=bookorder){
                    min=o.getFromDate();
                    nextOrder=o;
                }
            }
            bookorderRepo.delete(bookorder);
            nextOrder.setSubmitted(true);
            nextOrder.setFromDate(LocalDateTime.now());
            bookorderRepo.save(nextOrder);
        }
    }

}

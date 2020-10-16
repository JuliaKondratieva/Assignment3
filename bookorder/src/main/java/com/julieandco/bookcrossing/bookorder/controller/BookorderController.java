package com.julieandco.bookcrossing.bookorder.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.julieandco.bookcrossing.bookorder.entity.Bookorder;
import com.julieandco.bookcrossing.bookorder.entity.dto.*;
import com.julieandco.bookcrossing.bookorder.service.BookorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookorders")
public class BookorderController {
    private static final String URL = "http://localhost:8001";
    private static final String URLC = "http://localhost:8002";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);
    private final BookorderService bookorderService;

    @Autowired
    public BookorderController(BookorderService bookorderService) {
        this.bookorderService = bookorderService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Void> submitOrder(@RequestBody String deliverJson){
        boolean submitted = false;
        boolean custFound = false;
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        SubmitBookorderDTO deliver = gson.fromJson(deliverJson, SubmitBookorderDTO.class);
        CustomerDTO user = deliver.getUser();
        System.out.println("RECEIVED FROM JSON USER: "+user.getUsername());

        BookDTO book = deliver.getBook();
        System.out.println("RECEIVED FROM JSON BOOK: "+book.getTitle());

        BookDTO bookDTO=new BookDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        System.out.println("SENDING CUSTOMER REQUEST");

        ResponseEntity<CustomersDTO> response5 = restTemplate
                .exchange(URLC + "/api/customers", HttpMethod.GET, headersEntity, CustomersDTO.class);

        for (CustomerDTO c : Objects.requireNonNull(response5.getBody()).getUsers()) {
            //System.out.println(b);
            System.out.println("RECEIVED REQUEST: c: "+c.getUsername());
            if(c.getUsername().equals(user.getUsername())) {
                System.out.println("C==USER");
                System.out.println("сID: "+ c.getId().toString());

                customerDTO=c;
                custFound=true;
                break;
            }
        }
        //get books and check if uuid exists
        System.out.println("SENDING BOOK REQUEST");
        ResponseEntity<BooksDTO> response6 = restTemplate
                .exchange(URL + "/api/books", HttpMethod.GET, headersEntity, BooksDTO.class);

        for (BookDTO b : Objects.requireNonNull(response6.getBody()).getBooks()) {
            //System.out.println(b);
            System.out.println("RECEIVED REQUEST: b: "+b.getTitle());
            if(b.getTitle().equals(book.getTitle())) {
                System.out.println("B==BOOK");
                System.out.println("bID: "+ b.getId().toString());
                bookDTO=b;
                submitted=true;
                break;
            }
        }
        if(!submitted)
            System.out.println("Book doesnt exist!");
        System.out.println("bookID "+bookDTO.getId().toString());
        if(submitted&&custFound)
            bookorderService.addOrder(bookDTO, customerDTO);

        return ResponseEntity.ok().build();
    }

    /*@GetMapping
    public @ResponseBody
    BookorderDTO getAllOrders(){
        BookorderDTO ordersDTO = new BookorderDTO();
        List<Bookorder> orders = bookorderService.getAllOrders();
        for(Bookorder b: orders){
            SubmitBookorderDTO bookorderDTO=new SubmitBookorderDTO(b.getBookId(), b.getCustomerId());
            ordersDTO.getBookorders().add(bookorderDTO);
        }
        return ordersDTO;
    }*/

    @PostMapping("/save")
    public ResponseEntity<Void> saveBookorder(@RequestBody SubmitBookorderDTO bookorderDTO){
        CustomerDTO customerUUID = bookorderDTO.getUser();
        BookDTO bookUUID = bookorderDTO.getBook();
        LocalDateTime fromDate = bookorderDTO.getFromDate();
        LocalDateTime dueDate=bookorderDTO.getDueDate();
        boolean deliveryState = bookorderDTO.isDeliveryState();
        boolean submitted = bookorderDTO.isSubmitted();
        Bookorder bookorder=new Bookorder(customerUUID.getId(), bookUUID.getId(), fromDate, dueDate, deliveryState, submitted);
        bookorderService.saveOrder(bookorder);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public void deleteOrder(@RequestBody SubmitBookorderDTO bookorderDTO){
        CustomerDTO customerUUID = bookorderDTO.getUser();
        BookDTO bookUUID = bookorderDTO.getBook();
        LocalDateTime fromDate = bookorderDTO.getFromDate();
        LocalDateTime dueDate=bookorderDTO.getDueDate();
        boolean deliveryState = bookorderDTO.isDeliveryState();
        boolean submitted = bookorderDTO.isSubmitted();
        Bookorder bookorder=new Bookorder(customerUUID.getId(), bookUUID.getId(), fromDate, dueDate, deliveryState, submitted);
        bookorderService.deleteOrder(bookorder);
    }

    @PutMapping("/put")
    public ResponseEntity<Void> putOrder(@RequestBody SubmitBookorderDTO bookorderDTO){
        CustomerDTO customerUUID = bookorderDTO.getUser();
        BookDTO bookUUID = bookorderDTO.getBook();
        LocalDateTime fromDate = bookorderDTO.getFromDate();
        LocalDateTime dueDate=bookorderDTO.getDueDate();
        boolean deliveryState = bookorderDTO.isDeliveryState();
        boolean submitted = bookorderDTO.isSubmitted();
        Bookorder bookorder=new Bookorder(customerUUID.getId(), bookUUID.getId(), fromDate, dueDate, deliveryState, submitted);
        bookorderService.saveOrder(bookorder);
        return ResponseEntity.ok().build();
    }
}

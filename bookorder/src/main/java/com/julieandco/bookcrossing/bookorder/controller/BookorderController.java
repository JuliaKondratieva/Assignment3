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
        UUID user = deliver.getUser();
        UUID book = deliver.getBook();
        BookDTO bookDTO=new BookDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        ResponseEntity<CustomersDTO> response5 = restTemplate
                .exchange(URL + "/api/books", HttpMethod.GET, headersEntity, CustomersDTO.class);

        for (CustomerDTO c : Objects.requireNonNull(response5.getBody()).getUsers()) {
            //System.out.println(b);

            if(c.getId()==user) {
                customerDTO=c;
                custFound=true;
                break;
            }
        }
        //get books and check if uuid exists
        ResponseEntity<BooksDTO> response6 = restTemplate
                .exchange(URL + "/api/books", HttpMethod.GET, headersEntity, BooksDTO.class);

        for (BookDTO b : Objects.requireNonNull(response6.getBody()).getBooks()) {
            //System.out.println(b);

            if(b.getId()==book) {
                bookDTO=b;
                submitted=true;
                break;
            }
        }
        if(submitted==false)
            System.out.println("Book doesnt exist!");
        if(submitted==true&&custFound==true)
            bookorderService.addOrder(bookDTO, customerDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    BookorderDTO getAllOrders(){
        BookorderDTO ordersDTO = new BookorderDTO();
        List<Bookorder> orders = bookorderService.getAllOrders();
        for(Bookorder b: orders){
            SubmitBookorderDTO bookorderDTO=new SubmitBookorderDTO(b.getBookId(), b.getCustomerId());
            ordersDTO.getBookorders().add(bookorderDTO);
        }
        return ordersDTO;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveBookorder(@RequestBody SubmitBookorderDTO bookorderDTO){
        UUID customerUUID = bookorderDTO.getUser();
        UUID bookUUID = bookorderDTO.getBook();
        LocalDateTime fromDate = bookorderDTO.getFromDate();
        LocalDateTime dueDate=bookorderDTO.getDueDate();
        boolean deliveryState = bookorderDTO.isDeliveryState();
        boolean submitted = bookorderDTO.isSubmitted();
        Bookorder bookorder=new Bookorder(customerUUID, bookUUID, fromDate, dueDate, deliveryState, submitted);
        bookorderService.saveOrder(bookorder);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public void deleteOrder(@RequestBody SubmitBookorderDTO bookorderDTO){
        UUID customerUUID = bookorderDTO.getUser();
        UUID bookUUID = bookorderDTO.getBook();
        LocalDateTime fromDate = bookorderDTO.getFromDate();
        LocalDateTime dueDate=bookorderDTO.getDueDate();
        boolean deliveryState = bookorderDTO.isDeliveryState();
        boolean submitted = bookorderDTO.isSubmitted();
        Bookorder bookorder=new Bookorder(customerUUID, bookUUID, fromDate, dueDate, deliveryState, submitted);
        bookorderService.deleteOrder(bookorder);
    }

    @PutMapping("/put")
    public ResponseEntity<Void> putOrder(@RequestBody SubmitBookorderDTO bookorderDTO){
        UUID customerUUID = bookorderDTO.getUser();
        UUID bookUUID = bookorderDTO.getBook();
        LocalDateTime fromDate = bookorderDTO.getFromDate();
        LocalDateTime dueDate=bookorderDTO.getDueDate();
        boolean deliveryState = bookorderDTO.isDeliveryState();
        boolean submitted = bookorderDTO.isSubmitted();
        Bookorder bookorder=new Bookorder(customerUUID, bookUUID, fromDate, dueDate, deliveryState, submitted);
        bookorderService.saveOrder(bookorder);
        return ResponseEntity.ok().build();
    }
}

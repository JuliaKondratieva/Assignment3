package com.julieandco.bookcrossing.box.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.julieandco.bookcrossing.box.entity.Box;
import com.julieandco.bookcrossing.box.entity.dto.*;
import com.julieandco.bookcrossing.box.repo.BoxRepo;
import com.julieandco.bookcrossing.box.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {
    private final BoxService boxService;
    private final BoxRepo boxRepository;
    private static final String URL = "http://localhost:8001";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);


    @Autowired
    public BoxController(BoxService boxService, BoxRepo boxRepository) {
        this.boxService = boxService;
        this.boxRepository=boxRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveBox(@RequestBody String addressJson){
        Box newBox=new Box();
        newBox.setAddress(addressJson);
        boxService.addBox(newBox);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/deliver")
    public ResponseEntity<Void> deliverProducts(@RequestBody String deliverJson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DeliveryDTO deliver = gson.fromJson(deliverJson, DeliveryDTO.class);
        BookDTO book = deliver.getBook();
        BoxDTO putIn = deliver.getBox();
        UUID foundBook = null;
        BookDTO bookDTO=new BookDTO();

        ResponseEntity<BooksDTO> response6 = restTemplate
                .exchange(URL + "/api/books", HttpMethod.GET, headersEntity, BooksDTO.class);

        for (BookDTO b : Objects.requireNonNull(response6.getBody()).getBooks()) {
            //System.out.println(b);
            if (b.getId() == book.getId()) {
                bookDTO=b;
                foundBook = book.getId();
                break;
            }
        }
        Box foundBox = boxService.findByAddress(putIn.getAddress());
        if(foundBook!=null)
            boxService.addBook(foundBox, bookDTO);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkOut(@RequestBody String deliverJson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DeliveryDTO deliver = gson.fromJson(deliverJson, DeliveryDTO.class);

        BookDTO bookOut = deliver.getBook();
        BoxDTO takeOut = deliver.getBox();
        Box foundBox = boxService.findByAddress(takeOut.getAddress());
        UUID foundBook=null;
        BookDTO bookDTO=new BookDTO();

        ResponseEntity<BooksDTO> response7 = restTemplate
                .exchange(URL + "/api/books", HttpMethod.GET, headersEntity, BooksDTO.class);

        for (BookDTO b : Objects.requireNonNull(response7.getBody()).getBooks()) {
            //System.out.println(b);
            if (b.getId() == bookOut.getId()) {
                bookDTO=b;
                foundBook = bookOut.getId();
            }
        }

        /*List<Bookorder> toPickup = orderService.findByBookId(foundBook.getId());
        Bookorder checkedOut=new Bookorder();
        for(Bookorder o: toPickup){
            if(o.getSubmitted())
                checkedOut = o;
        }*/
        if(foundBook!=null)
            boxService.checkOut(foundBox, bookDTO);
        return ResponseEntity.ok().build();
    }
}

package com.julieandco.bookcrossing.mediator.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.julieandco.bookcrossing.mediator.dto.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookorders")
public class BookorderController {
    private static final String URL = "http://localhost:8003";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @PostMapping("/submit")
    public ResponseEntity<Void> submitOrder(@RequestBody String deliverJson){
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        SubmitBookorderDTO deliver = gson.fromJson(deliverJson, SubmitBookorderDTO.class);
        UUID user = deliver.getUser();
        UUID book = deliver.getBook();
        System.out.println("USER UUID: "+ user.toString());
        System.out.println("BOOK UUID: "+ book.toString());

        BookDTO bookDTO=new BookDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        HttpEntity<String> deliverJsonNew = new HttpEntity<>(deliverJson, headers);
        ResponseEntity<Void> response1 = restTemplate
                .exchange(URL + "/api/bookorders/submit", HttpMethod.POST, deliverJsonNew, Void.class);

        //String deliverJsonStr = deliverJson;
        //HttpEntity<String> deliverJsonNew = new HttpEntity<>(deliverJsonStr, headers);
        //ResponseEntity<Void> response3 = restTemplate
          //      .exchange(URL + "/api/bookorders/submit", HttpMethod.POST,
            //            deliverJsonNew, Void.class); ///so not sure

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    BookorderDTO getAllOrders(){
        ResponseEntity<BookorderDTO> response2 = restTemplate
                .exchange(URL + "/api/bookorders", HttpMethod.GET, headersEntity, BookorderDTO.class);
        List<SubmitBookorderDTO> bookordersFromDB = Objects.requireNonNull(response2.getBody()).getBookorders();
        BookorderDTO bookorderDTO = new BookorderDTO();
        bookorderDTO.setBookorders(bookordersFromDB);
        return bookorderDTO;
    }

}

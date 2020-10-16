package com.julieandco.bookcrossing.mediator.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.julieandco.bookcrossing.mediator.dto.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {
    private static final String URL = "http://localhost:8004";//boxes port
    private static final String URLO = "http://localhost:8003";//boxes port
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @PostMapping("/save")
    public ResponseEntity<Void> saveBox(@RequestBody String addressJson){
        String deliverJsonStr = addressJson;
        HttpEntity<String> deliverJsonNew = new HttpEntity<>(deliverJsonStr, headers);
        ResponseEntity<Void> response3 = restTemplate
                .exchange(URL + "/api/boxes/save", HttpMethod.POST,
                        deliverJsonNew, Void.class); ///so not sure

        return ResponseEntity.ok().build();
    }

    @PostMapping("/deliver")
    public ResponseEntity<Void> deliverProducts(@RequestBody String deliverJson) {
        String deliverJsonStr = deliverJson;
        HttpEntity<String> deliverJsonNew = new HttpEntity<>(deliverJsonStr, headers);
        ResponseEntity<Void> response3 = restTemplate
                .exchange(URL + "/api/boxes/deliver", HttpMethod.POST,
                        deliverJsonNew, Void.class); ///so not sure
        ///find deliverred book and set it delivered or delete it

        /*Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DeliveryDTO deliver = gson.fromJson(deliverJson, DeliveryDTO.class);
        BookDTO book = deliver.getBook();
        //BoxDTO putIn = deliver.getBox();
        HttpEntity<SubmitBookorderDTO> saveOrder = new HttpEntity<>(SubmitBookorderDTO);
        ResponseEntity<Void> response1 = restTemplate
                .exchange(URL + "/api/books/save", HttpMethod.POST,
                        saveBook, Void.class);*/
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        DeliveryDTO deliver = gson.fromJson(deliverJson, DeliveryDTO.class);
        BookDTO book = deliver.getBook();
        SubmitBookorderDTO foundOrder = new SubmitBookorderDTO();
        ResponseEntity<BookorderDTO> response2 = restTemplate
                .exchange(URLO + "/api/bookorders", HttpMethod.GET, headersEntity, BookorderDTO.class);
        List<SubmitBookorderDTO> orders =new ArrayList<>();
        orders =(Objects.requireNonNull(response2.getBody()).getBookorders());
        for(SubmitBookorderDTO o: orders){
            /*if(o.getBook()==book.getId()){
                foundOrder = o;
                break;
            }*/ /////////////////////delete **//
        }
        if(foundOrder.isDeliveryState()){
            //delete order and next in order set
            HttpEntity<SubmitBookorderDTO> orderDelete = new HttpEntity<>(foundOrder);
            ResponseEntity<Void> response5 = restTemplate
                    .exchange(URLO + "/api/bookorders/delete", HttpMethod.DELETE,
                            orderDelete, Void.class);
        }
        else { //set delivered
            foundOrder.setDeliveryState(true);
            HttpEntity<SubmitBookorderDTO> orderDelivered = new HttpEntity<>(foundOrder);
            ResponseEntity<Void> response5 = restTemplate
                    .exchange(URLO + "/api/bookorders/put", HttpMethod.PUT,
                            orderDelivered, Void.class);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Void> checkOut(@RequestBody String deliverJson) {
        String deliverJsonStr = deliverJson;
        HttpEntity<String> deliverJsonNew = new HttpEntity<>(deliverJsonStr, headers);
        ResponseEntity<Void> response3 = restTemplate
                .exchange(URL + "/api/boxes/checkout", HttpMethod.POST,
                        deliverJsonNew, Void.class); ///so not sure
        return ResponseEntity.ok().build();
    }
}

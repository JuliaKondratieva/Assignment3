package com.julieandco.bookcrossing.box.service;

import com.julieandco.bookcrossing.box.entity.Box;
import com.julieandco.bookcrossing.box.entity.dto.BookDTO;
import com.julieandco.bookcrossing.box.repo.BoxRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BoxService {
    private final BoxRepo boxRepository;

    public BoxService(BoxRepo boxRepository) {
        this.boxRepository=boxRepository;
    }

    @Transactional
    public void addBox(Box box){
        if(boxRepository.findByAddress(box.getAddress()) == null){
            boxRepository.save(box);
        }
    }

    @Transactional
    public Box findByAddress(String address){
        return boxRepository.findByAddress(address);
    }


    @Transactional
    public void addBook(Box box, BookDTO receivedBook) {
        System.out.println("------SERVICE-----");
        Box foundBox = boxRepository.findByAddress(box.getAddress());
        System.out.println("BOXID: "+foundBox.getId().toString());
        System.out.println("BOOKID: "+receivedBook.getId().toString());

        Box newBox=new Box(foundBox.getId(), receivedBook.getId(), foundBox.getAddress());
        boxRepository.save(newBox);
        System.out.println("END OF ADDBOOK");
        //Bookorder recOrder = null;
        //List<Bookorder> byBookId = new ArrayList<>();
        //List<Bookorder> nextOrderQueue = new ArrayList<>();

        //Bookorder nextOrder = null;
        /*byBookId=orderRepository.findByBookTitle(receivedBook.getTitle());
        for(Bookorder o: byBookId){
            if(o.getSubmitted())
                recOrder=o;
            System.out.println("RECORDER"+o.toString());
        }*/

        //System.out.println("RECORDERAFTERFOR"+ (recOrder==null?"NULL":recOrder.toString()));

        /*if(recOrder!=null){
            Box toBox = boxRepository.findByAddress(box.getAddress());
            recOrder.getBook().setBoxId(toBox);
            bookRepository.save(recOrder.getBook());

            nextOrderQueue=orderRepository.findByBookTitle(receivedBook.getTitle());
            if(!nextOrderQueue.isEmpty()) {
                System.out.println("nextordernot empty");
                LocalDateTime min = LocalDateTime.now();
                for (Bookorder o : nextOrderQueue) {
                    if (min.isAfter(o.getFromDate())&&o.getId()!=recOrder.getId()) {
                        min = o.getFromDate();
                        nextOrder = o;
                    }
                    System.out.println("O in for"+o.toString());
                }
            }
            System.out.println(recOrder.getDeliveryState());
            System.out.println(recOrder.getSubmitted());
            if(recOrder.getDeliveryState()||!recOrder.getSubmitted()) {
                System.out.println(nextOrder);

                if (nextOrder == null) {
                    recOrder.getBook().setAvailable(true);
                    orderRepository.delete(recOrder);
                } else {
                    orderRepository.delete(recOrder);
                    nextOrder.setSubmitted(true);
                    nextOrder.setFromDate(LocalDateTime.now());
                    orderRepository.save(nextOrder);
                    System.out.println(nextOrder.getUser().getUsername() + ", Your Order was submitted. Expect!");
                }*/
            //}
        //}
    }

    @Transactional
    public void checkOut(Box box, BookDTO book){//order){
        //List<Bookorder> orderList = new ArrayList<>();
        //Bookorder toReceive;
        //orderList = orderRepository.findByBookId(bookorder.getBook().getId());//.setDeliveryState(true);
        //for(int i=0; i<orderList.size();++i)
        //{
          //  if(orderList.get(i).getSubmitted()) {
                /*toReceive = orderRepository.getOne(orderList.get(i).getId());
                toReceive.setDeliveryState(true);
                Book received = toReceive.getBook();
                received.setBoxId(null);
                bookRepository.save(received);
                orderRepository.save(toReceive);*/
            //}
        //}
        Box removing = boxRepository.findByBook(book.getId());
        boxRepository.delete(removing);
    }
}

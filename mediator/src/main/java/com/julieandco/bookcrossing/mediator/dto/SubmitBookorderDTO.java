package com.julieandco.bookcrossing.mediator.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubmitBookorderDTO {
    private UUID user;
    private UUID book;
    private LocalDateTime fromDate;
    private LocalDateTime dueDate;
    private boolean deliveryState;
    private boolean submitted;

    public SubmitBookorderDTO(){

    }

    public SubmitBookorderDTO(UUID book, UUID user){
        this.book=book;
        this.user=user;
    }


    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setDeliveryState(boolean deliveryState) {
        this.deliveryState = deliveryState;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public boolean isDeliveryState() {
        return deliveryState;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public UUID getBook() {
        return book;
    }

    public void setBook(UUID book) {
        this.book = book;
    }
}

package com.julieandco.bookcrossing.mediator.dto;

import java.util.List;

public class CustomersDTO {
    private List<CustomerDTO> users;

    public List<CustomerDTO> getUsers() {
        return users;
    }

    public void setUsers(List<CustomerDTO> users) {
        this.users = users;
    }
}

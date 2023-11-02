package com.booking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer extends Person {
    private double wallet;
    private Membership member;

    public Customer copy(){
        return Customer.builder()
                .id(this.getId())
                .name(this.getName())
                .address(this.getAddress())
                .wallet(this.getWallet())
                .member(this.getMember())
                .build();
    }
}

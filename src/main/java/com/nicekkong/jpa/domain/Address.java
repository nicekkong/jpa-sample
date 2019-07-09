package com.nicekkong.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter@Setter
@Embeddable
public class Address {

    private String street;

    private String city;

    private String state;

    private String zipCode;
}

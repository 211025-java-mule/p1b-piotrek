package com.revature.p1bpiotrek.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Country {
    private String country_id;
    private String probability;
}

package com.revature.p1bpiotrek.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Item {
    private String name;
    private List<Piece> countries;
}

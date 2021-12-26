package dev.hongtudio_v3.controller;

import dev.hongtudio_v3.domain_vo.Categories;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    public ItemForm() {
    }

    public ItemForm(Long id, String name, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

//    public ItemForm(Long id, String name, int price, int stockQuantity) {
//
//    }

}

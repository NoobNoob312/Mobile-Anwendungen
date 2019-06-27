package com.example.myt.DateFragment_Tabs;

public class Item {
    public enum ItemType {
        ADDDATE_ITEM, REMEMBER_ITEM;
    }

    private String name;
    private ItemType type;

    public Item(String n, ItemType type) {
        this.name = n;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}

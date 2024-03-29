package com.example.assignmentjspservlet.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart {
    private String shipName;
    private String shipPhone;
    private String shipAddress;
    private double totalPrice;
    private final HashMap<Integer, CartItem> mapItems;

    public ShoppingCart() {
        mapItems = new HashMap<>();
    }

    public void AddItemToCart(Product product, int quantity){
        CartItem item = null;
        if (mapItems.containsKey(product.getId())){
            item = mapItems.get(product.getId());
        }else {
            item = new CartItem();
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setProductThumbnail(product.getThumbnail());
            item.setUnitPrice(product.getPrice());
        }
        item.setQuantity(item.getQuantity() + quantity);
        mapItems.put(product.getId(),item);
    }

    public void updateItemFromCart(Product product, int quantity){
        CartItem item = null;
        if (mapItems.containsKey(product.getId())){
            item = mapItems.get(product.getId());
        }else {
            item = new CartItem();
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setProductThumbnail(product.getThumbnail());
            item.setUnitPrice(product.getPrice());
        }
        item.setQuantity(quantity);
        mapItems.put(product.getId(),item);
    }

    public void removeItemFromCart(Product product){
        mapItems.remove(product.getId());
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<CartItem> getListItems(){
        return new ArrayList<>(mapItems.values());
    }
}

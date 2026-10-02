package com.example.shoppinglist.services;

import com.example.shoppinglist.models.ShoppingItem;

import java.util.List;

public interface ShoppingListService {
    List<ShoppingItem> getAllItems();
    ShoppingItem addItem(String name);
    ShoppingItem markAsPurchased(Long id);
    void deleteItem(Long id);
}
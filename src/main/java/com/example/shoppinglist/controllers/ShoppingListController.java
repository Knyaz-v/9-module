package com.example.shoppinglist.controllers;

import com.example.shoppinglist.models.ShoppingItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.shoppinglist.services.ShoppingListService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shopping-list")
public class ShoppingListController {

    private final ShoppingListService service;

    public ShoppingListController(ShoppingListService service) {
        this.service = service;
    }

    @GetMapping
    public List<ShoppingItem> getAllItems() {
        return service.getAllItems();
    }

    @PostMapping
    public ResponseEntity<ShoppingItem> addItem(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        ShoppingItem newItem = service.addItem(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
    }

    @PutMapping("/{id}/purchase")
    public ResponseEntity<ShoppingItem> markAsPurchased(@PathVariable Long id) {
        ShoppingItem updatedItem = service.markAsPurchased(id);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
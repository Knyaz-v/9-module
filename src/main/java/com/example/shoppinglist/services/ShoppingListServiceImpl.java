package com.example.shoppinglist.services;

import com.example.shoppinglist.models.ShoppingItem;
import org.springframework.stereotype.Service;
import com.example.shoppinglist.repository.ShoppingListRepository;

import java.util.List;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository repository;

    public ShoppingListServiceImpl(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ShoppingItem> getAllItems() {
        return repository.findAll();
    }

    @Override
    public ShoppingItem addItem(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        ShoppingItem item = new ShoppingItem(null, name.trim(), false);
        return repository.save(item);
    }

    @Override
    public ShoppingItem markAsPurchased(Long id) {
        ShoppingItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));
        item.setPurchased(true);
        return repository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
}

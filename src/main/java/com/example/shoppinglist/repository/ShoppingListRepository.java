package com.example.shoppinglist.repository;

import com.example.shoppinglist.models.ShoppingItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ShoppingListRepository {
    private final Map<Long, ShoppingItem> items = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<ShoppingItem> findAll() {
        return new ArrayList<>(items.values());
    }

    public ShoppingItem save(ShoppingItem item) {
        if (item.getId() == null) {
            item.setId(idGenerator.getAndIncrement());
        }
        items.put(item.getId(), item);
        return item;
    }

    public Optional<ShoppingItem> findById(Long id) {
        return Optional.ofNullable(items.get(id));
    }

    public void deleteById(Long id) {
        items.remove(id);
    }

    public void clear() {
        items.clear();
        idGenerator.set(1);
    }
}

package service;


import entity.Cart;

import java.util.Map;

public interface CartService {
    Cart findByUserId(String userId);

    void addBookToCart(String userId, Map<String, Integer> book);

    void addCart(String userId);
}

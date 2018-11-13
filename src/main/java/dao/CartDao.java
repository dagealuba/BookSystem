package dao;


import entity.Cart;

import java.util.Map;

public interface CartDao {
    byte[] findByUserId(String userId);
    void addBookToCart(String userId, byte[] book);
    void addCart(String userId);
}

package serviceImpl;

import dao.CartDao;
import daoImpl.CartDaoImpl;
import entity.Cart;
import service.CartService;
import util.SerializeUtil;

import java.util.HashMap;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private CartDao cartDaoImpl = new CartDaoImpl();

    @Override
    public Cart findByUserId(String userId) {
        byte[] book = cartDaoImpl.findByUserId(userId);
        if (book != null) {
            Object obj = SerializeUtil.deserialize(book);
            return new Cart(userId, (HashMap<String, Integer>) obj);
        } else {
            return null;
        }
    }

    @Override
    public void addBookToCart(String userId, Map<String, Integer> book) {
        byte[] buf = SerializeUtil.serialize(book);
        cartDaoImpl.addBookToCart(userId, buf);
    }

    @Override
    public void addCart(String userId) {
        cartDaoImpl.addCart(userId);
    }
}

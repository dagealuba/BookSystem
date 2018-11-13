package entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    private static final long serialVersionUID = 5670524409292508719L;
    private String userId;
    private Map<String, Integer> book = new HashMap<>();

    public Cart() {
    }

    public Cart(String userId, Map<String, Integer> book) {
        this.userId = userId;
        this.book = book;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getBook() {
        return book;
    }

    public void setBook(Map<String, Integer> book) {
        this.book = book;
    }
}

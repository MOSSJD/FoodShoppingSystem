package com.shopping.food;

import java.util.*;

public class ShoppingSystem {
    private final ItemList itemList;
    private final Map<String, ShoppingCart> carts;
    private ShoppingCart currentCart;
    ShoppingSystem() {
        itemList = new ItemList();
        carts = new HashMap<>();
        currentCart = null;
    }
    class ShoppingCart {
        private final Map<String, Integer> cart;
        ShoppingCart() {
            cart = new HashMap<>();
        }
        public double countTotalPrice() {
            return cart.entrySet().stream()
                    .mapToDouble(e -> itemList.getItem(e.getKey()).getPrice() * e.getValue())
                    .sum();
        }
        public void addToCart(String foodName, int quantity) {
            cart.put(foodName, quantity);
        }
        public String toString() {
            StringBuilder builder = new StringBuilder();
            int index = 1;
            builder.append("您购买了");
            builder.append(cart.size());
            builder.append("件物品：\n");
            boolean first = true;
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                var foodName = entry.getKey();
                var quantity = entry.getValue();
                if (!first)
                    builder.append("\n");
                else
                    first = false;
                builder.append("第");
                builder.append(index);
                builder.append("件物品是：");
                builder.append(itemList.getItem(foodName));
                builder.append(" 数量：");
                builder.append(quantity);
                index++;
            }
            return builder.toString();
        }
    }
    public void addACart(String customerName) {
        carts.put(customerName, new ShoppingCart());
    }
    public void use(String customerName) {
        if (carts.containsKey(customerName))
            currentCart = carts.get(customerName);
        else
            throw new NoSuchElementException();
    }
    public void addToCart(String item, int quantity) {
        currentCart.addToCart(item, quantity);
    }
    public boolean hasItem(String itemName) {
        if (validItemName(itemName))
            return itemList.hasItem(itemName);
        else
            throw new NoSuchItemException();
    }
    public void addItem(String name, double price) {
        if (validItemName(name))
            itemList.addToList(new Food(name, price));
        else
            throw new NoSuchItemException();
    }
    public boolean validItemName(String name) {
        return (name != null) && (name.length() > 1) && (name.charAt(0) >= 'a' && name.charAt(0) <= 'z');
    }
    public double countPrice() {
        return currentCart.countTotalPrice();
    }
    public String getItemListDescription() {
        return itemList.toString();
    }
    public String getCartDescription() {
        return currentCart.toString();
    }
}
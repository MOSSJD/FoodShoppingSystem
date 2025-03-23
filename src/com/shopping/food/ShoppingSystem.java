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
        private final Map<String, Double> cart;
        ShoppingCart() {
            cart = new HashMap<>();
        }
        public double countTotalPrice() {
            return cart.entrySet().stream()
                    .mapToDouble(e -> itemList.getItem(e.getKey()).getPrice() * e.getValue())
                    .sum();
        }
        public void addToCart(String foodName, double quantity) {
            if (cart.containsKey(foodName)) {
                cart.put(foodName, quantity + cart.get(foodName));
            }
            cart.put(foodName, quantity);
        }
        public boolean isEmpty() {
            return cart.isEmpty();
        }
        public int size() {
            return cart.size();
        }
        public String toString() {
            if (isEmpty()) {
                return "当前购物车为空。";
            }
            StringBuilder builder = new StringBuilder();
            int index = 1;
            builder.append("您购买了");
            builder.append(cart.size());
            builder.append("件物品：\n");
            boolean first = true;
            for (Map.Entry<String, Double> entry : cart.entrySet()) {
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
        public void removeFromItemName(String itemName) {
            if (!cart.containsKey(itemName)) {
                throw new NoSuchItemException("购物车内没有该商品。请检查输入的商品名是否正确。");
            }
            cart.remove(itemName);
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
    public void addToCart(String item, double quantity) {
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
        return (name != null) && (name.length() > 1);
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
    public int getCartSize() {
        return currentCart.size();
    }
    public void removeFromCart(String itemName) {
        currentCart.removeFromItemName(itemName);
    }
}
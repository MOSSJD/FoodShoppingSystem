package com.shopping.food;

import java.util.HashMap;
import java.util.Map;

class ItemList {
    private final Map<String, Food> list;
    ItemList() {
        list = new HashMap<>();
    }
    public void addToList(Food food) {
        list.put(food.getName(), food);
    }
    public boolean hasItem(String name) {
        return list.containsKey(name);
    }
    public Food getItem(String name) {
        return list.get(name);
    }
    public String toString() {
        if (list.isEmpty()) {
            return "当前商品列表无商品。";
        }
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (var item : list.values()) {
            if (!first)
                builder.append("\n");
            else
                first = false;
            builder.append(item);
        }
        return builder.toString();
    }
}
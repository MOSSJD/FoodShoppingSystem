package com.shopping.food;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingSystem shoppingSystem = new ShoppingSystem();
        String customerName = "customer1";
        shoppingSystem.addACart(customerName);
        shoppingSystem.use(customerName);
        boolean endLoop = false;
        do {
            try {
                System.out.println("请输入所需购买的商品（输入“list”获取商品列表，输入“add”新增商品，输入“end”结束）：");
                String input = scanner.next();
                if (input.equals("list")) {
                    System.out.println(shoppingSystem.getItemListDescription());
                }
                else if (input.equals("add")) {
                    System.out.println("请输入商品名：");
                    String name = scanner.next();
                    System.out.println("请输入商品价格：");
                    double price = scanner.nextDouble();
                    shoppingSystem.addItem(name, price);
                }
                else if (input.equals("end")) {
                    endLoop = true;
                }
                else if (shoppingSystem.hasItem(input)) {
                    System.out.println("请输入需要购买的数量：");
                    int quantity = scanner.nextInt();
                    shoppingSystem.addToCart(input, quantity);
                }
                else {
                    throw new PromptException();
                }
            } catch (NoSuchItemException e) {
                System.out.println("请输入正确的商品名" + e.getMessage());
            }
            catch (PromptException
                   | NoSuchElementException
                   | NotAValidNameException e) {
                System.out.println(e.getMessage());
            }
        }
        while (!endLoop);
        System.out.println("您购买了：");
        System.out.println(shoppingSystem.getCartDescription());
        System.out.println("总价格：" + shoppingSystem.countPrice());
    }
}



class PromptException extends RuntimeException {
    PromptException(String message) {
        super("Entered wrong token. " + message);
    }
    PromptException() {
        this("");
    }
}
class NotAValidNameException extends RuntimeException {
    NotAValidNameException(String message) {
        super("Please enter a valid name. " + message);
    }
    NotAValidNameException() {
        this("");
    }
}

class NoSuchItemException extends NoSuchElementException {
    NoSuchItemException(String message) {
        super(message);
    }
    NoSuchItemException() {
        this("");
    }
}
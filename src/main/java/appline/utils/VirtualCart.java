package appline.utils;

import java.util.*;

public class VirtualCart {
    private static final Map<String, Integer> prices = new HashMap<>();

    public static void addToVirtualCart(String name, Integer price) {
        getPrices().put(name, price);
    }

    public static Map<String, Integer> getPrices() {
        return prices;
    }

    public String getString(){
        return getPrices().toString();
    }

    public static int getMaxPrice() {
        return Collections.max(getPrices().values());
    }

}

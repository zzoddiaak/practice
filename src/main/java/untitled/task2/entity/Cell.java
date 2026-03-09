package untitled.task2.entity;

import lombok.Getter;

@Getter
public class Cell {
    private final int number;
    private String orderId;

    public Cell(int number) {
        this.number = number;
        this.orderId = null;
    }

    public boolean isFree() {
        return orderId == null;
    }

    public void placeOrder(String orderId) {
        this.orderId = orderId;
    }

    public String removeOrder() {
        String removedOrder = this.orderId;
        this.orderId = null;
        return removedOrder;
    }
}

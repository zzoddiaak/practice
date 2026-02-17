package untitled.task2.entity;

import lombok.Getter;

@Getter
public class Cell {
    private final int number;
    private boolean occupied;
    private String orderId;

    public Cell(int number) {
        this.number = number;
        this.occupied = false;
        this.orderId = null;
    }

    public boolean isFree() {
        return !occupied;
    }

    public void placeOrder(String orderId) {
        this.occupied = true;
        this.orderId = orderId;
    }

    public String removeOrder() {
        String removedOrder = this.orderId;
        this.occupied = false;
        this.orderId = null;
        return removedOrder;
    }
}

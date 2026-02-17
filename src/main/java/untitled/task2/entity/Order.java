package untitled.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {
    private final String orderId;
    private final String receiveCode;
    private final int cellNumber;
}

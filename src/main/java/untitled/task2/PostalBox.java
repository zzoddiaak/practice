package untitled.task2;

import lombok.RequiredArgsConstructor;
import untitled.task2.entity.Cell;
import untitled.task2.entity.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
class PostalBox {

    private final List<Cell> cells;
    private final Map<String, Order> codeToOrder = new HashMap<>();
    private final UserNotificationApi notificationApi;

    public int placeOrder(String orderId) {

        boolean orderExists = codeToOrder.values().stream()
                .anyMatch(order -> order.getOrderId().equals(orderId));

        if (orderExists) {
            throw new IllegalArgumentException("Заказ с таким ID уже находится в постамате");
        }
        for (Cell cell : cells) {
            if (cell.isFree()) {
                String code = notificationApi.generateCode(orderId);
                cell.placeOrder(orderId);
                Order order = new Order(orderId, code);
                codeToOrder.put(code, order);
                notificationApi.sendCode(orderId, code);

                return cell.getNumber();

            }


        }

        throw new IllegalArgumentException("Нет свободных ячеек");
    }

    public String getOrder(String receiveCode) {
        Order order = codeToOrder.get(receiveCode);
        if (order == null) {
            throw new IllegalArgumentException("Кода не существует");
        }

        Cell cell = cells.stream()
                .filter(c -> c.getOrderId().equals(order.getOrderId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ячейка не найдена"));

        cell.removeOrder();
        codeToOrder.remove(receiveCode);

        return "Ваш заказ " + order.getOrderId() + " в ячейке " + cell.getNumber();    }
}

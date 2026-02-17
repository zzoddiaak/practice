package untitled.task2;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

@RequiredArgsConstructor
class PostalBox {

    private final boolean[] cells;
    private final Map<String, String> codeToOrder;
    private final Map<String, Integer> orderToCells;
    private final UserNotificationApi notificationApi;


    public int placeOrder(String orderId, String userId) {

        for (int i = 0; i < cells.length; i++) {
            if (!cells[i]) {
                cells[i] = true;
                String code = RandomStringUtils.randomAlphanumeric(6);
                codeToOrder.put(code, orderId);
                orderToCells.put(orderId, i);
                notificationApi.sendCode(userId, orderId, code);

                return i;
            }

        }

        throw new IllegalArgumentException("Нет свободных ячеек");
    }

    public String getOrder(String receiveCode) {
        if (!codeToOrder.containsKey(receiveCode)) {
            throw new IllegalArgumentException("Кода не существует");

        }

        String orderId = codeToOrder.get(receiveCode);
        Integer cell = orderToCells.get(orderId);

        cells[cell] = false;

        codeToOrder.remove(receiveCode);
        orderToCells.remove(orderId);

        return "Ваш заказ " + orderId + " в ячейке " + cell;
    }
}

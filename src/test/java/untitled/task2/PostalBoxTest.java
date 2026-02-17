package untitled.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostalBoxTest {
    @Mock
    private UserNotificationApi notificationApi;

    private PostalBox postalBox;
    private boolean[] cells;
    private Map<String, String> codeToOrder;
    private Map<String, Integer> orderToCells;

    @BeforeEach
    void setUp() {
        cells = new boolean[3];
        codeToOrder = new HashMap<>();
        orderToCells = new HashMap<>();
        postalBox = new PostalBox(cells, codeToOrder, orderToCells, notificationApi);
    }

    @Test
    void placeOrderWhenFreeCell(){
        String orderId = "ORDER-123";
        String userId = "USER-456";

        int cellNumber = postalBox.placeOrder(orderId, userId);

        assertEquals(0, cellNumber);

        verify(notificationApi).sendCode(eq(userId), eq(orderId), anyString());
    }

   @Test
   void placeOrderWhenFirstCellOfTheLesson(){
       cells[0] = true;

       String orderId = "ORDER-123";
       String userId = "USER-456";

       int cellNumber = postalBox.placeOrder(orderId, userId);

       assertEquals(1, cellNumber);
   }

    @Test
    void placeOrderWhenAllCellsAreOccupied(){
        cells[0] = true;
        cells[1] = true;
        cells[2] = true;

        String orderId = "ORDER-123";
        String userId = "USER-456";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> postalBox.placeOrder(orderId, userId)
        );
        assertEquals("Нет свободных ячеек", exception.getMessage());

        verify(notificationApi, never()).sendCode(anyString(), anyString(), anyString());
    }

    @Test
    void getOrderWhenCorrectCodeAndCodeAlreadyUsed(){
        String orderId = "ORDER-123";
        String userId = "USER-456";
        String code = "ABC123";

        cells[0] = true;
        codeToOrder.put(code, orderId);
        orderToCells.put(orderId, 0);

        String message = postalBox.getOrder(code);

        assertEquals("Ваш заказ ORDER-123 в ячейке 0" ,message);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> postalBox.getOrder(code)
        );

        assertEquals("Кода не существует", exception.getMessage());
    }

    @Test
    void getOrderWhenInvalidCode(){
        String invalidCode = "WRONG-CODE";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> postalBox.getOrder(invalidCode)
        );

        assertEquals("Кода не существует", exception.getMessage());
    }
}
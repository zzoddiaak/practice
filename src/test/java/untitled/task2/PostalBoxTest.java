package untitled.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import untitled.task2.entity.Cell;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostalBoxTest {

    @Mock
    private UserNotificationApi notificationApi;

    private PostalBox postalBox;
    private List<Cell> cells;

    @BeforeEach
    void setUp() {
        cells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cells.add(new Cell(i));
        }

        postalBox = new PostalBox(cells, notificationApi);
    }

    @Test
    void placeOrderWhenFreeCellExists() {
        String orderId = "ORDER-123";
        String expectedCode = "ABC123";
        when(notificationApi.generateCode(orderId)).thenReturn(expectedCode);

        int cellNumber = postalBox.placeOrder(orderId);

        assertEquals(0, cellNumber);

        verify(notificationApi).generateCode(orderId);
        verify(notificationApi).sendCode(orderId, expectedCode);
    }

    @Test
    void placeOrderWhenMultipleCellsExist() {
        cells.get(0).placeOrder("EXISTING-ORDER");

        String orderId = "ORDER-123";
        when(notificationApi.generateCode(orderId)).thenReturn("ABC123");

        int cellNumber = postalBox.placeOrder(orderId);

        assertEquals(1, cellNumber);
    }

    @Test
    void placeOrderWhenOrderAlreadyExists() {
        String orderId = "ORDER-123";

        when(notificationApi.generateCode(orderId)).thenReturn("ABC123");
        postalBox.placeOrder(orderId);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> postalBox.placeOrder(orderId)
        );

        assertEquals("Заказ с таким ID уже находится в постамате", exception.getMessage());
    }

    @Test
    void placeOrderWhenNoFreeCells() {

        for (int i = 0; i < cells.size(); i++) {
            cells.get(i).placeOrder("ORDER-" + i);
        }

        String orderId = "NEW-ORDER";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> postalBox.placeOrder(orderId)
        );

        assertEquals("Нет свободных ячеек", exception.getMessage());
    }

    @Test
    void getOrderWhenCorrectCode() {
        String orderId = "ORDER-123";
        String code = "ABC123";

        when(notificationApi.generateCode(orderId)).thenReturn(code);

        int cellNumber = postalBox.placeOrder(orderId);

        String message = postalBox.getOrder(code);

        assertEquals("Ваш заказ ORDER-123 в ячейке " + cellNumber, message);

        assertTrue(cells.get(cellNumber).isFree());
        assertNull(cells.get(cellNumber).getOrderId());
    }

    @Test
    void getOrderWhenInvalidCode() {
        String invalidCode = "WRONG-CODE";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> postalBox.getOrder(invalidCode)
        );

        assertEquals("Кода не существует", exception.getMessage());
    }

    @Test
    void getOrderWhenCodeAlreadyUsed() {
        String orderId = "ORDER-123";
        String code = "ABC123";

        when(notificationApi.generateCode(orderId)).thenReturn(code);

        postalBox.placeOrder(orderId);

        postalBox.getOrder(code);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> postalBox.getOrder(code)
        );

        assertEquals("Кода не существует", exception.getMessage());
    }
}
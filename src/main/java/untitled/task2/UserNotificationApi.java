package untitled.task2;

public interface UserNotificationApi {
    void sendCode(String orderId, String code);
    String generateCode(String orderId);
}

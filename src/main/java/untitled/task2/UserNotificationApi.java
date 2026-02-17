package untitled.task2;

public interface UserNotificationApi {
    void sendCode(String userId, String orderId, String code);
}

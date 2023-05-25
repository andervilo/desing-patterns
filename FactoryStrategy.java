import java.util.HashMap;
import java.util.Map;

public enum MessageStrategyType {
    EMAIL,
    SMS,
    PUSH_NOTIFICATION
}

public interface MessageStrategy {
    void sendMessage(String message);
}

public class EmailMessageStrategy implements MessageStrategy {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending email: " + message);
    }
}

public class SMSMessageStrategy implements MessageStrategy {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

public class PushNotificationStrategy implements MessageStrategy {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending push notification: " + message);
    }
}

public class MessageStrategyFactory {
    private final Map<MessageStrategyType, MessageStrategy> strategyMap;

    public MessageStrategyFactory() {
        strategyMap = new HashMap<>();
        strategyMap.put(MessageStrategyType.EMAIL, new EmailMessageStrategy());
        strategyMap.put(MessageStrategyType.SMS, new SMSMessageStrategy());
        strategyMap.put(MessageStrategyType.PUSH_NOTIFICATION, new PushNotificationStrategy());
    }

    public MessageStrategy createMessageStrategy(MessageStrategyType type) {
        MessageStrategy strategy = strategyMap.get(type);
        if (strategy != null) {
            return strategy;
        } else {
            throw new IllegalArgumentException("Invalid message strategy type");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MessageStrategyFactory factory = new MessageStrategyFactory();
        
        MessageStrategy emailStrategy = factory.createMessageStrategy(MessageStrategyType.EMAIL);
        emailStrategy.sendMessage("Hello, via email!");
        
        MessageStrategy smsStrategy = factory.createMessageStrategy(MessageStrategyType.SMS);
        smsStrategy.sendMessage("Hello, via SMS!");
        
        MessageStrategy pushStrategy = factory.createMessageStrategy(MessageStrategyType.PUSH_NOTIFICATION);
        pushStrategy.sendMessage("Hello, via push notification!");
    }
}

package pl.koneckimarcin.chat_service.subscription;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SubscriptionService {

    private final Set<String> subscribedKeys = new HashSet<>();

    public void subscribe(String key) {
        subscribedKeys.add(key);
    }
    public void unsubscribe(String key) {
        subscribedKeys.remove(key);
    }

    public boolean isSubscribed(String key) {
        return subscribedKeys.contains(key);
    }
    public Set<String> getSubscribedKeys() {
        return subscribedKeys;
    }
}

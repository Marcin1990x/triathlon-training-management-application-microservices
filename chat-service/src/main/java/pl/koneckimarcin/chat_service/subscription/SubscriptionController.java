package pl.koneckimarcin.chat_service.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/chat/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @PostMapping("/subscribe")
    public void subscribe(@RequestParam String key) {
        service.subscribe(key);
    }
    @PostMapping("/unsubscribe")
    public void unsubscribe(@RequestParam String key) {
        service.unsubscribe(key);
    }
    @GetMapping("/keys")
    public Set<String> getSubscribedKeys() {
        return service.getSubscribedKeys();
    }
}

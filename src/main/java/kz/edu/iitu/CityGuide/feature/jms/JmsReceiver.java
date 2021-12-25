package kz.edu.iitu.CityGuide.feature.jms;

import kz.edu.iitu.CityGuide.CityGuideApplication;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class JmsReceiver {
    @JmsListener(destination = CityGuideApplication.MESSAGE_QUEUE_NAME, containerFactory = "myFactory")
    public void receiveMessage(String email) {
        System.out.println("\nJMS");
        log.info("Received email " + email);
    }
}

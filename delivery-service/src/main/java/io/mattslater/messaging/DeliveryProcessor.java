package io.mattslater.messaging;

import io.mattslater.model.Delivery;
import io.mattslater.repos.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.Message;

@MessageEndpoint
public class DeliveryProcessor {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryProcessor(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void onNewDelivery(Message<Delivery> msg) {
        this.deliveryRepository.save(msg.getPayload());
    }
}

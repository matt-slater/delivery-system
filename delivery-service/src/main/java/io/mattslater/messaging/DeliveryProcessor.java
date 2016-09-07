package io.mattslater.messaging;

import io.mattslater.model.Delivery;
import io.mattslater.model.SubmittedDelivery;
import io.mattslater.repos.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
public class DeliveryProcessor {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryProcessor(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onNewDelivery(Message<SubmittedDelivery> msg) {
        System.out.println("IN SERVICE ACTIVATOR");
        SubmittedDelivery d = msg.getPayload();
        System.out.println(this.deliveryRepository.save(new Delivery(d.getOrigin(), d.getDestination())));
    }
}

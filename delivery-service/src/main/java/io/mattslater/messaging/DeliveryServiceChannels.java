package io.mattslater.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by dewdmcmann on 9/2/16.
 */
public interface DeliveryServiceChannels {

    @Input
    SubscribableChannel input();
}

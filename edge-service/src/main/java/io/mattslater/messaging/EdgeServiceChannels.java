package io.mattslater.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EdgeServiceChannels {

    @Output
    MessageChannel output();
}

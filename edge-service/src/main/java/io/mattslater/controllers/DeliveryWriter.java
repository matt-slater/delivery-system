package io.mattslater.controllers;

import io.mattslater.model.SubmittedDelivery;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;


@MessagingGateway
public interface DeliveryWriter {

    @Gateway(requestChannel = "output")
    void write(SubmittedDelivery sd);
}

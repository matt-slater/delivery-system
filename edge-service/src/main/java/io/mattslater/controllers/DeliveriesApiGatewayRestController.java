package io.mattslater.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.mattslater.messaging.EdgeServiceChannels;
import io.mattslater.model.Delivery;
import io.mattslater.model.SubmittedDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deliveries")
public class DeliveriesApiGatewayRestController {

    private final DeliveryReader deliveryReader;
    private final DeliveryWriter deliveryWriter;

    @Autowired
    public DeliveriesApiGatewayRestController(DeliveryReader deliveryReader, DeliveryWriter deliveryWriter) {
        this.deliveryReader = deliveryReader;
        this.deliveryWriter = deliveryWriter;
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public Collection<Delivery> deliveries() {


        return deliveryReader.read()

                .getContent()
               .stream()
                .collect(Collectors.toList());
    }

    public Collection<Delivery> fallback() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestBody SubmittedDelivery d) {
        this.deliveryWriter.write(d);
    }
}

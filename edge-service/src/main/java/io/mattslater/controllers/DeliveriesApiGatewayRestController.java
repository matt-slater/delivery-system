package io.mattslater.controllers;

import io.mattslater.model.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deliveries")
public class DeliveriesApiGatewayRestController {

    private final RestTemplate restTemplate;

    @Autowired
    public DeliveriesApiGatewayRestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public Collection<Delivery> deliveries() {
        ParameterizedTypeReference<Resources<Delivery>> ptr =
                new ParameterizedTypeReference<Resources<Delivery>>() {
                };

        ResponseEntity<Resources<Delivery>> responseEntity =
                this.restTemplate.exchange("http://delivery-service/deliveries",
                        HttpMethod.GET, null, ptr);

        return responseEntity
                .getBody()
                .getContent()
                .stream()
                .collect(Collectors.toList());
    }
}

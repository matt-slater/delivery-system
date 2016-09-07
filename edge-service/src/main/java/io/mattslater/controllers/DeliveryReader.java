package io.mattslater.controllers;

import io.mattslater.model.Delivery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dewdmcmann on 9/7/16.
 */
@FeignClient("delivery-service")
public interface DeliveryReader {

    @RequestMapping(method = RequestMethod.GET, value ="/deliveries")
    Resources<Delivery> read();
}

package io.mattslater.controller;

import io.mattslater.model.Delivery;
import io.mattslater.repos.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DeliveryController {

    @Value("${message}")
    private String message;
    private DeliveryRepository repository;
    SimpMessagingTemplate template;

    @Autowired
    public DeliveryController(DeliveryRepository repository, SimpMessagingTemplate template) {
        this.repository = repository;
        this.template = template;

    }

    public void updateListandBroadcast() {
        template.convertAndSend("/topic/openDeliveries", getOpenDeliveries());
    }

    @RequestMapping(value="/message", method=RequestMethod.GET)
    public String message() {
        return message;
    }


    @RequestMapping(value="/opendeliveries", method=RequestMethod.GET)
    public @ResponseBody List<Delivery> getOpenDeliveries() {
        return repository.findByDeliveredFalse();
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public @ResponseBody Delivery newDelivery(@RequestBody Delivery delivery) {
        System.out.println("in new del");
        Delivery d = repository.save(delivery);
        updateListandBroadcast();
        return d;
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable String id) {
        repository.delete(Long.parseLong(id));
        updateListandBroadcast();
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.PUT)
    public @ResponseBody Delivery update(@PathVariable String id, @RequestBody Delivery d) {
        Delivery update = repository.findOne(Long.parseLong(id));
        update.setDelivered(d.isDelivered());
        return repository.save(update);

    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public @ResponseBody Delivery get(@PathVariable String id) {
        return repository.findOne(Long.parseLong(id));
    }




}

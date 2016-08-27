package io.mattslater.model;

/**
 * Created by dewdmcmann on 7/12/16.
 */
public class SubmittedDelivery {

    private String origin;
    private String destination;

    public SubmittedDelivery(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}

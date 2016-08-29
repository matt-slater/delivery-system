package io.mattslater.model;

/**
 * Created by dewdmcmann on 8/28/16.
 */
public class Delivery {

    private String origin;
    private String destination;
    private boolean delivered;

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

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}

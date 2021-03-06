package io.mattslater.repos;

import io.mattslater.model.Delivery;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


@RepositoryRestResource
public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, Long> {

    @RestResource(path="undelivered")
    List<Delivery> findByDeliveredFalse();
}

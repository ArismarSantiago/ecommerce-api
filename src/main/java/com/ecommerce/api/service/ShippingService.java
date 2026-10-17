package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.ShippingMapper;
import com.ecommerce.api.dto.request.updateRequest.ShippingStatusUpdateRequest;
import com.ecommerce.api.dto.response.ShippingResponse;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.events.OnChangeStatusShipping;
import com.ecommerce.api.exceptions.DuplicateExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;

@Service
public class ShippingService {


    @Autowired
    private ShippingRepository repository;

    @Autowired
    public ApplicationEventPublisher publisher;

    @Autowired
    private ShippingMapper shippingMapper;




    public List<ShippingResponse> findAll(){
        return repository.findAll().stream()
                .map(shippingMapper::toResponse)
                .toList();
    }

    public Shipping findByEntityId(Long id){
        return  repository.findById(id)
                .orElseThrow(()-> new NotFoundExceptions("Id não localizado", String.valueOf(id), "Shipping"));
    }

    public ShippingResponse findById(Long id){
        Shipping shipping = findByEntityId(id);
        return shippingMapper.toResponse(shipping);
    }
    public ShippingResponse findByTrackingCode(String trackingCode){
        Shipping shipping = repository.findByTrackingCode(trackingCode);
        return shippingMapper.toResponse(shipping);
    }
    public List<ShippingResponse> findByShippedAtBetween(LocalDate date){
        LocalDateTime dateIni = date.atStartOfDay();
        LocalDateTime dateFin = date.atTime(LocalTime.MAX);
        return repository.findByShippedAtBetween(dateIni, dateFin)
                .stream().map(shippingMapper::toResponse).toList();

    }

    public ShippingResponse updateStatusShipping(ShippingStatusUpdateRequest request, Long id){
        Shipping shipping = findByEntityId(id);
        if(shipping.getStatus().equals(request.status())){
            throw new DuplicateExceptions("A entrega já se encontra nesse status", String.valueOf(shipping.getStatus()));
        }
        shipping.setStatus(request.status());
        repository.save(shipping);
        OnChangeStatusShipping statusShipping = new OnChangeStatusShipping(shipping);
        publisher.publishEvent(statusShipping);
        return shippingMapper.toResponse(shipping);
    }

}

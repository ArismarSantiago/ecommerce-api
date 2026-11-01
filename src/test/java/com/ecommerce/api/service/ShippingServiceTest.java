package com.ecommerce.api.service;


import com.ecommerce.api.createDataFake.ShippingFakeData;
import com.ecommerce.api.dto.mapper.ShippingMapper;
import com.ecommerce.api.dto.response.ShippingResponse;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.repository.ShippingRepository;
import com.ecommerce.api.dto.request.updateRequest.ShippingStatusUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.util.ReflectionTestUtils;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShippingServiceTest {

    @Mock
    private ShippingRepository repository;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private ShippingService service;

    @BeforeEach
    void up(){
        ReflectionTestUtils.setField(service, "mapper", Mappers.getMapper(ShippingMapper.class));
        ReflectionTestUtils.setField(service,"repository", repository);
        ReflectionTestUtils.setField(service, "publisher", publisher);
    }

    @Test
    void findAll() {
        List<Shipping> shippingList = ShippingFakeData.shippingListFake(3);
        when(repository.findAll()).thenReturn(shippingList);

        List<ShippingResponse> responses = service.findAll();

       assertNotNull(responses);
        assertEquals(3, responses.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findById() {
        Shipping shipping = ShippingFakeData.shippingFake(1);
        when(repository.findById(anyLong())).thenReturn(Optional.of(shipping));

        ShippingResponse response = service.findById(1L);

        assertEquals(shipping.getTrackingCode(), response.getTrackingCode());

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void findByTrackingCode() {
        Shipping shipping = ShippingFakeData.shippingFake(1);
        when(repository.findByTrackingCode("11010101")).thenReturn(shipping);
        ShippingResponse response = service.findByTrackingCode("11010101");


       assertEquals(shipping.getTrackingCode(), response.getTrackingCode());

    }

    @Test
    void updateStatusShipping() {
        Shipping shipping = ShippingFakeData.shippingFake(1);
        shipping.setStatus(ShippingStatus.IN_TRANSIT);

        ShippingStatusUpdateRequest shippingStatusUpdateRequest =
                ShippingFakeData.shippingStatusUpdateRequestFake(1L, ShippingStatus.PENDING);

        when(repository.findById(1L)).thenReturn(Optional.of(shipping));
        when(repository.save(any())).thenReturn(shipping);

        ShippingResponse response = service.updateStatusShipping(shippingStatusUpdateRequest, 1L);

        assertNotNull(response);
        assertEquals(shipping.getStatus(), response.getStatus());

        verify(repository, times(1)).save(any());
    }
}
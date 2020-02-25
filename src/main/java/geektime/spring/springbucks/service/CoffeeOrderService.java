package geektime.spring.springbucks.service;

import geektime.spring.springbucks.model.Coffee;
import geektime.spring.springbucks.model.CoffeeOrder;
import geektime.spring.springbucks.model.OrderState;
import geektime.spring.springbucks.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
public class CoffeeOrderService {

    @Autowired
    CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffees){

        CoffeeOrder coffeeOrder = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderState.INIT)
                .build();

        CoffeeOrder saved = coffeeOrderRepository.save(coffeeOrder);
        log.info("New order saved: {}", saved);
        return saved;
    }


    public boolean updateState(CoffeeOrder order, OrderState state){
        if(order.getState().compareTo(state) >= 0){
            log.warn("Wrong state, old state: {}  new state: {}" , order.getState(), state);
            return false;
        }

        order.setState(state);
        CoffeeOrder saved = coffeeOrderRepository.save(order);
        log.info("Update order: {}", saved);
        return true;
    }

}

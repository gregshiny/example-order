package dev.practice.order.domain.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderStore orderStore;
    private final OrderItemSeriesFactory orderItemSeriesFactory;

    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder requestOrder) {
        Order order = orderStore.store(requestOrder.toEntity());
        orderItemSeriesFactory.store(order, requestOrder);
        return order.getOrderToken();
    }
}

package dev.practice.order.domain.order;

import dev.practice.order.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderStore orderStore;
    private final ItemReader itemReader;

    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder requestOrder) {
        Order order = orderStore.store(requestOrder.toEntity());
        requestOrder.getOrderItemList().forEach(orderItemRequest -> {
            var item = itemReader.getItemBy(orderItemRequest.getItemToken());
            var initOrderItem = orderItemRequest.toEntity(order, item);
            var orderItem = orderStore.store(initOrderItem);

            orderItemRequest.getOrderItemOptionGroupList().forEach(orderItemOptionGroupRequest -> {
                var initOrderItemOptionGroup = orderItemOptionGroupRequest.toEntity(orderItem);
                var orderItemOptionGroup = orderStore.store(initOrderItemOptionGroup);

                orderItemOptionGroupRequest.getOrderItemOptionList().forEach(orderItemOptionRequest -> {
                    var initOrderItemOption = orderItemOptionRequest.toEntity(orderItemOptionGroup);
                    orderStore.store(initOrderItemOption);
                });
            });
        });
        return order.getOrderToken();
    }
}

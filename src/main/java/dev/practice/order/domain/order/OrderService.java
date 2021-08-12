package dev.practice.order.domain.order;

public interface OrderService {
    String registerOrder(OrderCommand.RegisterOrder registerOrder);
}

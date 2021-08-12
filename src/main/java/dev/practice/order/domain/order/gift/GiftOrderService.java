package dev.practice.order.domain.order.gift;

import dev.practice.order.domain.order.OrderCommand;

public interface GiftOrderService {
    void paymentOrder(OrderCommand.PaymentRequest paymentRequest);
}

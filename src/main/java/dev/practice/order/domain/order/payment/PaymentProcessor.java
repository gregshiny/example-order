package dev.practice.order.domain.order.payment;

import dev.practice.order.domain.order.Order;
import dev.practice.order.domain.order.OrderCommand;

public interface PaymentProcessor {
    void pay(Order order, OrderCommand.PaymentRequest request);
}
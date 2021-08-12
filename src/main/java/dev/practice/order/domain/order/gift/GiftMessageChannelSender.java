package dev.practice.order.domain.order.gift;

public interface GiftMessageChannelSender {
    void paymentComplete(GiftPaymentCompleteMessage message);
}

package dev.practice.order.domain.order;

import dev.practice.order.domain.order.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderStore orderStore;
    private final OrderReader orderReader;
    private final OrderItemSeriesFactory orderItemSeriesFactory;
    private final PaymentProcessor paymentProcessor;
    private final OrderInfoMapper orderInfoMapper;

    @Override
    @Transactional
    public String registerOrder(OrderCommand.RegisterOrder requestOrder) {
        Order order = orderStore.store(requestOrder.toEntity());
        orderItemSeriesFactory.store(order, requestOrder);
        return order.getOrderToken();
    }

    @Override
    @Transactional
    public void paymentOrder(OrderCommand.PaymentRequest paymentRequest) {
        var orderToken = paymentRequest.getOrderToken();
        var order = orderReader.getOrder(orderToken);
        paymentProcessor.pay(order, paymentRequest);
        order.orderComplete();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderInfo.Main retrieveOrder(String orderToken) {
        var order = orderReader.getOrder(orderToken);
        var orderItemList = order.getOrderItemList();
        return orderInfoMapper.of(order, orderItemList);
    }

    @Override
    @Transactional
    public void updateReceiverInfo(String orderToken, OrderCommand.UpdateReceiverInfoRequest request) {
        var order = orderReader.getOrder(orderToken);
        order.updateDeliveryFragment(
                request.getReceiverName(),
                request.getReceiverPhone(),
                request.getReceiverZipcode(),
                request.getReceiverAddress1(),
                request.getReceiverAddress2(),
                request.getEtcMessage()
        );
        order.deliveryPrepare();
    }
}

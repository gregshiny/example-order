package dev.practice.order.infrastructure.order.gift;

import dev.practice.order.domain.order.gift.GiftMessageChannelSender;
import dev.practice.order.domain.order.gift.GiftPaymentCompleteMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiftMessageAwsSqsSender implements GiftMessageChannelSender {
    private final String SQS_QUEUE_NAME = "order-payComplete-live.fifo";
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Override
    public void paymentComplete(GiftPaymentCompleteMessage message) {
        try {
            Map<String, Object> headers = new HashMap<>();
            headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, "item-queues");
            headers.put(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER, UUID.randomUUID().toString());
            queueMessagingTemplate.convertAndSend(SQS_QUEUE_NAME, message, headers);
            log.info("[SQS enqueued topic: {}, message: {}", SQS_QUEUE_NAME, message);
        } catch (Exception e) {
            log.error("SQS failed topic: {}, message: {}", SQS_QUEUE_NAME, message);
            throw new RuntimeException(e);
        }
    }
}

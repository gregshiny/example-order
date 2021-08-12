package dev.practice.order.interfaces.order;

import dev.practice.order.domain.order.OrderCommand;
import dev.practice.order.domain.order.OrderInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

    @Mappings({
            @Mapping(source = "orderedAt", target = "orderedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    OrderDto.Main of(OrderInfo.Main mainResult);

    OrderDto.DeliveryInfo of(OrderInfo.DeliveryInfo deliveryResult);

    OrderDto.OrderItem of(OrderInfo.OrderItem orderItemResult);

    OrderCommand.RegisterOrder of(OrderDto.RegisterOrderRequest request);

    OrderCommand.RegisterOrderItem of(OrderDto.RegisterOrderItem request);

    OrderCommand.RegisterOrderItemOptionGroup of(OrderDto.RegisterOrderItemOptionGroupRequest request);

    OrderCommand.RegisterOrderItemOption of(OrderDto.RegisterOrderItemOptionRequest request);

    OrderDto.RegisterResponse of(String orderToken);

    OrderCommand.PaymentRequest of(OrderDto.PaymentRequest request);
}

package dev.practice.order.interfaces.order.gift;

import dev.practice.order.domain.order.OrderCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GiftOrderDtoMapper {

    @Mapping(source = "buyerUserId", target = "userId")
    OrderCommand.RegisterOrder of(GiftOrderDto.RegisterOrderRequest request);

    OrderCommand.RegisterOrderItem of(GiftOrderDto.RegisterOrderItem request);

    OrderCommand.RegisterOrderItemOptionGroup of(GiftOrderDto.RegisterOrderItemOptionGroupRequest request);

    OrderCommand.RegisterOrderItemOption of(GiftOrderDto.RegisterOrderItemOptionRequest request);

    GiftOrderDto.RegisterResponse of(String orderToken);

    OrderCommand.PaymentRequest of(GiftOrderDto.PaymentRequest request);
}

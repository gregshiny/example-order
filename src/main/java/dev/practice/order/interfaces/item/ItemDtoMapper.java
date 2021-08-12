package dev.practice.order.interfaces.item;

import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemInfo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ItemDtoMapper {

    // register
    @Mappings({@Mapping(source = "request.itemOptionGroupList", target = "itemOptionGroupRequestList")})
    ItemCommand.RegisterItemRequest of(ItemDto.RegisterItemRequest request);

    @Mappings({@Mapping(source = "itemOptionList", target = "itemOptionRequestList")})
    ItemCommand.RegisterItemOptionGroupRequest of(ItemDto.RegisterItemOptionGroupRequest request);

    ItemCommand.RegisterItemOptionRequest of(ItemDto.RegisterItemOptionRequest request);

    ItemDto.RegisterResponse of(String itemToken);

    // retrieve
    ItemDto.Main of(ItemInfo.Main main);

    ItemDto.ItemOptionGroupInfo of(ItemInfo.ItemOptionGroupInfo itemOptionGroup);

    ItemDto.ItemOptionInfo of(ItemInfo.ItemOptionInfo itemOption);
}

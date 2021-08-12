package dev.practice.order.domain.item;


import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;

import java.util.List;

public interface ItemOptionSeriesFactory {
    List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest request, Item item);
}

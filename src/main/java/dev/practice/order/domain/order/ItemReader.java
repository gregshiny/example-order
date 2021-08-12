package dev.practice.order.domain.order;

import java.util.List;

public interface ItemReader {
    Item getItemBy(String itemToken);
    List<Item> findItemAllBy(List<String> itemTokenList);
    List<ItemInfo.ItemOptionGroup> getItemOptionSeries(Item item);
}

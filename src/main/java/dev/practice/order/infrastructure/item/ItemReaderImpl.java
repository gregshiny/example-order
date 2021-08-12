package dev.practice.order.infrastructure.item;

import dev.practice.order.common.exception.EntityNotFoundException;
import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemInfo;
import dev.practice.order.domain.item.ItemReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemReaderImpl implements ItemReader {
    private final ItemRepository itemRepository;

    @Override
    public Item getItemBy(String itemToken) {
        return itemRepository.findByItemToken(itemToken)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Item> findItemAllBy(List<String> itemTokenList) {
        return null;
    }

    @Override
    public List<ItemInfo.ItemOptionGroup> getItemOptionSeries(Item item) {
        return null;
    }
}

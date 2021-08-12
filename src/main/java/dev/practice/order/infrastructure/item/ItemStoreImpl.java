package dev.practice.order.infrastructure.item;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemStoreImpl implements ItemStore {
    private final ItemRepository itemRepository;

    @Override
    public Item store(Item item) {
        validCheck(item);
        return itemRepository.save(item);
    }

    private void validCheck(Item item) {
        if (StringUtils.isEmpty(item.getItemToken())) throw new InvalidParamException("Item.itemToken");
        if (StringUtils.isEmpty(item.getItemName())) throw new InvalidParamException("Item.itemName");
        if (item.getPartnerId() == null) throw new InvalidParamException("Item.partnerId");
        if (item.getItemPrice() == null) throw new InvalidParamException("Item.itemPrice");
        if (item.getStatus() == null) throw new InvalidParamException("Item.status");
    }
}

package dev.practice.order.infrastructure.item.optiongroup;

import dev.practice.order.domain.order.optiongroup.ItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionGroupRepository extends JpaRepository<ItemOptionGroup, Long> {
}

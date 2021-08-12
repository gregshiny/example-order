package dev.practice.order.infrastructure.item.option;


import dev.practice.order.domain.order.option.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}

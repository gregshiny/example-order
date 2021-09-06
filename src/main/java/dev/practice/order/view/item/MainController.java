package dev.practice.order.view.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @GetMapping(value="/view/v1/items")
    public String main() {
        return "order/items/main";
    }
    @GetMapping(value="/view/v1/item/{itemToken}")
    public String main(@PathVariable("itemToken") String itemToken) {
        return "order/item/main";
    }
}
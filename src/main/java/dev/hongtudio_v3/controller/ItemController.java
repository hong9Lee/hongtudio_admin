package dev.hongtudio_v3.controller;

import dev.hongtudio_v3.domain_vo.Categories;
import dev.hongtudio_v3.domain_vo.Item;
import dev.hongtudio_v3.service.CategoryService;
import dev.hongtudio_v3.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    // itemList...

    // itemList (관리자)
    @GetMapping("/admin/itemList")
    public String adminItemList(Model model) {

        model.addAttribute("findAllItemList", itemService.findItemAll());
//        model.addAttribute("parentList", categoryService.getParents());
        return "admin/adminItemList";
    }

    // item 등록 화면 (관리자)
    @GetMapping("/items/new")
    public String createItem(Model model) {
        model.addAttribute("form", new ItemForm());
        return "items/createItemForm";
    }

    // item 등록 로직 (관리자)
    @PostMapping("/items/new")
    public String createItem(ItemForm itemForm) {
        Item item = Item.createItem(itemForm);
        itemService.saveItem(item);
        return "redirect:/";
    }

    @PostMapping("/admin/items/deleteItems")
    public String deleteSingleItem(@RequestParam(required = false, value="arr[]") List<Long> items) {
        System.out.println("paramMap = " + items);

        itemService.deleteItems(items);
        return "redirect:/admin/itemList";
    }
}

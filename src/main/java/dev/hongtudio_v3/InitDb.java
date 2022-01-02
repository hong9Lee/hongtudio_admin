package dev.hongtudio_v3;


import dev.hongtudio_v3.controller.ItemForm;
import dev.hongtudio_v3.domain_vo.Categories;
import dev.hongtudio_v3.domain_vo.Item;
import dev.hongtudio_v3.service.CategoryService;
import dev.hongtudio_v3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;


@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInitItem();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final ItemService itemService;
        private final CategoryService categoryService;

        public void dbInitItem() {
            System.out.println("Init Item =>>>" + this.getClass());

            for (int i = 0; i < 15; i++) {
                ItemForm itemForm = new ItemForm(Integer.toUnsignedLong(i), "init" + i, i * 1000, i * 1000);
                Item item = Item.createItem(itemForm);
                itemService.saveItem(item);
            }


            Categories cat = categoryService.addCategory(null, "아우터");
            // 부모 카테고리가 존재하는 경우, view에서 부모 카테고리 Form을 전달해 아래의 cat 파라미터에 넣는다.
            categoryService.addCategory(cat, "패딩");


            System.out.println("em = " + em);
        }
    }




}

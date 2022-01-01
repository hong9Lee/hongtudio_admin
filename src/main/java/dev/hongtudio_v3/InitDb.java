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



//            Categories rootCat = new Categories("root");
//            categoryService.saveCategoryItems(rootCat);
//
//            Categories categories = new Categories("패션", rootCat);
//
//            Categories cat2 = new Categories( "남성", categories);
//            categories.addChildCategory(cat2);
//
//            Categories cat3 = new Categories( "여성", categories);
//            categories.addChildCategory(cat3);
//
//            categoryService.saveCategoryItems(categories);
//
//
//            Categories categories2 = new Categories("악세서리", rootCat);
//            Categories cat4 = new Categories( "시계", categories2);
//            categories2.addChildCategory(cat4);
//
//            categoryService.saveCategoryItems(categories2);
//
//
//            List<Categories> ca = categoryService.getCategoryAll();
//            System.out.println("ca = " + ca);
//
//            for (Categories categories1 : ca) {
//                System.out.println("categories1 = " + categories1);
//            }

            categoryService.addCategory(null, "아우터");
            categoryService.addCategory(new Categories("아우터"), "패딩");


            System.out.println("em = " + em);
        }
    }




}

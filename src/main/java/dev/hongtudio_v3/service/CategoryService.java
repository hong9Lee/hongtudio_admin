package dev.hongtudio_v3.service;

import dev.hongtudio_v3.domain_vo.Categories;
import dev.hongtudio_v3.repository_dao.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void saveCategoryItems(Categories categories) {
        categoryRepository.save(categories);
    }

    public List<Categories> getCategoryAll() { return categoryRepository.findAll(); }

    @Transactional
    public Categories addCategory(Categories parentEntity, String newCat) {
        // root check (없으면 생성)
        Categories rootCat = rootCheck();// root 객체를 가져옴

        // parent check -> view Form에서 부모객체와 추가할 카테고리 객체를 받아온다.
        if (parentEntity == null) {
            // 1. parent -> 부모인 경우 rootCat과 함께 save
            Categories cat = new Categories(newCat, rootCat);
            return categoryRepository.save(cat);
        } else {
            // 2. child -> 자식인 경우 부모객체와 함께 save (부모객체를 전달받을 수 있나?)
            parentEntity.addChildCategory(new Categories(newCat));
            return categoryRepository.save(parentEntity);
        }
    }

    public Categories rootCheck() {
        if (categoryRepository.findAll().size() == 0) { // root 생성
            Categories rootCat = new Categories("root", null);
            saveCategoryItems(rootCat);
            return rootCat;
        }

        return categoryRepository.findRootData();
    }

    public List<Categories> getParents() { return categoryRepository.findParents(); }
}

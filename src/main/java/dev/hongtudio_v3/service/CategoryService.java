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

    public List<Categories> getCategoryAll() {
        return categoryRepository.findAll();
    }

    public List<Categories> getParents() { return categoryRepository.findParents(); }

    @Transactional
    public void addCategory(Categories parentEntity, String newCat) {
        // root check 있으면 continue 없으면 생성
        Categories rootCat = rootCheck();// root 객체를 가져옴

        // parent check -> 화면상에서 부모 이름과 check param을 받아온다.

//        String checkUnit = "parent";
//        String newCat = "아우터";

        if (parentEntity == null) {
            // 1. parent -> 부모인 경우 rootCat과 함께 save
            // addCategory
            Categories cat = new Categories(newCat, rootCat);
            categoryRepository.save(cat);
        } else {
            // 2. child -> 자식인 경우 부모객체와 함께 save (부모객체를 전달받을 수 있나?)
            // addCategory
            Categories cat = new Categories(newCat, parentEntity);
            categoryRepository.save(cat);
        }


        // return boolean -> 성공적으로 저장되었습니다.
    }

    public Categories rootCheck() {

        if (categoryRepository.findAll().size() == 0) { // root 생성
            Categories rootCat = new Categories("root", null);
            saveCategoryItems(rootCat);
            return rootCat;
        }

        return categoryRepository.findRootData();
    }

//


//    public Long saveCategory(CategoryDTO categoryDTO) {
//
//        Categories category = categoryDTO.toEntity();
//
//        //대분류 등록
//        if (categoryDTO.getParentCategoryName() == null) {
//
//            //JPA 사용하여 DB에서 branch와 name의 중복값을 검사. (대분류에서만 가능)
////            if (categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), categoryDTO.getName())) {
////                throw new RuntimeException("branch와 name이 같을 수 없습니다. ");
////            }
//
//            //orElse로 refactor
//
//
//            Categories rootCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(),"ROOT")
//                    .orElseGet( () ->
//                            Categories.builder()
//                                    .name("ROOT")
//                                    .code("ROOT")
//                                    .branch(categoryDTO.getBranch())
//                                    .level(0)
//                                    .build()
//                    );
//            if (!categoryRepository.existsByBranchAndName(categoryDTO.getBranch(), "ROOT")) {
//                categoryRepository.save(rootCategory);
//            }
//            category.setParentCategory(rootCategory);
//            category.setLevel(1);
//            //중, 소분류 등록
//        } else {
//            String parentCategoryName = categoryDTO.getParentCategoryName();
//            Categories parentCategory = categoryRepository.findByBranchAndName(categoryDTO.getBranch(), parentCategoryName)
//                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리 없음 예외"));
//            category.setLevel(parentCategory.getLevel() + 1);
//            category.setParentCategory(parentCategory);
//            parentCategory.getSubCategory().add(category);
//        }
//
//        //category.setLive(true);
//        Long save = categoryRepository.save(category);
//        return save;
//    }
//
//    public Map<String, CategoryDTO> getCategoryByBranch(String branch) {
//        Categories category = categoryRepository.findByBranchAndCode(branch, "ROOT")
//                .orElseThrow(() -> new IllegalArgumentException("찾는 대분류가 없습니다"));
//
//        CategoryDTO categoryDTO = new CategoryDTO(category);
//        Map<String, CategoryDTO> data = new HashMap<>();
//        data.put(categoryDTO.getName(), categoryDTO);
//        return data;

//    }

}

package dev.hongtudio_v3.service;

import dev.hongtudio_v3.domain_vo.Categories;
import dev.hongtudio_v3.dto.CategoryDTO;
import dev.hongtudio_v3.repository_dao.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    //SavedID
    private CategoryDTO createCategoryDTO(String testBranch, String testCode, String testName) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setBranch(testBranch);
        categoryDTO.setCode(testCode);
        categoryDTO.setName(testName);
        return categoryDTO;
    }

    //Find Category

    private Categories findCategory (Long savedId) {
        return categoryRepository.findById(savedId).orElseThrow(IllegalArgumentException::new);
    }


    @Test
    public void 카테고리_저장_테스트() throws Exception{
        //given
        CategoryDTO categoryDTO = createCategoryDTO("TestBranch", "TestCode", "TestName");
        Long savedId = categoryService.saveCategory(categoryDTO);

        //when

        Categories category = findCategory(savedId);
        //then
        assertThat(category.getId()).isEqualTo(2L);
    }
}

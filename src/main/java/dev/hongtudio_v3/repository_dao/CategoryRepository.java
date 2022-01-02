package dev.hongtudio_v3.repository_dao;

import dev.hongtudio_v3.domain_vo.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final EntityManager em;

    public Categories save(Categories categories) {
        em.persist(categories);
        return categories;
    }

    public List<Categories> findAll() {
        return em.createQuery("select i from Categories i where i.parent.categoryId IS NOT NULL", Categories.class)
                .getResultList();
    }

    public Categories findRootData() {
        return em.createQuery("select i from Categories i where i.parent.categoryId IS NULL", Categories.class)
                .getSingleResult();
    }

    public List<Categories> findParents() {
        List<Categories> all = findAll();

        System.out.println("all = " + all);
        return null;
    }

    public Categories findById(Long id) {
        return em.createQuery("select i from Categories i where i.categoryId = :id", Categories.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

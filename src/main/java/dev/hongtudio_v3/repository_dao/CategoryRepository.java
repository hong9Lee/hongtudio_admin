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


    public Optional<Categories> findByName(String name) {
        return Optional.ofNullable(em.createQuery("select i from Categories i where i.name = :name", Categories.class)
                .setParameter("name", name)
                .getSingleResult());
    }

    public void save(Categories categories) {
        em.persist(categories);
//        em.merge(categories);

//        return categories.getId();
    }

    public List<Categories> findAll() {
        return em.createQuery("select i from Categories i", Categories.class)
                .getResultList();
    }

    public Categories findRootData() {
        return em.createQuery("select i from Categories i where i.categoryId = :id", Categories.class)
                .setParameter("id", 1)
                .getSingleResult();
    }

    public List<Categories> findParents() {
        List<Categories> all = findAll();

        System.out.println("all = " + all);
        return null;
    }

}

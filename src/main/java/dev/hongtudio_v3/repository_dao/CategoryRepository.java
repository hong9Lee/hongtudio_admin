package dev.hongtudio_v3.repository_dao;

import dev.hongtudio_v3.domain_vo.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public Optional<Categories> findByBranchAndName(String branch, String name) {

        try {
            return Optional.ofNullable(em.createQuery("select i from Categories i where i.name = :name and i.branch = :branch", Categories.class)
                      .setParameter("name", name)
                    .setParameter("branch", branch)
                    .getSingleResult());

        } catch (NoResultException e) {
            return Optional.ofNullable(null);
        }

    }

    public Optional<Categories> findByBranchAndCode(String branch, String code) {
        return Optional.ofNullable(em.createQuery("select i from Categories i where i.code = :code and i.branch = :branch", Categories.class)
                .setParameter("code", code)
                .setParameter("branch", branch)
                .getSingleResult());
    }

    public Optional<Categories> findById(Long id) {
        return Optional.ofNullable(em.createQuery("select i from Categories i where i.id = :id", Categories.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    public Boolean existsByBranchAndName(String branch, String name) {
        Optional<Categories> byBranchAndName = findByBranchAndName(branch, name);

        System.out.println("byBranchAndName = " + byBranchAndName);

        if (byBranchAndName.equals(Optional.empty())) {
            return false;
        }
        return true;
    }



    public Long save(Categories categories) {
        em.persist(categories);
//        em.merge(categories);

        return categories.getId();
    }

    public List<Categories> findAll() {
        return em.createQuery("select i from Categories i", Categories.class)
                .getResultList();
    }

//    public Categories findRootData() {
//        return em.createQuery("select i from Categories i where i.categoryId = :id", Categories.class)
//                .setParameter("id", 1)
//                .getSingleResult();
//    }

    public List<Categories> findParents() {
        List<Categories> all = findAll();

        System.out.println("all = " + all);
        return null;
    }

}

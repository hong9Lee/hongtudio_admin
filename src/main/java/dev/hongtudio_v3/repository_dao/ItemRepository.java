package dev.hongtudio_v3.repository_dao;

import dev.hongtudio_v3.domain_vo.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item); // TODO
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public void deleteItems(List<Long> items) {
        em.createQuery("delete from Item i where i.id in :items")
                .setParameter("items", items)
                .executeUpdate();

        em.flush();
        em.clear();
    }
}

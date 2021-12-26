package dev.hongtudio_v3.service;

import dev.hongtudio_v3.domain_vo.Item;
import dev.hongtudio_v3.repository_dao.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    // controller -> service -> repository -> db
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) { itemRepository.save(item); }

    public List<Item> findItemAll() {
        return itemRepository.findAll();
    }

    @Transactional
    public void deleteItems(List<Long> items) {
        itemRepository.deleteItems(items);
    }

}

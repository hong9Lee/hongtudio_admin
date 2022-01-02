package dev.hongtudio_v3.domain_vo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@SequenceGenerator(
        name = "CATEGORIES_SEQ_GENERATOR",
        sequenceName = "CATEGORIES_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIES_SEQ_GENERATOR")
    @Column(name = "category_id")
    private Long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "categories")
    private List<CategoryItem> categoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Categories parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Categories> subCategories = new ArrayList<>();

    public void addChildCategory(Categories child) {
        this.subCategories.add(child);
        child.setParent(this);
    }

    public Categories() {}

    public Categories(String categoryName) { this.categoryName = categoryName; }

    public Categories(String categoryName, Categories parent) {
        this.categoryName = categoryName;
        this.parent = parent;
    }
}

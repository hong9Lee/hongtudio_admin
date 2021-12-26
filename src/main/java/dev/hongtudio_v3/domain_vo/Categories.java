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
@NoArgsConstructor
/** self referencing table */
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String branch;

    private String code;
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Categories parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Categories> subCategory = new ArrayList<>();
    private Integer level;


    @Builder
    public Categories(String branch, String code, String name, Categories parentCategory, List<Categories> subCategory, Integer level) {
        this.branch = branch;
        this.code = code;
        this.name = name;
        this.parentCategory = parentCategory;
        this.level = level;
    }
}

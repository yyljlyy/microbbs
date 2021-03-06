package me.micro.bbs.category.support;

import me.micro.bbs.category.Category;
import me.micro.bbs.category.CategoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService
 *
 * Created by microacup on 2016/11/3.
 */
@Service
public class CategoryService {
    public static final String CACHES_NAME = "cache.cates";
    public static final String CACHE_NAME = "cache.cate";
    public static final Class<?> CACHE_TYPE = Category.class;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryFormAdpater formAdpater;

    @Cacheable(value = CACHES_NAME, keyGenerator = "cacheKeyGenerator")
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Cacheable(value = CACHE_NAME, key = "#id")
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    @Cacheable(value = CACHE_NAME, key = "#code")
    public Category findByCode(String code) {
        return categoryRepository.findByCode(code);
    }


    /**
     * 添加
     *
     * @param categoryForm
     * @return
     */
    @CacheEvict(value = CACHES_NAME, allEntries = true)
    public Category addCategory(CategoryForm categoryForm) {
        Category category = formAdpater.createCategory(categoryForm);
        Category saved = categoryRepository.save(category);
        //saveToIndex(saved);
        return saved;
    }

    @Caching (evict = {
            @CacheEvict(value = CACHES_NAME, allEntries = true),
            @CacheEvict(value = CACHE_NAME, key="#category.id")
    })
    public Category updateCategory(Category category, CategoryForm categoryForm) {
        if (category.getCode().equals(categoryForm.getCode()) && category.getTitle().equals(categoryForm.getTitle())) {
            return category;
        }

        formAdpater.updateCategory(category, categoryForm);
        Category saved = categoryRepository.save(category);
        return saved;
    }

    @Caching (evict = {
            @CacheEvict(value = CACHES_NAME, allEntries = true),
            @CacheEvict(value = CACHE_NAME)
    })
    public void deleteCategory(long id) {
        categoryRepository.delete(id);
    }

}

package ru.zakharov.market.formatters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;
import ru.zakharov.market.entities.Category;
import ru.zakharov.market.services.CategoryService;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Service
public class CategoryFormatter implements Formatter<Category> {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public Category parse(String s, Locale locale) throws ParseException {
        Integer id = Integer.valueOf(s);
        Optional<Category> category = this.categoryService.getCategoryById(id);
        return category.orElse(null);
    }

    @Override
    public String print(Category category, Locale locale) {
        return (category != null ? String.valueOf(category.getId()) : "");
    }
}

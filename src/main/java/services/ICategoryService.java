package services;

import entity.Category;

import java.util.List;

public interface ICategoryService {
        int count();

        List<Category> findAll(int page, int pagesize);

        List<Category> findByCategoryname(String catname);

        List<Category> findAll();

        Category findById(int cateid);

        void delete(int cateid) throws Exception;

        void update(Category category);

        void insert(Category category);
}

package dao;

import entity.Category;

import java.util.List;

public interface ICategoryDao {
        void insert(Category category);

        void update(Category category);

        void delete(int cateid) throws Exception;

        Category findById(int cateid);

        List<Category> findAll();

        List<Category> findByCategoryname(String catname);

        List<Category> findAll(int page, int pagesize);

        int count();
}

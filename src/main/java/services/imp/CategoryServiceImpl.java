package services.imp;

import dao.ICategoryDao;
import dao.imp.CategoryDao;
import entity.Category;

import java.util.List;

public class CategoryServiceImpl implements services.ICategoryService {
        ICategoryDao cateDao = new CategoryDao();

        @Override
        public int count() {
                return cateDao.count();
        }

        @Override
        public List<Category> findAll(int page, int pagesize) {
                return cateDao.findAll(page, pagesize);
        }

        @Override
        public List<Category> findByCategoryname(String catname) {
                return cateDao.findByCategoryname(catname);
        }

        @Override
        public List<Category> findAll() {
                return cateDao.findAll();
        }

        @Override
        public Category findById(int cateid) {
                return cateDao.findById(cateid);
        }

        @Override
        public void delete(int cateid) throws Exception {
                cateDao.delete(cateid);
        }

        @Override
        public void update(Category category) {
                cateDao.update(category);
        }



        @Override
        public void insert(Category category) {
                cateDao.insert(category);
        }

}

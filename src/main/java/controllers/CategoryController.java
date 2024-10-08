package controllers;

import entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import services.ICategoryService;
import services.imp.CategoryServiceImpl;
import ultils.Constant;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/categories", "/admin/category/add", "/admin/category/insert",
        "/admin/category/edit", "/admin/category/update", "/admin/category/delete"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class CategoryController extends HttpServlet {
        private static final long serialVersionUID = 1L;

        public ICategoryService categoryService = new CategoryServiceImpl();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.setCharacterEncoding("UTF-8");
                resp.setCharacterEncoding("UTF-8");


                String url = req.getRequestURI();

                System.out.println(url);
                if (url.contains("/admin/categories")){
                        List<Category> list = categoryService.findAll();
                        req.setAttribute("listcate", list);
                        req.getRequestDispatcher("/view/admin/category-list.jsp").forward(req, resp);
                }
                else if (url.contains("/admin/category/add")){
                        req.getRequestDispatcher("/view/admin/category-add.jsp").forward(req, resp);
                }
                else if (url.contains("edit")){
                        int id = Integer.parseInt(req.getParameter("id"));
                        Category cateModel = categoryService.findById(id);

                        req.setAttribute("cate", cateModel);
                        req.getRequestDispatcher("/view/admin/category-edit.jsp").forward(req, resp);
                }
                else if (url.contains("delete")){
                        doPost(req, resp);
                }
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String url = req.getRequestURI();
                if (url.contains("/admin/category/insert")){
                        String categoryname = req.getParameter("categoryname");
                        String img = req.getParameter("img");
                        int stt = Integer.parseInt(req.getParameter("status"));

                        Category categoryModel = new Category();
                        categoryModel.setCategoryname(categoryname);
                        categoryModel.setStatus(stt);


                        Part part = req.getPart("img1");
                        String fname = getFileName(part);
                                String uploadPath = Constant.DEFAULT_FILENAME;
                        File uploadDir = new File(uploadPath);

                        if (!uploadDir.exists())
                                uploadDir.mkdirs();
                        try{
                                if (part.getSize() > 0){
                                       part.write(uploadPath + fname);
                                       categoryModel.setImages(fname);
                                }
                                else if (!img.isEmpty()){
                                        categoryModel.setImages(img);
                                }
                                else
                                        categoryModel.setImages("avatar.png");
                        }
                        catch (Exception e){
                                throw new RuntimeException(e);
                        }
                        //inesrt
                        categoryService.insert(categoryModel);
                        //tra ve view
                        resp.sendRedirect(req.getContextPath() + "/admin/categories");
                }
                else if (url.contains("update")){
                        int id = Integer.parseInt(req.getParameter("categoryid"));
                        String categoryname = req.getParameter("categoryname");
                        String img = req.getParameter("img");
                        int stt = Integer.parseInt(req.getParameter("status"));

                        Category categoryModel = categoryService.findById(id);
                        categoryModel.setCategoryname(categoryname);
                        categoryModel.setStatus(stt);


                        Part part = req.getPart("img1");
                        String fname = getFileName(part);
                        String uploadPath = Constant.DEFAULT_FILENAME;
                        File uploadDir = new File(uploadPath);

                        if (!uploadDir.exists())
                                uploadDir.mkdirs();
                        try{
                                if (part.getSize() > 0){
                                        part.write(uploadPath + fname);
                                        categoryModel.setImages(fname);
                                }
                                else if (!img.isEmpty()){
                                        categoryModel.setImages(img);
                                }
                        }
                        catch (Exception e){
                                throw new RuntimeException(e);
                        }
                        //inesrt
                        categoryService.update(categoryModel);
                        resp.sendRedirect(req.getContextPath() + "/admin/categories");
                }
                else if (url.contains("delete")){
                        int id = Integer.parseInt(req.getParameter("id"));
                        try {
                                categoryService.delete(id);
                        } catch (Exception e) {
                                throw new RuntimeException(e);
                        }
                        resp.sendRedirect(req.getContextPath() + "/admin/categories");
                }
        }
        private String getFileName(Part part) {
                for (String content : part.getHeader("content-disposition").split(";")) {
                        if (content.trim().startsWith("filename"))
                                return content.substring(content.indexOf("=") + 2,
                                        content.length() - 1);
                }
                return Constant.DEFAULT_FILENAME;
        }
}

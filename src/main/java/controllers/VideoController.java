package controllers;

import entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.ICategoryService;
import services.IVideoService;
import services.imp.CategoryServiceImpl;
import services.imp.VideoServiceImpl;
import ultils.Constant;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/delete",
"/admin/video/edit", "/admin/video/update"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class VideoController extends HttpServlet {
        IVideoService videoService = new VideoServiceImpl();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.setCharacterEncoding("UTF-8");
                resp.setCharacterEncoding("UTF-8");

                String url = req.getRequestURI();

                if (url.contains("/admin/videos")){

                        int cateId;
                        HttpSession session = req.getSession(true);
                        if (req.getParameter("id") != null) {
                                cateId = Integer.parseInt(req.getParameter("id"));
                                session.setAttribute("categoryId", cateId);
                        }
                        else {
                                cateId = (int) session.getAttribute("categoryId");
                        }


                        List<Video> videos = videoService.findByCategoryId(cateId);
                        req.setAttribute("listvideo", videos);
                        req.getRequestDispatcher("/view/admin/video/video-list.jsp").forward(req, resp);
                }
                else if (url.contains("add")){
                        req.getRequestDispatcher("/view/admin/video/video-add.jsp").forward(req, resp);
                }
                else if (url.contains("delete")){
                        doPost(req, resp);
                }
                else if (url.contains("edit")){
                        String videoId = req.getParameter("videoid");
                        Video video = videoService.findById(videoId);
                        req.setAttribute("video", video);
                        req.getRequestDispatcher("/view/admin/video/video-edit.jsp").forward(req, resp);
                }
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.setCharacterEncoding("UTF-8");
                resp.setCharacterEncoding("UTF-8");

                String url = req.getRequestURI();

                ICategoryService categoryService = new CategoryServiceImpl();

                if (url.contains("/insert")){

                        HttpSession session = req.getSession();

                        Video video = new Video();
                        int categoryId = (int) session.getAttribute("categoryId");
                        String videoId = req.getParameter("videoId");
                        String title = req.getParameter("title");
                        String description = req.getParameter("description");
                        int views = Integer.parseInt(req.getParameter("views"));
                        int active = Integer.parseInt(req.getParameter("active"));


                        String poster = req.getParameter("poster");

                        Part part = req.getPart("poster1");
                        String fname = getFileName(part);
                        String uploadPath = Constant.DEFAULT_FILENAME;
                        File uploadDir = new File(uploadPath);

                        System.out.println(fname);
                        if (!uploadDir.exists())
                                uploadDir.mkdirs();
                        try{
                                if (part.getSize() > 0){
                                        part.write(uploadPath + fname);
                                        video.setPoster(fname);
                                }
                                else if (!poster.isEmpty()){
                                        video.setPoster(poster);
                                }
                                else
                                        video.setPoster("avatar.png");
                        }
                        catch (Exception e){
                                throw new RuntimeException(e);
                        }

                        video.setVideoId(videoId);
                        video.setViews(views);
                        video.setTitle(title);
                        video.setActive(active);
                        video.setDescription(description);
                        video.setCategory(categoryService.findById(categoryId));

                        videoService.insert(video);

                        resp.sendRedirect(req.getContextPath() + "/admin/videos");
                }
                else if (url.contains("delete")){
                        String videoId = req.getParameter("videoid");
                        try {
                                videoService.delete(videoId);
                        } catch (Exception e) {
                                throw new RuntimeException(e);
                        }
                        resp.sendRedirect(req.getContextPath() + "/admin/videos");
                }
                else if (url.contains("update")){
                        HttpSession session = req.getSession();

                        Video video = new Video();
                        int categoryId = (int) session.getAttribute("categoryId");
                        String videoId = req.getParameter("videoId");
                        String title = req.getParameter("title");
                        String description = req.getParameter("description");
                        int views = Integer.parseInt(req.getParameter("views"));
                        int active = Integer.parseInt(req.getParameter("active"));

                        String poster = req.getParameter("poster");

                        Part part = req.getPart("poster1");
                        String fname = getFileName(part);
                        String uploadPath = Constant.DEFAULT_FILENAME;
                        File uploadDir = new File(uploadPath);

                        if (!uploadDir.exists())
                                uploadDir.mkdirs();
                        try{
                                if (part.getSize() > 0){
                                        part.write(uploadPath + fname);
                                        video.setPoster(fname);
                                }
                                else if (!poster.isEmpty()){
                                        video.setPoster(poster);
                                }
                                else
                                        video.setPoster("avatar.png");
                        }
                        catch (Exception e){
                                throw new RuntimeException(e);
                        }

                        video.setVideoId(videoId);
                        video.setViews(views);
                        video.setTitle(title);
                        video.setActive(active);
                        video.setDescription(description);
                        video.setCategory(categoryService.findById(categoryId));

                        videoService.update(video);

                        resp.sendRedirect(req.getContextPath() + "/admin/videos");
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

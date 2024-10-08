package controllers;

import com.mysql.cj.xdevapi.Session;
import entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.ICategoryService;
import services.IVideoService;
import services.imp.CategoryServiceImpl;
import services.imp.VideoServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/videos", "/admin/video/add", "/admin/video/insert"})
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
                        String poster = req.getParameter("poster");
                        String description = req.getParameter("description");
                        int views = Integer.parseInt(req.getParameter("views"));
                        int active = Integer.parseInt(req.getParameter("active"));


                        video.setVideoId(videoId);
                        video.setViews(views);
                        video.setPoster(poster);
                        video.setTitle(title);
                        video.setActive(active);
                        video.setDescription(description);
                        video.setCategory(categoryService.findById(categoryId));

                        videoService.insert(video);

                        resp.sendRedirect(req.getContextPath() + "/admin/videos");
                }
        }
}

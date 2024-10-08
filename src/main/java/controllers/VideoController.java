package controllers;

import entity.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.IVideoService;
import services.imp.VideoServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet({"/admin/videos"})
public class VideoController extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.setCharacterEncoding("UTF-8");
                resp.setCharacterEncoding("UTF-8");

                String url = req.getRequestURI();

                IVideoService videoService = new VideoServiceImpl();
                int cateId = Integer.parseInt(req.getParameter("id"));
                if (url.contains("/admin/videos")){
                        List<Video> videos = videoService.findByCategoryId(cateId);
                        req.setAttribute("listvideo", videos);
                        System.out.println(videos.size());
                        req.getRequestDispatcher("/view/admin/video/video-list.jsp").forward(req, resp);
                }
        }
}

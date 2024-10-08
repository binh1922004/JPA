package services.imp;

import dao.ICategoryDao;
import dao.IVideoDao;
import dao.imp.CategoryDao;
import dao.imp.VideoDao;
import entity.Category;
import entity.Video;
import services.IVideoService;

import java.util.List;

public class VideoServiceImpl implements services.IVideoService {
        IVideoDao videoDao = new VideoDao();
        @Override
        public void insert(Video video){
                videoDao.insert(video);
        }

        @Override
        public void update(Video video){
                videoDao.update(video);
        }

        @Override
        public void delete(int videoid) throws Exception{
                videoDao.delete(videoid);
        }

        @Override
        public Video findById(int videoid){
                return videoDao.findById(videoid);
        }

        @Override
        public List<Video> findAll(){
                return videoDao.findAll();
        }
        public List<Video> findByCategoryId(int categoryid){
                return videoDao.findByCategoryId(categoryid);
        }

        @Override
        public List<Video> findByVideoTilte(String title){
                return videoDao.findByVideoTilte(title);
        }

        @Override
        public List<Video> findAll(int page, int pagesize){
                return videoDao.findAll(page, pagesize);
        }

        @Override
        public int count(){
                return videoDao.count();
        }

        public static void main(String[] args) {
                IVideoService videoService = new VideoServiceImpl();
                System.out.println(videoService.findByCategoryId(1));
        }
}

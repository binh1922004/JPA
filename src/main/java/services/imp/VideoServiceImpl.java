package services.imp;

import dao.IVideoDao;
import dao.imp.VideoDao;
import entity.Video;

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
}

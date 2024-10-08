package services;

import entity.Video;

import java.util.List;

public interface IVideoService {
        void insert(Video video);

        void update(Video video);

        void delete(String videoid) throws Exception;

        Video findById(String videoid);

        List<Video> findAll();
        List<Video> findByCategoryId(int categoryid);

        List<Video> findByVideoTilte(String title);

        List<Video> findAll(int page, int pagesize);

        int count();
}

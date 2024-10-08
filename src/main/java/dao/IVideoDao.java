package dao;

import entity.Video;

import java.util.List;

public interface IVideoDao {
        void insert(Video video);

        void update(Video video);

        void delete(int videoid) throws Exception;

        Video findById(int videoid);

        List<Video> findAll();

        List<Video> findByVideoTilte(String title);

        List<Video> findAll(int page, int pagesize);

        int count();
}

package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "CategoryID")
        private int categoryID;

        @Column(name = "CategoryName", columnDefinition = "nvarchar(50) not null")
        @NotEmpty(message = "Không được bỏ trống")
        private String categoryname;

        @Column(name = "Images", columnDefinition = "nvarchar(500) null")
        private String images;

        private int status;
        //bi-directional many-to-one association to Video


        @OneToMany(mappedBy="category")
        private List<Video> videos;
        public Video addVideo(Video video) {
                getVideos().add(video);
                video.setCategory(this);
                return video;


        }

        public Video removeVideo(Video video) {
                getVideos().remove(video);
                video.setCategory(null);
                return video;
        }

}

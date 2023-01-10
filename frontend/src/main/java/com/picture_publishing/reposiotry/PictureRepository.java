package com.picture_publishing.reposiotry;

import com.picture_publishing.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    @Query("SELECT p FROM Picture p WHERE p.pictureStatus = 'accepted'")
    List<Picture> findAcceptedPictures();

}

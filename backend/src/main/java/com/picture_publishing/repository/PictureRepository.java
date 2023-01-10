package com.picture_publishing.repository;

import com.picture_publishing.entities.Picture;
import com.picture_publishing.enums.PictureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    @Query("SELECT p FROM Picture p WHERE p.pictureStatus IS NULL or p.pictureStatus = ''")
    List<Picture> findUnProcessPictures();

    @Query("UPDATE Picture p SET p.pictureStatus = ?1 WHERE p.id = ?2")
    @Modifying
    void updatePictureStatus(PictureStatus pictureStatus, int id);

}

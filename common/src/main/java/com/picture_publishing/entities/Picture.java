package com.picture_publishing.entities;

import com.picture_publishing.enums.PictureCategory;
import com.picture_publishing.enums.PictureStatus;

import javax.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private PictureCategory pictureCategory;

    @Enumerated(EnumType.STRING)
    private PictureStatus pictureStatus;

    @Column(nullable = true)
    private Integer width;

    @Column(nullable = true)
    private Integer height;

    private String attachment;

    @ManyToOne
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PictureCategory getPictureCategory() {
        return pictureCategory;
    }

    public void setPictureCategory(PictureCategory pictureCategory) {
        this.pictureCategory = pictureCategory;
    }

    public PictureStatus getPictureStatus() {
        return pictureStatus;
    }

    public void setPictureStatus(PictureStatus pictureStatus) {
        this.pictureStatus = pictureStatus;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getWidth() {
        return width != null ? width : 0;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height != null ? height : 0;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Transient
    public String getUrl() {
        if (id <= 0 || attachment.isEmpty()) return null;
        return "uploaded-images/" + id + "/" + attachment;
    }

    @Override
    public String toString() {
        return "Picture{" + "id=" + id + ", description='" + description + '\'' + ", " +
                "pictureCategory=" + pictureCategory +
                ", pictureStatus=" + pictureStatus + ", attachment='" + attachment + '\'' + '}';
    }
}

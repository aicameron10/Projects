package com.pub.projects.model;

import java.util.Date;
import java.util.List;


public class Details {

    private String title;
    private String description;
    private Date creationDate;
    private List<Comments> comment;
    private boolean inReview;

    public Details(String title, String description, Date creationDate, List<Comments> comment, boolean inReview) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.comment = comment;
        this.inReview = inReview;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Comments> getComment() {
        return comment;
    }

    public void setComment(List<Comments> comment) {
        this.comment = comment;
    }

    public boolean isInReview() {
        return inReview;
    }

    public void setInReview(boolean inReview) {
        this.inReview = inReview;
    }

}

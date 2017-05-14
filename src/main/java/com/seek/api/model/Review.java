package com.seek.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by langley on 8/5/17.
 */
public class Review {

    /**
     * review id.
     */
    @Setter @Getter private Long id;

    @Setter @Getter private String jobID;

    @Setter @Getter private String applicationID;

    /**
     * reviewer username.
     */
    @Setter @Getter private String reviewerID;

    @Setter @Getter private String reviewerName;

    @Setter @Getter private String content;

    @Setter @Getter private boolean result;

    public Review() {
    }

    public Review(String jobID, String applicationID, String reviewerID, String reviewerName, String content, boolean result) {
        this.jobID = jobID;
        this.applicationID = applicationID;
        this.reviewerID = reviewerID;
        this.reviewerName = reviewerName;
        this.content = content;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", jobID='" + jobID + '\'' +
                ", applicationID='" + applicationID + '\'' +
                ", reviewerID='" + reviewerID + '\'' +
                ", reviewerName='" + reviewerName + '\'' +
                ", content='" + content + '\'' +
                ", result=" + result +
                '}';
    }
}

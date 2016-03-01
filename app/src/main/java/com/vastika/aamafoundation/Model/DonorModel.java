package com.vastika.aamafoundation.Model;

/**
 * Created by Almighty Amir on 11-Feb-16.
 */
public class DonorModel {

    private int ID;
    private String Title;
    private String Description;
    private String Source;
    private int PublishedBy;
    private String PublishedDate;
    private String Related;

    public DonorModel() {
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public void setPublishedBy(int PublishedBy) {
        this.PublishedBy = PublishedBy;
    }

    public void setPublishedDate(String PublishedDate) {
        this.PublishedDate = PublishedDate;
    }

    public void setRelated(String Related) {
        this.Related = Related;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getSource() {
        return Source;
    }

    public int getPublishedBy() {
        return PublishedBy;
    }

    public String getPublishedDate() {
        return PublishedDate;
    }

    public String getRelated() {
        return Related;
    }
}

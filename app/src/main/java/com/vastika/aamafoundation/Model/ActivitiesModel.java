package com.vastika.aamafoundation.Model;

/**
 * Created by Almighty Amir on 11-Feb-16.
 */
public class ActivitiesModel {


    /**
     * ID : 4
     * Title : पाकिस्तानी अम्पाएर असदलाई पाँच बर्षको प्रतिबन्ध
     * Description : Description here Description here Description here Description here Description here Description here Description here
     * Source : OnlineKhabar
     * PublishedBy : 1
     * PublishedDate : /Date(1449907200000)/
     * Related : rel
     */

    private int ID;
    private String Title;
    private String Description;
    private String Source;
    private int PublishedBy;
    private String PublishedDate;
    private String Related;

    public ActivitiesModel() {
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

    public String getTitle() { return Title; }

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

    public String getRelated() { return Related; }
}

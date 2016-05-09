package com.vastika.aamafoundation.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by admin on 5/9/16.
 */

@Table(name="Activities")
public class ActivitiesTestModel extends Model {


    /**
     * ID : 4
     * Title : पाकिस्तानी अम्पाएर असदलाई पाँच बर्षको प्रतिबन्ध
     * Description : Description here Description here Description here Description here Description here Description here Description here
     * Source : OnlineKhabar
     * PublishedBy : 1
     * PublishedDate : /Date(1449907200000)/
     * Related : rel
     */

    @Column(name="ID")
    private int ID;
    @Column(name="Title")
    private String Title;
    @Column(name="Description")
    private String Description;
    @Column(name="Source")
    private String Source;
    @Column(name = "PublishedBy")
    private int PublishedBy;
    @Column(name = "PublishedDate")
    private String PublishedDate;
    @Column(name="Related")
    private String Related;

    public ActivitiesTestModel() {
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

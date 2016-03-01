package com.vastika.aamafoundation.Model;

/**
 * Created by Almighty Amir on 11-Feb-16.
 */
public class CampaignModel {


    /**
     * ID : 1
     * Title : This is Campaign
     * Description : Description here Description here Description here Description here Description here Description here Description here
     * Image : Image will be here
     * EventDate : /Date(1422864000000)/
     * Related : related things will go here
     */
    private int ID;
    private String Title;
    private String Description;
    private String Image;
    private String EventDate;
    private String Related;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setEventDate(String EventDate) {
        this.EventDate = EventDate;
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

    public String getImage() {
        return Image;
    }

    public String getEventDate() {
        return EventDate;
    }

    public String getRelated() {
        return Related;
    }



}

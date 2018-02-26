package com.amidezcod.impulse2k18.modal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amidezcod on 24/2/18.
 */

public class EventDetailsInfo implements Parcelable {
    private String description;
    private String rules;
    private String registrationFee;
    private String prize;
    private String venue;
    private String[] coordinator;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public EventDetailsInfo(String description, String rules, String registrationFee, String prize, String venue, String[] coordinator, String url) {
        this.description = description;
        this.rules = rules;
        this.registrationFee = registrationFee;
        this.prize = prize;
        this.venue = venue;
        this.coordinator = coordinator;
        this.url = url;
    }

    private EventDetailsInfo(Parcel in) {
        description = in.readString();
        rules = in.readString();
        registrationFee = in.readString();
        prize = in.readString();
        venue = in.readString();
        coordinator = in.createStringArray();
        url = in.readString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(String registrationFee) {
        this.registrationFee = registrationFee;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String[] getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator[]) {
        this.coordinator = coordinator;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<EventDetailsInfo> CREATOR = new Parcelable.Creator<EventDetailsInfo>() {


        @Override
        public EventDetailsInfo createFromParcel(Parcel source) {
            return new EventDetailsInfo(source);
        }

        @Override
        public EventDetailsInfo[] newArray(int size) {
            return new EventDetailsInfo[0];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(rules);
        dest.writeString(registrationFee);
        dest.writeString(prize);
        dest.writeString(venue);
        dest.writeStringArray(coordinator);
        dest.writeString(url);

    }
}

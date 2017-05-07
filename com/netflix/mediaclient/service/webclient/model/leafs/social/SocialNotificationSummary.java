// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs.social;

import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import android.os.Parcel;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.google.gson.annotations.SerializedName;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class SocialNotificationSummary implements Parcelable
{
    public static final Parcelable$Creator<SocialNotificationSummary> CREATOR;
    @SerializedName("isRead")
    private boolean bWasRead;
    @SerializedName("isThanked")
    private boolean bWasThanked;
    @SerializedName("fromUser")
    private SocialNotificationsFriendProfile friendProfile;
    private String horizontalBoxartUrl;
    @SerializedName("id")
    private String id;
    private Video.InQueue inQueue;
    @SerializedName("msgString")
    private String messageString;
    @SerializedName("storyId")
    private String storyId;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("msgType")
    private NotificationTypes type;
    private Video.Summary videoSummary;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<SocialNotificationSummary>() {
            public SocialNotificationSummary createFromParcel(final Parcel parcel) {
                return new SocialNotificationSummary(parcel);
            }
            
            public SocialNotificationSummary[] newArray(final int n) {
                return new SocialNotificationSummary[n];
            }
        };
    }
    
    protected SocialNotificationSummary(final Parcel parcel) {
        final String[] array = new String[17];
        parcel.readStringArray(array);
        this.bWasRead = Boolean.valueOf(array[0]);
        this.bWasThanked = Boolean.valueOf(array[1]);
        this.friendProfile = new SocialNotificationsFriendProfile(array[2], array[3], array[4], array[5]);
        this.id = array[6];
        this.storyId = array[7];
        this.type = NotificationTypes.valueOf(array[8]);
        this.messageString = array[9];
        this.timestamp = Long.valueOf(array[10]);
        this.horizontalBoxartUrl = array[11];
        this.videoSummary = new Video.Summary();
        this.videoSummary.id = array[12];
        this.videoSummary.title = array[13];
        this.videoSummary.type = array[14];
        this.videoSummary.boxartUrl = array[15];
        this.inQueue = new Video.InQueue();
        this.inQueue.inQueue = Boolean.parseBoolean(array[16]);
    }
    
    public SocialNotificationSummary(final String id, final String storyId) {
        this.id = id;
        this.storyId = storyId;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final SocialNotificationSummary socialNotificationSummary = (SocialNotificationSummary)o;
            if (this.id == null) {
                if (socialNotificationSummary.id != null) {
                    return false;
                }
            }
            else if (!this.id.equals(socialNotificationSummary.id)) {
                return false;
            }
            if (this.bWasRead != socialNotificationSummary.bWasRead) {
                return false;
            }
            if (this.bWasThanked != socialNotificationSummary.bWasThanked) {
                return false;
            }
            if (this.inQueue == null) {
                if (socialNotificationSummary.inQueue != null) {
                    return false;
                }
            }
            else {
                if (socialNotificationSummary.inQueue == null) {
                    return false;
                }
                if (this.inQueue.inQueue != socialNotificationSummary.inQueue.inQueue) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public FriendProfile getFriendProfile() {
        return this.friendProfile;
    }
    
    public String getHorizontalBoxart() {
        return this.horizontalBoxartUrl;
    }
    
    public String getId() {
        return this.id;
    }
    
    public Video.InQueue getInQueue() {
        return this.inQueue;
    }
    
    public String getMessageString() {
        return this.messageString;
    }
    
    public String getStoryId() {
        return this.storyId;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public NotificationTypes getType() {
        return this.type;
    }
    
    public Video.Summary getVideoSummary() {
        return this.videoSummary;
    }
    
    public boolean getWasRead() {
        return this.bWasRead;
    }
    
    public boolean getWasThanked() {
        return this.bWasThanked;
    }
    
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public void setHorizontalBoxart(final String horizontalBoxartUrl) {
        this.horizontalBoxartUrl = horizontalBoxartUrl;
    }
    
    public void setInQueue(final Video.InQueue inQueue) {
        this.inQueue = inQueue;
    }
    
    public void setVideoSummary(final Video.Summary videoSummary) {
        this.videoSummary = videoSummary;
    }
    
    public void setWasRead(final boolean bWasRead) {
        this.bWasRead = bWasRead;
    }
    
    public void setWasThanked(final boolean bWasThanked) {
        this.bWasThanked = bWasThanked;
    }
    
    @Override
    public String toString() {
        return "SocialNotificationSummary [id=" + this.id + ", type=" + this.type + ", messageString=" + this.messageString + ", friendProfile=" + this.friendProfile + ", timestamp=" + this.timestamp + ", bWasRead=" + this.bWasRead + ", bWasThanked=" + this.bWasThanked + ", videoSummary=" + this.videoSummary + ", inQueue=" + this.inQueue + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final String[] array = { String.valueOf(this.bWasRead), String.valueOf(this.bWasThanked), this.friendProfile.getId(), this.friendProfile.getFirstName(), this.friendProfile.getLastName(), this.friendProfile.getImageUrl(), this.id, this.storyId, this.type.name(), this.messageString, String.valueOf(this.timestamp), this.horizontalBoxartUrl, this.videoSummary.id, this.videoSummary.title, this.videoSummary.type, this.videoSummary.boxartUrl, null };
        if (this.inQueue != null) {
            array[16] = String.valueOf(this.inQueue.inQueue);
        }
        parcel.writeStringArray(array);
    }
    
    public enum NotificationTypes
    {
        ADDED_TO_VIDEO_PLAYLIST, 
        IMPLICIT_FEEDBACK, 
        THANKS_SENT, 
        VIDEO_RECOMMENDATION;
    }
}

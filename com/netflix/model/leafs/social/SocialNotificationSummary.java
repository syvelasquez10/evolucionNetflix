// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.Log;
import android.os.Parcel;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import com.netflix.mediaclient.service.webclient.model.branches.Video$InQueue;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import com.netflix.model.branches.FalkorVideo;
import com.google.gson.annotations.SerializedName;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;
import android.os.Parcelable;

public class SocialNotificationSummary implements Parcelable, JsonPopulator
{
    public static final Parcelable$Creator<SocialNotificationSummary> CREATOR;
    private static final String TAG = "SocialNotificationSummary";
    @SerializedName("isRead")
    private boolean bWasRead;
    @SerializedName("isThanked")
    private boolean bWasThanked;
    private FalkorVideo falkorVideo;
    @SerializedName("fromUser")
    private FriendProfile friendProfile;
    @SerializedName("id")
    private String id;
    @SerializedName("msgString")
    private String messageString;
    @Deprecated
    private Video$InQueue obsoleteInQueue;
    @Deprecated
    private Video$Summary obsoleteVideoSummary;
    @SerializedName("storyId")
    private String storyId;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("msgType")
    private SocialNotificationSummary$NotificationTypes type;
    
    static {
        CREATOR = (Parcelable$Creator)new SocialNotificationSummary$1();
    }
    
    public SocialNotificationSummary() {
    }
    
    protected SocialNotificationSummary(final Parcel parcel) {
        final String[] array = new String[17];
        parcel.readStringArray(array);
        this.bWasRead = Boolean.valueOf(array[0]);
        this.bWasThanked = Boolean.valueOf(array[1]);
        final String s = array[2];
        final String s2 = array[3];
        final String s3 = array[4];
        final String s4 = array[5];
        this.friendProfile = new FriendProfile(s, s2, s3, s4, s4);
        this.id = array[6];
        this.storyId = array[7];
        this.type = SocialNotificationSummary$NotificationTypes.valueOf(array[8]);
        this.messageString = array[9];
        this.timestamp = Long.valueOf(array[10]);
        this.obsoleteVideoSummary = new Video$Summary();
        this.obsoleteVideoSummary.horzDispUrl = array[11];
        this.obsoleteVideoSummary.id = array[12];
        this.obsoleteVideoSummary.title = array[13];
        this.obsoleteVideoSummary.type = array[14];
        this.obsoleteVideoSummary.boxartUrl = array[15];
        this.obsoleteInQueue = new Video$InQueue();
        this.obsoleteInQueue.inQueue = Boolean.parseBoolean(array[16]);
    }
    
    public SocialNotificationSummary(final String id, final String storyId) {
        this.id = id;
        this.storyId = storyId;
    }
    
    public static SocialNotificationSummary$NotificationTypes getNotificationType(final String s) {
        switch (s) {
            default: {
                if (Log.isLoggable("SocialNotificationSummary", 5)) {
                    Log.w("SocialNotificationSummary", "Couldn't resolve the following notification type: " + s + "; returning default one: VIDEO_RECOMMENDATION");
                }
                return SocialNotificationSummary$NotificationTypes.VIDEO_RECOMMENDATION;
            }
            case "VideoRecommendation": {
                return SocialNotificationSummary$NotificationTypes.VIDEO_RECOMMENDATION;
            }
            case "ImplicitVideoRecommendationFeedback": {
                return SocialNotificationSummary$NotificationTypes.IMPLICIT_FEEDBACK;
            }
            case "AddToVideoPlaylist": {
                return SocialNotificationSummary$NotificationTypes.ADDED_TO_VIDEO_PLAYLIST;
            }
            case "SendThanks": {
                return SocialNotificationSummary$NotificationTypes.THANKS_SENT;
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
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
        if (this.falkorVideo == null) {
            if (socialNotificationSummary.falkorVideo != null) {
                return false;
            }
        }
        else {
            final com.netflix.model.leafs.Video$InQueue video$InQueue = (com.netflix.model.leafs.Video$InQueue)this.falkorVideo.get("inQueue");
            if (video$InQueue == null) {
                if (socialNotificationSummary.falkorVideo != null && socialNotificationSummary.falkorVideo.get("inQueue") != null) {
                    return false;
                }
            }
            else {
                if (socialNotificationSummary.falkorVideo == null || socialNotificationSummary.falkorVideo.get("inQueue") == null) {
                    return false;
                }
                if (video$InQueue.inQueue != ((com.netflix.model.leafs.Video$InQueue)socialNotificationSummary.falkorVideo.get("inQueue")).inQueue) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public FriendProfile getFriendProfile() {
        return this.friendProfile;
    }
    
    public String getId() {
        return this.id;
    }
    
    public boolean getInQueueValue() {
        if (this.falkorVideo != null) {
            return ((com.netflix.model.leafs.Video$InQueue)this.falkorVideo.get("inQueue")).inQueue;
        }
        if (this.obsoleteInQueue != null) {
            return this.obsoleteInQueue.inQueue;
        }
        Log.e("SocialNotificationSummary", "Both values are null: falkorVideo and obsoleteInQueue!");
        return false;
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
    
    public SocialNotificationSummary$NotificationTypes getType() {
        return this.type;
    }
    
    public Video getVideo() {
        if (this.falkorVideo != null) {
            return (com.netflix.model.leafs.Video$Summary)this.falkorVideo.get("summary");
        }
        return this.obsoleteVideoSummary;
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
    
    public void populate(JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SocialNotificationSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            jsonElement = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0174: {
                switch (s.hashCode()) {
                    case 3355: {
                        if (s.equals("id")) {
                            n = 0;
                            break Label_0174;
                        }
                        break;
                    }
                    case -1884251920: {
                        if (s.equals("storyId")) {
                            n = 1;
                            break Label_0174;
                        }
                        break;
                    }
                    case 1343750747: {
                        if (s.equals("msgType")) {
                            n = 2;
                            break Label_0174;
                        }
                        break;
                    }
                    case -1473868046: {
                        if (s.equals("msgString")) {
                            n = 3;
                            break Label_0174;
                        }
                        break;
                    }
                    case -1244622187: {
                        if (s.equals("fromUser")) {
                            n = 4;
                            break Label_0174;
                        }
                        break;
                    }
                    case 55126294: {
                        if (s.equals("timestamp")) {
                            n = 5;
                            break Label_0174;
                        }
                        break;
                    }
                    case -1180158496: {
                        if (s.equals("isRead")) {
                            n = 6;
                            break Label_0174;
                        }
                        break;
                    }
                    case -1933137793: {
                        if (s.equals("isThanked")) {
                            n = 7;
                            break Label_0174;
                        }
                        break;
                    }
                }
                n = -1;
            }
            switch (n) {
                default: {
                    continue;
                }
                case 0: {
                    this.id = jsonElement.getAsString();
                    continue;
                }
                case 1: {
                    this.storyId = jsonElement.getAsString();
                    continue;
                }
                case 2: {
                    this.type = getNotificationType(jsonElement.getAsString());
                    continue;
                }
                case 3: {
                    String asString;
                    if (jsonElement.isJsonNull()) {
                        asString = null;
                    }
                    else {
                        asString = jsonElement.getAsString();
                    }
                    this.messageString = asString;
                    continue;
                }
                case 4: {
                    (this.friendProfile = new FriendProfile()).populate(jsonElement.getAsJsonObject());
                    continue;
                }
                case 5: {
                    this.timestamp = jsonElement.getAsLong();
                    continue;
                }
                case 6: {
                    this.bWasRead = jsonElement.getAsBoolean();
                    continue;
                }
                case 7: {
                    this.bWasThanked = (!jsonElement.isJsonNull() && jsonElement.getAsBoolean());
                    continue;
                }
            }
        }
    }
    
    @Deprecated
    public void setInQueue(final Video$InQueue obsoleteInQueue) {
        this.obsoleteInQueue = obsoleteInQueue;
    }
    
    public void setVideo(final FalkorVideo falkorVideo) {
        this.falkorVideo = falkorVideo;
    }
    
    @Deprecated
    public void setVideoSummary(final Video$Summary obsoleteVideoSummary) {
        this.obsoleteVideoSummary = obsoleteVideoSummary;
    }
    
    public void setWasRead(final boolean bWasRead) {
        this.bWasRead = bWasRead;
    }
    
    public void setWasThanked(final boolean bWasThanked) {
        this.bWasThanked = bWasThanked;
    }
    
    @Override
    public String toString() {
        return "SocialNotificationSummary [id=" + this.id + ", storyId=" + this.storyId + ", type=" + this.type + ", messageString=" + this.messageString + ", friendProfile=" + this.friendProfile + ", timestamp=" + this.timestamp + ", bWasRead=" + this.bWasRead + ", bWasThanked=" + this.bWasThanked + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final String[] array = { String.valueOf(this.bWasRead), String.valueOf(this.bWasThanked), this.friendProfile.getId(), this.friendProfile.getFirstName(), this.friendProfile.getLastName(), this.friendProfile.getImageUrl(), this.id, this.storyId, this.type.name(), this.messageString, String.valueOf(this.timestamp), null, null, null, null, null, null };
        if (this.obsoleteVideoSummary != null) {
            array[11] = this.obsoleteVideoSummary.horzDispUrl;
            array[12] = this.obsoleteVideoSummary.id;
            array[13] = this.obsoleteVideoSummary.title;
            array[14] = this.obsoleteVideoSummary.type;
            array[15] = this.obsoleteVideoSummary.boxartUrl;
        }
        else if (this.falkorVideo != null && this.falkorVideo.get("summary") != null) {
            final com.netflix.model.leafs.Video$Summary video$Summary = (com.netflix.model.leafs.Video$Summary)this.falkorVideo.get("summary");
            array[11] = video$Summary.horzDispUrl;
            array[12] = video$Summary.id;
            array[13] = video$Summary.title;
            array[14] = video$Summary.type;
            array[15] = video$Summary.boxartUrl;
        }
        if (this.obsoleteInQueue != null) {
            array[16] = String.valueOf(this.obsoleteInQueue.inQueue);
        }
        else if (this.falkorVideo != null && this.falkorVideo.get("inQueue") != null) {
            array[16] = String.valueOf(((com.netflix.model.leafs.Video$InQueue)this.falkorVideo.get("inQueue")).inQueue);
        }
        parcel.writeStringArray(array);
    }
}
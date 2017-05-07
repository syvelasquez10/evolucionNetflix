// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.model.leafs.Video$InQueue;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.webclient.model.leafs.FriendProfile;
import com.google.gson.annotations.SerializedName;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import android.os.Parcelable;

public class SocialNotificationSummary implements Parcelable, JsonPopulator
{
    public static final Parcelable$Creator<SocialNotificationSummary> CREATOR;
    private static final String TAG = "SocialNotificationSummary";
    @SerializedName("isRead")
    private boolean bWasRead;
    @SerializedName("isThanked")
    private boolean bWasThanked;
    @SerializedName("fromUser")
    private FriendProfile friendProfile;
    @SerializedName("id")
    private String id;
    private boolean inQueue;
    @SerializedName("msgString")
    private String messageString;
    @SerializedName("nsaBoxartUrl")
    private String nsaBoxartUrl;
    @SerializedName("nsaSeasonIndex")
    private int nsaSeasonIndex;
    @SerializedName("nsaSeasonsCount")
    private int nsaSeasonsCount;
    @SerializedName("nsaTimestamp")
    private long nsaTimestamp;
    @SerializedName("showType")
    private SocialNotificationSummary$ShowTypes showType;
    @SerializedName("storyId")
    private String storyId;
    @SerializedName("timestamp")
    private long timestamp;
    private String tvCardUrl;
    @SerializedName("msgType")
    private SocialNotificationSummary$NotificationTypes type;
    private String videoId;
    private String videoTitle;
    private VideoType videoType;
    
    static {
        CREATOR = (Parcelable$Creator)new SocialNotificationSummary$1();
    }
    
    public SocialNotificationSummary() {
    }
    
    protected SocialNotificationSummary(final Parcel parcel) {
        final String[] array = new String[21];
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
        this.nsaTimestamp = Long.valueOf(array[11]);
        this.nsaSeasonIndex = Integer.valueOf(array[12]);
        this.nsaSeasonsCount = Integer.valueOf(array[13]);
        this.nsaBoxartUrl = array[14];
        this.videoId = array[15];
        this.videoType = VideoType.create(array[16]);
        this.videoTitle = array[17];
        this.tvCardUrl = array[18];
        this.inQueue = Boolean.valueOf(array[19]);
        if (StringUtils.isNotEmpty(array[20])) {
            this.showType = SocialNotificationSummary$ShowTypes.valueOf(array[20]);
        }
    }
    
    public SocialNotificationSummary(final String id, final String storyId) {
        this.id = id;
        this.storyId = storyId;
    }
    
    public static SocialNotificationSummary$NotificationTypes getNotificationType(final String s) {
        switch (s) {
            default: {
                if (Log.isLoggable()) {
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
            case "NewSeasonAlert": {
                return SocialNotificationSummary$NotificationTypes.NEW_SEASON_ALERT;
            }
        }
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
            if (!StringUtils.safeEquals(this.videoId, socialNotificationSummary.videoId)) {
                return false;
            }
            if (this.inQueue != socialNotificationSummary.inQueue) {
                return false;
            }
        }
        return true;
    }
    
    public void fillVideoDetails(final FalkorVideo falkorVideo) {
        final Video$Summary video$Summary = (Video$Summary)falkorVideo.get("summary");
        this.videoId = video$Summary.getId();
        this.videoType = video$Summary.getType();
        this.videoTitle = video$Summary.getTitle();
        this.tvCardUrl = video$Summary.getTvCardUrl();
        this.inQueue = ((Video$InQueue)falkorVideo.get("inQueue")).inQueue;
    }
    
    public FriendProfile getFriendProfile() {
        return this.friendProfile;
    }
    
    public String getId() {
        return this.id;
    }
    
    public boolean getInQueueValue() {
        return this.inQueue;
    }
    
    public String getMessageString() {
        return this.messageString;
    }
    
    public int getNSASeasonIndex() {
        return this.nsaSeasonIndex;
    }
    
    public int getNSASeasonsCount() {
        return this.nsaSeasonsCount;
    }
    
    public long getNSATimestamp() {
        return this.nsaTimestamp;
    }
    
    public SocialNotificationSummary$ShowTypes getShowType() {
        return this.showType;
    }
    
    public String getStoryId() {
        return this.storyId;
    }
    
    public String getTVCardUrl() {
        if (StringUtils.isNotEmpty(this.tvCardUrl)) {
            return this.tvCardUrl;
        }
        return this.nsaBoxartUrl;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public SocialNotificationSummary$NotificationTypes getType() {
        return this.type;
    }
    
    public String getVideoId() {
        return this.videoId;
    }
    
    public String getVideoTitle() {
        return this.videoTitle;
    }
    
    public VideoType getVideoType() {
        return this.videoType;
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
    
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SocialNotificationSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            if (jsonElement2.isJsonNull()) {
                if (!Log.isLoggable()) {
                    continue;
                }
                Log.w("SocialNotificationSummary", "Skipping null value for: " + entry.getKey());
            }
            else {
                final String s = entry.getKey();
                int n = 0;
                Label_0262: {
                    switch (s.hashCode()) {
                        case 3355: {
                            if (s.equals("id")) {
                                n = 0;
                                break Label_0262;
                            }
                            break;
                        }
                        case -1884251920: {
                            if (s.equals("storyId")) {
                                n = 1;
                                break Label_0262;
                            }
                            break;
                        }
                        case 1343750747: {
                            if (s.equals("msgType")) {
                                n = 2;
                                break Label_0262;
                            }
                            break;
                        }
                        case -1473868046: {
                            if (s.equals("msgString")) {
                                n = 3;
                                break Label_0262;
                            }
                            break;
                        }
                        case -1244622187: {
                            if (s.equals("fromUser")) {
                                n = 4;
                                break Label_0262;
                            }
                            break;
                        }
                        case 55126294: {
                            if (s.equals("timestamp")) {
                                n = 5;
                                break Label_0262;
                            }
                            break;
                        }
                        case 1000374586: {
                            if (s.equals("nsaTimestamp")) {
                                n = 6;
                                break Label_0262;
                            }
                            break;
                        }
                        case 1265500883: {
                            if (s.equals("nsaSeasonIndex")) {
                                n = 7;
                                break Label_0262;
                            }
                            break;
                        }
                        case 1738877531: {
                            if (s.equals("nsaSeasonsCount")) {
                                n = 8;
                                break Label_0262;
                            }
                            break;
                        }
                        case 2098898363: {
                            if (s.equals("nsaBoxartUrl")) {
                                n = 9;
                                break Label_0262;
                            }
                            break;
                        }
                        case -1180158496: {
                            if (s.equals("isRead")) {
                                n = 10;
                                break Label_0262;
                            }
                            break;
                        }
                        case -1933137793: {
                            if (s.equals("isThanked")) {
                                n = 11;
                                break Label_0262;
                            }
                            break;
                        }
                        case -338815017: {
                            if (s.equals("showType")) {
                                n = 12;
                                break Label_0262;
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
                        this.id = jsonElement2.getAsString();
                        continue;
                    }
                    case 1: {
                        this.storyId = jsonElement2.getAsString();
                        continue;
                    }
                    case 2: {
                        this.type = getNotificationType(jsonElement2.getAsString());
                        continue;
                    }
                    case 3: {
                        this.messageString = jsonElement2.getAsString();
                        continue;
                    }
                    case 4: {
                        (this.friendProfile = new FriendProfile()).populate(jsonElement2.getAsJsonObject());
                        continue;
                    }
                    case 5: {
                        this.timestamp = jsonElement2.getAsLong();
                        continue;
                    }
                    case 6: {
                        this.nsaTimestamp = jsonElement2.getAsLong();
                        continue;
                    }
                    case 7: {
                        this.nsaSeasonIndex = jsonElement2.getAsInt();
                        continue;
                    }
                    case 8: {
                        this.nsaSeasonsCount = jsonElement2.getAsInt();
                        continue;
                    }
                    case 9: {
                        this.nsaBoxartUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 10: {
                        this.bWasRead = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 11: {
                        this.bWasThanked = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 12: {
                        this.showType = SocialNotificationSummary$ShowTypes.getType(jsonElement2.getAsString());
                        continue;
                    }
                }
            }
        }
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
        final String[] array = new String[21];
        array[0] = String.valueOf(this.bWasRead);
        array[1] = String.valueOf(this.bWasThanked);
        if (this.friendProfile != null) {
            array[2] = this.friendProfile.getId();
            array[3] = this.friendProfile.getFirstName();
            array[4] = this.friendProfile.getLastName();
            array[5] = this.friendProfile.getImageUrl();
        }
        array[6] = this.id;
        array[7] = this.storyId;
        array[8] = this.type.name();
        array[9] = this.messageString;
        array[10] = String.valueOf(this.timestamp);
        array[11] = String.valueOf(this.nsaTimestamp);
        array[12] = String.valueOf(this.nsaSeasonIndex);
        array[13] = String.valueOf(this.nsaSeasonsCount);
        array[14] = this.nsaBoxartUrl;
        array[15] = this.videoId;
        array[16] = this.videoType.getValue();
        array[17] = this.videoTitle;
        array[18] = this.tvCardUrl;
        array[19] = String.valueOf(this.inQueue);
        if (this.showType != null) {
            array[20] = this.showType.name();
        }
        parcel.writeStringArray(array);
    }
}

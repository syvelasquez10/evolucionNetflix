// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.model.leafs.Video$TvCardArt;
import com.netflix.model.leafs.Video$InQueue;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.webclient.model.leafs.FriendProfile;
import com.google.gson.annotations.SerializedName;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;
import android.os.Parcelable;

public class IrisNotificationSummary implements Parcelable, JsonMerger, JsonPopulator
{
    public static final Parcelable$Creator<IrisNotificationSummary> CREATOR;
    private static final int NUM_OF_FIELDS = 25;
    private static final String TAG = "SocialNotificationSummary";
    @SerializedName("isRead")
    private boolean bWasRead;
    @SerializedName("isThanked")
    private boolean bWasThanked;
    @SerializedName("body")
    private String bodyText;
    @SerializedName("fromUser")
    private FriendProfile friendProfile;
    @SerializedName("header")
    private String headerText;
    @SerializedName("id")
    private String id;
    @SerializedName("imageAltText")
    private String imageAltText;
    @SerializedName("imageTarget")
    private String imageTarget;
    @SerializedName("imageUrl")
    private String imageUrl;
    private boolean inQueue;
    @SerializedName("msgString")
    private String messageString;
    @SerializedName("showTimestamp")
    private boolean showTimestamp;
    @SerializedName("showType")
    private VideoType showType;
    @SerializedName("storyId")
    private String storyId;
    @SerializedName("textTarget")
    private String textTarget;
    @SerializedName("timestamp")
    private long timestamp;
    private String tvCardUrl;
    @SerializedName("msgType")
    private IrisNotificationSummary$NotificationTypes type;
    @SerializedName("urlTarget")
    private String urlTarget;
    private String videoId;
    private String videoTitle;
    private VideoType videoType;
    
    static {
        CREATOR = (Parcelable$Creator)new IrisNotificationSummary$1();
    }
    
    public IrisNotificationSummary() {
    }
    
    protected IrisNotificationSummary(final Parcel parcel) {
        final String[] array = new String[25];
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
        this.type = IrisNotificationSummary$NotificationTypes.valueOf(array[8]);
        this.messageString = array[9];
        this.timestamp = Long.valueOf(array[10]);
        this.imageAltText = array[11];
        this.showTimestamp = Boolean.valueOf(array[12]);
        this.headerText = array[13];
        this.imageUrl = array[14];
        this.videoId = array[15];
        this.videoType = VideoType.create(array[16]);
        this.videoTitle = array[17];
        this.tvCardUrl = array[18];
        this.inQueue = Boolean.valueOf(array[19]);
        if (StringUtils.isNotEmpty(array[20])) {
            this.showType = VideoType.valueOf(array[20]);
        }
        this.bodyText = array[21];
        this.imageTarget = array[22];
        this.textTarget = array[23];
        this.urlTarget = array[24];
    }
    
    public IrisNotificationSummary(final String id, final String storyId) {
        this.id = id;
        this.storyId = storyId;
    }
    
    public static IrisNotificationSummary$NotificationTypes getNotificationType(final String s) {
        return IrisNotificationSummary$NotificationTypes.NEW_SEASON_ALERT;
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
            final IrisNotificationSummary irisNotificationSummary = (IrisNotificationSummary)o;
            if (this.id == null) {
                if (irisNotificationSummary.id != null) {
                    return false;
                }
            }
            else if (!this.id.equals(irisNotificationSummary.id)) {
                return false;
            }
            if (this.bWasRead != irisNotificationSummary.bWasRead) {
                return false;
            }
            if (this.bWasThanked != irisNotificationSummary.bWasThanked) {
                return false;
            }
            if (!StringUtils.safeEquals(this.videoId, irisNotificationSummary.videoId)) {
                return false;
            }
            if (this.inQueue != irisNotificationSummary.inQueue) {
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
        this.inQueue = ((Video$InQueue)falkorVideo.get("inQueue")).inQueue;
        final Video$TvCardArt video$TvCardArt = (Video$TvCardArt)falkorVideo.get("tvCardArt");
        if (video$TvCardArt != null) {
            this.tvCardUrl = video$TvCardArt.getUrl();
        }
    }
    
    public String getBodyText() {
        return this.bodyText;
    }
    
    public FriendProfile getFriendProfile() {
        return this.friendProfile;
    }
    
    public String getHeaderText() {
        return this.headerText;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getImageAltText() {
        return this.imageAltText;
    }
    
    public String getImageTarget() {
        return this.imageTarget;
    }
    
    public boolean getInQueueValue() {
        return this.inQueue;
    }
    
    public String getMessageString() {
        return this.messageString;
    }
    
    public boolean getShowTimestamp() {
        return this.showTimestamp;
    }
    
    public VideoType getShowType() {
        return this.showType;
    }
    
    public String getStoryId() {
        return this.storyId;
    }
    
    public String getTVCardUrl() {
        if (StringUtils.isNotEmpty(this.tvCardUrl)) {
            return this.tvCardUrl;
        }
        return this.imageUrl;
    }
    
    public String getTextTarget() {
        return this.textTarget;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public IrisNotificationSummary$NotificationTypes getType() {
        return this.type;
    }
    
    public String getUrlTarget() {
        return this.urlTarget;
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
    
    public void populate(JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SocialNotificationSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            jsonElement = entry.getValue();
            if (jsonElement.isJsonNull()) {
                if (!Log.isLoggable()) {
                    continue;
                }
                Log.w("SocialNotificationSummary", "Skipping null value for: " + entry.getKey());
            }
            else {
                final String s = entry.getKey();
                int n = 0;
                Label_0294: {
                    switch (s.hashCode()) {
                        case 3355: {
                            if (s.equals("id")) {
                                n = 0;
                                break Label_0294;
                            }
                            break;
                        }
                        case -1884251920: {
                            if (s.equals("storyId")) {
                                n = 1;
                                break Label_0294;
                            }
                            break;
                        }
                        case 1343750747: {
                            if (s.equals("msgType")) {
                                n = 2;
                                break Label_0294;
                            }
                            break;
                        }
                        case -1473868046: {
                            if (s.equals("msgString")) {
                                n = 3;
                                break Label_0294;
                            }
                            break;
                        }
                        case -1244622187: {
                            if (s.equals("fromUser")) {
                                n = 4;
                                break Label_0294;
                            }
                            break;
                        }
                        case 55126294: {
                            if (s.equals("timestamp")) {
                                n = 5;
                                break Label_0294;
                            }
                            break;
                        }
                        case -306513765: {
                            if (s.equals("imageAltText")) {
                                n = 6;
                                break Label_0294;
                            }
                            break;
                        }
                        case -2108533044: {
                            if (s.equals("imageTarget")) {
                                n = 7;
                                break Label_0294;
                            }
                            break;
                        }
                        case -586188135: {
                            if (s.equals("showTimestamp")) {
                                n = 8;
                                break Label_0294;
                            }
                            break;
                        }
                        case -1221270899: {
                            if (s.equals("header")) {
                                n = 9;
                                break Label_0294;
                            }
                            break;
                        }
                        case 3029410: {
                            if (s.equals("body")) {
                                n = 10;
                                break Label_0294;
                            }
                            break;
                        }
                        case 1862946078: {
                            if (s.equals("textTarget")) {
                                n = 11;
                                break Label_0294;
                            }
                            break;
                        }
                        case -859610604: {
                            if (s.equals("imageUrl")) {
                                n = 12;
                                break Label_0294;
                            }
                            break;
                        }
                        case -1180158496: {
                            if (s.equals("isRead")) {
                                n = 13;
                                break Label_0294;
                            }
                            break;
                        }
                        case -1933137793: {
                            if (s.equals("isThanked")) {
                                n = 14;
                                break Label_0294;
                            }
                            break;
                        }
                        case -338815017: {
                            if (s.equals("showType")) {
                                n = 15;
                                break Label_0294;
                            }
                            break;
                        }
                        case -342813728: {
                            if (s.equals("urlTarget")) {
                                n = 16;
                                break Label_0294;
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
                        this.messageString = jsonElement.getAsString();
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
                        this.imageAltText = jsonElement.getAsString();
                        continue;
                    }
                    case 7: {
                        this.imageTarget = jsonElement.getAsString();
                        continue;
                    }
                    case 8: {
                        this.showTimestamp = jsonElement.getAsBoolean();
                        continue;
                    }
                    case 9: {
                        this.headerText = jsonElement.getAsString();
                        continue;
                    }
                    case 10: {
                        this.bodyText = jsonElement.getAsString();
                        continue;
                    }
                    case 11: {
                        this.textTarget = jsonElement.getAsString();
                        continue;
                    }
                    case 12: {
                        this.imageUrl = jsonElement.getAsString();
                        continue;
                    }
                    case 13: {
                        this.bWasRead = jsonElement.getAsBoolean();
                        continue;
                    }
                    case 14: {
                        this.bWasThanked = jsonElement.getAsBoolean();
                        continue;
                    }
                    case 15: {
                        VideoType showType;
                        if ("movie".equalsIgnoreCase(jsonElement.getAsString())) {
                            showType = VideoType.MOVIE;
                        }
                        else {
                            showType = VideoType.SHOW;
                        }
                        this.showType = showType;
                        continue;
                    }
                    case 16: {
                        this.urlTarget = jsonElement.getAsString();
                        continue;
                    }
                }
            }
        }
    }
    
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SocialNotificationSummary", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "id": {
                this.id = jsonParser.getValueAsString();
                break;
            }
            case "storyId": {
                this.storyId = jsonParser.getValueAsString();
                break;
            }
            case "msgType": {
                this.type = getNotificationType(jsonParser.getValueAsString());
                break;
            }
            case "msgString": {
                this.messageString = jsonParser.getValueAsString();
                break;
            }
            case "fromUser": {
                (this.friendProfile = new FriendProfile()).set(s, jsonParser);
                break;
            }
            case "timestamp": {
                this.timestamp = jsonParser.getValueAsLong();
                break;
            }
            case "imageAltText": {
                this.imageAltText = jsonParser.getValueAsString();
                break;
            }
            case "imageTarget": {
                this.imageTarget = jsonParser.getValueAsString();
                break;
            }
            case "showTimestamp": {
                this.showTimestamp = jsonParser.getValueAsBoolean();
                break;
            }
            case "header": {
                this.headerText = jsonParser.getValueAsString();
                break;
            }
            case "body": {
                this.bodyText = jsonParser.getValueAsString();
                break;
            }
            case "textTarget": {
                this.textTarget = jsonParser.getValueAsString();
                break;
            }
            case "imageUrl": {
                this.imageUrl = jsonParser.getValueAsString();
                break;
            }
            case "isRead": {
                this.bWasRead = jsonParser.getValueAsBoolean();
                break;
            }
            case "isThanked": {
                this.bWasThanked = jsonParser.getValueAsBoolean();
                break;
            }
            case "showType": {
                VideoType showType;
                if ("movie".equalsIgnoreCase(jsonParser.getValueAsString())) {
                    showType = VideoType.MOVIE;
                }
                else {
                    showType = VideoType.SHOW;
                }
                this.showType = showType;
                break;
            }
            case "urlTarget": {
                this.urlTarget = jsonParser.getValueAsString();
                break;
            }
        }
        return true;
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
        final String[] array = new String[25];
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
        array[9] = this.messageString;
        array[8] = this.type.name();
        array[10] = String.valueOf(this.timestamp);
        array[11] = this.imageAltText;
        array[12] = String.valueOf(this.showTimestamp);
        array[13] = this.headerText;
        array[14] = this.imageUrl;
        array[15] = this.videoId;
        array[16] = this.videoType.getValue();
        array[17] = this.videoTitle;
        array[18] = this.tvCardUrl;
        array[19] = String.valueOf(this.inQueue);
        if (this.showType != null) {
            array[20] = this.showType.name();
        }
        array[21] = this.bodyText;
        array[22] = this.imageTarget;
        array[23] = this.textTarget;
        array[24] = this.urlTarget;
        parcel.writeStringArray(array);
    }
}

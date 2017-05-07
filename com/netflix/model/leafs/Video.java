// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;
import com.netflix.mediaclient.servicemgr.model.VideoType;

public final class Video
{
    public static boolean isSocialVideoType(final VideoType videoType) {
        return VideoType.SOCIAL_FRIEND.equals(videoType) || VideoType.SOCIAL_GROUP.equals(videoType) || VideoType.SOCIAL_POPULAR.equals(videoType);
    }
    
    public static final class Bookmark implements JsonPopulator
    {
        private static final String TAG = "Bookmark";
        private int bookmarkPosition;
        private long lastModified;
        
        public void deepCopy(final Bookmark bookmark) {
            this.bookmarkPosition = bookmark.bookmarkPosition;
            this.lastModified = bookmark.lastModified;
        }
        
        public int getBookmarkPosition() {
            return this.bookmarkPosition;
        }
        
        public long getLastModified() {
            return this.lastModified;
        }
        
        @Override
        public void populate(final JsonElement jsonElement) {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (Log.isLoggable("Bookmark", 2)) {
                Log.v("Bookmark", "Populating with: " + asJsonObject);
            }
            for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                final JsonElement jsonElement2 = entry.getValue();
                final String s = entry.getKey();
                int n = 0;
                Label_0126: {
                    switch (s.hashCode()) {
                        case -1829827457: {
                            if (s.equals("bookmarkPosition")) {
                                n = 0;
                                break Label_0126;
                            }
                            break;
                        }
                        case 1959003007: {
                            if (s.equals("lastModified")) {
                                n = 1;
                                break Label_0126;
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
                        this.bookmarkPosition = jsonElement2.getAsInt();
                        continue;
                    }
                    case 1: {
                        this.lastModified = jsonElement2.getAsLong();
                        continue;
                    }
                }
            }
        }
        
        public void setBookmarkPosition(final int bookmarkPosition) {
            this.bookmarkPosition = bookmarkPosition;
        }
        
        public void setLastModified(final long lastModified) {
            this.lastModified = lastModified;
        }
        
        @Override
        public String toString() {
            return "Bookmark [bookmarkPosition=" + this.bookmarkPosition + ", lastModified=" + this.lastModified + "]";
        }
    }
    
    public static final class BookmarkStill implements JsonPopulator
    {
        private static final String TAG = "BookmarkStill";
        public String stillUrl;
        
        @Override
        public void populate(final JsonElement jsonElement) {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (Log.isLoggable("BookmarkStill", 2)) {
                Log.v("BookmarkStill", "Populating with: " + asJsonObject);
            }
            for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                final JsonElement jsonElement2 = entry.getValue();
                final String s = entry.getKey();
                int n = 0;
                Label_0118: {
                    switch (s.hashCode()) {
                        case 1540041383: {
                            if (s.equals("stillUrl")) {
                                n = 0;
                                break Label_0118;
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
                        this.stillUrl = jsonElement2.getAsString();
                        continue;
                    }
                }
            }
        }
    }
    
    public static class Detail implements JsonPopulator
    {
        private static final String TAG = "Detail";
        public String actors;
        public String baseUrl;
        public String bifUrl;
        public String certification;
        public String directors;
        public int endtime;
        public int episodeCount;
        public String genres;
        public String horzDispUrl;
        public String intrUrl;
        public boolean isAutoPlayEnabled;
        public boolean isHdAvailable;
        public boolean isNextPlayableEpisode;
        public boolean isPinProtected;
        public String mdxHorzUrl;
        public String mdxVertUrl;
        public float predictedRating;
        public String quality;
        public String restUrl;
        public int runtime;
        public int seasonCount;
        public String storyImgDispUrl;
        public String storyImgUrl;
        public String synopsis;
        public String synopsisNarrative;
        public String tvCardUrl;
        public int year;
        
        @Override
        public void populate(final JsonElement jsonElement) {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (Log.isLoggable("Detail", 2)) {
                Log.v("Detail", "Populating with: " + asJsonObject);
            }
            for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                final JsonElement jsonElement2 = entry.getValue();
                final String s = entry.getKey();
                int n = 0;
                Label_0326: {
                    switch (s.hashCode()) {
                        case 3704893: {
                            if (s.equals("year")) {
                                n = 0;
                                break Label_0326;
                            }
                            break;
                        }
                        case 1828656532: {
                            if (s.equals("synopsis")) {
                                n = 1;
                                break Label_0326;
                            }
                            break;
                        }
                        case -496641730: {
                            if (s.equals("synopsisNarrative")) {
                                n = 2;
                                break Label_0326;
                            }
                            break;
                        }
                        case 651215103: {
                            if (s.equals("quality")) {
                                n = 3;
                                break Label_0326;
                            }
                            break;
                        }
                        case -962584985: {
                            if (s.equals("directors")) {
                                n = 4;
                                break Label_0326;
                            }
                            break;
                        }
                        case -1422944994: {
                            if (s.equals("actors")) {
                                n = 5;
                                break Label_0326;
                            }
                            break;
                        }
                        case -1249499312: {
                            if (s.equals("genres")) {
                                n = 6;
                                break Label_0326;
                            }
                            break;
                        }
                        case -644524870: {
                            if (s.equals("certification")) {
                                n = 7;
                                break Label_0326;
                            }
                            break;
                        }
                        case -1217996834: {
                            if (s.equals("horzDispUrl")) {
                                n = 8;
                                break Label_0326;
                            }
                            break;
                        }
                        case 1097494779: {
                            if (s.equals("restUrl")) {
                                n = 9;
                                break Label_0326;
                            }
                            break;
                        }
                        case -1389216784: {
                            if (s.equals("bifUrl")) {
                                n = 10;
                                break Label_0326;
                            }
                            break;
                        }
                        case -332625698: {
                            if (s.equals("baseUrl")) {
                                n = 11;
                                break Label_0326;
                            }
                            break;
                        }
                        case -1794520227: {
                            if (s.equals("tvCardUrl")) {
                                n = 12;
                                break Label_0326;
                            }
                            break;
                        }
                        case 1144479839: {
                            if (s.equals("mdxHorzUrl")) {
                                n = 13;
                                break Label_0326;
                            }
                            break;
                        }
                        case 398159229: {
                            if (s.equals("mdxVertUrl")) {
                                n = 14;
                                break Label_0326;
                            }
                            break;
                        }
                        case -1551264767: {
                            if (s.equals("storyImgUrl")) {
                                n = 15;
                                break Label_0326;
                            }
                            break;
                        }
                        case 1251932671: {
                            if (s.equals("storyImgDispUrl")) {
                                n = 16;
                                break Label_0326;
                            }
                            break;
                        }
                        case 1958422540: {
                            if (s.equals("intrUrl")) {
                                n = 17;
                                break Label_0326;
                            }
                            break;
                        }
                        case 1590765524: {
                            if (s.equals("episodeCount")) {
                                n = 18;
                                break Label_0326;
                            }
                            break;
                        }
                        case -885502996: {
                            if (s.equals("seasonCount")) {
                                n = 19;
                                break Label_0326;
                            }
                            break;
                        }
                        case 1550962648: {
                            if (s.equals("runtime")) {
                                n = 20;
                                break Label_0326;
                            }
                            break;
                        }
                        case -1606289880: {
                            if (s.equals("endtime")) {
                                n = 21;
                                break Label_0326;
                            }
                            break;
                        }
                        case -515828317: {
                            if (s.equals("isHdAvailable")) {
                                n = 22;
                                break Label_0326;
                            }
                            break;
                        }
                        case -1077707340: {
                            if (s.equals("isAutoPlayEnabled")) {
                                n = 23;
                                break Label_0326;
                            }
                            break;
                        }
                        case 1426350736: {
                            if (s.equals("isNextPlayableEpisode")) {
                                n = 24;
                                break Label_0326;
                            }
                            break;
                        }
                        case -1931492381: {
                            if (s.equals("isPinProtected")) {
                                n = 25;
                                break Label_0326;
                            }
                            break;
                        }
                        case -263240971: {
                            if (s.equals("predictedRating")) {
                                n = 26;
                                break Label_0326;
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
                        this.year = jsonElement2.getAsInt();
                        continue;
                    }
                    case 1: {
                        this.synopsis = jsonElement2.getAsString();
                        continue;
                    }
                    case 2: {
                        this.synopsisNarrative = jsonElement2.getAsString();
                        continue;
                    }
                    case 3: {
                        this.quality = jsonElement2.getAsString();
                        continue;
                    }
                    case 4: {
                        this.directors = jsonElement2.getAsString();
                        continue;
                    }
                    case 5: {
                        this.actors = jsonElement2.getAsString();
                        continue;
                    }
                    case 6: {
                        this.genres = jsonElement2.getAsString();
                        continue;
                    }
                    case 7: {
                        this.certification = jsonElement2.getAsString();
                        continue;
                    }
                    case 8: {
                        this.horzDispUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 9: {
                        this.restUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 10: {
                        this.bifUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 11: {
                        this.baseUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 12: {
                        this.tvCardUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 13: {
                        this.mdxHorzUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 14: {
                        this.mdxVertUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 15: {
                        this.storyImgUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 16: {
                        this.storyImgDispUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 17: {
                        this.intrUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 18: {
                        this.episodeCount = jsonElement2.getAsInt();
                        continue;
                    }
                    case 19: {
                        this.seasonCount = jsonElement2.getAsInt();
                        continue;
                    }
                    case 20: {
                        this.runtime = jsonElement2.getAsInt();
                        continue;
                    }
                    case 21: {
                        this.endtime = jsonElement2.getAsInt();
                        continue;
                    }
                    case 22: {
                        this.isHdAvailable = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 23: {
                        this.isAutoPlayEnabled = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 24: {
                        this.isNextPlayableEpisode = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 25: {
                        this.isPinProtected = jsonElement2.getAsBoolean();
                        continue;
                    }
                    case 26: {
                        this.predictedRating = jsonElement2.getAsFloat();
                        continue;
                    }
                }
            }
        }
        
        @Override
        public String toString() {
            return "Detail [year=" + this.year + ", synopsis=" + this.synopsis + ", synopsisNarrative=" + this.synopsisNarrative + ", quality=" + this.quality + ", directors=" + this.directors + ", actors=" + this.actors + ", genres=" + this.genres + ", certification=" + this.certification + ", horzDispUrl=" + this.horzDispUrl + ", restUrl=" + this.restUrl + ", bifUrl=" + this.bifUrl + ", baseUrl=" + this.baseUrl + ", tvCardUrl=" + this.tvCardUrl + ", mdxHorzUrl=" + this.mdxHorzUrl + ", mdxVertUrl=" + this.mdxVertUrl + ", storyImgUrl=" + this.storyImgUrl + ", storyImgDispUrl=" + this.storyImgDispUrl + ", intrUrl=" + this.intrUrl + ", episodeCount=" + this.episodeCount + ", seasonCount=" + this.seasonCount + ", isHdAvailable=" + this.isHdAvailable + ", isAutoPlayEnabled=" + this.isAutoPlayEnabled + ", isNextPlayableEpisode=" + this.isNextPlayableEpisode + ", predictedRating=" + this.predictedRating + ", isPinProtected=" + this.isPinProtected + ", runtime=" + this.runtime + ", endtime=" + this.endtime + "]";
        }
    }
    
    public static final class InQueue implements JsonPopulator
    {
        private static final String TAG = "InQueue";
        public boolean inQueue;
        
        @Override
        public void populate(final JsonElement jsonElement) {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (Log.isLoggable("InQueue", 2)) {
                Log.v("InQueue", "Populating with: " + asJsonObject);
            }
            for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                final JsonElement jsonElement2 = entry.getValue();
                final String s = entry.getKey();
                int n = 0;
                Label_0118: {
                    switch (s.hashCode()) {
                        case 1926204140: {
                            if (s.equals("inQueue")) {
                                n = 0;
                                break Label_0118;
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
                        this.inQueue = jsonElement2.getAsBoolean();
                        continue;
                    }
                }
            }
        }
        
        @Override
        public String toString() {
            return "InQueue [inQueue=" + this.inQueue + "]";
        }
    }
    
    public static final class Rating implements JsonPopulator
    {
        private static final String TAG = "Rating";
        public float userRating;
        
        @Override
        public void populate(final JsonElement jsonElement) {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (Log.isLoggable("Rating", 2)) {
                Log.v("Rating", "Populating with: " + asJsonObject);
            }
            for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                final JsonElement jsonElement2 = entry.getValue();
                final String s = entry.getKey();
                int n = 0;
                Label_0118: {
                    switch (s.hashCode()) {
                        case 1546011976: {
                            if (s.equals("userRating")) {
                                n = 0;
                                break Label_0118;
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
                        this.userRating = jsonElement2.getAsFloat();
                        continue;
                    }
                }
            }
        }
        
        @Override
        public String toString() {
            return "Rating [userRating=" + this.userRating + "]";
        }
    }
    
    public static final class SearchTitle implements JsonPopulator
    {
        private static final String TAG = "SearchTitle";
        public String certification;
        public int releaseYear;
        public String title;
        
        @Override
        public void populate(final JsonElement jsonElement) {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (Log.isLoggable("SearchTitle", 2)) {
                Log.v("SearchTitle", "Populating with: " + asJsonObject);
            }
            for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                final JsonElement jsonElement2 = entry.getValue();
                final String s = entry.getKey();
                int n = 0;
                Label_0134: {
                    switch (s.hashCode()) {
                        case 110371416: {
                            if (s.equals("title")) {
                                n = 0;
                                break Label_0134;
                            }
                            break;
                        }
                        case -644524870: {
                            if (s.equals("certification")) {
                                n = 1;
                                break Label_0134;
                            }
                            break;
                        }
                        case 213502180: {
                            if (s.equals("releaseYear")) {
                                n = 2;
                                break Label_0134;
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
                        this.title = jsonElement2.getAsString();
                        continue;
                    }
                    case 1: {
                        this.certification = jsonElement2.getAsString();
                        continue;
                    }
                    case 2: {
                        this.releaseYear = jsonElement2.getAsInt();
                        continue;
                    }
                }
            }
        }
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    public static final class Summary implements Video, JsonPopulator
    {
        private static final String TAG = "FalkorVideo";
        public String boxartUrl;
        public VideoType enumType;
        public VideoType errorType;
        public String horzDispUrl;
        public String id;
        public boolean isEpisode;
        public String squareUrl;
        public String title;
        public String tvCardUrl;
        public String type;
        public int videoYear;
        
        @Override
        public String getBoxshotURL() {
            return this.boxartUrl;
        }
        
        @Override
        public VideoType getErrorType() {
            return this.errorType;
        }
        
        @Override
        public String getHorzDispUrl() {
            return this.horzDispUrl;
        }
        
        @Override
        public String getId() {
            return this.id;
        }
        
        @Override
        public String getSquareUrl() {
            return this.squareUrl;
        }
        
        @Override
        public String getTitle() {
            return this.title;
        }
        
        @Override
        public String getTvCardUrl() {
            return this.tvCardUrl;
        }
        
        @Override
        public VideoType getType() {
            if (this.enumType == null) {
                this.enumType = VideoType.create(this.type);
            }
            return this.enumType;
        }
        
        public boolean isEpisode() {
            return this.isEpisode;
        }
        
        @Override
        public void populate(final JsonElement jsonElement) {
            final JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (Log.isLoggable("FalkorVideo", 2)) {
                Log.v("FalkorVideo", "Populating with: " + asJsonObject);
            }
            for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                final JsonElement jsonElement2 = entry.getValue();
                final String s = entry.getKey();
                int n = 0;
                Label_0186: {
                    switch (s.hashCode()) {
                        case 3355: {
                            if (s.equals("id")) {
                                n = 0;
                                break Label_0186;
                            }
                            break;
                        }
                        case 1153650071: {
                            if (s.equals("boxartUrl")) {
                                n = 1;
                                break Label_0186;
                            }
                            break;
                        }
                        case 3575610: {
                            if (s.equals("type")) {
                                n = 2;
                                break Label_0186;
                            }
                            break;
                        }
                        case 110371416: {
                            if (s.equals("title")) {
                                n = 3;
                                break Label_0186;
                            }
                            break;
                        }
                        case -2124216975: {
                            if (s.equals("isEpisode")) {
                                n = 4;
                                break Label_0186;
                            }
                            break;
                        }
                        case 329552226: {
                            if (s.equals("errorType")) {
                                n = 5;
                                break Label_0186;
                            }
                            break;
                        }
                        case -1794520227: {
                            if (s.equals("tvCardUrl")) {
                                n = 6;
                                break Label_0186;
                            }
                            break;
                        }
                        case -1217996834: {
                            if (s.equals("horzDispUrl")) {
                                n = 7;
                                break Label_0186;
                            }
                            break;
                        }
                        case 1314358034: {
                            if (s.equals("squareUrl")) {
                                n = 8;
                                break Label_0186;
                            }
                            break;
                        }
                        case 1333091160: {
                            if (s.equals("videoYear")) {
                                n = 9;
                                break Label_0186;
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
                        this.boxartUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 2: {
                        this.type = jsonElement2.getAsString();
                        continue;
                    }
                    case 3: {
                        this.title = jsonElement2.getAsString();
                        continue;
                    }
                    case 4: {
                        this.isEpisode = entry.getValue().getAsBoolean();
                        continue;
                    }
                    case 5: {
                        this.errorType = VideoType.create(jsonElement2.getAsString());
                        continue;
                    }
                    case 6: {
                        this.tvCardUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 7: {
                        this.horzDispUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 8: {
                        this.squareUrl = jsonElement2.getAsString();
                        continue;
                    }
                    case 9: {
                        entry.getValue().getAsInt();
                        continue;
                    }
                }
            }
        }
        
        public void setErrorType(final VideoType errorType) {
            this.errorType = errorType;
        }
        
        @Override
        public String toString() {
            return "Summary [id=" + this.id + ", type=" + this.type + ", title=" + this.title + ", isEpisode=" + this.isEpisode + "]";
        }
    }
}

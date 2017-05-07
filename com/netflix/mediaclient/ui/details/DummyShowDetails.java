// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.IconFontGlyph;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.util.Random;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;

public class DummyShowDetails implements ShowDetails
{
    private float userRating;
    
    public DummyShowDetails() {
        this.userRating = 0.0f;
    }
    
    @Override
    public String getActors() {
        return null;
    }
    
    @Override
    public String getBifUrl() {
        return null;
    }
    
    @Override
    public String getBoxshotURL() {
        return "http://dummyimage.com/150x214/bb0000/884444.png&text=Sample";
    }
    
    @Override
    public String getCatalogIdUrl() {
        return null;
    }
    
    @Override
    public String getCertification() {
        return null;
    }
    
    @Override
    public String getCreators() {
        return "Creators";
    }
    
    @Override
    public String getCurrentEpisodeId() {
        return null;
    }
    
    @Override
    public int getCurrentEpisodeNumber() {
        return -1;
    }
    
    @Override
    public String getCurrentEpisodeTitle() {
        return null;
    }
    
    @Override
    public int getCurrentSeasonNumber() {
        return new Random().nextInt(this.getNumOfSeasons());
    }
    
    @Override
    public VideoType getErrorType() {
        return null;
    }
    
    @Override
    public IconFontGlyph getEvidenceGlyph() {
        return null;
    }
    
    @Override
    public String getEvidenceText() {
        return null;
    }
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        return null;
    }
    
    @Override
    public String getGenres() {
        return null;
    }
    
    @Override
    public String getHighResolutionLandscapeBoxArtUrl() {
        return null;
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        return null;
    }
    
    @Override
    public String getHorzDispUrl() {
        return null;
    }
    
    @Override
    public String getId() {
        return "70177057";
    }
    
    @Override
    public int getNumOfEpisodes() {
        return -1;
    }
    
    @Override
    public int getNumOfSeasons() {
        return 100;
    }
    
    @Override
    public Playable getPlayable() {
        return new DummyPlayable();
    }
    
    @Override
    public float getPredictedRating() {
        return 0.0f;
    }
    
    @Override
    public String getQuality() {
        return null;
    }
    
    @Override
    public List<Video> getSimilars() {
        return null;
    }
    
    @Override
    public int getSimilarsListPos() {
        return 0;
    }
    
    @Override
    public String getSimilarsRequestId() {
        return null;
    }
    
    @Override
    public int getSimilarsTrackId() {
        return 0;
    }
    
    @Override
    public String getSquareUrl() {
        return null;
    }
    
    @Override
    public String getStoryDispUrl() {
        return null;
    }
    
    @Override
    public String getStoryUrl() {
        return null;
    }
    
    @Override
    public String getSynopsis() {
        return "Max (Max Records) is a young boy who feels misunderstood and wants to have fun all the time. He makes an igloo out of snow, but his sister's friends gang up on him and smash it. After making a scene in front of his mother's boyfriend, Max bites his mother and runs away. He keeps running until he stumbles upon a small boat; he climbs aboard and sets sail. After a few days at sea, he reaches the dangerous, rocky shore of a strange island at night. He leaves the boat on shore and walks toward voices he hears in the woods. Max eavesdrops on a few creatures arguing. One of them, Carol (voice: James Gandolfini), is destroying the huts and screaming. The other wild things are yelling at him, telling him to stop. Max runs out of the trees and joins Carol in destroying the huts. The wild things are angry about this and want to eat him. But Max tells them that he was the king where he came from, and he has \"special powers,\" so the wild things can't eat him. Carol crowns him king of the wild things and the island. Max's first order of business is to \"let the wild rumpus start!\" The wild things and Max dance and run around the forest destroying things. The wild things introduce themselves: Ira (voice: Forest Whitaker), Carol, Douglas (voice: Chris Cooper), the bull, Judith (voice: Catherine O'Hara), and Alexander (voice: Paul Dano). K.W. (voice: Lauren Ambrose) is missing; she's gone to hang out with other friends, apparently after a disagreement. Carol shows Max his \"Kingdom\" and shows him his secret hideaway, where has built a miniature of the island. Carol says \"There should be a place where only the things you want to happen, happen.\" Max thinks that with effort from all the wild things, they can build a place like that. Over the next few days, Max and the wild things build a large fortress of rocks and sticks. Tension grows between Max and the wild things when Judith starts to think Max isn't a good king. They have a dirtball fight and many of the wild things get hurt. K.W. and Carol argue and K.W. takes Max to see her friends Terry and Bob, who turn out to be owls. They go back to the fortress and the wild things (minus Carol) greet them with open arms. Carol throws a fit and is angry that they are letting two outsiders into the group. K.W. runs away with Terry and Bob. Max and the wild things are sad, sitting in the rain. Judith demands to see Max's \"special powers\" and wants K.W. to come back. The wild things discover Max isn't a king and that he has no powers. Carol is angry with Max, telling him he didn't keep everyone safe, and that he is an awful king. Max runs away with Carol pursuing him. K.W. hides Max in her stomach until Carol leaves. Max then decides it's time to go home. Things seem to be better when he and the wild things all go to the beach, where Max's boat is. Carol is in his secret hideaway crying when he realizes he is being stupid and sees a heart with his initial that Max made for him. (Carol made a similar one for Max on the fort earlier.) Carol runs toward the beach. Max gets into his boat and says goodbye to all the wild things. Carol finally arrives and is unable to speak, so he howls. The other wild things join in and Max howls back. After a brief boat trip he runs back home and his mother greets him with open arms, and feeds him.";
    }
    
    @Override
    public String getTitle() {
        return "GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB";
    }
    
    @Override
    public String getTitleImgUrl() {
        return null;
    }
    
    @Override
    public String getTvCardUrl() {
        return null;
    }
    
    @Override
    public VideoType getType() {
        return VideoType.SHOW;
    }
    
    @Override
    public float getUserRating() {
        return this.userRating;
    }
    
    @Override
    public int getYear() {
        return 1909;
    }
    
    @Override
    public boolean isInQueue() {
        return false;
    }
    
    @Override
    public boolean isVideoHd() {
        return true;
    }
    
    @Override
    public void setUserRating(final float userRating) {
        this.userRating = userRating;
    }
}

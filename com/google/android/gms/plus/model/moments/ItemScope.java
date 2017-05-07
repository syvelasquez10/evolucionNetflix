// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.moments;

import java.util.List;
import com.google.android.gms.common.data.Freezable;

public interface ItemScope extends Freezable<ItemScope>
{
    ItemScope getAbout();
    
    List<String> getAdditionalName();
    
    ItemScope getAddress();
    
    String getAddressCountry();
    
    String getAddressLocality();
    
    String getAddressRegion();
    
    List<ItemScope> getAssociated_media();
    
    int getAttendeeCount();
    
    List<ItemScope> getAttendees();
    
    ItemScope getAudio();
    
    List<ItemScope> getAuthor();
    
    String getBestRating();
    
    String getBirthDate();
    
    ItemScope getByArtist();
    
    String getCaption();
    
    String getContentSize();
    
    String getContentUrl();
    
    List<ItemScope> getContributor();
    
    String getDateCreated();
    
    String getDateModified();
    
    String getDatePublished();
    
    String getDescription();
    
    String getDuration();
    
    String getEmbedUrl();
    
    String getEndDate();
    
    String getFamilyName();
    
    String getGender();
    
    ItemScope getGeo();
    
    String getGivenName();
    
    String getHeight();
    
    String getId();
    
    String getImage();
    
    ItemScope getInAlbum();
    
    double getLatitude();
    
    ItemScope getLocation();
    
    double getLongitude();
    
    String getName();
    
    ItemScope getPartOfTVSeries();
    
    List<ItemScope> getPerformers();
    
    String getPlayerType();
    
    String getPostOfficeBoxNumber();
    
    String getPostalCode();
    
    String getRatingValue();
    
    ItemScope getReviewRating();
    
    String getStartDate();
    
    String getStreetAddress();
    
    String getText();
    
    ItemScope getThumbnail();
    
    String getThumbnailUrl();
    
    String getTickerSymbol();
    
    String getType();
    
    String getUrl();
    
    String getWidth();
    
    String getWorstRating();
    
    boolean hasAbout();
    
    boolean hasAdditionalName();
    
    boolean hasAddress();
    
    boolean hasAddressCountry();
    
    boolean hasAddressLocality();
    
    boolean hasAddressRegion();
    
    boolean hasAssociated_media();
    
    boolean hasAttendeeCount();
    
    boolean hasAttendees();
    
    boolean hasAudio();
    
    boolean hasAuthor();
    
    boolean hasBestRating();
    
    boolean hasBirthDate();
    
    boolean hasByArtist();
    
    boolean hasCaption();
    
    boolean hasContentSize();
    
    boolean hasContentUrl();
    
    boolean hasContributor();
    
    boolean hasDateCreated();
    
    boolean hasDateModified();
    
    boolean hasDatePublished();
    
    boolean hasDescription();
    
    boolean hasDuration();
    
    boolean hasEmbedUrl();
    
    boolean hasEndDate();
    
    boolean hasFamilyName();
    
    boolean hasGender();
    
    boolean hasGeo();
    
    boolean hasGivenName();
    
    boolean hasHeight();
    
    boolean hasId();
    
    boolean hasImage();
    
    boolean hasInAlbum();
    
    boolean hasLatitude();
    
    boolean hasLocation();
    
    boolean hasLongitude();
    
    boolean hasName();
    
    boolean hasPartOfTVSeries();
    
    boolean hasPerformers();
    
    boolean hasPlayerType();
    
    boolean hasPostOfficeBoxNumber();
    
    boolean hasPostalCode();
    
    boolean hasRatingValue();
    
    boolean hasReviewRating();
    
    boolean hasStartDate();
    
    boolean hasStreetAddress();
    
    boolean hasText();
    
    boolean hasThumbnail();
    
    boolean hasThumbnailUrl();
    
    boolean hasTickerSymbol();
    
    boolean hasType();
    
    boolean hasUrl();
    
    boolean hasWidth();
    
    boolean hasWorstRating();
}

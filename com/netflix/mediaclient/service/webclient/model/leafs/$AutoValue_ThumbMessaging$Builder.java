// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

final class $AutoValue_ThumbMessaging$Builder extends ThumbMessaging$Builder
{
    private Boolean shouldShowFirstThumbsRatingMessage;
    private Boolean shouldShowOneTimeProfileThumbsMessage;
    
    $AutoValue_ThumbMessaging$Builder() {
    }
    
    $AutoValue_ThumbMessaging$Builder(final ThumbMessaging thumbMessaging) {
        this.shouldShowOneTimeProfileThumbsMessage = thumbMessaging.shouldShowOneTimeProfileThumbsMessage();
        this.shouldShowFirstThumbsRatingMessage = thumbMessaging.shouldShowFirstThumbsRatingMessage();
    }
    
    @Override
    public ThumbMessaging build() {
        String string = "";
        if (this.shouldShowOneTimeProfileThumbsMessage == null) {
            string = "" + " shouldShowOneTimeProfileThumbsMessage";
        }
        String string2 = string;
        if (this.shouldShowFirstThumbsRatingMessage == null) {
            string2 = string + " shouldShowFirstThumbsRatingMessage";
        }
        if (!string2.isEmpty()) {
            throw new IllegalStateException("Missing required properties:" + string2);
        }
        return new AutoValue_ThumbMessaging(this.shouldShowOneTimeProfileThumbsMessage, this.shouldShowFirstThumbsRatingMessage);
    }
    
    @Override
    public ThumbMessaging$Builder setShouldShowFirstThumbsRatingMessage(final boolean b) {
        this.shouldShowFirstThumbsRatingMessage = b;
        return this;
    }
    
    @Override
    public ThumbMessaging$Builder setShouldShowOneTimeProfileThumbsMessage(final boolean b) {
        this.shouldShowOneTimeProfileThumbsMessage = b;
        return this;
    }
}

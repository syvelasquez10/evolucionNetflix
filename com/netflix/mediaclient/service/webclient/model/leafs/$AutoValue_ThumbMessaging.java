// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

abstract class $AutoValue_ThumbMessaging extends ThumbMessaging
{
    private final boolean shouldShowFirstThumbsRatingMessage;
    private final boolean shouldShowOneTimeProfileThumbsMessage;
    
    $AutoValue_ThumbMessaging(final boolean shouldShowOneTimeProfileThumbsMessage, final boolean shouldShowFirstThumbsRatingMessage) {
        this.shouldShowOneTimeProfileThumbsMessage = shouldShowOneTimeProfileThumbsMessage;
        this.shouldShowFirstThumbsRatingMessage = shouldShowFirstThumbsRatingMessage;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ThumbMessaging)) {
                return false;
            }
            final ThumbMessaging thumbMessaging = (ThumbMessaging)o;
            if (this.shouldShowOneTimeProfileThumbsMessage != thumbMessaging.shouldShowOneTimeProfileThumbsMessage() || this.shouldShowFirstThumbsRatingMessage != thumbMessaging.shouldShowFirstThumbsRatingMessage()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int n = 1231;
        int n2;
        if (this.shouldShowOneTimeProfileThumbsMessage) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        if (!this.shouldShowFirstThumbsRatingMessage) {
            n = 1237;
        }
        return (n2 ^ 0xF4243) * 1000003 ^ n;
    }
    
    @Override
    public boolean shouldShowFirstThumbsRatingMessage() {
        return this.shouldShowFirstThumbsRatingMessage;
    }
    
    @Override
    public boolean shouldShowOneTimeProfileThumbsMessage() {
        return this.shouldShowOneTimeProfileThumbsMessage;
    }
    
    @Override
    public String toString() {
        return "ThumbMessaging{shouldShowOneTimeProfileThumbsMessage=" + this.shouldShowOneTimeProfileThumbsMessage + ", shouldShowFirstThumbsRatingMessage=" + this.shouldShowFirstThumbsRatingMessage + "}";
    }
}

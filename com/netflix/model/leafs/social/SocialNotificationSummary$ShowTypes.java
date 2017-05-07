// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;

public enum SocialNotificationSummary$ShowTypes
{
    BOOK("Book", 2131623945), 
    CHAPTER("Chapter", 2131623948), 
    COLLECTION("Collection", 2131623942), 
    PART("Part", 2131623946), 
    SEASON("Season", 2131623940), 
    SERIES("Series", 2131623947), 
    SET("Set", 2131623944), 
    SPECIAL("Special", 2131623943), 
    VOLUME("Volume", 2131623941);
    
    private int resId;
    private String typeValue;
    
    private SocialNotificationSummary$ShowTypes(final String typeValue, final int resId) {
        this.typeValue = typeValue;
        this.resId = resId;
    }
    
    public static SocialNotificationSummary$ShowTypes getType(final String s) {
        final SocialNotificationSummary$ShowTypes season = SocialNotificationSummary$ShowTypes.SEASON;
        final SocialNotificationSummary$ShowTypes[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final SocialNotificationSummary$ShowTypes socialNotificationSummary$ShowTypes = values[i];
            if (socialNotificationSummary$ShowTypes.typeValue.equals(s)) {
                return socialNotificationSummary$ShowTypes;
            }
        }
        ErrorLoggingManager.logHandledException(new Throwable("SPY-7553 - Got unrecognized type: " + s));
        return season;
    }
    
    public int getResourceStringId() {
        return this.resId;
    }
}

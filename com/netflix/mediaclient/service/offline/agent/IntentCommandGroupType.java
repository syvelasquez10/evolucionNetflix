// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import android.content.Intent;

enum IntentCommandGroupType
{
    DownloadNotification(1);
    
    private static final String OFFLINE_INTENT_GROUP_TYPE = "of_intent_group_type";
    
    Unknown(-1);
    
    private final int mValue;
    
    private IntentCommandGroupType(final int mValue) {
        this.mValue = mValue;
    }
    
    private static IntentCommandGroupType getByValue(final int n) {
        final IntentCommandGroupType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final IntentCommandGroupType intentCommandGroupType = values[i];
            if (intentCommandGroupType.getIntValue() == n) {
                return intentCommandGroupType;
            }
        }
        return IntentCommandGroupType.Unknown;
    }
    
    public static IntentCommandGroupType getGroupType(final Intent intent) {
        return getByValue(intent.getIntExtra("of_intent_group_type", IntentCommandGroupType.Unknown.mValue));
    }
    
    private int getIntValue() {
        return this.mValue;
    }
    
    public static void setIntentGroupType(final Intent intent, final IntentCommandGroupType intentCommandGroupType) {
        intent.putExtra("of_intent_group_type", intentCommandGroupType.getIntValue());
    }
}

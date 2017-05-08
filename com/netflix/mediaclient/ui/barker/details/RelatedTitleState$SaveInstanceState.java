// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import android.os.Parcelable;
import java.util.Stack;
import android.os.Bundle;

public class RelatedTitleState$SaveInstanceState
{
    public static final String RELATED_TITLES_INSTANCE_STATE = "RELATED_TITLES_INSTANCE_STATE";
    
    public static void invoke(final Bundle bundle, final Stack<RelatedTitleState> stack) {
        bundle.putParcelableArray("RELATED_TITLES_INSTANCE_STATE", (Parcelable[])stack.toArray((Object[])new Parcelable[stack.size()]));
    }
}

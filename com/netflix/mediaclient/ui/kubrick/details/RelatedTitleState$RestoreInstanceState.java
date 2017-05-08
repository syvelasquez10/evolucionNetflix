// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.os.Parcelable;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.LinearLayoutManager;
import java.util.Stack;
import android.os.Bundle;

public class RelatedTitleState$RestoreInstanceState
{
    public static final String RELATED_TITLES_INSTANCE_STATE = "RELATED_TITLES_INSTANCE_STATE";
    
    public static void invoke(final Bundle bundle, final Stack<RelatedTitleState> stack) {
        bundle.setClassLoader(AndroidUtils.getClassLoader(LinearLayoutManager.class));
        final Parcelable[] parcelableArray = bundle.getParcelableArray("RELATED_TITLES_INSTANCE_STATE");
        if (parcelableArray != null && parcelableArray.length > 0) {
            stack.empty();
            for (int i = 0; i < parcelableArray.length; ++i) {
                stack.push((RelatedTitleState)parcelableArray[i]);
            }
        }
    }
}

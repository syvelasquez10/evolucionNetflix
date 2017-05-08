// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.app.Fragment;
import android.content.Context;

public class MiniPlayerFactory
{
    public static Fragment createMiniPlayer(final Context context) {
        if (BrowseExperience.shouldShowMemento(context)) {
            return new MiniPlayerControlsFrag();
        }
        return new MdxMiniPlayerFrag();
    }
}

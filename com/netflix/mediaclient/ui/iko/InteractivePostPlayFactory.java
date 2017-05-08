// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

import com.netflix.mediaclient.ui.iko.kong.postplay.KongInteractivePostPlayManager;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.iko.model.InteractivePostplayModel;
import com.netflix.mediaclient.ui.player.PostPlay;

public class InteractivePostPlayFactory
{
    public static final String KONG = "KONG_POST_PLAY";
    
    public static InteractivePostPlayManager getManager(final String s, final PostPlay postPlay, final InteractivePostplayModel interactivePostplayModel) {
        if (!StringUtils.isEmpty(s) && "KONG_POST_PLAY".equalsIgnoreCase(s) && interactivePostplayModel instanceof KongInteractivePostPlayModel) {
            return (InteractivePostPlayManager)new KongInteractivePostPlayManager(postPlay, (KongInteractivePostPlayModel)interactivePostplayModel);
        }
        return null;
    }
}

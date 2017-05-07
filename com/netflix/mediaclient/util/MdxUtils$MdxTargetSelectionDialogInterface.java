// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import com.netflix.mediaclient.ui.mdx.RemotePlayer;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlayContext;

public interface MdxUtils$MdxTargetSelectionDialogInterface
{
    long getCurrentPositionMs();
    
    PlayContext getPlayContext();
    
    Playable getPlayable();
    
    RemotePlayer getPlayer();
    
    MdxTargetSelection getTargetSelection();
    
    boolean isPlayingLocally();
    
    boolean isPlayingRemotely();
    
    void notifyPlayingBackLocal();
    
    void notifyPlayingBackRemote();
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.client.model.DataContext;

public interface UIViewLogging
{
    void endCommandSession();
    
    void startCommandSession(final UIViewCommandName p0, final IClientLogging.ModalView p1, final DataContext p2);
    
    public enum UIViewCommandName
    {
        actionBarKidsEntry, 
        actionBarKidsExit, 
        genreKidsEntry, 
        moreButton, 
        slidingMenuClosed, 
        slidingMenuKidsEntry, 
        slidingMenuKidsExit, 
        slidingMenuOpened;
    }
}

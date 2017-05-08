// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

import java.util.Map;
import io.realm.Realm;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.ui.offline.ActivityPageOfflineAgentListener$SnackbarMessage;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Context;
import com.netflix.mediaclient.util.ReadOnlyList;

public interface OfflinePlayableUiList extends ReadOnlyList<OfflineAdapterData>
{
    CharSequence getColoredStatusString(final Context p0, final int p1, final String p2, final VideoType p3);
    
    long getCurrentSpace(final int p0);
    
    int getInProgressCount();
    
    OfflinePlayableViewData getOfflinePlayableViewData(final String p0);
    
    Collection<OfflinePlayableViewData> getOfflinePlayableViewData();
    
    int getPercentage(final int p0);
    
    ActivityPageOfflineAgentListener$SnackbarMessage getSnackbarStatus(final Context p0, final OfflineAgentInterface p1);
    
    int getTitleCount();
    
    int getTitleCount(final UserProfile p0);
    
    int numberOfIncompleteItems();
    
    int numberOfItemsToDownload();
    
    void regenerate(final Realm p0, final Map<String, OfflinePlayableViewData> p1, final boolean p2);
}

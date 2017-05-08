// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.android.app.Status;
import java.util.Iterator;
import com.netflix.falkor.PQL;
import java.util.ArrayList;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import java.util.List;

public class MarkNotificationAsReadTask extends CmpTask
{
    private final List<IrisNotificationSummary> notifications;
    
    public MarkNotificationAsReadTask(final CachedModelProxy cachedModelProxy, final IrisNotificationSummary irisNotificationSummary, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        if (irisNotificationSummary == null) {
            throw new RuntimeException("MarkNotificationAsReadTask got null notification");
        }
        (this.notifications = new ArrayList<IrisNotificationSummary>()).add(irisNotificationSummary);
    }
    
    public MarkNotificationAsReadTask(final CachedModelProxy cachedModelProxy, final List<IrisNotificationSummary> notifications, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        if (notifications == null || notifications.size() == 0) {
            throw new RuntimeException("MarkNotificationAsReadTask got invalid notifications list: " + notifications);
        }
        this.notifications = notifications;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        final Iterator<IrisNotificationSummary> iterator = this.notifications.iterator();
        while (iterator.hasNext()) {
            list2.add(iterator.next().getId());
        }
        list.add(PQL.create("notifications", list2, "markAsRead"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onIrisNotificationsMarkedAsRead(status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onIrisNotificationsMarkedAsRead(new NetflixStatus(StatusCode.OK));
    }
    
    @Override
    protected boolean shouldUseCallMethod() {
        return true;
    }
}

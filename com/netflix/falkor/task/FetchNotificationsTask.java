// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.social.IrisNotificationsListImpl;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.android.app.Status;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.branches.FalkorIrisNotification;
import java.util.List;
import com.netflix.falkor.PQL;

public class FetchNotificationsTask extends CmpTask
{
    private static final String IN_QUEUE_FIELD = "inQueue";
    private static final String NOTIFICATIONS_LIST_FIELD = "notificationsList";
    private static final String NOTIFICATION_VIDEO_FIELD = "notificationVideo";
    private static final String SUMMARY_FIELD = "summary";
    private static final String TV_CARD_IMAGE_URL = "tvCardArt";
    private final int fromIndex;
    private PQL notificationsListSummaryPql;
    private final PQL notificationsSummaryPql;
    private PQL notificationsVideoSummaryPql;
    private List<FalkorIrisNotification> oldNotificationsList;
    private final boolean skipCache;
    private final int toIndex;
    
    public FetchNotificationsTask(final CachedModelProxy cachedModelProxy, final int fromIndex, final int toIndex, final boolean skipCache, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.skipCache = skipCache;
        this.notificationsSummaryPql = PQL.create("notificationsList", PQL.range(fromIndex, toIndex), "summary");
        if (skipCache) {
            this.oldNotificationsList = this.modelProxy.getItemsAsList(this.notificationsSummaryPql);
        }
    }
    
    private void clearFalkorCacheOfExistingNotifications() {
        final List itemsAsList = this.modelProxy.getItemsAsList(PQL.create("notificationsList", PQL.range(this.toIndex + 1, this.toIndex + (this.toIndex - this.fromIndex) * 10), "summary"));
        if (itemsAsList != null) {
            for (int size = itemsAsList.size(), i = this.toIndex + 1; i < this.toIndex + 1 + size; ++i) {
                this.modelProxy.invalidate(PQL.create("notificationsList", i));
            }
        }
    }
    
    private boolean notificationsListValid(final List<FalkorIrisNotification> list) {
        final boolean b = false;
        boolean b2;
        if (this.oldNotificationsList == null) {
            b2 = true;
        }
        else {
            b2 = b;
            if (list != null) {
                if (this.oldNotificationsList.size() != list.size()) {
                    return true;
                }
                final Iterator<FalkorIrisNotification> iterator = this.oldNotificationsList.iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    b2 = b;
                    if (!StringUtils.safeEquals(iterator.next().summary.getId(), list.get(n).summary.getId())) {
                        return b2;
                    }
                    ++n;
                }
                return true;
            }
        }
        return b2;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        this.notificationsVideoSummaryPql = PQL.create("notificationsList", PQL.range(this.fromIndex, this.toIndex), "notificationVideo", PQL.array("summary", "inQueue", "tvCardArt"));
        this.notificationsListSummaryPql = PQL.create("notificationsList", "summary");
        list.add(this.notificationsSummaryPql);
        list.add(this.notificationsVideoSummaryPql);
        list.add(this.notificationsListSummaryPql);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onNotificationsListFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final List<FalkorIrisNotification> itemsAsList = this.modelProxy.getItemsAsList(this.notificationsSummaryPql);
        if (this.skipCache && !this.notificationsListValid(itemsAsList)) {
            this.clearFalkorCacheOfExistingNotifications();
        }
        browseAgentCallback.onNotificationsListFetched((IrisNotificationsList)new IrisNotificationsListImpl((IrisNotificationsListSummary)this.modelProxy.getValue(this.notificationsListSummaryPql), (List)itemsAsList, (ModelProxy)this.modelProxy), CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldCollapseMissingPql() {
        return true;
    }
    
    @Override
    protected boolean shouldSkipCache() {
        return this.skipCache;
    }
}

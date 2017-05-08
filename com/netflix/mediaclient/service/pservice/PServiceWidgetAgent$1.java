// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.service.pservice.logging.PServiceLogging;
import com.netflix.mediaclient.service.pservice.logging.PServiceWidgetLogEvent$WidgetAction;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.LinkedList;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.pservice.logging.PreAppWidgetLogActionData$PreAppWidgetActionName;
import android.annotation.TargetApi;
import android.os.Bundle;
import com.netflix.mediaclient.util.DeviceUtils;
import android.graphics.Matrix;
import com.netflix.mediaclient.util.TimeUtils;
import android.graphics.BitmapFactory;
import java.io.File;
import android.content.ComponentName;
import android.appwidget.AppWidgetManager;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.app.PendingIntent;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.net.Uri;
import android.content.Context;
import android.graphics.Bitmap;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.Log;
import android.widget.RemoteViews;
import java.util.List;

class PServiceWidgetAgent$1 implements Runnable
{
    final /* synthetic */ PServiceWidgetAgent this$0;
    final /* synthetic */ String val$id;
    final /* synthetic */ List val$imageTypeList;
    final /* synthetic */ List val$imageUrlsList;
    final /* synthetic */ RemoteViews val$remoteView;
    final /* synthetic */ PDiskData$ListType val$type;
    final /* synthetic */ int val$widgetId;
    
    PServiceWidgetAgent$1(final PServiceWidgetAgent this$0, final List val$imageUrlsList, final int val$widgetId, final List val$imageTypeList, final RemoteViews val$remoteView, final String val$id, final PDiskData$ListType val$type) {
        this.this$0 = this$0;
        this.val$imageUrlsList = val$imageUrlsList;
        this.val$widgetId = val$widgetId;
        this.val$imageTypeList = val$imageTypeList;
        this.val$remoteView = val$remoteView;
        this.val$id = val$id;
        this.val$type = val$type;
    }
    
    @Override
    public void run() {
        final Iterator<String> iterator = this.val$imageUrlsList.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final String s = iterator.next();
            Log.d("nf_preapp_widgetagent", String.format("decoding (%d), bitmap (%d), %s, %s, ", this.val$widgetId, n, this.val$imageTypeList.get(n), s));
            ThreadUtils.assertNotOnMain();
            this.val$remoteView.setImageViewBitmap(this.this$0.getImageResourceId(n + 1), this.this$0.getBitmapFromDisk(s, this.val$imageTypeList.get(n)));
            ++n;
        }
        this.this$0.updateWidget(this.this$0.getContext(), this.val$remoteView, this.val$widgetId, this.val$id, this.val$type);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.service.pservice.logging.PServiceLogging;
import com.netflix.mediaclient.service.pservice.logging.PServiceWidgetLogEvent$WidgetAction;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.pservice.logging.PreAppWidgetLogActionData$PreAppWidgetActionName;
import android.annotation.TargetApi;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;
import android.graphics.BitmapFactory;
import java.io.File;
import android.content.ComponentName;
import android.appwidget.AppWidgetManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.RemoteViews;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.net.Uri;

public class PServiceWidgetAgent extends PServiceAgent implements PServiceAgent$PServiceWidgetAgentInterface
{
    public static final String EXTRA_SOURCE = "FROM_PREAPP_WIDGET";
    public static final String FILE_PREFIX = "file://";
    public static final String NFLX_WIDGET = "NetflixWidget";
    private static final String PREAPP_NFLX_BASE = "nflx://www.netflix.com/Browse?q=source%3DNetflixWidget%26action%3D";
    private static final String PREAPP_NFLX_EPISODE_URL = "%26episodeid%3Dhttp%3A%2F%2Fapi-global.netflix.com%2Fcatalog%2Ftitles%2Fprograms%2F";
    private static final String PREAPP_NFLX_MOVIE_URL = "%26movieid%3Dhttp%3A%2F%2Fapi-global.netflix.com%2Fcatalog%2Ftitles%2Fmovies%2F";
    private static final String PREAPP_NFLX_SHOW_URL = "%26movieid%3Dhttp%3A%2F%2Fapi-global.netflix.com%2Fcatalog%2Ftitles%2Fseries%2F";
    private static final String TAG = "nf_preapp_fetchagent";
    
    private Uri buildNflxUri(final String s, final PVideo pVideo) {
        if ("home".equals(s)) {
            return Uri.parse(this.getNflxBaseReq(s));
        }
        final StringBuilder sb = new StringBuilder(this.getNflxBaseReq(s));
        if (VideoType.SHOW.equals(pVideo.videoType)) {
            sb.append("%26movieid%3Dhttp%3A%2F%2Fapi-global.netflix.com%2Fcatalog%2Ftitles%2Fseries%2F").append(pVideo.id);
            if (StringUtils.isNotEmpty(pVideo.playableId)) {
                sb.append("%26episodeid%3Dhttp%3A%2F%2Fapi-global.netflix.com%2Fcatalog%2Ftitles%2Fprograms%2F").append(pVideo.playableId);
            }
        }
        else if (VideoType.MOVIE.equals(pVideo.videoType)) {
            sb.append("%26movieid%3Dhttp%3A%2F%2Fapi-global.netflix.com%2Fcatalog%2Ftitles%2Fmovies%2F").append(pVideo.id);
        }
        return Uri.parse(sb.toString());
    }
    
    private RemoteViews buildWidgetNonMemberRemoteView(final Context context, final int n) {
        final boolean widgetOneCellHigh = this.isWidgetOneCellHigh(context, n);
        final String packageName = context.getPackageName();
        int n2;
        if (widgetOneCellHigh) {
            n2 = 2130903172;
        }
        else {
            n2 = 2130903171;
        }
        final RemoteViews remoteViews = new RemoteViews(packageName, n2);
        int n3;
        if (DeviceUtils.isTabletByContext(context)) {
            n3 = 2130837871;
        }
        else {
            n3 = 2130837868;
        }
        remoteViews.setImageViewResource(2131427755, n3);
        remoteViews.setViewVisibility(2131427757, 8);
        remoteViews.setViewVisibility(2131427763, 8);
        remoteViews.setViewVisibility(2131427761, 8);
        remoteViews.setViewVisibility(2131427762, 8);
        remoteViews.setOnClickPendingIntent(2131427755, this.getWidgetHomeIntent(n));
        remoteViews.setOnClickPendingIntent(2131427763, this.getWidgetHomeIntent(n));
        remoteViews.setOnClickPendingIntent(2131427758, this.getWidgetHomeIntent(n));
        return remoteViews;
    }
    
    private RemoteViews buildWidgetRemoteView(final Context context, final PVideo pVideo, final PDiskData$ListName pDiskData$ListName, final PDiskData pDiskData, final int n) {
        if (pVideo == null || StringUtils.isEmpty(pVideo.storyImgDispUrl)) {
            Log.w("nf_preapp_fetchagent", "pVideo is not valid, ignore newRemoteView");
            return null;
        }
        final String s = pDiskData.urlMap.get(pVideo.storyImgDispUrl);
        if (StringUtils.isEmpty(s)) {
            Log.w("nf_preapp_fetchagent", "resource not on disk, ignore widet update");
            return null;
        }
        final Bitmap bitmap = this.getBitmap(s);
        if (bitmap == null) {
            Log.w("nf_preapp_fetchagent", "bitmap does not exist");
            return null;
        }
        final boolean widgetOneCellHigh = this.isWidgetOneCellHigh(context, n);
        final String packageName = context.getPackageName();
        int n2;
        if (widgetOneCellHigh) {
            n2 = 2130903172;
        }
        else {
            n2 = 2130903171;
        }
        final RemoteViews remoteViews = new RemoteViews(packageName, n2);
        remoteViews.setImageViewBitmap(2131427755, bitmap);
        if (pVideo.isPlayable && PDiskData$ListName.CW.equals(pDiskData$ListName)) {
            int n3;
            if (pVideo.playableRuntime > 0) {
                n3 = pVideo.plyableBookmarkPos * 100 / pVideo.playableRuntime;
            }
            else {
                n3 = 0;
            }
            remoteViews.setProgressBar(2131427762, 100, n3, false);
            Log.d("nf_preapp_fetchagent", String.format(" progressValue=%d", n3));
            remoteViews.setViewVisibility(2131427762, 0);
        }
        else {
            remoteViews.setViewVisibility(2131427762, 8);
        }
        remoteViews.setTextViewText(2131427761, (CharSequence)this.getVideoTitle(context, pVideo));
        int n4;
        if (pVideo.isPlayable) {
            n4 = 0;
        }
        else {
            n4 = 8;
        }
        remoteViews.setViewVisibility(2131427757, n4);
        remoteViews.setViewVisibility(2131427763, 0);
        remoteViews.setViewVisibility(2131427761, 0);
        remoteViews.setOnClickPendingIntent(2131427757, this.getWidgetDetailsOrPlayIntent(pVideo, pDiskData$ListName, n));
        remoteViews.setOnClickPendingIntent(2131427755, this.getWidgetDetailsOrPlayIntent(pVideo, pDiskData$ListName, n));
        remoteViews.setOnClickPendingIntent(2131427763, this.getWidgetRefreshIntent(pVideo, pDiskData$ListName, n));
        remoteViews.setOnClickPendingIntent(2131427758, this.getWidgetHomeIntent(n));
        return remoteViews;
    }
    
    private PendingIntent createWidgetButtonIntent(final Intent intent, final PVideo pVideo, final PDiskData$ListName pDiskData$ListName, final int n) {
        intent.setClass(this.getContext(), (Class)PService.class).addCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_WIDGET").putExtra("widgetId", n);
        if (pVideo != null && StringUtils.isNotEmpty(pVideo.id)) {
            intent.putExtra("videoId", pVideo.id);
        }
        if (pDiskData$ListName != null && !PDiskData$ListName.UNKNOWN.equals(pDiskData$ListName)) {
            intent.putExtra("listName", pDiskData$ListName.toString());
        }
        return PendingIntent.getService(this.getContext(), 0, intent, 134217728);
    }
    
    private int[] getAppWidgetIds(final Context context) {
        return AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetIds(new ComponentName(context.getApplicationContext(), (Class)PServiceWidgetProvider.class));
    }
    
    private Bitmap getBitmap(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        String substring = s;
        if (s.contains("file://")) {
            substring = s.substring("file://".length() - 1);
        }
        final File file = new File(substring);
        if (!file.exists()) {
            Log.w("nf_preapp_fetchagent", String.format("%s does not exist", substring));
            return null;
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }
    
    private PVideo getFirstVideo(final List<PVideo> list) {
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    private List<PVideo> getListByName(final PDiskData$ListName pDiskData$ListName, final PDiskData pDiskData) {
        if (pDiskData == null) {
            Log.w("nf_preapp_fetchagent", "diskData is null - ignoring request");
            return null;
        }
        switch (PServiceWidgetAgent$1.$SwitchMap$com$netflix$mediaclient$service$pservice$PDiskData$ListName[pDiskData$ListName.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return pDiskData.billboardList;
            }
            case 2: {
                return pDiskData.cwList;
            }
            case 3: {
                return pDiskData.iqList;
            }
            case 4: {
                return pDiskData.standardFirstList;
            }
            case 5: {
                return pDiskData.standardSecondList;
            }
        }
    }
    
    private PDiskData$ListName getListName(final Intent intent) {
        if (intent.getExtras() != null) {
            final String string = intent.getExtras().getString("listName");
            if (StringUtils.isNotEmpty(string)) {
                return PDiskData$ListName.valueOf(string);
            }
        }
        return PDiskData$ListName.UNKNOWN;
    }
    
    private PDiskData$ListName getListNameToUse(final Intent intent, final PDiskData pDiskData) {
        if (pDiskData == null) {
            return PDiskData$ListName.UNKNOWN;
        }
        if ("com.netflix.mediaclient.intent.action.ALL_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
            if (pDiskData.billboardList != null && pDiskData.billboardList.size() > 0) {
                return PDiskData$ListName.BILLBOARD;
            }
            if (pDiskData.cwList != null && pDiskData.cwList.size() > 0) {
                return PDiskData$ListName.CW;
            }
            if (pDiskData.standardFirstList != null && pDiskData.standardFirstList.size() > 0) {
                return PDiskData$ListName.STANDARD_FIRST;
            }
        }
        else {
            if ("com.netflix.mediaclient.intent.action.CW_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
                return PDiskData$ListName.CW;
            }
            if ("com.netflix.mediaclient.intent.action.IQ_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
                return PDiskData$ListName.IQ;
            }
        }
        return PDiskData$ListName.STANDARD_FIRST;
    }
    
    private PDiskData$ListName getNextListName(final PDiskData$ListName pDiskData$ListName, final PDiskData pDiskData) {
        switch (PServiceWidgetAgent$1.$SwitchMap$com$netflix$mediaclient$service$pservice$PDiskData$ListName[pDiskData$ListName.ordinal()]) {
            case 1: {
                if (pDiskData.cwList != null && pDiskData.cwList.size() > 0) {
                    return PDiskData$ListName.CW;
                }
                if (pDiskData.iqList != null && pDiskData.iqList.size() > 0) {
                    return PDiskData$ListName.IQ;
                }
                if (pDiskData.standardFirstList != null && pDiskData.standardFirstList.size() > 0) {
                    return PDiskData$ListName.STANDARD_FIRST;
                }
                if (pDiskData.standardSecondList != null && pDiskData.standardSecondList.size() > 0) {
                    return PDiskData$ListName.STANDARD_SECOND;
                }
                break;
            }
            case 2: {
                if (pDiskData.iqList != null && pDiskData.iqList.size() > 0) {
                    return PDiskData$ListName.IQ;
                }
                if (pDiskData.standardFirstList != null && pDiskData.standardFirstList.size() > 0) {
                    return PDiskData$ListName.STANDARD_FIRST;
                }
                if (pDiskData.standardSecondList != null && pDiskData.standardSecondList.size() > 0) {
                    return PDiskData$ListName.STANDARD_SECOND;
                }
                if (pDiskData.billboardList != null && pDiskData.billboardList.size() > 0) {
                    return PDiskData$ListName.BILLBOARD;
                }
                break;
            }
            case 3: {
                if (pDiskData.standardFirstList != null && pDiskData.standardFirstList.size() > 0) {
                    return PDiskData$ListName.STANDARD_FIRST;
                }
                if (pDiskData.standardSecondList != null && pDiskData.standardSecondList.size() > 0) {
                    return PDiskData$ListName.STANDARD_SECOND;
                }
                if (pDiskData.billboardList != null && pDiskData.billboardList.size() > 0) {
                    return PDiskData$ListName.BILLBOARD;
                }
                if (pDiskData.cwList != null && pDiskData.cwList.size() > 0) {
                    return PDiskData$ListName.CW;
                }
                break;
            }
            case 4: {
                if (pDiskData.standardSecondList != null && pDiskData.standardSecondList.size() > 0) {
                    return PDiskData$ListName.STANDARD_SECOND;
                }
                if (pDiskData.billboardList != null && pDiskData.billboardList.size() > 0) {
                    return PDiskData$ListName.BILLBOARD;
                }
                if (pDiskData.cwList != null && pDiskData.cwList.size() > 0) {
                    return PDiskData$ListName.CW;
                }
                if (pDiskData.iqList != null && pDiskData.iqList.size() > 0) {
                    return PDiskData$ListName.IQ;
                }
                break;
            }
            case 5: {
                if (pDiskData.billboardList != null && pDiskData.billboardList.size() > 0) {
                    return PDiskData$ListName.BILLBOARD;
                }
                if (pDiskData.cwList != null && pDiskData.cwList.size() > 0) {
                    return PDiskData$ListName.CW;
                }
                if (pDiskData.iqList != null && pDiskData.iqList.size() > 0) {
                    return PDiskData$ListName.IQ;
                }
                if (pDiskData.standardFirstList != null && pDiskData.standardFirstList.size() > 0) {
                    return PDiskData$ListName.STANDARD_FIRST;
                }
                break;
            }
        }
        return pDiskData$ListName;
    }
    
    private PVideo getNextVideoInList(final PDiskData$ListName pDiskData$ListName, final PVideo pVideo, final PDiskData pDiskData) {
        if (pDiskData == null) {
            Log.w("nf_preapp_fetchagent", "diskData is null - ignoring request");
            return null;
        }
        final List<PVideo> listByName = this.getListByName(pDiskData$ListName, pDiskData);
        if (listByName == null || pVideo == null) {
            Log.w("nf_preapp_fetchagent", "(getNextVideoInList) listName or currentVideo is null getting first video");
            return this.getFirstVideo(this.getListByName(this.getListNameToUse(new Intent("com.netflix.mediaclient.intent.action.ALL_UPDATED_FROM_PREAPP_AGENT"), pDiskData), pDiskData));
        }
        final int index = listByName.indexOf(pVideo);
        if (index + 1 < Math.min(listByName.size(), PServiceABTest.getVideoCountOfListForWidgetExp(pDiskData$ListName, pDiskData))) {
            return listByName.get(index + 1);
        }
        Log.d("nf_preapp_fetchagent", String.format("next null - videoId: %s, is last in listName: %s, index: %d, size: %d", pVideo.id, pDiskData$ListName, index, listByName.size()));
        return null;
    }
    
    private String getNflxBaseReq(final String s) {
        return "nflx://www.netflix.com/Browse?q=source%3DNetflixWidget%26action%3D" + s;
    }
    
    private PVideo getVideo(final PDiskData$ListName pDiskData$ListName, final Intent intent, final PDiskData pDiskData) {
        if (pDiskData$ListName == null || PDiskData$ListName.UNKNOWN.equals(pDiskData$ListName) || intent.getExtras() == null) {
            return null;
        }
        final String string = intent.getExtras().getString("videoId");
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        final List<PVideo> listByName = this.getListByName(pDiskData$ListName, pDiskData);
        if (listByName != null) {
            for (final PVideo pVideo : listByName) {
                if (StringUtils.safeEquals(string, pVideo.id)) {
                    return pVideo;
                }
            }
        }
        return null;
    }
    
    private String getVideoTitle(final Context context, final PVideo pVideo) {
        if (pVideo.isPlayable && VideoType.SHOW.equals(pVideo.videoType)) {
            return context.getString(2131493387, new Object[] { pVideo.title, pVideo.playableSeasonNumber, pVideo.playableEpisodeNumber });
        }
        return pVideo.title;
    }
    
    private PendingIntent getWidgetDetailsOrPlayIntent(final PVideo pVideo, final PDiskData$ListName pDiskData$ListName, final int n) {
        return this.createWidgetButtonIntent(new Intent("com.netflix.mediaclient.intent.action.PLAY_OR_DETAILS_FROM_PREAPP_WIDGET"), pVideo, pDiskData$ListName, n);
    }
    
    private PendingIntent getWidgetHomeIntent(final int n) {
        return this.createWidgetButtonIntent(new Intent("com.netflix.mediaclient.intent.action.HOME_FROM_PREAPP_WIDGET"), null, null, n);
    }
    
    private int getWidgetId(final Intent intent) {
        int n = PService.INVALID_WIDGET_ID;
        if (intent.getExtras() != null) {
            n = intent.getExtras().getInt("widgetId", (int)PService.INVALID_WIDGET_ID);
        }
        return n;
    }
    
    private PendingIntent getWidgetRefreshIntent(final PVideo pVideo, final PDiskData$ListName pDiskData$ListName, final int n) {
        return this.createWidgetButtonIntent(new Intent("com.netflix.mediaclient.intent.action.REFRESH_FROM_PREAPP_WIDGET"), pVideo, pDiskData$ListName, n);
    }
    
    private boolean isWidgetOneCellHigh(final int n, final int n2) {
        return n > 50 && n < 80 && n2 > 70 && n2 < 110;
    }
    
    @TargetApi(16)
    private boolean isWidgetOneCellHigh(final Context context, final int n) {
        final Bundle appWidgetOptions = AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetOptions(n);
        boolean widgetOneCellHigh = false;
        if (appWidgetOptions != null) {
            widgetOneCellHigh = this.isWidgetOneCellHigh(appWidgetOptions.getInt("appWidgetMinHeight"), appWidgetOptions.getInt("appWidgetMaxHeight"));
        }
        return widgetOneCellHigh;
    }
    
    private void launchNetflixHome(final Context context, final int n) {
        Log.d("nf_preapp_fetchagent", "luanching Netflix Home");
        this.launchNflxAction(context, this.buildNflxUri("home", null), n, PreAppWidgetLogActionData$PreAppWidgetActionName.HOME.getValue());
    }
    
    private void launchNetflixPlay(final Context context, final PVideo pVideo, final int n) {
        Log.d("nf_preapp_fetchagent", "luanching Netflix Play");
        this.launchNflxAction(context, this.buildNflxUri("play", pVideo), n, PreAppWidgetLogActionData$PreAppWidgetActionName.START_PLAY.getValue());
    }
    
    private void launchNetflixVideoDetails(final Context context, final PVideo pVideo, final int n) {
        Log.d("nf_preapp_fetchagent", "luanching Netflix MDP/SDP");
        this.launchNflxAction(context, this.buildNflxUri("view_details", pVideo), n, PreAppWidgetLogActionData$PreAppWidgetActionName.VIEW_TITLE_DETAILS.getValue());
    }
    
    private void launchNflxAction(final Context context, final Uri uri, final int n, final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.addFlags(872415232);
        intent.putExtra("FROM_PREAPP_WIDGET", "NetflixWidget");
        intent.putExtra("widgetId", n);
        intent.putExtra("actionName", s);
        context.startActivity(intent);
    }
    
    private void sendNonMemberExpToWidget(final Context context) {
        Log.d("nf_preapp_fetchagent", "Sending non-member video to widget");
        this.updateAllWidgetsWithNonMemberExperience(context);
    }
    
    private void sendVideoToWidget(final Context context, final PVideo pVideo, final PDiskData$ListName pDiskData$ListName, final PDiskData pDiskData) {
        if (pVideo == null) {
            Log.w("nf_preapp_fetchagent", "video == null, unable to notify widget");
            return;
        }
        Log.d("nf_preapp_fetchagent", String.format("Sending video to widget. id:%s, type:%s", pVideo.id, pVideo.videoType));
        this.updateAllWidgetsWithMemberExperience(context, pVideo, pDiskData$ListName, pDiskData);
    }
    
    private void updateAllWidgetsWithMemberExperience(final Context context, final PVideo pVideo, final PDiskData$ListName pDiskData$ListName, final PDiskData pDiskData) {
        final int[] appWidgetIds = this.getAppWidgetIds(context);
        for (int length = appWidgetIds.length, i = 0; i < length; ++i) {
            final int n = appWidgetIds[i];
            this.updateWidget(context, this.buildWidgetRemoteView(context, pVideo, pDiskData$ListName, pDiskData, n), n);
        }
    }
    
    private void updateAllWidgetsWithNonMemberExperience(final Context context) {
        final int[] appWidgetIds = this.getAppWidgetIds(context);
        for (int length = appWidgetIds.length, i = 0; i < length; ++i) {
            final int n = appWidgetIds[i];
            this.updateWidget(context, this.buildWidgetNonMemberRemoteView(context, n), n);
        }
    }
    
    private void updateWidget(final Context context, final RemoteViews remoteViews, final int n) {
        if (remoteViews == null) {
            Log.w("nf_preapp_fetchagent", "RemoteViews is null, ignore refreshing widget");
            return;
        }
        AppWidgetManager.getInstance(context.getApplicationContext()).updateAppWidget(n, remoteViews);
    }
    
    @Override
    protected void doInit() {
        this.initCompleted(CommonStatus.OK);
    }
    
    public void handlePlayOrDetailsReq(final Context context, final Intent intent) {
        final PServiceAgent$PServiceFetchAgentInterface fetchAgent = this.getFetchAgent();
        if (fetchAgent == null) {
            Log.w("nf_preapp_fetchagent", "Fetch agent is null");
        }
        else {
            final PDiskData diskData = fetchAgent.getDiskData();
            if (diskData == null) {
                Log.w("nf_preapp_fetchagent", "pDiskData null. Ignoring refresh request");
                return;
            }
            final PVideo video = this.getVideo(this.getListName(intent), intent, diskData);
            if (video != null) {
                if (video.isPlayable) {
                    this.launchNetflixPlay(context, video, this.getWidgetId(intent));
                    return;
                }
                this.launchNetflixVideoDetails(context, video, this.getWidgetId(intent));
            }
        }
    }
    
    public void handleWidgetHomeReq(final Context context, final Intent intent) {
        this.launchNetflixHome(context, this.getWidgetId(intent));
    }
    
    public void handleWidgetRefreshReq(final Context context, final Intent intent) {
        final PServiceAgent$PServiceFetchAgentInterface fetchAgent = this.getFetchAgent();
        if (fetchAgent == null) {
            Log.w("nf_preapp_fetchagent", "Fetch agent is null");
            return;
        }
        final PDiskData diskData = fetchAgent.getDiskData();
        if (diskData == null) {
            Log.w("nf_preapp_fetchagent", "pDiskData null. Ignoring refresh request - going to non-member");
            this.updateAllWidgetsWithNonMemberExperience(context);
            return;
        }
        PDiskData$ListName pDiskData$ListName = this.getListName(intent);
        final PVideo video = this.getVideo(pDiskData$ListName, intent, diskData);
        PVideo pVideo;
        if (pDiskData$ListName == null || PDiskData$ListName.UNKNOWN.equals(pDiskData$ListName) || video == null) {
            Log.w("nf_preapp_fetchagent", "listName or currentVideo is null getting first video");
            pDiskData$ListName = this.getListNameToUse(new Intent("com.netflix.mediaclient.intent.action.ALL_UPDATED_FROM_PREAPP_AGENT"), diskData);
            pVideo = this.getFirstVideo(this.getListByName(pDiskData$ListName, diskData));
        }
        else {
            pVideo = this.getNextVideoInList(pDiskData$ListName, video, diskData);
        }
        PVideo firstVideo = pVideo;
        PDiskData$ListName nextListName = pDiskData$ListName;
        if (pVideo == null) {
            nextListName = this.getNextListName(pDiskData$ListName, diskData);
            if ((firstVideo = this.getFirstVideo(this.getListByName(nextListName, diskData))) == null) {
                Log.e("nf_preapp_fetchagent", "getNextListName is not valid - ignoring refresh request");
                return;
            }
        }
        this.updateAllWidgetsWithMemberExperience(context, firstVideo, nextListName, diskData);
    }
    
    public void logWidgetRefreshEvent(final Context context, final Intent intent) {
        final int widgetId = this.getWidgetId(intent);
        if (widgetId == PService.INVALID_WIDGET_ID) {
            Log.w("nf_preapp_fetchagent", "cannot log refresh action invalid widgetId");
            return;
        }
        PServiceLogging.storeLogEvent(context, PServiceWidgetLogEvent$WidgetAction.GO_TO_NEXT, widgetId);
    }
    
    @Override
    public void updateWidgetWithLatestData(final Intent intent) {
        final PServiceAgent$PServiceFetchAgentInterface fetchAgent = this.getFetchAgent();
        if (fetchAgent == null) {
            Log.w("nf_preapp_fetchagent", "Fetch agent is null");
            return;
        }
        final PDiskData diskData = fetchAgent.getDiskData();
        if (diskData == null) {
            Log.w("nf_preapp_fetchagent", "mDiskData is null - ignoring request");
            return;
        }
        final PDiskData$ListName listNameToUse = this.getListNameToUse(intent, diskData);
        this.sendVideoToWidget(this.getContext(), this.getFirstVideo(this.getListByName(listNameToUse, diskData)), listNameToUse, diskData);
    }
    
    public void updateWidgetWithNonMemberData(final Intent intent) {
        this.sendNonMemberExpToWidget(this.getContext());
    }
}

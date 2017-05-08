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
import java.util.Iterator;
import android.graphics.Matrix;
import com.netflix.mediaclient.util.TimeUtils;
import android.graphics.BitmapFactory;
import java.io.File;
import android.content.ComponentName;
import android.appwidget.AppWidgetManager;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.app.PendingIntent;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.net.Uri;
import java.util.List;
import android.widget.RemoteViews;
import android.content.Context;
import android.graphics.Bitmap;

public class PServiceWidgetAgent extends PServiceAgent implements PServiceAgent$PServiceWidgetAgentInterface
{
    private static final float ASPECT_RATIO_16_9 = 1.78f;
    private static final float ASPECT_RATIO_LIMIT_FOR_CROPPING = 4.8f;
    public static final String EXTRA_SOURCE = "FROM_PREAPP_WIDGET";
    public static final String FILE_PREFIX = "file://";
    private static final int LOGO_HEADER_HEIGHT_DP = 40;
    private static final int MAX_IMAGES_IN_VIEW = 3;
    public static final String NFLX_WIDGET = "NetflixWidget";
    private static final String PREAPP_NFLX_BASE = "nflx://www.netflix.com/Browse?q=source%3DNetflixWidget%26trkid%3D14836231%26action%3D";
    private static final String PREAPP_NFLX_EPISODE_URL = "%26episodeid%3Dhttp%3A%2F%2Fapi-global.netflix.com%2Fcatalog%2Ftitles%2Fprograms%2F";
    private static final String PREAPP_NFLX_MOVIE_URL = "%26movieid%3Dhttp%3A%2F%2Fapi-global.netflix.com%2Fcatalog%2Ftitles%2Fmovies%2F";
    private static final String PREAPP_NFLX_SHOW_URL = "%26movieid%3Dhttp%3A%2F%2Fapi-global.netflix.com%2Fcatalog%2Ftitles%2Fseries%2F";
    private static final String PREAPP_TRACKID = "14836231";
    private static final String PREAP_TRACKID_PARAM = "%26trkid%3D14836231";
    private static final String TAG = "nf_preapp_widgetagent";
    private static final int TV_CARD_SCALE_UP = 3;
    private static boolean mIsNflxServicePlayerInPauseState;
    private static PDiskData$ListType mListTypeOnWidget;
    private static String mNflxServicePlayableId;
    private static String mVideoIdOnWidget;
    
    private RemoteViews adjustLogoAndRefreshIcon(final Context context, final RemoteViews remoteViews, int n, final PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final int n2, final int n3, final String s, final List<PVideo> list) {
        remoteViews.setOnClickPendingIntent(2131624545, this.getWidgetHomeIntent(n2));
        if (2130903214 == n3) {
            remoteViews.setOnClickPendingIntent(2131624546, this.getWidgetRefreshIntent(pVideo, pDiskData$ListType, n2));
            remoteViews.setViewVisibility(2131624544, 8);
            remoteViews.setViewVisibility(this.getGradientResourceId(1), 0);
        }
        else {
            remoteViews.setOnClickPendingIntent(2131624544, this.getWidgetRefreshIntent(pVideo, pDiskData$ListType, n2));
            if (this.canFitListName(context, n2)) {
                if (PDiskData$ListType.NON_MEMBER.equals(pDiskData$ListType)) {
                    n = list.indexOf(pVideo) / n;
                    if (n <= 0) {
                        n = 0;
                    }
                    remoteViews.setTextViewText(2131624544, (CharSequence)this.getPreAppAdString(context, n));
                    return remoteViews;
                }
                remoteViews.setTextViewText(2131624544, (CharSequence)s);
                return remoteViews;
            }
        }
        return remoteViews;
    }
    
    private void adjustViews(final Context context, final RemoteViews remoteViews, int n) {
        final int n2 = 0;
        remoteViews.setViewVisibility(2131624541, 8);
        remoteViews.setViewVisibility(2131624551, 8);
        remoteViews.setViewVisibility(2131624545, 0);
        remoteViews.setViewVisibility(2131624548, 0);
        remoteViews.setViewVisibility(2131624549, 0);
        final int cellFromDp = cellFromDp(this.getWidgetHeight(context, n));
        final int cellFromDp2 = cellFromDp(this.getWidgetWidth(context, n));
        n = n2;
        if (cellFromDp2 <= cellFromDp) {
            n = 1;
        }
        if (n != 0 && cellFromDp2 <= 3) {
            remoteViews.setViewVisibility(2131624549, 8);
        }
        if (cellFromDp <= 1) {
            remoteViews.setViewVisibility(2131624548, 8);
            remoteViews.setViewVisibility(2131624549, 8);
        }
    }
    
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
    
    private RemoteViews buildRemoteViews(final Context context, PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final List<String> list, final List<PDiskData$ImageType> list2, final int n, final int n2, final PDiskData pDiskData) {
        final RemoteViews viewsToDefault = new RemoteViews(context.getPackageName(), n2);
        this.setViewsToDefault(viewsToDefault);
        List<PVideo> list3 = this.getListByType(pDiskData$ListType, pDiskData);
        final String s = pDiskData.lomoMap.get(pDiskData$ListType.getValue());
        final int numberOfImages = this.getNumberOfImages(context, n2, n);
        if (numberOfImages < 3) {
            for (int i = numberOfImages + 1; i <= 3; ++i) {
                Log.d("nf_preapp_widgetagent", String.format("setting %d to gone", i));
                viewsToDefault.setViewVisibility(this.getImageGroupResourceId(i), 8);
            }
        }
        int j = 1;
        PDiskData$ListType nextList = pDiskData$ListType;
        PVideo pVideo2 = pVideo;
        RemoteViews remoteViews = viewsToDefault;
        while (j <= numberOfImages) {
            Log.d("nf_preapp_widgetagent", String.format("for widgetId:%d, image:%d, (%s), %s:%s", n, j, nextList, pVideo2.id, pVideo2.title));
            remoteViews.setViewVisibility(this.getImageGroupResourceId(j), 0);
            final RemoteViews fillInRemoteView = this.fillInRemoteView(context, remoteViews, pVideo2, n, nextList, j, n2);
            if (j == numberOfImages) {
                return this.adjustLogoAndRefreshIcon(context, fillInRemoteView, numberOfImages, pVideo2, nextList, n, n2, s, list3);
            }
            if (this.listHasNextVideo(nextList, list3, pVideo2, pDiskData)) {
                pVideo = this.getNextVideoInList(list3, pVideo2, nextList, pDiskData);
            }
            else {
                nextList = this.getNextList(nextList, pDiskData);
                list3 = this.getListByType(nextList, pDiskData);
                pVideo = this.getFirstVideo(list3);
                Log.d("nf_preapp_widgetagent", String.format("(%d)list at end, next video (%s) %s:%s ", j, nextList, pVideo.id, pVideo.title));
            }
            final PDiskData$ImageType imageType = this.getImageType(pVideo, nextList, n2, j + 1);
            final String imageUrlOnDisk = this.getImageUrlOnDisk(pDiskData, pVideo, imageType);
            if (StringUtils.isNotEmpty(imageUrlOnDisk)) {
                list.add(imageUrlOnDisk);
                list2.add(imageType);
            }
            else {
                Log.w("nf_preapp_widgetagent", String.format("(%d)bitmap null for video:%s in list %s", j, pVideo.title, nextList));
            }
            ++j;
            pVideo2 = pVideo;
            remoteViews = fillInRemoteView;
        }
        final RemoteViews fillInRemoteView = remoteViews;
        return this.adjustLogoAndRefreshIcon(context, fillInRemoteView, numberOfImages, pVideo2, nextList, n, n2, s, list3);
    }
    
    private RemoteViews buildWidgetStaticImageRemoteView(final Context context, final int n) {
        int n2 = 2130903215;
        if (this.toAlignByHeight(context, n) || this.isWidgetOneCellHigh(context, n)) {
            n2 = 2130903214;
        }
        Log.d("nf_preapp_widgetagent", "buildWidgetStaticImageRemoteView layoutId:" + n2);
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), n2);
        this.adjustViews(context, remoteViews, n);
        remoteViews.setImageViewResource(2131624540, 2130837898);
        remoteViews.setViewVisibility(2131624548, 0);
        remoteViews.setViewVisibility(2131624549, 0);
        remoteViews.setTextViewText(2131624548, context.getText(2131165705));
        remoteViews.setOnClickPendingIntent(2131624540, this.getWidgetHomeIntent(n));
        remoteViews.setOnClickPendingIntent(2131624544, this.getWidgetRefreshIntent(null, null, n));
        remoteViews.setOnClickPendingIntent(2131624545, this.getWidgetHomeIntent(n));
        return remoteViews;
    }
    
    private boolean canFitListName(final Context context, final int n) {
        final float n2 = this.getWidgetWidth(context, n);
        return !this.isWidgetOneCellHigh(context, n) && n2 > 200.0f;
    }
    
    public static int cellFromDp(final int n) {
        return (n + 30) / 70;
    }
    
    private PendingIntent createWidgetButtonIntent(final Intent intent, final PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final int n) {
        intent.setClass(this.getContext(), (Class)PService.class).addCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_WIDGET").putExtra("widgetId", n);
        if (pVideo != null && StringUtils.isNotEmpty(pVideo.id)) {
            intent.putExtra("videoId", pVideo.id);
        }
        if (pDiskData$ListType != null && !PDiskData$ListType.UNKNOWN.equals(pDiskData$ListType)) {
            intent.putExtra("listType", pDiskData$ListType.toString());
        }
        return PendingIntent.getService(this.getContext(), 0, intent, 134217728);
    }
    
    private void decodeBitmapsAndUpdate(final int n, final RemoteViews remoteViews, final String s, final PDiskData$ListType pDiskData$ListType, final List<String> list, final List<PDiskData$ImageType> list2) {
        new BackgroundTask().execute(new PServiceWidgetAgent$1(this, list, n, list2, remoteViews, s, pDiskData$ListType));
    }
    
    private boolean doesListHaveEnoughVideos(final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData, final int n, final int n2) {
        final List<PVideo> listByType = this.getListByType(pDiskData$ListType, pDiskData);
        final boolean b = listByType != null && this.getListUpperBound(listByType, pDiskData$ListType, pDiskData) >= n + n2;
        Log.d("nf_preapp_widgetagent", String.format("list:%s, size:%d, upperBound:%d, position:%d, need:%d, hasEnough:%b", pDiskData$ListType, listByType.size(), this.getListUpperBound(listByType, pDiskData$ListType, pDiskData), n, n2, b));
        return b;
    }
    
    private RemoteViews fillInRemoteView(final Context context, final RemoteViews remoteViews, final PVideo pVideo, final int n, final PDiskData$ListType pDiskData$ListType, final int n2, final int n3) {
        final int imageResourceId = this.getImageResourceId(n2);
        remoteViews.setOnClickPendingIntent(imageResourceId, this.getWidgetDetailsIntent(pVideo, pDiskData$ListType, n, n2));
        if (PDiskData$ListType.CW.equals(pDiskData$ListType)) {
            int n4;
            if (pVideo.playableRuntime > 0) {
                n4 = pVideo.plyableBookmarkPos * 100 / pVideo.playableRuntime;
            }
            else {
                n4 = 0;
            }
            remoteViews.setProgressBar(this.getProgressResourceId(n2), 100, n4, false);
            if (PDiskData$ImageType.TRICKPLAY.equals(this.getImageType(pVideo, pDiskData$ListType, n3, n2))) {
                remoteViews.setTextViewText(this.getVideoTitleResourceId(n2), (CharSequence)this.getVideoTitle(context, pDiskData$ListType, pVideo));
            }
            remoteViews.setViewVisibility(this.getGradientResourceId(n2), 0);
            remoteViews.setViewVisibility(this.getPlayResourceId(n2), 0);
            remoteViews.setViewVisibility(this.getProgressGroupResourceId(n2), 0);
            remoteViews.setOnClickPendingIntent(this.getPlayResourceId(n2), this.getWidgetPlayIntent(pVideo, pDiskData$ListType, n, n2));
            remoteViews.setOnClickPendingIntent(imageResourceId, this.getWidgetPlayIntent(pVideo, pDiskData$ListType, n, n2));
        }
        return remoteViews;
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
            Log.w("nf_preapp_widgetagent", String.format("%s does not exist", substring));
            return null;
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }
    
    private Bitmap getBitmapFromDisk(final String s, final PDiskData$ImageType pDiskData$ImageType) {
        final long nanoTime = System.nanoTime();
        final Bitmap bitmap = this.getBitmap(s);
        Bitmap bitmap2;
        if (bitmap == null) {
            Log.w("nf_preapp_widgetagent", "bitmap does not exist");
            bitmap2 = null;
        }
        else {
            Log.d("nf_preapp_widgetagent", String.format("bitmap decode took (%s) %d ms", pDiskData$ImageType, TimeUtils.computeTimeInMsSinceStart(nanoTime)));
            Bitmap resizedBitmapForTrickplay = bitmap;
            if (PDiskData$ImageType.TRICKPLAY.equals(pDiskData$ImageType)) {
                final long nanoTime2 = System.nanoTime();
                resizedBitmapForTrickplay = this.getResizedBitmapForTrickplay(bitmap);
                Log.d("nf_preapp_widgetagent", String.format("bitmap resize (CW) took %d ms", TimeUtils.computeTimeInMsSinceStart(nanoTime2)));
            }
            bitmap2 = resizedBitmapForTrickplay;
            if (PDiskData$ImageType.TITLE_CARD.equals(pDiskData$ImageType)) {
                final long nanoTime3 = System.nanoTime();
                final Bitmap resizedBitmapForTvCard = this.getResizedBitmapForTvCard(resizedBitmapForTrickplay);
                Log.d("nf_preapp_widgetagent", String.format("bitmap resize (tvCard) took %d ms", TimeUtils.computeTimeInMsSinceStart(nanoTime3)));
                return resizedBitmapForTvCard;
            }
        }
        return bitmap2;
    }
    
    private PVideo getFirstVideo(final List<PVideo> list) {
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    private int getGradientResourceId(final int n) {
        switch (n) {
            default: {
                return 2131624543;
            }
            case 2: {
                return 2131624555;
            }
            case 3: {
                return 2131624562;
            }
        }
    }
    
    private int getImageGroupResourceId(final int n) {
        switch (n) {
            default: {
                return 2131624566;
            }
            case 2: {
                return 2131624552;
            }
            case 3: {
                return 2131624559;
            }
        }
    }
    
    private int getImageResourceId(final int n) {
        switch (n) {
            default: {
                return 2131624540;
            }
            case 2: {
                return 2131624553;
            }
            case 3: {
                return 2131624560;
            }
        }
    }
    
    private PDiskData$ImageType getImageType(final PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final int n, final int n2) {
        Log.d("nf_preapp_widgetagent", String.format("getImageType - getResizedBitmapForTrickplay%d, listType:%s, videoPos:%d", n, pDiskData$ListType, n2));
        if (2130903216 == n && n2 > 1) {
            return PDiskData$ImageType.HORIZONTAL_ART;
        }
        if (2130903214 == n) {
            return PDiskData$ImageType.TITLE_CARD;
        }
        if (PDiskData$ListType.CW.equals(pDiskData$ListType) && pVideo.isPlayable) {
            return PDiskData$ImageType.TRICKPLAY;
        }
        return PDiskData$ImageType.HORIZONTAL_ART;
    }
    
    public static String getImageUrl(final PVideo pVideo, final PDiskData$ImageType pDiskData$ImageType) {
        switch (PServiceWidgetAgent$2.$SwitchMap$com$netflix$mediaclient$service$pservice$PDiskData$ImageType[pDiskData$ImageType.ordinal()]) {
            default: {
                return pVideo.horzDispUrl;
            }
            case 1: {
                return pVideo.storyImgDispUrl;
            }
            case 2: {
                return pVideo.tvCardUrl;
            }
            case 3: {
                return pVideo.trickplayUrl;
            }
        }
    }
    
    private String getImageUrlOnDisk(final PDiskData pDiskData, final PVideo pVideo, final PDiskData$ImageType pDiskData$ImageType) {
        String s;
        if (pVideo == null || StringUtils.isEmpty(getImageUrl(pVideo, pDiskData$ImageType))) {
            Log.w("nf_preapp_widgetagent", "pVideo is not valid, ignore newRemoteView");
            s = null;
        }
        else if (StringUtils.isEmpty(s = pDiskData.urlMap.get(getImageUrl(pVideo, pDiskData$ImageType)))) {
            Log.w("nf_preapp_widgetagent", "resource not on disk, ignore widget update");
            return null;
        }
        return s;
    }
    
    private int getLayoutId(final Context context, int n) {
        if (this.isWidgetOneCellHigh(context, n)) {
            Log.d("nf_preapp_widgetagent", String.format("using preapp_widget_align_height %d", 2130903214));
            return 2130903214;
        }
        if (this.toAlignByWidth(context, n)) {
            Log.d("nf_preapp_widgetagent", String.format("using preapp_widget_vertical %d", 2130903216));
            return 2130903216;
        }
        final int cellFromDp = cellFromDp(this.getWidgetWidth(context, n));
        if (this.toAlignByHeight(context, n)) {
            n = 2130903214;
        }
        else if (cellFromDp > 2) {
            n = 2130903217;
        }
        else {
            n = 2130903218;
        }
        Log.d("nf_preapp_widgetagent", String.format("using %d, preapp_widget_align_height=%d, preapp_width:%d, (widthInCell:%d) preapp_width_2cell:%d", n, 2130903214, 2130903217, cellFromDp, 2130903218));
        return n;
    }
    
    private List<PVideo> getListByType(final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        if (pDiskData == null || pDiskData$ListType == null) {
            Log.w("nf_preapp_widgetagent", "diskData is null - ignoring request");
            return null;
        }
        switch (PServiceWidgetAgent$2.$SwitchMap$com$netflix$mediaclient$service$pservice$PDiskData$ListType[pDiskData$ListType.ordinal()]) {
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
            case 6: {
                return pDiskData.nonMemberList;
            }
        }
    }
    
    private PDiskData$ListType getListTypeToUse(final Intent intent, final PDiskData pDiskData) {
        if (pDiskData == null) {
            return PDiskData$ListType.UNKNOWN;
        }
        if (PDiskData.isMemberDataAvailable(pDiskData)) {
            if ("com.netflix.mediaclient.intent.action.ALL_MEMBER_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
                if (!PDiskData.isListEmpty(pDiskData.billboardList)) {
                    return PDiskData$ListType.BILLBOARD;
                }
                if (!PDiskData.isListEmpty(pDiskData.cwList)) {
                    return PDiskData$ListType.CW;
                }
                if (!PDiskData.isListEmpty(pDiskData.standardFirstList)) {
                    return PDiskData$ListType.STANDARD_FIRST;
                }
                if (!PDiskData.isListEmpty(pDiskData.standardSecondList)) {
                    return PDiskData$ListType.STANDARD_SECOND;
                }
            }
            else {
                if ("com.netflix.mediaclient.intent.action.CW_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
                    return PDiskData$ListType.CW;
                }
                if ("com.netflix.mediaclient.intent.action.IQ_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
                    return PDiskData$ListType.IQ;
                }
            }
        }
        return PDiskData$ListType.NON_MEMBER;
    }
    
    private int getListUpperBound(final List<PVideo> list, final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        if (list == null) {
            return 0;
        }
        return Math.min(list.size(), PServiceABTest.getVideoCountOfListForWidgetExp(pDiskData$ListType, pDiskData));
    }
    
    private int getMaxNumOfImagesForAllWidgets(final Context context) {
        final int[] appWidgetIds = this.getAppWidgetIds(context);
        final int length = appWidgetIds.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            final int n2 = appWidgetIds[i];
            final int numberOfImages = this.getNumberOfImages(context, this.getLayoutId(context, n2), n2);
            if (numberOfImages > n) {
                n = numberOfImages;
            }
            ++i;
        }
        Log.d("nf_preapp_widgetagent", String.format("maxNumOfImages among all widgets %d", n));
        return n;
    }
    
    private PDiskData$ListType getNameOfListShownInWidget(final Intent intent) {
        if (intent.getExtras() != null) {
            final String string = intent.getExtras().getString("listType");
            if (StringUtils.isNotEmpty(string)) {
                return PDiskData$ListType.valueOf(string);
            }
        }
        if (PServiceWidgetAgent.mListTypeOnWidget != null) {
            return PServiceWidgetAgent.mListTypeOnWidget;
        }
        return PDiskData$ListType.UNKNOWN;
    }
    
    private PDiskData$ListType getNextList(final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        switch (PServiceWidgetAgent$2.$SwitchMap$com$netflix$mediaclient$service$pservice$PDiskData$ListType[pDiskData$ListType.ordinal()]) {
            case 1: {
                if (pDiskData.cwList != null && pDiskData.cwList.size() > 0) {
                    return PDiskData$ListType.CW;
                }
                if (pDiskData.iqList != null && pDiskData.iqList.size() > 0) {
                    return PDiskData$ListType.IQ;
                }
                if (pDiskData.standardFirstList != null && pDiskData.standardFirstList.size() > 0) {
                    return PDiskData$ListType.STANDARD_FIRST;
                }
                if (pDiskData.standardSecondList != null && pDiskData.standardSecondList.size() > 0) {
                    return PDiskData$ListType.STANDARD_SECOND;
                }
                break;
            }
            case 2: {
                if (pDiskData.iqList != null && pDiskData.iqList.size() > 0) {
                    return PDiskData$ListType.IQ;
                }
                if (pDiskData.standardFirstList != null && pDiskData.standardFirstList.size() > 0) {
                    return PDiskData$ListType.STANDARD_FIRST;
                }
                if (pDiskData.standardSecondList != null && pDiskData.standardSecondList.size() > 0) {
                    return PDiskData$ListType.STANDARD_SECOND;
                }
                if (pDiskData.billboardList != null && pDiskData.billboardList.size() > 0) {
                    return PDiskData$ListType.BILLBOARD;
                }
                break;
            }
            case 3: {
                if (pDiskData.standardFirstList != null && pDiskData.standardFirstList.size() > 0) {
                    return PDiskData$ListType.STANDARD_FIRST;
                }
                if (pDiskData.standardSecondList != null && pDiskData.standardSecondList.size() > 0) {
                    return PDiskData$ListType.STANDARD_SECOND;
                }
                if (pDiskData.billboardList != null && pDiskData.billboardList.size() > 0) {
                    return PDiskData$ListType.BILLBOARD;
                }
                if (pDiskData.cwList != null && pDiskData.cwList.size() > 0) {
                    return PDiskData$ListType.CW;
                }
                break;
            }
            case 4: {
                if (pDiskData.standardSecondList != null && pDiskData.standardSecondList.size() > 0) {
                    return PDiskData$ListType.STANDARD_SECOND;
                }
                if (pDiskData.billboardList != null && pDiskData.billboardList.size() > 0) {
                    return PDiskData$ListType.BILLBOARD;
                }
                if (pDiskData.cwList != null && pDiskData.cwList.size() > 0) {
                    return PDiskData$ListType.CW;
                }
                if (pDiskData.iqList != null && pDiskData.iqList.size() > 0) {
                    return PDiskData$ListType.IQ;
                }
                break;
            }
            case 5: {
                if (pDiskData.billboardList != null && pDiskData.billboardList.size() > 0) {
                    return PDiskData$ListType.BILLBOARD;
                }
                if (pDiskData.cwList != null && pDiskData.cwList.size() > 0) {
                    return PDiskData$ListType.CW;
                }
                if (pDiskData.iqList != null && pDiskData.iqList.size() > 0) {
                    return PDiskData$ListType.IQ;
                }
                if (pDiskData.standardFirstList != null && pDiskData.standardFirstList.size() > 0) {
                    return PDiskData$ListType.STANDARD_FIRST;
                }
                break;
            }
            case 6: {
                return PDiskData$ListType.NON_MEMBER;
            }
        }
        return pDiskData$ListType;
    }
    
    private PDiskData$ListType getNextListTypeToUse(final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        final int maxNumOfImagesForAllWidgets = this.getMaxNumOfImagesForAllWidgets(this.getContext());
        PDiskData$ListType pDiskData$ListType2 = this.getNextList(pDiskData$ListType, pDiskData);
        while (!this.hasListGotEnoughVideos(pDiskData$ListType2, pDiskData, 0, maxNumOfImagesForAllWidgets)) {
            Log.d("nf_preapp_widgetagent", String.format("skip list:%s, currentList:%s, need:%d", pDiskData$ListType2, pDiskData$ListType, maxNumOfImagesForAllWidgets));
            final PDiskData$ListType pDiskData$ListType3 = pDiskData$ListType2 = this.getNextList(pDiskData$ListType2, pDiskData);
            if (pDiskData$ListType.equals(pDiskData$ListType3)) {
                pDiskData$ListType2 = pDiskData$ListType3;
                break;
            }
        }
        Log.d("nf_preapp_widgetagent", String.format("using nextList:%s, currentList:%s, need:%s", pDiskData$ListType2, pDiskData$ListType, maxNumOfImagesForAllWidgets));
        return pDiskData$ListType2;
    }
    
    private PVideo getNextVideo(PDiskData$ListType pDiskData$ListType, final PVideo pVideo, final PDiskData pDiskData) {
        if (pDiskData == null) {
            Log.w("nf_preapp_widgetagent", "diskData is null - ignoring request");
            return null;
        }
        final List<PVideo> listByType = this.getListByType(pDiskData$ListType, pDiskData);
        if (listByType == null || pVideo == null) {
            Log.w("nf_preapp_widgetagent", String.format("(getNextVideo) listType: %s or currentVideo %s is null getting first video", pDiskData$ListType, pVideo));
            final PDiskData$ListType pDiskData$ListType2 = pDiskData$ListType = this.getListTypeToUse(new Intent("com.netflix.mediaclient.intent.action.ALL_MEMBER_UPDATED_FROM_PREAPP_AGENT"), pDiskData);
            if (!this.hasListGotEnoughVideos(pDiskData$ListType2, pDiskData)) {
                Log.d("nf_preapp_widgetagent", String.format("list:%s, not enough - going to next", pDiskData$ListType2));
                pDiskData$ListType = this.getNextListTypeToUse(pDiskData$ListType2, pDiskData);
            }
            return this.getFirstVideo(this.getListByType(pDiskData$ListType, pDiskData));
        }
        final int index = listByType.indexOf(pVideo);
        final int listUpperBound = this.getListUpperBound(listByType, pDiskData$ListType, pDiskData);
        if (index + 1 < listUpperBound) {
            return listByType.get(index + 1);
        }
        Log.d("nf_preapp_widgetagent", String.format("next null - videoId: %s, is last in listType: %s, index: %d, size: %d, upperBound:%d", pVideo.id, pDiskData$ListType, index, listByType.size(), listUpperBound));
        return null;
    }
    
    private PVideo getNextVideoInList(final List<PVideo> list, final PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        final int n = list.indexOf(pVideo) + 1;
        if (n > 0 && n < this.getListUpperBound(list, pDiskData$ListType, pDiskData)) {
            return list.get(n);
        }
        Log.w("nf_preapp_widgetagent", "getNextVideoInList is null");
        return null;
    }
    
    private String getNflxBaseReq(final String s) {
        return "nflx://www.netflix.com/Browse?q=source%3DNetflixWidget%26trkid%3D14836231%26action%3D" + s;
    }
    
    private int getNumberOfImages(final Context context, int widgetHeight, final int n) {
        int n2 = 3;
        if (2130903214 == widgetHeight || 2130903218 == widgetHeight) {
            n2 = 1;
        }
        else if (2130903216 != widgetHeight) {
            if (2130903217 != widgetHeight) {
                return 1;
            }
            widgetHeight = this.getWidgetHeight(context, n);
            final float n3 = this.getWidgetWidth(context, n);
            final float n4 = n3 / widgetHeight;
            Log.d("nf_preapp_widgetagent", String.format("w-h(%f-%d) %f ", n3, widgetHeight, n4));
            if (n4 < 1.8) {
                Log.d("nf_preapp_widgetagent", "1 image only");
                return 1;
            }
            if (n4 < 3.0f) {
                Log.d("nf_preapp_widgetagent", "2 image only");
                return 2;
            }
            Log.d("nf_preapp_widgetagent", "3 images");
            return 3;
        }
        return n2;
    }
    
    private int getPlayResourceId(final int n) {
        switch (n) {
            default: {
                return 2131624541;
            }
            case 2: {
                return 2131624554;
            }
            case 3: {
                return 2131624561;
            }
        }
    }
    
    private String getPreAppAdString(final Context context, final int n) {
        String s = null;
        switch (n % 3) {
            default: {
                s = context.getString(2131165705);
                break;
            }
            case 0: {
                s = context.getString(2131165703);
                break;
            }
            case 1: {
                s = context.getString(2131165704);
                break;
            }
        }
        Log.d("nf_preapp_widgetagent", String.format("ad (%d) %s", n, s));
        return s;
    }
    
    private int getProgressGroupResourceId(final int n) {
        switch (n) {
            default: {
                return 2131624550;
            }
            case 2: {
                return 2131624556;
            }
            case 3: {
                return 2131624563;
            }
        }
    }
    
    private int getProgressResourceId(final int n) {
        switch (n) {
            default: {
                return 2131624551;
            }
            case 2: {
                return 2131624558;
            }
            case 3: {
                return 2131624565;
            }
        }
    }
    
    private Bitmap getResizedBitmapForTrickplay(final Bitmap bitmap) {
        final boolean b = true;
        final boolean b2 = true;
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final float n = width / height;
        final boolean b3 = n > 1.78f;
        Log.d("nf_preapp_widgetagent", String.format("getResizedBitmapForTrickplay -  w-h: %d-%d, aspectRatio:%f(%f), cropWidth:%b", width, height, n, 1.78f, b3));
        Bitmap bitmap2;
        if (b3) {
            final int round = Math.round(height * 1.78f);
            final int n2 = (width - round) / 2;
            Log.d("nf_preapp_widgetagent", String.format("getResizedBitmapForTrickplay - w-h: %d-%d, aspectWidth:%d, x:%d, shouldCropWidth:%b", width, height, round, n2, n2 + round < width && b2));
            bitmap2 = bitmap;
            if (n2 + round < width) {
                bitmap2 = Bitmap.createBitmap(bitmap, n2, 0, round, height);
                bitmap.recycle();
            }
        }
        else {
            final int round2 = Math.round(width / 1.78f);
            final int n3 = (height - round2) / 2;
            Log.d("nf_preapp_widgetagent", String.format("getResizedBitmapForTrickplay - w-h: %d-%d, aspectHeight:%d, y:%d, shouldCropHeight:%b", width, height, round2, n3, n3 + round2 < height && b));
            bitmap2 = bitmap;
            if (n3 + round2 < height) {
                final Bitmap bitmap3 = Bitmap.createBitmap(bitmap, 0, n3, width, round2);
                bitmap.recycle();
                return bitmap3;
            }
        }
        return bitmap2;
    }
    
    private Bitmap getResizedBitmapForTvCard(final Bitmap bitmap) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final Matrix matrix = new Matrix();
        matrix.postScale(3.0f, 3.0f);
        final Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return bitmap2;
    }
    
    private PVideo getVideoShownInWidget(final PDiskData$ListType pDiskData$ListType, final Intent intent, final PDiskData pDiskData) {
        if (pDiskData$ListType == null || PDiskData$ListType.UNKNOWN.equals(pDiskData$ListType) || intent.getExtras() == null) {
            Log.v("nf_preapp_widgetagent", "getVideoShownInWidget - listType / videoId  null");
            return null;
        }
        final String string = intent.getExtras().getString("videoId");
        if (StringUtils.isEmpty(string)) {
            Log.d("nf_preapp_widgetagent", String.format("extra_video_id is null; using stored %s-%s", PServiceWidgetAgent.mListTypeOnWidget, PServiceWidgetAgent.mVideoIdOnWidget));
            return this.getVideoShownInWidget(PServiceWidgetAgent.mListTypeOnWidget, PServiceWidgetAgent.mVideoIdOnWidget, pDiskData);
        }
        Log.d("nf_preapp_widgetagent", String.format("getVideoShownInWidget %s-%s", pDiskData$ListType, string));
        final List<PVideo> listByType = this.getListByType(pDiskData$ListType, pDiskData);
        if (listByType != null) {
            for (final PVideo pVideo : listByType) {
                if (StringUtils.safeEquals(string, pVideo.id)) {
                    return pVideo;
                }
            }
        }
        return null;
    }
    
    private PVideo getVideoShownInWidget(final PDiskData$ListType pDiskData$ListType, final String s, final PDiskData pDiskData) {
        final List<PVideo> listByType = this.getListByType(pDiskData$ListType, pDiskData);
        if (listByType != null) {
            for (final PVideo pVideo : listByType) {
                if (StringUtils.safeEquals(s, pVideo.id)) {
                    return pVideo;
                }
            }
        }
        return null;
    }
    
    private String getVideoTitle(final Context context, final PDiskData$ListType pDiskData$ListType, final PVideo pVideo) {
        if (PDiskData$ListType.CW.equals(pDiskData$ListType) && VideoType.SHOW.equals(pVideo.videoType)) {
            return context.getString(2131165614, new Object[] { pVideo.title, pVideo.playableSeasonNumAbbrLabel, pVideo.playableEpisodeNumber });
        }
        return pVideo.title;
    }
    
    private int getVideoTitleResourceId(final int n) {
        switch (n) {
            default: {
                return 2131624548;
            }
            case 2: {
                return 2131624557;
            }
            case 3: {
                return 2131624564;
            }
        }
    }
    
    private PendingIntent getWidgetDetailsIntent(final PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final int n, final int n2) {
        String s = "com.netflix.mediaclient.intent.action.DETAILS_1_FROM_PREAPP_WIDGET";
        if (n2 == 2) {
            s = "com.netflix.mediaclient.intent.action.DETAILS_2_FROM_PREAPP_WIDGET";
        }
        if (n2 == 3) {
            s = "com.netflix.mediaclient.intent.action.DETAILS_3_FROM_PREAPP_WIDGET";
        }
        return this.createWidgetButtonIntent(new Intent(s), pVideo, pDiskData$ListType, n);
    }
    
    @TargetApi(16)
    private int getWidgetHeight(final Context context, final int n) {
        final Bundle appWidgetOptions = AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetOptions(n);
        if (DeviceUtils.isLandscape(context)) {
            return appWidgetOptions.getInt("appWidgetMinHeight");
        }
        return appWidgetOptions.getInt("appWidgetMaxHeight");
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
    
    private PendingIntent getWidgetPlayIntent(final PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final int n, final int n2) {
        String s = "com.netflix.mediaclient.intent.action.PLAY_1_FROM_PREAPP_WIDGET";
        if (n2 == 2) {
            s = "com.netflix.mediaclient.intent.action.PLAY_2_FROM_PREAPP_WIDGET";
        }
        if (n2 == 3) {
            s = "com.netflix.mediaclient.intent.action.PLAY_3_FROM_PREAPP_WIDGET";
        }
        return this.createWidgetButtonIntent(new Intent(s), pVideo, pDiskData$ListType, n);
    }
    
    private PendingIntent getWidgetRefreshIntent(final PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final int n) {
        return this.createWidgetButtonIntent(new Intent("com.netflix.mediaclient.intent.action.REFRESH_FROM_PREAPP_WIDGET"), pVideo, pDiskData$ListType, n);
    }
    
    @TargetApi(16)
    private int getWidgetWidth(final Context context, final int n) {
        final Bundle appWidgetOptions = AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetOptions(n);
        if (DeviceUtils.isLandscape(context)) {
            return appWidgetOptions.getInt("appWidgetMaxWidth");
        }
        return appWidgetOptions.getInt("appWidgetMinWidth");
    }
    
    private boolean hasAtleastOneToShow(final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData, final int n) {
        return this.doesListHaveEnoughVideos(pDiskData$ListType, pDiskData, n, 1);
    }
    
    private boolean hasListGotEnoughVideos(final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        return this.hasListGotEnoughVideos(pDiskData$ListType, pDiskData, 0, this.getMaxNumOfImagesForAllWidgets(this.getContext()));
    }
    
    private boolean hasListGotEnoughVideos(final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData, final int n, final int n2) {
        return ((PDiskData$ListType.CW.equals(pDiskData$ListType) || PDiskData$ListType.IQ.equals(pDiskData$ListType)) && this.hasAtleastOneToShow(pDiskData$ListType, pDiskData, n)) || this.doesListHaveEnoughVideos(pDiskData$ListType, pDiskData, n, n2);
    }
    
    private boolean isWidgetOneCellHigh(final Context context, final int n) {
        return cellFromDp(this.getWidgetHeight(context, n)) <= 1;
    }
    
    private void launchNetflixHome(final Context context, final int n) {
        Log.d("nf_preapp_widgetagent", "launching Netflix Home");
        this.launchNflxAction(context, this.buildNflxUri("home", null), n, PreAppWidgetLogActionData$PreAppWidgetActionName.HOME.getValue());
    }
    
    private void launchNetflixPlay(final Context context, final PVideo pVideo, final int n) {
        if (NetflixService.isInstanceCreated() && PServiceWidgetAgent.mIsNflxServicePlayerInPauseState && StringUtils.safeEquals(pVideo.playableId, PServiceWidgetAgent.mNflxServicePlayableId)) {
            Log.d("nf_preapp_widgetagent", "Resuming paused playback");
            this.resumeNetflixPlay(context, pVideo);
            return;
        }
        PServiceWidgetAgent.mIsNflxServicePlayerInPauseState = false;
        PServiceWidgetAgent.mNflxServicePlayableId = null;
        this.launchNflxAction(context, this.buildNflxUri("play", pVideo), n, PreAppWidgetLogActionData$PreAppWidgetActionName.START_PLAY.getValue());
    }
    
    private void launchNetflixVideoDetails(final Context context, final PVideo pVideo, final int n) {
        Log.d("nf_preapp_widgetagent", "launching Netflix MDP/SDP");
        this.launchNflxAction(context, this.buildNflxUri("view_details", pVideo), n, PreAppWidgetLogActionData$PreAppWidgetActionName.VIEW_TITLE_DETAILS.getValue());
    }
    
    private void launchNflxAction(final Context context, final Uri uri, final int n, final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.addFlags(268468224);
        intent.putExtra("FROM_PREAPP_WIDGET", "NetflixWidget");
        intent.putExtra("widgetId", n);
        intent.putExtra("actionName", s);
        context.startActivity(intent);
    }
    
    private boolean listHasNextVideo(final PDiskData$ListType pDiskData$ListType, final List<PVideo> list, final PVideo pVideo, final PDiskData pDiskData) {
        final int index = list.indexOf(pVideo);
        return index >= 0 && index + 1 < Math.min(list.size(), PServiceABTest.getVideoCountOfListForWidgetExp(pDiskData$ListType, pDiskData));
    }
    
    private void resumeNetflixPlay(final Context context, final PVideo pVideo) {
        final VideoType videoType = pVideo.videoType;
        String s = pVideo.id;
        VideoType episode = videoType;
        if (VideoType.SHOW.equals(pVideo.videoType)) {
            s = s;
            episode = videoType;
            if (StringUtils.isNotEmpty(pVideo.playableId)) {
                episode = VideoType.EPISODE;
                s = pVideo.playableId;
            }
        }
        final Intent coldStartIntent = PlayerActivity.createColdStartIntent(context, s, episode, PlayContext.DFLT_MDX_CONTEXT);
        coldStartIntent.addFlags(268435456);
        context.startActivity(coldStartIntent);
    }
    
    private void sendVideoToWidget(final Context context, final PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        if (pVideo == null) {
            Log.w("nf_preapp_widgetagent", "video == null, unable to notify widget");
            return;
        }
        Log.d("nf_preapp_widgetagent", String.format("Sending video to widget. id:%s, type:%s, list:%s", pVideo.id, pVideo.videoType, pDiskData$ListType));
        this.updateAllWidgetsWithLatestExperience(context, pVideo, pDiskData$ListType, pDiskData);
    }
    
    private void setViewsToDefault(final RemoteViews remoteViews) {
        remoteViews.setTextViewText(2131624544, (CharSequence)"");
        remoteViews.setViewVisibility(2131624541, 8);
        remoteViews.setViewVisibility(2131624554, 8);
        remoteViews.setViewVisibility(2131624561, 8);
        remoteViews.setViewVisibility(2131624543, 8);
        remoteViews.setViewVisibility(2131624555, 8);
        remoteViews.setViewVisibility(2131624562, 8);
        remoteViews.setViewVisibility(2131624550, 8);
        remoteViews.setViewVisibility(2131624556, 8);
        remoteViews.setViewVisibility(2131624563, 8);
    }
    
    private boolean toAlignByHeight(final Context context, int cellFromDp) {
        final int n = this.getWidgetHeight(context, cellFromDp) - 40;
        final float n2 = this.getWidgetWidth(context, cellFromDp);
        final float n3 = n2 / n;
        cellFromDp = cellFromDp(n);
        final boolean b = cellFromDp == 1 || n3 > 4.8f;
        Log.d("nf_preapp_widgetagent", String.format("w-h(%f-%d) %f, heightInCells: %d, alignByHeight: %b", n2, n, n3, cellFromDp, b));
        return b;
    }
    
    private boolean toAlignByWidth(final Context context, int cellFromDp) {
        final int cellFromDp2 = cellFromDp(this.getWidgetHeight(context, cellFromDp) - 40);
        cellFromDp = cellFromDp(this.getWidgetWidth(context, cellFromDp));
        final boolean b = cellFromDp2 > 1 && cellFromDp > 2 && cellFromDp <= cellFromDp2;
        Log.d("nf_preapp_widgetagent", String.format("(%d, %d)(w,h), useMultipleTitles:%b", cellFromDp, cellFromDp2, b));
        return b;
    }
    
    private void updateAllWidgetsWithLatestExperience(final Context context, final PVideo pVideo, final PDiskData$ListType pDiskData$ListType, final PDiskData pDiskData) {
        final int[] appWidgetIds = this.getAppWidgetIds(context);
        final int length = appWidgetIds.length;
        int i = 0;
        PDiskData$ListType pDiskData$ListType2 = pDiskData$ListType;
        PVideo pVideo2 = pVideo;
        while (i < length) {
            final int n = appWidgetIds[i];
            final int layoutId = this.getLayoutId(context, n);
            Log.d("nf_preapp_widgetagent", String.format("(%s), widgetId: %d, layoutId:%d", pDiskData$ListType2, n, layoutId));
            PDiskData$ImageType pDiskData$ImageType = this.getImageType(pVideo2, pDiskData$ListType2, layoutId, 1);
            String s2;
            final String s = s2 = this.getImageUrlOnDisk(pDiskData, pVideo2, pDiskData$ImageType);
            PVideo firstVideo = pVideo2;
            PDiskData$ListType non_MEMBER = pDiskData$ListType2;
            if (StringUtils.isEmpty(s)) {
                Log.e("nf_preapp_widgetagent", String.format("pVideo.id %s in list %s, not present on disk - going to nm list", pVideo2.id, pDiskData$ListType2));
                non_MEMBER = PDiskData$ListType.NON_MEMBER;
                firstVideo = this.getFirstVideo(pDiskData.nonMemberList);
                pDiskData$ImageType = this.getImageType(firstVideo, non_MEMBER, layoutId, 1);
                s2 = this.getImageUrlOnDisk(pDiskData, firstVideo, pDiskData$ImageType);
            }
            if (StringUtils.isEmpty(s2)) {
                Log.e("nf_preapp_widgetagent", "cannot refresh, even nm list is bad");
                break;
            }
            final LinkedList<String> list = new LinkedList<String>();
            final LinkedList<PDiskData$ImageType> list2 = new LinkedList<PDiskData$ImageType>();
            list.add(s2);
            list2.add(pDiskData$ImageType);
            this.decodeBitmapsAndUpdate(n, this.buildRemoteViews(context, firstVideo, non_MEMBER, list, list2, n, layoutId, pDiskData), firstVideo.id, non_MEMBER, list, list2);
            ++i;
            pVideo2 = firstVideo;
            pDiskData$ListType2 = non_MEMBER;
        }
    }
    
    private void updateAllWidgetsWithStaticExperience(final Context context) {
        final int[] appWidgetIds = this.getAppWidgetIds(context);
        for (int length = appWidgetIds.length, i = 0; i < length; ++i) {
            final int n = appWidgetIds[i];
            this.updateWidget(context, this.buildWidgetStaticImageRemoteView(context, n), n, "", PDiskData$ListType.UNKNOWN);
        }
    }
    
    @TargetApi(16)
    private void updateWidget(final Context context, final RemoteViews remoteViews, final int n, final String mVideoIdOnWidget, final PDiskData$ListType mListTypeOnWidget) {
        if (remoteViews == null) {
            Log.w("nf_preapp_widgetagent", "RemoteViews is null, ignore refreshing widget");
            return;
        }
        PServiceWidgetAgent.mVideoIdOnWidget = mVideoIdOnWidget;
        PServiceWidgetAgent.mListTypeOnWidget = mListTypeOnWidget;
        AppWidgetManager.getInstance(context.getApplicationContext()).updateAppWidget(n, remoteViews);
    }
    
    @Override
    protected void doInit() {
        PServiceWidgetAgent.mVideoIdOnWidget = null;
        PServiceWidgetAgent.mListTypeOnWidget = null;
        this.initCompleted(CommonStatus.OK);
    }
    
    public void handlePlayOrDetailsReq(final Context context, final Intent intent, final boolean b) {
        final PServiceAgent$PServiceFetchAgentInterface fetchAgent = this.getFetchAgent();
        if (fetchAgent == null) {
            Log.w("nf_preapp_widgetagent", "Fetch agent is null");
        }
        else {
            final PDiskData diskData = fetchAgent.getDiskData();
            if (diskData == null) {
                Log.w("nf_preapp_widgetagent", "pDiskData null. Ignoring refresh request");
                this.getService().notifyToFetchData();
                return;
            }
            final PDiskData$ListType nameOfListShownInWidget = this.getNameOfListShownInWidget(intent);
            Log.d("nf_preapp_widgetagent", String.format("handlePlayOrDetailsReq play? %b - %s", b, nameOfListShownInWidget));
            final PVideo videoShownInWidget = this.getVideoShownInWidget(nameOfListShownInWidget, intent, diskData);
            if (videoShownInWidget != null) {
                Log.d("nf_preapp_widgetagent", String.format("handlePlayOrDetailsReq play? %b - %s, %s - %s", b, nameOfListShownInWidget, videoShownInWidget.id, videoShownInWidget.title));
                if (b) {
                    this.launchNetflixPlay(context, videoShownInWidget, this.getWidgetId(intent));
                    return;
                }
                this.launchNetflixVideoDetails(context, videoShownInWidget, this.getWidgetId(intent));
            }
        }
    }
    
    public void handlePlayerStateChange(final Intent intent) {
        PServiceWidgetAgent.mIsNflxServicePlayerInPauseState = intent.getBooleanExtra("isPlayerPaused", false);
        PServiceWidgetAgent.mNflxServicePlayableId = intent.getStringExtra("videoId");
        if (Log.isLoggable()) {
            Log.d("nf_preapp_widgetagent", String.format("handlePlayerStateChange mIsNflxServicePlayerInPauseState: %b, mNflxServicePlayableId:%s", PServiceWidgetAgent.mIsNflxServicePlayerInPauseState, PServiceWidgetAgent.mNflxServicePlayableId));
        }
    }
    
    public void handleWidgetHomeReq(final Context context, final Intent intent) {
        this.launchNetflixHome(context, this.getWidgetId(intent));
    }
    
    public void handleWidgetRefreshReq(final Context context, final Intent intent) {
        final PServiceAgent$PServiceFetchAgentInterface fetchAgent = this.getFetchAgent();
        if (fetchAgent == null) {
            Log.w("nf_preapp_widgetagent", "Fetch agent is null");
            return;
        }
        final PDiskData diskData = fetchAgent.getDiskData();
        if (diskData == null || (!PDiskData.isMemberDataAvailable(diskData) && !PDiskData.isNonMemberDataAvailable(diskData))) {
            Log.w("nf_preapp_widgetagent", "pDiskData null. Ignoring refresh request - going to static experience");
            this.updateAllWidgetsWithStaticExperience(context);
            this.getService().notifyToFetchData();
            return;
        }
        PDiskData$ListType pDiskData$ListType = this.getNameOfListShownInWidget(intent);
        final PVideo videoShownInWidget = this.getVideoShownInWidget(pDiskData$ListType, intent, diskData);
        PVideo pVideo;
        if (pDiskData$ListType == null || PDiskData$ListType.UNKNOWN.equals(pDiskData$ListType) || videoShownInWidget == null) {
            Log.w("nf_preapp_widgetagent", "listType or currentVideo is null getting first video");
            PDiskData$ListType pDiskData$ListType3;
            final PDiskData$ListType pDiskData$ListType2 = pDiskData$ListType3 = this.getListTypeToUse(new Intent("com.netflix.mediaclient.intent.action.ALL_MEMBER_UPDATED_FROM_PREAPP_AGENT"), diskData);
            if (!this.hasListGotEnoughVideos(pDiskData$ListType2, diskData)) {
                Log.d("nf_preapp_widgetagent", String.format("list:%s, not enough - going to next", pDiskData$ListType2));
                pDiskData$ListType3 = this.getNextListTypeToUse(pDiskData$ListType2, diskData);
            }
            final PVideo firstVideo = this.getFirstVideo(this.getListByType(pDiskData$ListType3, diskData));
            pDiskData$ListType = pDiskData$ListType3;
            pVideo = firstVideo;
        }
        else {
            final List<PVideo> listByType = this.getListByType(pDiskData$ListType, diskData);
            final int maxNumOfImagesForAllWidgets = this.getMaxNumOfImagesForAllWidgets(context);
            if (listByType != null && !this.hasListGotEnoughVideos(pDiskData$ListType, diskData, listByType.indexOf(videoShownInWidget) + 1, maxNumOfImagesForAllWidgets)) {
                Log.d("nf_preapp_widgetagent", String.format("list:%s, not enough videos  pos:%d, need:%d, (%s-%s)", pDiskData$ListType, listByType.indexOf(videoShownInWidget) + 1, maxNumOfImagesForAllWidgets, videoShownInWidget.id, videoShownInWidget.title));
                pDiskData$ListType = this.getNextListTypeToUse(pDiskData$ListType, diskData);
                pVideo = this.getFirstVideo(this.getListByType(pDiskData$ListType, diskData));
                Log.d("nf_preapp_widgetagent", String.format("jumped to list:%s", pDiskData$ListType));
            }
            else {
                Log.d("nf_preapp_widgetagent", String.format("list:%s, has enough pos:%d, need:%d, (%s-%s)", pDiskData$ListType, listByType.indexOf(videoShownInWidget) + 1, maxNumOfImagesForAllWidgets, videoShownInWidget.id, videoShownInWidget.title));
                pVideo = this.getNextVideo(pDiskData$ListType, videoShownInWidget, diskData);
            }
        }
        PVideo firstVideo2 = pVideo;
        PDiskData$ListType nextList = pDiskData$ListType;
        if (pVideo == null) {
            nextList = this.getNextList(pDiskData$ListType, diskData);
            if ((firstVideo2 = this.getFirstVideo(this.getListByType(nextList, diskData))) == null) {
                Log.e("nf_preapp_widgetagent", "getNextList is not valid - ignoring refresh request");
                return;
            }
        }
        this.updateAllWidgetsWithLatestExperience(context, firstVideo2, nextList, diskData);
    }
    
    public void handleWidgetResizeReq(final Context context, final Intent intent) {
        Log.d("nf_preapp_widgetagent", "handleWidgetResizeReq");
        final PServiceAgent$PServiceFetchAgentInterface fetchAgent = this.getFetchAgent();
        if (fetchAgent == null) {
            Log.w("nf_preapp_widgetagent", "Fetch agent is null");
        }
        else {
            final PDiskData diskData = fetchAgent.getDiskData();
            if (diskData == null || (!PDiskData.isMemberDataAvailable(diskData) && !PDiskData.isNonMemberDataAvailable(diskData))) {
                Log.w("nf_preapp_widgetagent", "pDiskData null. Ignoring refresh request - going to static experience");
                this.updateAllWidgetsWithStaticExperience(context);
                this.getService().notifyToFetchData();
                return;
            }
            final PVideo videoShownInWidget = this.getVideoShownInWidget(PServiceWidgetAgent.mListTypeOnWidget, PServiceWidgetAgent.mVideoIdOnWidget, diskData);
            if (videoShownInWidget != null) {
                this.updateAllWidgetsWithLatestExperience(context, videoShownInWidget, PServiceWidgetAgent.mListTypeOnWidget, diskData);
            }
        }
    }
    
    public void logWidgetRefreshEvent(final Context context, final Intent intent) {
        final int widgetId = this.getWidgetId(intent);
        if (widgetId == PService.INVALID_WIDGET_ID) {
            Log.w("nf_preapp_widgetagent", "cannot log refresh action invalid widgetId");
            return;
        }
        PServiceLogging.storeLogEvent(context, PServiceWidgetLogEvent$WidgetAction.GO_TO_NEXT, widgetId);
    }
    
    @Override
    public void updateWidgetWithLatestData(final Intent intent) {
        final PServiceAgent$PServiceFetchAgentInterface fetchAgent = this.getFetchAgent();
        if (fetchAgent == null) {
            Log.w("nf_preapp_widgetagent", "Fetch agent is null");
        }
        else {
            final PDiskData diskData = fetchAgent.getDiskData();
            if (diskData == null) {
                Log.w("nf_preapp_widgetagent", "mDiskData is null - ignoring request");
                return;
            }
            final PDiskData$ListType listTypeToUse = this.getListTypeToUse(intent, diskData);
            final int maxNumOfImagesForAllWidgets = this.getMaxNumOfImagesForAllWidgets(this.getContext());
            PDiskData$ListType nextListTypeToUse = listTypeToUse;
            if (!this.hasListGotEnoughVideos(listTypeToUse, diskData, 0, maxNumOfImagesForAllWidgets)) {
                Log.d("nf_preapp_widgetagent", String.format("list:%s, not enough need:%d - going to next", listTypeToUse, maxNumOfImagesForAllWidgets));
                nextListTypeToUse = this.getNextListTypeToUse(listTypeToUse, diskData);
            }
            this.sendVideoToWidget(this.getContext(), this.getFirstVideo(this.getListByType(nextListTypeToUse, diskData)), nextListTypeToUse, diskData);
            if ("com.netflix.mediaclient.intent.action.ALL_MEMBER_UPDATED_FROM_PREAPP_AGENT".equals(intent.getAction())) {
                this.getService().notifyToFetchNonMemberData();
            }
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import android.widget.Toast;
import com.google.android.gms.cast.CastMediaControlIntent;
import android.support.v7.media.MediaRouteSelector$Builder;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import org.json.JSONArray;
import java.util.Iterator;
import android.support.v7.media.MediaRouter$ProviderInfo;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.google.android.gms.cast.CastDevice;
import java.util.HashMap;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouteSelector;
import com.netflix.mediaclient.service.mdx.MdxNrdpLogger;
import android.support.v7.media.MediaRouter$RouteInfo;
import java.util.Map;
import android.os.Handler;
import android.content.Context;
import android.support.v7.media.MediaRouter$Callback;

public class CastManager extends MediaRouter$Callback implements MdxCastApplication$MdxCastApplicaCallback
{
    private static final String CAST_SERVICE_PREFIX = "CastMediaRouteProviderService:";
    static final String NETFLIX_NAMESPACE = "urn:x-cast:mdx-netflix-com:service:target:2";
    private static final String NF_APPID = "CA5E8412";
    private static final long RE_DISCOVER_AFTER_PREVIOUS_MS = 16000L;
    private static final String TAG;
    private String mApplicationId;
    private String mCastPrefix;
    private Context mContext;
    private boolean mForceLaunch;
    private Handler mMainHandler;
    private Map<String, MediaRouter$RouteInfo> mMapOfRoutes;
    private MdxNrdpLogger mMdxNrdpLogger;
    private MediaRouteSelector mMediaRouteSelector;
    private MediaRouter mMediaRouter;
    private String mMyUuid;
    private long mPreviousDiscoveryTime;
    private MdxCastApplication mSelectedMdxCastApp;
    private MediaRouter$RouteInfo mSelectedRoute;
    private String mTargetId;
    private Handler mWorkerHandler;
    
    static {
        TAG = CastManager.class.getSimpleName();
    }
    
    public CastManager(final Context mContext, final Handler mMainHandler, final Handler mWorkerHandler, final String mMyUuid, final MdxNrdpLogger mMdxNrdpLogger) {
        this.mApplicationId = "CA5E8412";
        this.mMapOfRoutes = new HashMap<String, MediaRouter$RouteInfo>();
        this.mPreviousDiscoveryTime = 0L;
        if (mMyUuid == null) {
            throw new IllegalArgumentException("ESN can not be null!");
        }
        this.mContext = mContext;
        this.mMainHandler = mMainHandler;
        this.mWorkerHandler = mWorkerHandler;
        this.mMyUuid = mMyUuid;
        this.mMdxNrdpLogger = mMdxNrdpLogger;
        this.mMediaRouter = MediaRouter.getInstance(this.mContext);
        this.nativeInit();
    }
    
    private void castLaunchApplication(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (this.mSelectedMdxCastApp != null) {
            this.mSelectedMdxCastApp.stop();
        }
        if (mediaRouter$RouteInfo == null) {
            this.onFailToLaunch("launch route is null");
            return;
        }
        final CastDevice fromBundle = CastDevice.getFromBundle(mediaRouter$RouteInfo.getExtras());
        if (fromBundle == null) {
            this.onFailToLaunch("launch CastDevice is null");
            return;
        }
        this.mSelectedMdxCastApp = new MdxCastApplication(this.mContext, this.mApplicationId, fromBundle, this, this.mForceLaunch);
    }
    
    private String createCastHandShakeMessage(final String s, final String s2) {
        final JSONObject jsonObject = new JSONObject();
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "createCastHandShakeMessage " + s + ", " + s2);
        }
        try {
            jsonObject.put("type", (Object)"castHandShake").put("uuid", (Object)s).put("controlleruuid", (Object)this.mMyUuid).put("friendlyName", (Object)s2).put("payload", (Object)"intent=sync");
            if (Log.isLoggable()) {
                Log.d(CastManager.TAG, "createCastHandShakeMessage " + jsonObject.toString());
            }
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e(CastManager.TAG, "createCastHandShakeMessage failed, e");
            return null;
        }
    }
    
    private String createCastMessage(final String s) {
        final String reqPath = this.findReqPath(s);
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "@" + reqPath + "createCastMessage " + s);
        }
        if (StringUtils.isEmpty(reqPath)) {
            return "";
        }
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("path", (Object)reqPath).put("body", (Object)s);
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e(CastManager.TAG, "createMessage failed");
            return null;
        }
    }
    
    private String findReqPath(final String s) {
        final int index = s.indexOf("action=");
        if (index >= 0) {
            final int n = "action=".length() + index;
            final int index2 = s.indexOf("\r\n", index);
            if (index2 > n) {
                return s.substring(n, index2);
            }
        }
        return null;
    }
    
    private String getIpAddress(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        final String hostAddress = CastDevice.getFromBundle(mediaRouter$RouteInfo.getExtras()).getIpAddress().getHostAddress();
        String string;
        final String s = string = null;
        if (StringUtils.isNotEmpty(hostAddress)) {
            final int index = hostAddress.indexOf(".");
            string = s;
            if (index > 0) {
                string = "0" + hostAddress.substring(index);
            }
        }
        if (StringUtils.isNotEmpty(string)) {
            return "cast://" + string;
        }
        return "cast://0.1.2.3";
    }
    
    private String getUuid(final String s) {
        return s.substring(s.indexOf("CastMediaRouteProviderService:") + "CastMediaRouteProviderService:".length());
    }
    
    private void handleDeviceFirstSeen(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        this.mMapOfRoutes.put(this.getUuid(mediaRouter$RouteInfo.getId()), mediaRouter$RouteInfo);
        if (this.mTargetId != null && this.mTargetId.equalsIgnoreCase(this.getUuid(mediaRouter$RouteInfo.getId()))) {
            if (!mediaRouter.getSelectedRoute().equals(mediaRouter$RouteInfo)) {
                Log.d(CastManager.TAG, "route added, selectRoute ");
                this.mForceLaunch = false;
                this.mSelectedRoute = mediaRouter$RouteInfo;
                this.mMediaRouter.selectRoute(this.mSelectedRoute);
            }
            else {
                Log.d(CastManager.TAG, "route added and selected, launch app ");
                this.castLaunchApplication(this.mSelectedRoute = mediaRouter$RouteInfo);
            }
        }
        String s;
        if (StringUtils.isEmpty(this.mCastPrefix)) {
            s = mediaRouter$RouteInfo.getName();
        }
        else {
            s = this.mCastPrefix + mediaRouter$RouteInfo.getName();
        }
        this.nativeDeviceFoundWrapper(this.getUuid(mediaRouter$RouteInfo.getId()), this.getIpAddress(mediaRouter$RouteInfo), s);
    }
    
    private void logCastDevice(final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        final CastDevice fromBundle = CastDevice.getFromBundle(mediaRouter$RouteInfo.getExtras());
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "Id: " + fromBundle.getDeviceId());
            Log.d(CastManager.TAG, "Version: " + fromBundle.getDeviceVersion());
            Log.d(CastManager.TAG, "FriendlyName: " + fromBundle.getFriendlyName());
            Log.d(CastManager.TAG, "IpAddress: " + fromBundle.getIpAddress());
            Log.d(CastManager.TAG, "ModelName: " + fromBundle.getModelName());
            Log.d(CastManager.TAG, "ServicePort: " + fromBundle.getServicePort());
        }
    }
    
    private synchronized native void nativeDeviceFound(final String p0, final String p1, final String p2);
    
    private void nativeDeviceFoundWrapper(final String s, final String s2, final String s3) {
        this.mWorkerHandler.post((Runnable)new CastManager$6(this, s, s2, s3));
    }
    
    private synchronized native void nativeDeviceLost(final String p0);
    
    private void nativeDeviceLostWrapper(final String s) {
        this.mWorkerHandler.post((Runnable)new CastManager$7(this, s));
    }
    
    private synchronized native void nativeInit();
    
    private synchronized native void nativeLaunchResult(final boolean p0, final String p1);
    
    private void nativeLaunchResultWrapper(final boolean b, final String s) {
        this.mWorkerHandler.post((Runnable)new CastManager$8(this, b, s));
    }
    
    private synchronized native void nativeMessageReceived(final String p0, final String p1, final String p2);
    
    private void nativeMessageReceivedWrapper(final String s, final String s2, final String s3) {
        this.mWorkerHandler.post((Runnable)new CastManager$10(this, s, s2, s3));
    }
    
    private synchronized native void nativeRelease();
    
    private synchronized native void nativeSendMessageResult(final boolean p0, final String p1);
    
    private void nativeSendMessageResultWrapper(final boolean b, final String s) {
        this.mWorkerHandler.post((Runnable)new CastManager$9(this, b, s));
    }
    
    private void notifySessionend() {
        if (this.mSelectedRoute != null) {
            String s2;
            final String s = s2 = this.getUuid(this.mSelectedRoute.getId());
            if (StringUtils.isNotEmpty(this.mTargetId)) {
                s2 = s;
                if (!this.mTargetId.equalsIgnoreCase(s)) {
                    if (Log.isLoggable()) {
                        Log.d(CastManager.TAG, "targetId " + this.mTargetId + "does not match " + s + ", use targetId");
                    }
                    s2 = this.mTargetId;
                }
            }
            final String string = "action=endCastSession\r\nfromuuid=" + s2 + "\r\n";
            if (Log.isLoggable()) {
                Log.d(CastManager.TAG, "onMessageReceived @session, body:" + string);
            }
            this.nativeMessageReceivedWrapper(string, this.getUuid(this.mSelectedRoute.getId()), "session");
        }
    }
    
    private void sendMessageToBackgroundReceiver(final CastDevice castDevice, final String s) {
        this.mWorkerHandler.post((Runnable)new CastManager$11(this, castDevice, s));
    }
    
    private void startDiscovery() {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "startDiscovery() AppId: " + this.mApplicationId);
        }
        this.mPreviousDiscoveryTime = System.currentTimeMillis();
        this.mMapOfRoutes.clear();
        this.mMediaRouter.addCallback(this.mMediaRouteSelector, this, 1);
        this.mSelectedRoute = this.mMediaRouter.getSelectedRoute();
        if (this.mSelectedRoute != null && this.mSelectedRoute.matchesSelector(this.mMediaRouteSelector)) {
            this.onRouteAdded(this.mMediaRouter, this.mSelectedRoute);
        }
    }
    
    private void stopDiscovery() {
        Log.d(CastManager.TAG, "stopDiscovery");
        this.mPreviousDiscoveryTime = 0L;
        this.mSelectedRoute = null;
        this.mMapOfRoutes.clear();
        if (this.mMediaRouter != null) {
            this.mMediaRouter.unselect(3);
            this.mMediaRouter.removeCallback(this);
        }
        Log.d(CastManager.TAG, "stopDiscovery done");
    }
    
    public void destroy() {
        this.nativeRelease();
    }
    
    public void disconnect() {
        this.mWorkerHandler.post((Runnable)new CastManager$4(this));
    }
    
    public void launchNetflix(final String s) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "launchNetflix " + s);
        }
        this.mMainHandler.post((Runnable)new CastManager$5(this, s));
    }
    
    @Override
    public void onApplicationStopped(String uuid) {
        if (this.mSelectedRoute != null) {
            this.mMdxNrdpLogger.logDebug(this.mSelectedRoute.getName() + ": netflix stopped, " + uuid);
            uuid = this.getUuid(this.mSelectedRoute.getId());
            this.notifySessionend();
            this.mWorkerHandler.postDelayed((Runnable)new CastManager$12(this, uuid), 50L);
        }
    }
    
    @Override
    public void onFailToConnect(final String s) {
        Log.d(CastManager.TAG, "onFailToConnect");
        if (this.mSelectedRoute != null) {
            this.mMdxNrdpLogger.logDebug(this.mSelectedRoute.getName() + ": cannot coonect to netflix, " + s);
        }
        this.notifySessionend();
    }
    
    @Override
    public void onFailToLaunch(final String s) {
        Log.d(CastManager.TAG, "onFailToLaunch");
        if (this.mSelectedRoute != null) {
            this.mMdxNrdpLogger.logDebug(this.mSelectedRoute.getName() + ": cannot launch netflix, " + s);
            this.nativeLaunchResultWrapper(false, this.getUuid(this.mSelectedRoute.getId()));
            return;
        }
        Log.d(CastManager.TAG, "onFailToLaunch, no selected route");
    }
    
    @Override
    public void onFailToSendMessage(final String s) {
        Log.d(CastManager.TAG, "onFailToSendMessage");
        if (this.mSelectedRoute != null) {
            this.mMdxNrdpLogger.logDebug(this.mSelectedRoute.getName() + ": cannot send message, " + s);
            this.nativeSendMessageResultWrapper(false, this.getUuid(this.mSelectedRoute.getId()));
            return;
        }
        Log.d(CastManager.TAG, "onFailToSendMessage, no selected route");
    }
    
    @Override
    public void onLaunched() {
        Log.d(CastManager.TAG, "onLaunched");
        if (this.mSelectedRoute != null) {
            this.sendCastMessage(this.createCastHandShakeMessage(this.getUuid(this.mSelectedRoute.getId()), this.mSelectedRoute.getName()));
            return;
        }
        Log.d(CastManager.TAG, "onLaunched, no selected route");
    }
    
    @Override
    public void onMessageReceived(String s) {
        String optString;
        String optString2;
        try {
            final JSONObject jsonObject = new JSONObject(s);
            optString = jsonObject.optString("body");
            final String s2 = s = jsonObject.optString("url");
            if (s2.indexOf("/") >= 0) {
                s = s2.substring(s2.lastIndexOf("/"));
            }
            optString2 = jsonObject.optString("type");
            if (optString2.equals("castHandShakeAck") && this.mSelectedRoute != null) {
                this.nativeLaunchResultWrapper(true, this.getUuid(this.mSelectedRoute.getId()));
                return;
            }
        }
        catch (JSONException ex) {
            Log.e(CastManager.TAG, "error onMessageReceived ", (Throwable)ex);
            return;
        }
        if (optString2.equals("castHandShakeRequest")) {
            Log.d(CastManager.TAG, "onMessageReceived castHandShakeRequest");
            this.onLaunched();
            return;
        }
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "onMessageReceived @" + s + ", body:" + optString);
        }
        if (this.mSelectedRoute != null) {
            this.nativeMessageReceivedWrapper(optString, this.getUuid(this.mSelectedRoute.getId()), s);
            return;
        }
        Log.d(CastManager.TAG, "onMessageReceived, no selected route");
    }
    
    @Override
    public void onMessageSent() {
        Log.d(CastManager.TAG, "onMessageSent");
        if (this.mSelectedRoute != null) {
            this.nativeSendMessageResultWrapper(true, this.getUuid(this.mSelectedRoute.getId()));
            return;
        }
        Log.d(CastManager.TAG, "onMessageSent, no selected route");
    }
    
    @Override
    public void onProviderAdded(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "onProviderAdded " + mediaRouter + ", provider: " + mediaRouter$ProviderInfo);
        }
    }
    
    @Override
    public void onProviderChanged(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "onProviderChanged " + mediaRouter + ", provider: " + mediaRouter$ProviderInfo);
        }
    }
    
    @Override
    public void onProviderRemoved(final MediaRouter mediaRouter, final MediaRouter$ProviderInfo mediaRouter$ProviderInfo) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "onProviderRemoved " + mediaRouter + ", provider: " + mediaRouter$ProviderInfo);
        }
    }
    
    @Override
    public void onRouteAdded(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "onRouteAdded " + mediaRouter$RouteInfo);
            this.logCastDevice(mediaRouter$RouteInfo);
        }
        if (CastDevice.getFromBundle(mediaRouter$RouteInfo.getExtras()) == null) {
            Log.d(CastManager.TAG, "device is not whitelisted");
            return;
        }
        this.handleDeviceFirstSeen(mediaRouter, mediaRouter$RouteInfo);
    }
    
    @Override
    public void onRouteChanged(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "onRouteChanged " + mediaRouter$RouteInfo);
            this.logCastDevice(mediaRouter$RouteInfo);
        }
        if (CastDevice.getFromBundle(mediaRouter$RouteInfo.getExtras()) == null) {
            Log.d(CastManager.TAG, "device is not whitelisted");
            return;
        }
        if (this.mMapOfRoutes.get(this.getUuid(mediaRouter$RouteInfo.getId())) != null) {
            String s;
            if (StringUtils.isEmpty(this.mCastPrefix)) {
                s = mediaRouter$RouteInfo.getName();
            }
            else {
                s = this.mCastPrefix + mediaRouter$RouteInfo.getName();
            }
            this.nativeDeviceFoundWrapper(this.getUuid(mediaRouter$RouteInfo.getId()), this.getIpAddress(mediaRouter$RouteInfo), s);
            return;
        }
        this.handleDeviceFirstSeen(mediaRouter, mediaRouter$RouteInfo);
    }
    
    @Override
    public void onRouteRemoved(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "onRouteRemoved " + mediaRouter$RouteInfo);
        }
        this.mMapOfRoutes.remove(this.getUuid(mediaRouter$RouteInfo.getId()));
        if (mediaRouter$RouteInfo != null) {
            this.nativeDeviceLostWrapper(this.getUuid(mediaRouter$RouteInfo.getId()));
        }
    }
    
    @Override
    public void onRouteSelected(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "onRouteSelected " + mediaRouter$RouteInfo);
        }
        if (!this.mMediaRouter.getSelectedRoute().equals(this.mSelectedRoute)) {
            if (Log.isLoggable()) {
                Log.d(CastManager.TAG, "mSelectedRoute has changed to " + this.mSelectedRoute);
            }
            this.mSelectedRoute = this.mMediaRouter.getSelectedRoute();
        }
        this.castLaunchApplication(this.mSelectedRoute);
    }
    
    @Override
    public void onRouteUnselected(final MediaRouter mediaRouter, final MediaRouter$RouteInfo mediaRouter$RouteInfo) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "onRouteUnselected " + mediaRouter$RouteInfo);
        }
        if (this.mSelectedMdxCastApp != null) {
            this.mSelectedMdxCastApp.stop();
            this.mSelectedMdxCastApp = null;
        }
        this.mSelectedRoute = null;
    }
    
    public void prefetchVideo(final String s) {
        Log.d(CastManager.TAG, "sending prefetch message, " + s);
        final Iterator<MediaRouter$RouteInfo> iterator = this.mMapOfRoutes.values().iterator();
        while (iterator.hasNext()) {
            final CastDevice fromBundle = CastDevice.getFromBundle(iterator.next().getExtras());
            Log.d(CastManager.TAG, "schedule sending message to cast device, " + fromBundle);
            this.sendMessageToBackgroundReceiver(fromBundle, s);
        }
    }
    
    public void restartCastDiscoveryIfNeeded() {
        Log.d(CastManager.TAG, "restartCastDiscoveryIfNeeded");
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.mPreviousDiscoveryTime > 0L && currentTimeMillis > this.mPreviousDiscoveryTime + 16000L && this.mMediaRouter != null) {
            this.mPreviousDiscoveryTime = currentTimeMillis;
            this.mMediaRouter.removeCallback(this);
            Log.d(CastManager.TAG, "restartCastDiscoveryIfNeeded, restarted");
            this.mMediaRouter.addCallback(this.mMediaRouteSelector, this, 1);
        }
    }
    
    public void sendCastMessage(final String s) {
        if (this.mSelectedMdxCastApp == null) {
            return;
        }
        try {
            this.mSelectedMdxCastApp.sendMessage(s);
        }
        catch (Exception ex) {
            Log.e(CastManager.TAG, "sendCastMessage caught an excpetion " + ex);
        }
    }
    
    public void sendMessage(final String s) {
        this.sendCastMessage(this.createCastMessage(s));
    }
    
    public void setCastWhiteList(final JSONArray jsonArray) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "SPY-8472, ignored. setCastWhiteList: " + jsonArray);
        }
    }
    
    public void setTargetId(final String mTargetId) {
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "setTargetId " + mTargetId);
        }
        this.mTargetId = mTargetId;
    }
    
    public void start() {
        if (StringUtils.isNotEmpty(SettingsConfiguration.getNewCastApplicationId(this.mContext))) {
            this.mApplicationId = SettingsConfiguration.getNewCastApplicationId(this.mContext);
        }
        if (Log.isLoggable()) {
            Log.d(CastManager.TAG, "ApplicationId is: " + this.mApplicationId);
        }
        SettingsConfiguration.setCastApplicationId(this.mContext, this.mApplicationId);
        try {
            this.mMediaRouteSelector = new MediaRouteSelector$Builder().addControlCategory(CastMediaControlIntent.categoryForCast(this.mApplicationId)).build();
            this.mMainHandler.post((Runnable)new CastManager$1(this));
        }
        catch (IllegalArgumentException ex) {
            Log.e(CastManager.TAG, "MediaRouteSelector: " + ex);
            SettingsConfiguration.setCastApplicationId(this.mContext, "==invalid ApplicationId==");
            Toast.makeText(this.mContext, (CharSequence)"Invalid ApplicationId, Enter New One", 1).show();
            throw new IllegalArgumentException("Invalid ApplicationId!");
        }
    }
    
    public void stop() {
        this.mWorkerHandler.post((Runnable)new CastManager$2(this));
        this.mMainHandler.post((Runnable)new CastManager$3(this));
    }
}

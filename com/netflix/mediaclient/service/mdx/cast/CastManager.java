// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.cast;

import com.google.android.gms.cast.CastMediaControlIntent;
import com.netflix.mediaclient.service.configuration.CastConfiguration;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.google.android.gms.cast.CastDevice;
import java.util.ArrayList;
import android.support.v7.media.MediaRouteSelector;
import android.os.Handler;
import java.util.List;
import android.content.Context;
import org.json.JSONArray;
import android.support.v7.media.MediaRouter;

public class CastManager extends Callback implements MdxCastApplicaCallback
{
    private static final String CAST_SERVICE_PREFIX = "CastMediaRouteProviderService:";
    static final String NETFLIX_NAMESPACE = "urn:mdx-netflix-com:service:target:2";
    private static final String NF_APPID = "CA5E8412";
    private static final String TAG;
    private static final String mFakeUrl = "cast://192.168.1.107";
    private String mApplicationId;
    private JSONArray mBlackList;
    private Context mContext;
    private boolean mForceLaunch;
    private List<RouteInfo> mListOfRoutes;
    private Handler mMainHandler;
    private MediaRouteSelector mMediaRouteSelector;
    private MediaRouter mMediaRouter;
    private MdxCastApplication mSelectedMdxCastApp;
    private RouteInfo mSelectedRoute;
    private String mTargetId;
    
    static {
        TAG = CastManager.class.getSimpleName();
    }
    
    public CastManager(final Context mContext, final Handler mMainHandler) {
        this.mApplicationId = "CA5E8412";
        this.mListOfRoutes = new ArrayList<RouteInfo>();
        this.mContext = mContext;
        this.mMainHandler = mMainHandler;
        this.nativeInit();
    }
    
    private void castLaunchApplication(final RouteInfo routeInfo) {
        if (this.mSelectedMdxCastApp != null) {
            this.mSelectedMdxCastApp.stop();
        }
        this.mSelectedMdxCastApp = new MdxCastApplication(this.mContext, this.mApplicationId, CastDevice.getFromBundle(routeInfo.getExtras()), (MdxCastApplication.MdxCastApplicaCallback)this, this.mForceLaunch);
    }
    
    private String createCastHandShakeMessage(final String s, final String s2) {
        final JSONObject jsonObject = new JSONObject();
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "createCastHandShakeMessage " + s + ", " + s2);
        }
        try {
            jsonObject.put("type", (Object)"castHandShake").put("uuid", (Object)s).put("friendlyName", (Object)s2);
            if (Log.isLoggable(CastManager.TAG, 3)) {
                Log.d(CastManager.TAG, "createCastHandShakeMessage " + jsonObject.toString());
            }
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e(CastManager.TAG, "createCastHandShakeMessage failedme");
            return null;
        }
    }
    
    private String createCastMessage(final String s) {
        final String reqPath = this.findReqPath(s);
        if (Log.isLoggable(CastManager.TAG, 3)) {
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
            final int n = index + "action=".length();
            final int index2 = s.indexOf("\r\n", index);
            if (index2 > n) {
                return s.substring(n, index2);
            }
        }
        return null;
    }
    
    private String getUuid(final String s) {
        return s.substring(s.indexOf("CastMediaRouteProviderService:") + "CastMediaRouteProviderService:".length());
    }
    
    private boolean isCastDeviceBlackListed(final CastDevice castDevice) {
        if (this.mBlackList != null) {
            for (int i = 0; i < this.mBlackList.length(); ++i) {
                if (this.mBlackList.opt(i) instanceof JSONObject) {
                    final String optString = ((JSONObject)this.mBlackList.opt(i)).optString("modelName");
                    final String modelName = castDevice.getModelName();
                    if (StringUtils.isNotEmpty(optString) && optString.equalsIgnoreCase(modelName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private void logCastDevice(final RouteInfo routeInfo) {
        final CastDevice fromBundle = CastDevice.getFromBundle(routeInfo.getExtras());
        Log.d(CastManager.TAG, "Id: " + fromBundle.getDeviceId());
        Log.d(CastManager.TAG, "Version: " + fromBundle.getDeviceVersion());
        Log.d(CastManager.TAG, "FriendlyName: " + fromBundle.getFriendlyName());
        Log.d(CastManager.TAG, "IpAddress: " + fromBundle.getIpAddress());
        Log.d(CastManager.TAG, "ModelName: " + fromBundle.getModelName());
        Log.d(CastManager.TAG, "ServicePort: " + fromBundle.getServicePort());
    }
    
    private synchronized native void nativeDeviceFound(final String p0, final String p1, final String p2);
    
    private synchronized native void nativeDeviceLost(final String p0);
    
    private synchronized native void nativeInit();
    
    private synchronized native void nativeLaunchResult(final boolean p0, final String p1);
    
    private synchronized native void nativeMessageReceived(final String p0, final String p1, final String p2);
    
    private synchronized native void nativeRelease();
    
    private synchronized native void nativeSendMessageResult(final boolean p0, final String p1);
    
    private void startDiscovery() {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "startDiscovery() AppId: " + this.mApplicationId);
        }
        this.mListOfRoutes.clear();
        this.mMediaRouter.addCallback(this.mMediaRouteSelector, (MediaRouter.Callback)this, 1);
        this.mSelectedRoute = this.mMediaRouter.getSelectedRoute();
        if (this.mSelectedRoute != null && this.mSelectedRoute.matchesSelector(this.mMediaRouteSelector)) {
            this.onRouteAdded(this.mMediaRouter, this.mSelectedRoute);
        }
    }
    
    private void stopDiscovery() {
        Log.d(CastManager.TAG, "stop()");
        if (this.mSelectedMdxCastApp != null) {
            this.mSelectedMdxCastApp.stop();
            this.mSelectedMdxCastApp = null;
        }
        this.mSelectedRoute = null;
        this.mListOfRoutes.clear();
        if (this.mMediaRouter != null) {
            this.mMediaRouter.removeCallback((MediaRouter.Callback)this);
        }
    }
    
    public void destroy() {
        this.nativeRelease();
    }
    
    public void launchNetflix(final String s) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "launchNetflix " + s);
        }
        this.mMainHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                CastManager.this.mSelectedRoute = null;
                for (final RouteInfo routeInfo : CastManager.this.mListOfRoutes) {
                    if (s.equalsIgnoreCase(CastManager.this.getUuid(routeInfo.getId()))) {
                        CastManager.this.mSelectedRoute = routeInfo;
                        CastManager.this.mTargetId = s;
                    }
                }
                if (CastManager.this.mSelectedRoute == null) {
                    return;
                }
                CastManager.this.mForceLaunch = true;
                if (!CastManager.this.mMediaRouter.getSelectedRoute().equals(CastManager.this.mSelectedRoute)) {
                    CastManager.this.mMediaRouter.selectRoute(CastManager.this.mSelectedRoute);
                    return;
                }
                CastManager.this.castLaunchApplication(CastManager.this.mSelectedRoute);
            }
        });
    }
    
    @Override
    public void onApplicationStopped() {
        if (this.mSelectedRoute != null) {
            final String string = "action=endCastSession\r\nfromuuid=" + this.getUuid(this.mSelectedRoute.getId()) + "\r\n";
            if (Log.isLoggable(CastManager.TAG, 3)) {
                Log.d(CastManager.TAG, "onMessageReceived @session, body:" + string);
            }
            this.nativeMessageReceived(string, this.getUuid(this.mSelectedRoute.getId()), "session");
        }
    }
    
    @Override
    public void onFailToConnect() {
        Log.d(CastManager.TAG, "onFailToConnect");
    }
    
    @Override
    public void onFailToLaunch() {
        Log.d(CastManager.TAG, "onFailToLaunch");
        if (this.mSelectedRoute != null) {
            this.nativeLaunchResult(false, this.getUuid(this.mSelectedRoute.getId()));
        }
        else if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onFailToLaunch, no selected route");
        }
    }
    
    @Override
    public void onFailToSendMessage() {
        Log.d(CastManager.TAG, "onFailToSendMessage");
        if (this.mSelectedRoute != null) {
            this.nativeSendMessageResult(false, this.getUuid(this.mSelectedRoute.getId()));
            return;
        }
        Log.d(CastManager.TAG, "onFailToSendMessage, no selected route");
    }
    
    @Override
    public void onLaunched() {
        Log.d(CastManager.TAG, "onLaunched");
        if (this.mSelectedRoute != null) {
            this.sendCastMessage(this.createCastHandShakeMessage(this.getUuid(this.mSelectedRoute.getId()), this.mSelectedRoute.getName()));
        }
        else if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onLaunched, no selected route");
        }
    }
    
    @Override
    public void onMessageReceived(String s) {
        while (true) {
            String optString;
            try {
                final JSONObject jsonObject = new JSONObject(s);
                optString = jsonObject.optString("body");
                s = jsonObject.optString("url");
                if (s.indexOf("/") >= 0) {
                    s = s.substring(s.lastIndexOf("/"));
                }
                if (jsonObject.optString("type").equals("castHandShakeAck")) {
                    this.nativeLaunchResult(true, this.getUuid(this.mSelectedRoute.getId()));
                    return;
                }
            }
            catch (JSONException ex) {
                Log.e(CastManager.TAG, "error onMessageReceived " + ex);
                return;
            }
            if (Log.isLoggable(CastManager.TAG, 3)) {
                Log.d(CastManager.TAG, "onMessageReceived @" + s + ", body:" + optString);
            }
            if (this.mSelectedRoute != null) {
                this.nativeMessageReceived(optString, this.getUuid(this.mSelectedRoute.getId()), s);
                return;
            }
            if (Log.isLoggable(CastManager.TAG, 3)) {
                Log.d(CastManager.TAG, "onMessageReceived, no selected route");
            }
        }
    }
    
    @Override
    public void onMessageSent() {
        Log.d(CastManager.TAG, "onMessageSent");
        if (this.mSelectedRoute != null) {
            this.nativeSendMessageResult(true, this.getUuid(this.mSelectedRoute.getId()));
            return;
        }
        Log.d(CastManager.TAG, "onMessageSent, no selected route");
    }
    
    @Override
    public void onProviderAdded(final MediaRouter mediaRouter, final ProviderInfo providerInfo) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onProviderAdded " + mediaRouter + ", provider: " + providerInfo);
        }
    }
    
    @Override
    public void onProviderChanged(final MediaRouter mediaRouter, final ProviderInfo providerInfo) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onProviderChanged " + mediaRouter + ", provider: " + providerInfo);
        }
    }
    
    @Override
    public void onProviderRemoved(final MediaRouter mediaRouter, final ProviderInfo providerInfo) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onProviderRemoved " + mediaRouter + ", provider: " + providerInfo);
        }
    }
    
    @Override
    public void onRouteAdded(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onRouteAdded " + routeInfo);
            this.logCastDevice(routeInfo);
        }
        final CastDevice fromBundle = CastDevice.getFromBundle(routeInfo.getExtras());
        if (fromBundle == null || this.isCastDeviceBlackListed(fromBundle)) {
            Log.d(CastManager.TAG, "device is blacklisted");
            return;
        }
        this.mListOfRoutes.add(routeInfo);
        if (this.mTargetId != null && this.mTargetId.equalsIgnoreCase(this.getUuid(routeInfo.getId()))) {
            if (!mediaRouter.getSelectedRoute().equals(routeInfo)) {
                Log.d(CastManager.TAG, "onRouteAdded, selectRoute ");
                this.mForceLaunch = false;
                this.mSelectedRoute = routeInfo;
                this.mMediaRouter.selectRoute(this.mSelectedRoute);
            }
            else {
                this.castLaunchApplication(this.mSelectedRoute = routeInfo);
            }
        }
        this.nativeDeviceFound(this.getUuid(routeInfo.getId()), "cast://192.168.1.107", "CAST:" + routeInfo.getName());
    }
    
    @Override
    public void onRouteChanged(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onRouteChanged " + routeInfo);
        }
    }
    
    @Override
    public void onRouteRemoved(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onRouteRemoved " + routeInfo);
        }
        this.mListOfRoutes.remove(routeInfo);
        if (routeInfo.equals(this.mSelectedRoute)) {
            this.nativeDeviceLost(this.getUuid(routeInfo.getId()));
        }
    }
    
    @Override
    public void onRouteSelected(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onRouteSelected " + routeInfo);
        }
        if (!this.mMediaRouter.getSelectedRoute().equals(this.mSelectedRoute)) {
            if (Log.isLoggable(CastManager.TAG, 3)) {
                Log.d(CastManager.TAG, "XXX: wrong route is selected, suppose to be" + this.mSelectedRoute);
            }
            return;
        }
        this.castLaunchApplication(this.mSelectedRoute);
    }
    
    @Override
    public void onRouteUnselected(final MediaRouter mediaRouter, final RouteInfo routeInfo) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "onRouteUnselected " + routeInfo);
        }
        this.mSelectedMdxCastApp = null;
        this.mSelectedRoute = null;
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
    
    public void setCastBlackList(final JSONArray mBlackList) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "setCastBlackList: " + mBlackList);
        }
        this.mBlackList = mBlackList;
    }
    
    public void setTargetId(final String mTargetId) {
        if (Log.isLoggable(CastManager.TAG, 3)) {
            Log.d(CastManager.TAG, "setTargetId " + mTargetId);
        }
        this.mTargetId = mTargetId;
    }
    
    public void start() {
        if (StringUtils.isNotEmpty(CastConfiguration.getNewCastApplicationId(this.mContext))) {
            this.mApplicationId = CastConfiguration.getNewCastApplicationId(this.mContext);
        }
        CastConfiguration.setCastApplicationId(this.mContext, this.mApplicationId);
        this.mMainHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                new ArrayList<String>().add("urn:mdx-netflix-com:service:target:2");
                CastManager.this.mMediaRouter = MediaRouter.getInstance(CastManager.this.mContext);
                CastManager.this.mMediaRouteSelector = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(CastManager.this.mApplicationId)).build();
                CastManager.this.startDiscovery();
            }
        });
    }
    
    public void stop() {
        this.mMainHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                CastManager.this.stopDiscovery();
            }
        });
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.location;

import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.SystemClock;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import android.os.Build$VERSION;
import com.facebook.react.bridge.Arguments;
import android.location.LocationManager;
import com.facebook.react.modules.core.DeviceEventManagerModule$RCTDeviceEventEmitter;
import com.facebook.react.bridge.WritableMap;
import android.location.Location;
import com.facebook.react.bridge.ReactApplicationContext;
import android.location.LocationListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class LocationModule extends ReactContextBaseJavaModule
{
    private static final float RCT_DEFAULT_LOCATION_ACCURACY = 100.0f;
    private final LocationListener mLocationListener;
    private String mWatchedProvider;
    
    public LocationModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mLocationListener = (LocationListener)new LocationModule$1(this);
    }
    
    private void emitError(final int n, final String s) {
        this.getReactApplicationContext().getJSModule(DeviceEventManagerModule$RCTDeviceEventEmitter.class).emit("geolocationError", PositionError.buildError(n, s));
    }
    
    private static String getValidProvider(final LocationManager locationManager, final boolean b) {
        String s;
        if (b) {
            s = "gps";
        }
        else {
            s = "network";
        }
        String s2 = s;
        if (!locationManager.isProviderEnabled(s)) {
            String s3;
            if (s.equals("gps")) {
                s3 = "network";
            }
            else {
                s3 = "gps";
            }
            s2 = s3;
            if (!locationManager.isProviderEnabled(s3)) {
                s2 = null;
            }
        }
        return s2;
    }
    
    private static WritableMap locationToMap(final Location location) {
        final WritableMap map = Arguments.createMap();
        final WritableMap map2 = Arguments.createMap();
        map2.putDouble("latitude", location.getLatitude());
        map2.putDouble("longitude", location.getLongitude());
        map2.putDouble("altitude", location.getAltitude());
        map2.putDouble("accuracy", location.getAccuracy());
        map2.putDouble("heading", location.getBearing());
        map2.putDouble("speed", location.getSpeed());
        map.putMap("coords", map2);
        map.putDouble("timestamp", location.getTime());
        if (Build$VERSION.SDK_INT >= 18) {
            map.putBoolean("mocked", location.isFromMockProvider());
        }
        return map;
    }
    
    private static void throwLocationPermissionMissing(final SecurityException ex) {
        throw new SecurityException("Looks like the app doesn't have the permission to access location.\nAdd the following line to your app's AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />", ex);
    }
    
    @ReactMethod
    public void getCurrentPosition(final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        final LocationModule$LocationOptions access$300 = fromReactMap(readableMap);
        LocationManager locationManager;
        String validProvider;
        try {
            locationManager = (LocationManager)this.getReactApplicationContext().getSystemService("location");
            validProvider = getValidProvider(locationManager, access$300.highAccuracy);
            if (validProvider == null) {
                callback2.invoke("No available location provider.");
                return;
            }
            final Location lastKnownLocation = locationManager.getLastKnownLocation(validProvider);
            if (lastKnownLocation != null && SystemClock.currentTimeMillis() - lastKnownLocation.getTime() < access$300.maximumAge) {
                callback.invoke(locationToMap(lastKnownLocation));
                return;
            }
        }
        catch (SecurityException ex) {
            throwLocationPermissionMissing(ex);
            return;
        }
        new LocationModule$SingleUpdateRequest(locationManager, validProvider, access$300.timeout, callback, callback2, null).invoke();
    }
    
    @Override
    public String getName() {
        return "LocationObserver";
    }
    
    @ReactMethod
    public void startObserving(final ReadableMap readableMap) {
        if ("gps".equals(this.mWatchedProvider)) {
            return;
        }
        final LocationModule$LocationOptions access$300 = fromReactMap(readableMap);
        LocationManager locationManager;
        String validProvider;
        try {
            locationManager = (LocationManager)this.getReactApplicationContext().getSystemService("location");
            validProvider = getValidProvider(locationManager, access$300.highAccuracy);
            if (validProvider == null) {
                this.emitError(PositionError.PERMISSION_DENIED, "No location provider available.");
                return;
            }
        }
        catch (SecurityException ex) {
            throwLocationPermissionMissing(ex);
            return;
        }
        if (!validProvider.equals(this.mWatchedProvider)) {
            locationManager.removeUpdates(this.mLocationListener);
            locationManager.requestLocationUpdates(validProvider, 1000L, access$300.distanceFilter, this.mLocationListener);
        }
        this.mWatchedProvider = validProvider;
    }
    
    @ReactMethod
    public void stopObserving() {
        ((LocationManager)this.getReactApplicationContext().getSystemService("location")).removeUpdates(this.mLocationListener);
        this.mWatchedProvider = null;
    }
}

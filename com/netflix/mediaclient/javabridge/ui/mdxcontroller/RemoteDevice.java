// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui.mdxcontroller;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import java.net.URLDecoder;

public class RemoteDevice
{
    private static final String ATTR_USN = "USN";
    private static final String ATTR_UUID = "UUID";
    private static final String ATTR_activated = "activated";
    private static final String ATTR_dialUsn = "dialUsn";
    private static final String ATTR_dialUuid = "dialUuid";
    private static final String ATTR_friendlyName = "friendlyName";
    private static final String ATTR_launchStatus = "launchStatus";
    private static final String ATTR_location = "location";
    private static final String ATTR_pairingContext = "pairingContext";
    private static final String ATTR_registrationAcceptance = "registrationAcceptance";
    private static final String ATTR_serviceType = "serviceType";
    private static final String ATTR_usn = "usn";
    private static final String ATTR_uuid = "uuid";
    public boolean activated;
    public String dialUsn;
    public String dialUuid;
    public String friendlyName;
    public int launchStatus;
    public String location;
    public String pairingContext;
    public int registrationAcceptance;
    public String serviceType;
    public String usn;
    public String uuid;
    
    public static String decode(final String s) {
        if (s == null || "".equals(s.trim())) {
            return s;
        }
        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (Throwable t) {
            return s;
        }
    }
    
    public static RemoteDevice toRemoteDevice(final JSONObject jsonObject) {
        final RemoteDevice remoteDevice = new RemoteDevice();
        remoteDevice.usn = JsonUtils.getString(jsonObject, "usn", JsonUtils.getString(jsonObject, "USN", null));
        remoteDevice.dialUsn = JsonUtils.getString(jsonObject, "dialUsn", null);
        remoteDevice.uuid = JsonUtils.getString(jsonObject, "uuid", JsonUtils.getString(jsonObject, "UUID", null));
        remoteDevice.dialUuid = JsonUtils.getString(jsonObject, "dialUuid", null);
        remoteDevice.pairingContext = JsonUtils.getString(jsonObject, "pairingContext", null);
        remoteDevice.activated = JsonUtils.getBoolean(jsonObject, "activated", true);
        remoteDevice.location = JsonUtils.getString(jsonObject, "location", null);
        remoteDevice.friendlyName = JsonUtils.getString(jsonObject, "friendlyName", null);
        remoteDevice.serviceType = JsonUtils.getString(jsonObject, "serviceType", null);
        remoteDevice.registrationAcceptance = JsonUtils.getInt(jsonObject, "registrationAcceptance", 0);
        remoteDevice.launchStatus = JsonUtils.getInt(jsonObject, "launchStatus", 0);
        try {
            if (remoteDevice.friendlyName != null) {
                remoteDevice.friendlyName = URLDecoder.decode(remoteDevice.friendlyName, "UTF-8");
            }
            if (remoteDevice.usn != null) {
                remoteDevice.usn = URLDecoder.decode(remoteDevice.usn, "UTF-8");
            }
            if (remoteDevice.dialUsn != null) {
                remoteDevice.dialUsn = URLDecoder.decode(remoteDevice.dialUsn, "UTF-8");
            }
            if (remoteDevice.uuid != null) {
                remoteDevice.uuid = URLDecoder.decode(remoteDevice.uuid, "UTF-8");
            }
            if (remoteDevice.dialUuid != null) {
                remoteDevice.dialUuid = URLDecoder.decode(remoteDevice.dialUuid, "UTF-8");
            }
            return remoteDevice;
        }
        catch (Exception ex) {
            return remoteDevice;
        }
    }
    
    @Override
    public String toString() {
        return "RemoteDevice [usn=" + this.usn + ", uuid=" + this.uuid + ", location=" + this.location + ", friendlyName=" + this.friendlyName + ", dialUsn=" + this.dialUsn + ", dialUuid=" + this.dialUuid + ", launchStatus=" + this.launchStatus + ", registrationAcceptance=" + this.registrationAcceptance + ", activated=" + this.activated + ", pairingContext=" + this.pairingContext + ", serviceType=" + this.serviceType + "]";
    }
}

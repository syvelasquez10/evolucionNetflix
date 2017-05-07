// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.esn;

import java.util.Locale;
import java.io.Serializable;
import java.util.UUID;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import java.security.NoSuchAlgorithmException;
import com.netflix.mediaclient.util.CryptoUtils;
import android.provider.Settings$Secure;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Build;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.repository.SecurityRepository;

public abstract class BaseEsnProvider implements EsnProvider
{
    protected static final String DELIM;
    protected static final int DEVICE_MODEL_LIMIT = 31;
    protected static final int DEVICE_MODEL_LIMIT_BRAND = 10;
    protected static final String ESN_DELIM;
    public static final String ESN_PREFIX;
    protected static final int MANUFACTURER_LIMIT = 5;
    protected static final int MODEL_LIMIT = 45;
    protected static final String TAG = "ESN";
    protected static final String UKNOWN = "unknown";
    protected static String hashedDeviceId;
    protected static String hashedDeviceId2;
    protected String deviceId;
    protected String esn;
    protected String fesn;
    protected String fesn2;
    protected String fesnModelId;
    protected String modelId;
    protected String nrdpDeviceModel;
    
    static {
        ESN_PREFIX = SecurityRepository.getEsnPrefix();
        ESN_DELIM = SecurityRepository.getEsnDelim();
        DELIM = SecurityRepository.getModelDelim();
    }
    
    public static String buildFesnModelId() {
        return validateChars(findBaseModelId());
    }
    
    public static String findBaseModelId() {
        if (Log.isLoggable()) {
            Log.d("ESN", "Display " + Build.DISPLAY);
            Log.d("ESN", "ID " + Build.ID);
            Log.d("ESN", "MANUFACTURER " + Build.MANUFACTURER);
            Log.d("ESN", "Model " + Build.MODEL);
            Log.d("ESN", "PRODUCT " + Build.PRODUCT);
            Log.d("ESN", "USER " + Build.USER);
            Log.d("ESN", "BOARD " + Build.BOARD);
            Log.d("ESN", "BOOTLOADER " + Build.BOOTLOADER);
            Log.d("ESN", "BRAND " + Build.BRAND);
            Log.d("ESN", "FINGERPRINT " + Build.FINGERPRINT);
            Log.d("ESN", "CPU_ABI " + Build.CPU_ABI);
            Log.d("ESN", "CPU_ABI2 " + Build.CPU_ABI2);
            Log.d("ESN", "HARDWARE " + Build.HARDWARE);
            Log.d("ESN", "TYPE " + Build.TYPE);
            Log.d("ESN", "TAGS " + Build.TAGS);
        }
        final String model = Build.MODEL;
        if (Log.isLoggable()) {
            Log.d("ESN", "Model: " + model);
        }
        String substring = model;
        if (model.length() > 45) {
            final String s = substring = model.substring(0, 45);
            if (Log.isLoggable()) {
                Log.d("ESN", "Model was bigger than: 45. Using first 45 characters: " + s);
                substring = s;
            }
        }
        final String string = getManufactorer() + StringUtils.replaceWhiteSpace(substring, BaseEsnProvider.DELIM) + "S";
        if (Log.isLoggable()) {
            Log.d("ESN", "Model ID: " + string);
        }
        return string;
    }
    
    public static String findDeviceModel() {
        int length = 10;
        final StringBuilder sb = new StringBuilder();
        final String manufacturer = Build.MANUFACTURER;
        final String model = Build.MODEL;
        Log.d("ESN", "BRAND " + manufacturer);
        Log.d("ESN", "MODEL " + model);
        if (manufacturer != null && !"".equals(manufacturer.trim())) {
            if (manufacturer.length() <= 10) {
                length = manufacturer.length();
                sb.append(manufacturer);
            }
            else {
                sb.append(manufacturer.substring(0, 10));
            }
        }
        else {
            sb.append("unknown");
            length = 0;
        }
        sb.append("_");
        if (model != null && !"".equals(model.trim())) {
            final int n = 31 - length;
            if (model.length() <= n) {
                sb.append(model);
            }
            else {
                sb.append(model.substring(0, n));
            }
        }
        else {
            sb.append("unknown");
        }
        return sb.toString();
    }
    
    private static String findFutureDeviceId(final Context context) {
        String s;
        if ((s = getIMEA(context)) == null) {
            s = getMacAddressAndSerial(context);
        }
        String androidId;
        if ((androidId = s) == null) {
            androidId = getAndroidId(context);
        }
        if (androidId == null) {
            Log.w("ESN", "Device ID not found, use and save random id");
            return getRandom(context);
        }
        if ("000000000000000".equalsIgnoreCase(androidId)) {
            Log.w("ESN", "Emulator");
            return "1012UAR71QB0A91";
        }
        return StringUtils.replaceWhiteSpace(androidId, BaseEsnProvider.DELIM);
    }
    
    private static String findFutureDeviceId2(final Context context) {
        String s;
        if ((s = getMacAddressAndSerial(context)) == null) {
            s = getAndroidId(context);
        }
        if (s == null) {
            Log.w("ESN", "Device ID not found, use and save random id");
            return getRandom(context);
        }
        if ("000000000000000".equalsIgnoreCase(s)) {
            Log.w("ESN", "Emulator");
            return "1012UAR71QB0A91";
        }
        return StringUtils.replaceWhiteSpace(s, BaseEsnProvider.DELIM);
    }
    
    protected static String getAndroidId(final Context context) {
        final String string = Settings$Secure.getString(context.getContentResolver(), "android_id");
        if (Log.isLoggable()) {
            Log.d("ESN", "Android ID is " + string);
        }
        return string;
    }
    
    public static String getHashedDeviceId(Context hashedDeviceId) {
        synchronized (BaseEsnProvider.class) {
            if (BaseEsnProvider.hashedDeviceId != null) {
                hashedDeviceId = (Context)BaseEsnProvider.hashedDeviceId;
            }
            else {
                hashedDeviceId = (Context)findFutureDeviceId(hashedDeviceId);
                if (Log.isLoggable()) {
                    Log.d("ESN", "===> Future Device ID: " + (String)hashedDeviceId);
                }
                try {
                    BaseEsnProvider.hashedDeviceId = CryptoUtils.hashSHA256((String)hashedDeviceId, SecurityRepository.getDeviceIdToken());
                    if (Log.isLoggable()) {
                        Log.d("ESN", "===> Hashed Device ID: " + BaseEsnProvider.hashedDeviceId);
                    }
                    hashedDeviceId = (Context)validateChars(BaseEsnProvider.hashedDeviceId);
                }
                catch (NoSuchAlgorithmException ex) {
                    Log.e("ESN", "===> Failed to hash device id. Use plain and report this", ex);
                    BaseEsnProvider.hashedDeviceId = (String)hashedDeviceId;
                }
            }
            return (String)hashedDeviceId;
        }
    }
    
    public static String getHashedDeviceId2(Context hashedDeviceId2) {
        synchronized (BaseEsnProvider.class) {
            if (BaseEsnProvider.hashedDeviceId2 != null) {
                hashedDeviceId2 = (Context)BaseEsnProvider.hashedDeviceId2;
            }
            else {
                hashedDeviceId2 = (Context)findFutureDeviceId2(hashedDeviceId2);
                if (Log.isLoggable()) {
                    Log.d("ESN", "===> Future Device ID2: " + (String)hashedDeviceId2);
                }
                try {
                    BaseEsnProvider.hashedDeviceId2 = CryptoUtils.hashSHA256((String)hashedDeviceId2, SecurityRepository.getDeviceIdToken());
                    if (Log.isLoggable()) {
                        Log.d("ESN", "===> Hashed Device ID2: " + BaseEsnProvider.hashedDeviceId2);
                    }
                    hashedDeviceId2 = (Context)validateChars(BaseEsnProvider.hashedDeviceId2);
                }
                catch (NoSuchAlgorithmException ex) {
                    Log.e("ESN", "===> Failed to hash device id2. Use plain and report this", ex);
                    BaseEsnProvider.hashedDeviceId2 = (String)hashedDeviceId2;
                }
            }
            return (String)hashedDeviceId2;
        }
    }
    
    protected static String getIMEA(final Context context) {
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        String deviceId;
        if (telephonyManager == null) {
            Log.d("ESN", "Device is not a phone");
            deviceId = null;
        }
        else {
            final String s = deviceId = telephonyManager.getDeviceId();
            if (Log.isLoggable()) {
                Log.d("ESN", "IMEA is " + s);
                return s;
            }
        }
        return deviceId;
    }
    
    protected static String getMacAddress(final Context context) {
        return ((WifiManager)context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }
    
    private static String getMacAddressAndSerial(final Context context) {
        final String macAddress = getMacAddress(context);
        final String serial = Build.SERIAL;
        String string;
        if (macAddress == null && serial == null) {
            Log.w("ESN", "Both mac address and SERIAL are null!");
            string = null;
        }
        else if (macAddress == null) {
            string = serial;
            if (Log.isLoggable()) {
                Log.d("ESN", "MAC address is NULL, but SERIAL exist: " + serial);
                return serial;
            }
        }
        else {
            if (serial == null) {
                if (Log.isLoggable()) {
                    Log.d("ESN", "SERIAL is NULL, but MAC address exist: " + macAddress);
                }
                return macAddress;
            }
            final String s = string = macAddress + serial;
            if (Log.isLoggable()) {
                Log.d("ESN", "SERIAL and MAC address both exist, return : " + s);
                return s;
            }
        }
        return string;
    }
    
    protected static String getManufactorer() {
        final String manufacturer = Build.MANUFACTURER;
        if (Log.isLoggable()) {
            Log.d("ESN", "manufacturer: " + manufacturer + "'");
        }
        String string = manufacturer;
        if (manufacturer.length() < 5) {
            string = manufacturer + "       ";
        }
        if (Log.isLoggable()) {
            Log.d("ESN", "manufacturer: " + string + "'");
        }
        final String substring = string.substring(0, 5);
        if (Log.isLoggable()) {
            Log.d("ESN", "manufacturer: " + substring + "'");
        }
        final String replaceWhiteSpace = StringUtils.replaceWhiteSpace(substring, BaseEsnProvider.DELIM, false);
        if (Log.isLoggable()) {
            Log.d("ESN", "manufacturer: " + replaceWhiteSpace + "'");
        }
        return replaceWhiteSpace;
    }
    
    private static String getRandom(final Context context) {
        synchronized (BaseEsnProvider.class) {
            String s;
            if ((s = PreferenceUtils.getStringPref(context, "nf_rnd_device_id", null)) == null) {
                s = UUID.randomUUID().toString();
                PreferenceUtils.putStringPref(context, "nf_rnd_device_id", s);
            }
            return s;
        }
    }
    
    private void init(final Context context) {
        this.modelId = validateChars(this.findModelId());
        Serializable deviceId = this.findDeviceId(context);
        Log.d("ESN", "===> Device ID: " + (String)deviceId);
        Log.d("ESN", "Token: " + SecurityRepository.getDeviceIdToken());
        while (true) {
            try {
                final Serializable hashSHA256 = CryptoUtils.hashSHA256((String)deviceId, SecurityRepository.getDeviceIdToken());
                final String validateChars = validateChars((String)hashSHA256);
                this.deviceId = validateChars((String)deviceId);
                deviceId = new StringBuilder();
                ((StringBuilder)deviceId).append(BaseEsnProvider.ESN_PREFIX);
                ((StringBuilder)deviceId).append(this.modelId).append(BaseEsnProvider.ESN_DELIM).append(validateChars);
                this.esn = ((StringBuilder)deviceId).toString();
                this.nrdpDeviceModel = findDeviceModel();
                if (Log.isLoggable()) {
                    Log.d("ESN", "NRDP device model: '" + this.nrdpDeviceModel + "'");
                }
                if (this.esn != null && this.esn.equals(this.fesn)) {
                    Log.d("ESN", "==> ESN and future ESN are the same!");
                }
            }
            catch (NoSuchAlgorithmException ex) {
                Log.e("ESN", "===> Failed to hash device id. Use plain and report this", ex);
                final Serializable hashSHA256 = deviceId;
                continue;
            }
            break;
        }
    }
    
    private void initFutureEsn(final Context context) {
        this.fesnModelId = validateChars(findBaseModelId());
        BaseEsnProvider.hashedDeviceId = getHashedDeviceId(context);
        final StringBuilder sb = new StringBuilder();
        sb.append(BaseEsnProvider.ESN_PREFIX);
        sb.append(this.fesnModelId).append(BaseEsnProvider.ESN_DELIM).append(BaseEsnProvider.hashedDeviceId);
        this.fesn = sb.toString();
        if (Log.isLoggable()) {
            Log.d("ESN", "===> fESN: " + this.fesn);
        }
    }
    
    private void initFutureEsn2(final Context context) {
        this.fesnModelId = validateChars(findBaseModelId());
        BaseEsnProvider.hashedDeviceId2 = getHashedDeviceId2(context);
        final StringBuilder sb = new StringBuilder();
        sb.append(BaseEsnProvider.ESN_PREFIX);
        sb.append(this.fesnModelId).append(BaseEsnProvider.ESN_DELIM).append(BaseEsnProvider.hashedDeviceId2);
        this.fesn2 = sb.toString();
        if (Log.isLoggable()) {
            Log.d("ESN", "===> fEsn2: " + this.fesn2);
        }
    }
    
    public static String validateChars(String upperCase) {
        if (upperCase == null || "".equals(upperCase.trim())) {
            return "";
        }
        upperCase = upperCase.toUpperCase(Locale.ENGLISH);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < upperCase.length(); ++i) {
            final char char1 = upperCase.charAt(i);
            if ((char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9') || char1 == '-' || char1 == '=') {
                sb.append(char1);
            }
            else {
                sb.append('=');
            }
        }
        return sb.toString();
    }
    
    protected abstract String findDeviceId(final Context p0);
    
    protected abstract String findModelId();
    
    @Override
    public abstract int getCryptoFactoryType();
    
    @Override
    public String getDeviceId() {
        return this.deviceId;
    }
    
    @Override
    public String getDeviceModel() {
        return this.nrdpDeviceModel;
    }
    
    @Override
    public String getESNPrefix() {
        return BaseEsnProvider.ESN_PREFIX;
    }
    
    @Override
    public String getEsn() {
        return this.esn;
    }
    
    @Override
    public String getFesn() {
        return this.fesn;
    }
    
    @Override
    public String getFesn2() {
        return this.fesn2;
    }
    
    @Override
    public String getFesnModelId() {
        return this.fesnModelId;
    }
    
    @Override
    public String getManufacturer() {
        return validateChars(StringUtils.replaceWhiteSpace(Build.MANUFACTURER, BaseEsnProvider.DELIM));
    }
    
    @Override
    public String getModelId() {
        return validateChars(StringUtils.replaceWhiteSpace(Build.MODEL, BaseEsnProvider.DELIM));
    }
    
    void initialize(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        this.init(context);
        this.initFutureEsn(context);
        this.initFutureEsn2(context);
    }
}

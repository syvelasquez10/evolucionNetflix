// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import org.json.JSONException;
import android.view.Window;
import android.graphics.Rect;
import android.app.Activity;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.UUID;
import android.os.Build;
import java.util.Locale;
import java.util.ArrayList;
import android.net.UrlQuerySanitizer$ParameterValuePair;
import android.net.UrlQuerySanitizer;
import java.util.HashMap;
import android.content.pm.PackageManager;
import java.util.Arrays;
import org.json.JSONObject;
import java.util.Map;
import android.os.Bundle;
import android.os.Build$VERSION;
import android.webkit.WebView;
import java.net.HttpURLConnection;
import java.util.List;
import android.webkit.WebSettings;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.Collection;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.util.TimeZone;
import android.text.TextUtils;
import android.net.Uri;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

final class gj$a extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            gj.wn = true;
        }
        else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            gj.wn = false;
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import java.io.OutputStream;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.BufferedOutputStream;
import java.net.URLDecoder;
import java.net.URL;
import java.net.HttpURLConnection;
import com.facebook.internal.Utility;
import java.net.URLEncoder;
import java.util.Iterator;
import android.os.Bundle;

public final class Util
{
    @Deprecated
    public static String encodePostBody(final Bundle bundle, final String s) {
        if (bundle == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (final String s2 : bundle.keySet()) {
            final Object value = bundle.get(s2);
            if (value instanceof String) {
                sb.append("Content-Disposition: form-data; name=\"" + s2 + "\"\r\n\r\n" + (String)value);
                sb.append("\r\n--" + s + "\r\n");
            }
        }
        return sb.toString();
    }
    
    @Deprecated
    public static String encodeUrl(final Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> iterator = (Iterator<String>)bundle.keySet().iterator();
        int n = 1;
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (bundle.get(s) instanceof String) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(s) + "=" + URLEncoder.encode(bundle.getString(s)));
            }
        }
        return sb.toString();
    }
    
    @Deprecated
    public static String openUrl(String o, String read, final Bundle bundle) {
        Object string = o;
        if (read.equals("GET")) {
            string = (String)o + "?" + encodeUrl(bundle);
        }
        Utility.logd("Facebook-Util", read + " URL: " + (String)string);
        o = new URL((String)string).openConnection();
        ((URLConnection)o).setRequestProperty("User-Agent", System.getProperties().getProperty("http.agent") + " FacebookAndroidSDK");
        if (!read.equals("GET")) {
            final Bundle bundle2 = new Bundle();
            for (final String s : bundle.keySet()) {
                final Object value = bundle.get(s);
                if (value instanceof byte[]) {
                    bundle2.putByteArray(s, (byte[])value);
                }
            }
            if (!bundle.containsKey("method")) {
                bundle.putString("method", read);
            }
            if (bundle.containsKey("access_token")) {
                bundle.putString("access_token", URLDecoder.decode(bundle.getString("access_token")));
            }
            ((HttpURLConnection)o).setRequestMethod("POST");
            ((URLConnection)o).setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
            ((URLConnection)o).setDoOutput(true);
            ((URLConnection)o).setDoInput(true);
            ((URLConnection)o).setRequestProperty("Connection", "Keep-Alive");
            ((URLConnection)o).connect();
            read = (String)new BufferedOutputStream(((URLConnection)o).getOutputStream());
            try {
                ((OutputStream)read).write(("--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
                ((OutputStream)read).write(encodePostBody(bundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
                ((OutputStream)read).write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
                if (!bundle2.isEmpty()) {
                    for (final String s2 : bundle2.keySet()) {
                        ((OutputStream)read).write(("Content-Disposition: form-data; filename=\"" + s2 + "\"" + "\r\n").getBytes());
                        ((OutputStream)read).write(("Content-Type: content/unknown" + "\r\n" + "\r\n").getBytes());
                        ((OutputStream)read).write(bundle2.getByteArray(s2));
                        ((OutputStream)read).write(("\r\n" + "--" + "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" + "\r\n").getBytes());
                    }
                }
            }
            finally {
                ((OutputStream)read).close();
            }
            ((OutputStream)read).flush();
            ((OutputStream)read).close();
        }
        try {
            read = read(((URLConnection)o).getInputStream());
            return read;
        }
        catch (FileNotFoundException ex) {
            return read(((HttpURLConnection)o).getErrorStream());
        }
    }
    
    @Deprecated
    private static String read(final InputStream inputStream) {
        final StringBuilder sb = new StringBuilder();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1000);
        for (String s = bufferedReader.readLine(); s != null; s = bufferedReader.readLine()) {
            sb.append(s);
        }
        inputStream.close();
        return sb.toString();
    }
}

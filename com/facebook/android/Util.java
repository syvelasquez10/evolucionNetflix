// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import java.io.OutputStream;
import java.net.URLConnection;
import android.app.AlertDialog$Builder;
import android.content.Context;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.MalformedURLException;
import org.json.JSONObject;
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
    private static final String UTF8 = "UTF-8";
    
    @Deprecated
    public static Bundle decodeUrl(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: new             Landroid/os/Bundle;
        //     5: dup            
        //     6: invokespecial   android/os/Bundle.<init>:()V
        //     9: astore_3       
        //    10: aload_0        
        //    11: ifnull          101
        //    14: aload_0        
        //    15: ldc             "&"
        //    17: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    20: astore_0       
        //    21: aload_0        
        //    22: arraylength    
        //    23: istore_2       
        //    24: iload_1        
        //    25: iload_2        
        //    26: if_icmpge       101
        //    29: aload_0        
        //    30: iload_1        
        //    31: aaload         
        //    32: ldc             "="
        //    34: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    37: astore          4
        //    39: aload           4
        //    41: arraylength    
        //    42: iconst_2       
        //    43: if_icmpne       71
        //    46: aload_3        
        //    47: aload           4
        //    49: iconst_0       
        //    50: aaload         
        //    51: ldc             "UTF-8"
        //    53: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    56: aload           4
        //    58: iconst_1       
        //    59: aaload         
        //    60: ldc             "UTF-8"
        //    62: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    65: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    68: goto            103
        //    71: aload           4
        //    73: arraylength    
        //    74: iconst_1       
        //    75: if_icmpne       103
        //    78: aload_3        
        //    79: aload           4
        //    81: iconst_0       
        //    82: aaload         
        //    83: ldc             "UTF-8"
        //    85: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    88: ldc             ""
        //    90: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    93: goto            103
        //    96: astore          4
        //    98: goto            103
        //   101: aload_3        
        //   102: areturn        
        //   103: iload_1        
        //   104: iconst_1       
        //   105: iadd           
        //   106: istore_1       
        //   107: goto            24
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  39     68     96     101    Ljava/io/UnsupportedEncodingException;
        //  71     93     96     101    Ljava/io/UnsupportedEncodingException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
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
    public static JSONObject parseJson(final String s) {
        if (s.equals("false")) {
            throw new FacebookError("request failed");
        }
        String s2 = s;
        if (s.equals("true")) {
            s2 = "{value : true}";
        }
        final JSONObject jsonObject = new JSONObject(s2);
        if (jsonObject.has("error")) {
            final JSONObject jsonObject2 = jsonObject.getJSONObject("error");
            throw new FacebookError(jsonObject2.getString("message"), jsonObject2.getString("type"), 0);
        }
        if (jsonObject.has("error_code") && jsonObject.has("error_msg")) {
            throw new FacebookError(jsonObject.getString("error_msg"), "", Integer.parseInt(jsonObject.getString("error_code")));
        }
        if (jsonObject.has("error_code")) {
            throw new FacebookError("request failed", "", Integer.parseInt(jsonObject.getString("error_code")));
        }
        if (jsonObject.has("error_msg")) {
            throw new FacebookError(jsonObject.getString("error_msg"));
        }
        if (jsonObject.has("error_reason")) {
            throw new FacebookError(jsonObject.getString("error_reason"));
        }
        return jsonObject;
    }
    
    @Deprecated
    public static Bundle parseUrl(String replace) {
        replace = replace.replace("fbconnect", "http");
        try {
            final URL url = new URL(replace);
            final Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        }
        catch (MalformedURLException ex) {
            return new Bundle();
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
    
    @Deprecated
    public static void showAlert(final Context context, final String title, final String message) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
        alertDialog$Builder.setTitle((CharSequence)title);
        alertDialog$Builder.setMessage((CharSequence)message);
        alertDialog$Builder.create().show();
    }
}

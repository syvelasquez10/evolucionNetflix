// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.android.gms.common.images.WebImage;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import android.text.TextUtils;
import java.util.Calendar;

public class dp
{
    private static final dk lA;
    private static final String[] mx;
    private static final String my;
    
    static {
        lA = new dk("MetadataUtils");
        mx = new String[] { "Z", "+hh", "+hhmm", "+hh:mm" };
        my = "yyyyMMdd'T'HHmmss" + dp.mx[0];
    }
    
    public static Calendar G(String my) {
        if (TextUtils.isEmpty((CharSequence)my)) {
            dp.lA.b("Input string is empty or null", new Object[0]);
            return null;
        }
        final String h = H(my);
        if (TextUtils.isEmpty((CharSequence)h)) {
            dp.lA.b("Invalid date format", new Object[0]);
            return null;
        }
        final String i = I(my);
        my = "yyyyMMdd";
        String string = h;
        Label_0127: {
            if (!TextUtils.isEmpty((CharSequence)i)) {
                string = h + "T" + i;
                if (i.length() != "HHmmss".length()) {
                    break Label_0127;
                }
                my = "yyyyMMdd'T'HHmmss";
            }
            while (true) {
                final Calendar instance = Calendar.getInstance();
                try {
                    instance.setTime(new SimpleDateFormat(my).parse(string));
                    return instance;
                    my = dp.my;
                }
                catch (ParseException ex) {
                    dp.lA.b("Error parsing string: %s", ex.getMessage());
                    return null;
                }
            }
        }
    }
    
    private static String H(String substring) {
        if (TextUtils.isEmpty((CharSequence)substring)) {
            dp.lA.b("Input string is empty or null", new Object[0]);
            return null;
        }
        try {
            substring = substring.substring(0, "yyyyMMdd".length());
            return substring;
        }
        catch (IndexOutOfBoundsException ex) {
            dp.lA.c("Error extracting the date: %s", ex.getMessage());
            return null;
        }
    }
    
    private static String I(String substring) {
        if (TextUtils.isEmpty((CharSequence)substring)) {
            dp.lA.b("string is empty or null", new Object[0]);
        }
        else {
            final int index = substring.indexOf(84);
            if (index != "yyyyMMdd".length()) {
                dp.lA.b("T delimeter is not found", new Object[0]);
                return null;
            }
            try {
                substring = substring.substring(index + 1);
                if (substring.length() == "HHmmss".length()) {
                    return substring;
                }
            }
            catch (IndexOutOfBoundsException ex) {
                dp.lA.b("Error extracting the time substring: %s", ex.getMessage());
                return null;
            }
            switch (substring.charAt("HHmmss".length())) {
                default: {
                    return null;
                }
                case '+':
                case '-': {
                    if (J(substring)) {
                        return substring.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
                    }
                    break;
                }
                case 'Z': {
                    if (substring.length() == "HHmmss".length() + dp.mx[0].length()) {
                        return substring.substring(0, substring.length() - 1) + "+0000";
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    private static boolean J(final String s) {
        final int length = s.length();
        final int length2 = "HHmmss".length();
        return length == dp.mx[1].length() + length2 || length == dp.mx[2].length() + length2 || length == length2 + dp.mx[3].length();
    }
    
    public static String a(final Calendar calendar) {
        String format;
        if (calendar == null) {
            dp.lA.b("Calendar object cannot be null", new Object[0]);
            format = null;
        }
        else {
            String my;
            final String s = my = dp.my;
            if (calendar.get(11) == 0) {
                my = s;
                if (calendar.get(12) == 0) {
                    my = s;
                    if (calendar.get(13) == 0) {
                        my = "yyyyMMdd";
                    }
                }
            }
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(my);
            simpleDateFormat.setTimeZone(calendar.getTimeZone());
            final String s2 = format = simpleDateFormat.format(calendar.getTime());
            if (s2.endsWith("+0000")) {
                return s2.replace("+0000", dp.mx[0]);
            }
        }
        return format;
    }
    
    public static void a(final List<WebImage> p0, final JSONObject p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface java/util/List.clear:()V
        //     6: aload_1        
        //     7: ldc             "images"
        //     9: invokevirtual   org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //    12: astore_1       
        //    13: aload_1        
        //    14: invokevirtual   org/json/JSONArray.length:()I
        //    17: istore_3       
        //    18: iconst_0       
        //    19: istore_2       
        //    20: iload_2        
        //    21: iload_3        
        //    22: if_icmpge       56
        //    25: aload_1        
        //    26: iload_2        
        //    27: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //    30: astore          4
        //    32: aload_0        
        //    33: new             Lcom/google/android/gms/common/images/WebImage;
        //    36: dup            
        //    37: aload           4
        //    39: invokespecial   com/google/android/gms/common/images/WebImage.<init>:(Lorg/json/JSONObject;)V
        //    42: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    47: pop            
        //    48: iload_2        
        //    49: iconst_1       
        //    50: iadd           
        //    51: istore_2       
        //    52: goto            20
        //    55: astore_0       
        //    56: return         
        //    57: astore          4
        //    59: goto            48
        //    Signature:
        //  (Ljava/util/List<Lcom/google/android/gms/common/images/WebImage;>;Lorg/json/JSONObject;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     55     56     Lorg/json/JSONException;
        //  25     32     55     56     Lorg/json/JSONException;
        //  32     48     57     62     Ljava/lang/IllegalArgumentException;
        //  32     48     55     56     Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public static void a(final JSONObject jsonObject, final List<WebImage> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        final JSONArray jsonArray = new JSONArray();
        final Iterator<WebImage> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.put((Object)iterator.next().aP());
        }
        try {
            jsonObject.put("images", (Object)jsonArray);
        }
        catch (JSONException ex) {}
    }
}

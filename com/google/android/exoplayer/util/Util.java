// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.util.Locale;
import java.util.List;
import java.util.regex.Matcher;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.text.ParseException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.lang.reflect.Method;
import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import android.net.Uri;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.exoplayer.upstream.DataSpec;
import java.io.Serializable;
import android.view.WindowManager;
import android.util.Log;
import android.text.TextUtils;
import android.content.Context;
import android.view.Display$Mode;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.view.Display;
import java.util.Arrays;
import android.os.Build;
import android.os.Build$VERSION;
import java.util.regex.Pattern;

public final class Util
{
    private static final int[] CRC32_BYTES_MSBF;
    public static final String DEVICE;
    private static final Pattern ESCAPED_CHARACTER_PATTERN;
    public static final String MANUFACTURER;
    public static final String MODEL;
    public static final int SDK_INT;
    private static final Pattern XS_DATE_TIME_PATTERN;
    private static final Pattern XS_DURATION_PATTERN;
    
    static {
        int sdk_INT;
        if (Build$VERSION.SDK_INT == 23 && Build$VERSION.CODENAME.charAt(0) == 'N') {
            sdk_INT = 24;
        }
        else {
            sdk_INT = Build$VERSION.SDK_INT;
        }
        SDK_INT = sdk_INT;
        DEVICE = Build.DEVICE;
        MANUFACTURER = Build.MANUFACTURER;
        MODEL = Build.MODEL;
        XS_DATE_TIME_PATTERN = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)(\\.(\\d+))?([Zz]|((\\+|\\-)(\\d\\d):(\\d\\d)))?");
        XS_DURATION_PATTERN = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
        ESCAPED_CHARACTER_PATTERN = Pattern.compile("%([A-Fa-f0-9]{2})");
        CRC32_BYTES_MSBF = new int[] { 0, 79764919, 159529838, 222504665, 319059676, 398814059, 445009330, 507990021, 638119352, 583659535, 797628118, 726387553, 890018660, 835552979, 1015980042, 944750013, 1276238704, 1221641927, 1167319070, 1095957929, 1595256236, 1540665371, 1452775106, 1381403509, 1780037320, 1859660671, 1671105958, 1733955601, 2031960084, 2111593891, 1889500026, 1952343757, -1742489888, -1662866601, -1851683442, -1788833735, -1960329156, -1880695413, -2103051438, -2040207643, -1104454824, -1159051537, -1213636554, -1284997759, -1389417084, -1444007885, -1532160278, -1603531939, -734892656, -789352409, -575645954, -646886583, -952755380, -1007220997, -827056094, -898286187, -231047128, -151282273, -71779514, -8804623, -515967244, -436212925, -390279782, -327299027, 881225847, 809987520, 1023691545, 969234094, 662832811, 591600412, 771767749, 717299826, 311336399, 374308984, 453813921, 533576470, 25881363, 88864420, 134795389, 214552010, 2023205639, 2086057648, 1897238633, 1976864222, 1804852699, 1867694188, 1645340341, 1724971778, 1587496639, 1516133128, 1461550545, 1406951526, 1302016099, 1230646740, 1142491917, 1087903418, -1398421865, -1469785312, -1524105735, -1578704818, -1079922613, -1151291908, -1239184603, -1293773166, -1968362705, -1905510760, -2094067647, -2014441994, -1716953613, -1654112188, -1876203875, -1796572374, -525066777, -462094256, -382327159, -302564546, -206542021, -143559028, -97365931, -17609246, -960696225, -1031934488, -817968335, -872425850, -709327229, -780559564, -600130067, -654598054, 1762451694, 1842216281, 1619975040, 1682949687, 2047383090, 2127137669, 1938468188, 2001449195, 1325665622, 1271206113, 1183200824, 1111960463, 1543535498, 1489069629, 1434599652, 1363369299, 622672798, 568075817, 748617968, 677256519, 907627842, 853037301, 1067152940, 995781531, 51762726, 131386257, 177728840, 240578815, 269590778, 349224269, 429104020, 491947555, -248556018, -168932423, -122852000, -60002089, -500490030, -420856475, -341238852, -278395381, -685261898, -739858943, -559578920, -630940305, -1004286614, -1058877219, -845023740, -916395085, -1119974018, -1174433591, -1262701040, -1333941337, -1371866206, -1426332139, -1481064244, -1552294533, -1690935098, -1611170447, -1833673816, -1770699233, -2009983462, -1930228819, -2119160460, -2056179517, 1569362073, 1498123566, 1409854455, 1355396672, 1317987909, 1246755826, 1192025387, 1137557660, 2072149281, 2135122070, 1912620623, 1992383480, 1753615357, 1816598090, 1627664531, 1707420964, 295390185, 358241886, 404320391, 483945776, 43990325, 106832002, 186451547, 266083308, 932423249, 861060070, 1041341759, 986742920, 613929101, 542559546, 756411363, 701822548, -978770311, -1050133554, -869589737, -924188512, -693284699, -764654318, -550540341, -605129092, -475935807, -413084042, -366743377, -287118056, -257573603, -194731862, -114850189, -35218492, -1984365303, -1921392450, -2143631769, -2063868976, -1698919467, -1635936670, -1824608069, -1744851700, -1347415887, -1418654458, -1506661409, -1561119128, -1129027987, -1200260134, -1254728445, -1309196108 };
    }
    
    public static boolean areEqual(final Object o, final Object o2) {
        if (o == null) {
            return o2 == null;
        }
        return o.equals(o2);
    }
    
    public static int binarySearchFloor(final long[] array, final long n, final boolean b, final boolean b2) {
        final int binarySearch = Arrays.binarySearch(array, n);
        int n2;
        if (binarySearch < 0) {
            n2 = -(binarySearch + 2);
        }
        else {
            n2 = binarySearch;
            if (!b) {
                n2 = binarySearch - 1;
            }
        }
        int max = n2;
        if (b2) {
            max = Math.max(0, n2);
        }
        return max;
    }
    
    public static int ceilDivide(final int n, final int n2) {
        return (n + n2 - 1) / n2;
    }
    
    public static long ceilDivide(final long n, final long n2) {
        return (n + n2 - 1L) / n2;
    }
    
    public static boolean contains(final Object[] array, final Object o) {
        final boolean b = false;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= array.length) {
                break;
            }
            if (areEqual(array[n], o)) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    public static int[] firstIntegersArray(final int n) {
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = i;
        }
        return array;
    }
    
    public static int getBottomInt(final long n) {
        return (int)n;
    }
    
    public static byte[] getBytesFromHexString(final String s) {
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < array.length; ++i) {
            final int n = i * 2;
            array[i] = (byte)(Character.digit(s.charAt(n + 1), 16) + (Character.digit(s.charAt(n), 16) << 4));
        }
        return array;
    }
    
    @TargetApi(16)
    private static void getDisplaySizeV16(final Display display, final Point point) {
        display.getSize(point);
    }
    
    @TargetApi(17)
    private static void getDisplaySizeV17(final Display display, final Point point) {
        display.getRealSize(point);
    }
    
    @TargetApi(23)
    private static void getDisplaySizeV23(final Display display, final Point point) {
        final Display$Mode mode = display.getMode();
        point.x = mode.getPhysicalWidth();
        point.y = mode.getPhysicalHeight();
    }
    
    private static void getDisplaySizeV9(final Display display, final Point point) {
        point.x = display.getWidth();
        point.y = display.getHeight();
    }
    
    public static int getIntegerCodeForString(final String s) {
        int i = 0;
        final int length = s.length();
        Assertions.checkArgument(length <= 4);
        int n = 0;
        while (i < length) {
            n = (n << 8 | s.charAt(i));
            ++i;
        }
        return n;
    }
    
    public static long getLong(final int n, final int n2) {
        return n << 32 | (n2 & 0xFFFFFFFFL);
    }
    
    public static int getPcmEncoding(final int n) {
        switch (n) {
            default: {
                return 0;
            }
            case 8: {
                return 3;
            }
            case 16: {
                return 2;
            }
            case 24: {
                return Integer.MIN_VALUE;
            }
            case 32: {
                return 1073741824;
            }
        }
    }
    
    public static Point getPhysicalDisplaySize(final Context context) {
        Label_0246: {
            if (Util.SDK_INT < 25) {
                if ("Sony".equals(Util.MANUFACTURER) && Util.MODEL != null && Util.MODEL.startsWith("BRAVIA") && context.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd")) {
                    return new Point(3840, 2160);
                }
                if ("NVIDIA".equals(Util.MANUFACTURER) && Util.MODEL != null && Util.MODEL.contains("SHIELD")) {
                    Serializable s = null;
                    Label_0219: {
                        while (true) {
                            try {
                                final Class<?> forName = Class.forName("android.os.SystemProperties");
                                s = (String)forName.getMethod("get", String.class).invoke(forName, "sys.display-size");
                                if (TextUtils.isEmpty((CharSequence)s)) {
                                    break Label_0246;
                                }
                                final Serializable s2 = s;
                                final String s3 = ((String)s2).trim();
                                final String s4 = "x";
                                final String[] array = s3.split(s4);
                                final String[] array3;
                                final String[] array2 = array3 = array;
                                final int n = array3.length;
                                final int n2 = 2;
                                if (n != n2) {
                                    break Label_0219;
                                }
                                final String[] array4 = array2;
                                final int n3 = 0;
                                final String s5 = array4[n3];
                                final int n4 = Integer.parseInt(s5);
                                final String[] array5 = array2;
                                final int n5 = 1;
                                final String s6 = array5[n5];
                                final int n6 = Integer.parseInt(s6);
                                final int n7 = n4;
                                if (n7 <= 0) {
                                    break Label_0219;
                                }
                                final int n8 = n6;
                                if (n8 > 0) {
                                    final int n9 = n4;
                                    final int n10 = n6;
                                    final Point point = new Point(n9, n10);
                                    return point;
                                }
                                break Label_0219;
                            }
                            catch (Exception s) {
                                Log.e("Util", "Failed to read sys.display-size", (Throwable)s);
                                s = null;
                                continue;
                            }
                            break;
                        }
                        try {
                            final Serializable s2 = s;
                            final String s3 = ((String)s2).trim();
                            final String s4 = "x";
                            final String[] array = s3.split(s4);
                            final String[] array3;
                            final String[] array2 = array3 = array;
                            final int n = array3.length;
                            final int n2 = 2;
                            if (n == n2) {
                                final String[] array4 = array2;
                                final int n3 = 0;
                                final String s5 = array4[n3];
                                final int n4 = Integer.parseInt(s5);
                                final String[] array5 = array2;
                                final int n5 = 1;
                                final String s6 = array5[n5];
                                final int n6 = Integer.parseInt(s6);
                                final int n7 = n4;
                                if (n7 > 0) {
                                    final int n8 = n6;
                                    if (n8 > 0) {
                                        final int n9 = n4;
                                        final int n10 = n6;
                                        final Point point2;
                                        final Point point = point2 = new Point(n9, n10);
                                        return point2;
                                    }
                                }
                            }
                        }
                        catch (NumberFormatException ex) {}
                    }
                    Log.e("Util", "Invalid sys.display-size: " + (String)s);
                }
            }
        }
        final Display defaultDisplay = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        final Point point3 = new Point();
        if (Util.SDK_INT >= 23) {
            getDisplaySizeV23(defaultDisplay, point3);
            return point3;
        }
        if (Util.SDK_INT >= 17) {
            getDisplaySizeV17(defaultDisplay, point3);
            return point3;
        }
        if (Util.SDK_INT >= 16) {
            getDisplaySizeV16(defaultDisplay, point3);
            return point3;
        }
        getDisplaySizeV9(defaultDisplay, point3);
        return point3;
    }
    
    public static DataSpec getRemainderDataSpec(final DataSpec dataSpec, final int n) {
        long n2 = -1L;
        if (n == 0) {
            return dataSpec;
        }
        if (dataSpec.length != -1L) {
            n2 = dataSpec.length - n;
        }
        return new DataSpec(dataSpec.uri, dataSpec.position + n, n2, dataSpec.key, dataSpec.flags);
    }
    
    public static int getTopInt(final long n) {
        return (int)(n >>> 32);
    }
    
    public static String getUserAgent(final Context context, final String s) {
        try {
            final String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return s + "/" + versionName + " (Linux;Android " + Build$VERSION.RELEASE + ") ExoPlayerLib/" + "1.5.11";
        }
        catch (PackageManager$NameNotFoundException ex) {
            final String versionName = "?";
            return s + "/" + versionName + " (Linux;Android " + Build$VERSION.RELEASE + ") ExoPlayerLib/" + "1.5.11";
        }
    }
    
    public static boolean isLocalFileUri(final Uri uri) {
        final String scheme = uri.getScheme();
        return TextUtils.isEmpty((CharSequence)scheme) || scheme.equals("file");
    }
    
    public static void maybeTerminateInputStream(final HttpURLConnection httpURLConnection, final long n) {
        if (Util.SDK_INT == 19 || Util.SDK_INT == 20) {
            try {
                final InputStream inputStream = httpURLConnection.getInputStream();
                if (n != -1L) {
                    goto Label_0102;
                }
                if (inputStream.read() != -1) {
                    final String name = inputStream.getClass().getName();
                    if (name.equals("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream") || name.equals("com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream")) {
                        final Method declaredMethod = inputStream.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", (Class<?>[])new Class[0]);
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(inputStream, new Object[0]);
                    }
                }
            }
            catch (IOException ex) {}
            catch (Exception ex2) {}
        }
    }
    
    public static ExecutorService newSingleThreadExecutor(final String s) {
        return Executors.newSingleThreadExecutor(new Util$1(s));
    }
    
    public static long parseXsDateTime(final String s) {
        final Matcher matcher = Util.XS_DATE_TIME_PATTERN.matcher(s);
        if (!matcher.matches()) {
            throw new ParseException("Invalid date/time format: " + s, 0);
        }
        int n;
        if (matcher.group(9) == null) {
            n = 0;
        }
        else if (matcher.group(9).equalsIgnoreCase("Z")) {
            n = 0;
        }
        else {
            n = Integer.parseInt(matcher.group(12)) * 60 + Integer.parseInt(matcher.group(13));
            if (matcher.group(11).equals("-")) {
                n *= -1;
            }
        }
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        gregorianCalendar.clear();
        gregorianCalendar.set(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)) - 1, Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
        if (!TextUtils.isEmpty((CharSequence)matcher.group(8))) {
            gregorianCalendar.set(14, new BigDecimal("0." + matcher.group(8)).movePointRight(3).intValue());
        }
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        if (n != 0) {
            timeInMillis -= 60000 * n;
        }
        return timeInMillis;
    }
    
    public static long parseXsDuration(String s) {
        int n = 1;
        double double1 = 0.0;
        final Matcher matcher = Util.XS_DURATION_PATTERN.matcher(s);
        if (!matcher.matches()) {
            return (long)(Double.parseDouble(s) * 3600.0 * 1000.0);
        }
        if (TextUtils.isEmpty((CharSequence)matcher.group(1))) {
            n = 0;
        }
        s = matcher.group(3);
        double n2;
        if (s != null) {
            n2 = Double.parseDouble(s) * 3.1556908E7;
        }
        else {
            n2 = 0.0;
        }
        s = matcher.group(5);
        double n3;
        if (s != null) {
            n3 = Double.parseDouble(s) * 2629739.0;
        }
        else {
            n3 = 0.0;
        }
        s = matcher.group(7);
        double n4;
        if (s != null) {
            n4 = Double.parseDouble(s) * 86400.0;
        }
        else {
            n4 = 0.0;
        }
        s = matcher.group(10);
        double n5;
        if (s != null) {
            n5 = Double.parseDouble(s) * 3600.0;
        }
        else {
            n5 = 0.0;
        }
        s = matcher.group(12);
        double n6;
        if (s != null) {
            n6 = Double.parseDouble(s) * 60.0;
        }
        else {
            n6 = 0.0;
        }
        s = matcher.group(14);
        if (s != null) {
            double1 = Double.parseDouble(s);
        }
        final long n7 = (long)((n6 + (n3 + n2 + n4 + n5) + double1) * 1000.0);
        if (n != 0) {
            return -n7;
        }
        return n7;
    }
    
    public static long scaleLargeTimestamp(final long n, final long n2, final long n3) {
        if (n3 >= n2 && n3 % n2 == 0L) {
            return n / (n3 / n2);
        }
        if (n3 < n2 && n2 % n3 == 0L) {
            return n2 / n3 * n;
        }
        return n2 / n3 * n;
    }
    
    public static int[] toArray(final List<Integer> list) {
        if (list == null) {
            return null;
        }
        final int size = list.size();
        final int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = list.get(i);
        }
        return array;
    }
    
    public static String toLowerInvariant(final String s) {
        if (s == null) {
            return null;
        }
        return s.toLowerCase(Locale.US);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.device.yearclass;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager$MemoryInfo;
import android.content.Context;
import android.os.Build$VERSION;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileFilter;

public class DeviceInfo
{
    private static final FileFilter CPU_FILTER;
    
    static {
        CPU_FILTER = new DeviceInfo$1();
    }
    
    private static int extractValue(final byte[] array, int n) {
        while (n < array.length && array[n] != 10) {
            if (Character.isDigit(array[n])) {
                int n2;
                for (n2 = n + 1; n2 < array.length && Character.isDigit(array[n2]); ++n2) {}
                return Integer.parseInt(new String(array, 0, n, n2 - n));
            }
            ++n;
        }
        return -1;
    }
    
    public static int getCPUMaxFreqKHz() {
        int n = 0;
        int n2 = -1;
        int n3;
        try {
            while (true) {
                int intValue = 0;
                Label_0218: {
                    if (n < getNumberOfCPUCores()) {
                        final File file = new File("/sys/devices/system/cpu/cpu" + n + "/cpufreq/cpuinfo_max_freq");
                        intValue = n2;
                        if (!file.exists()) {
                            break Label_0218;
                        }
                        final byte[] array = new byte[128];
                        final FileInputStream fileInputStream = new FileInputStream(file);
                        try {
                            fileInputStream.read(array);
                            for (intValue = 0; Character.isDigit(array[intValue]) && intValue < array.length; ++intValue) {}
                            final Integer value = Integer.parseInt(new String(array, 0, intValue));
                            if (value > (intValue = n2)) {
                                intValue = value;
                            }
                            break Label_0218;
                        }
                        catch (NumberFormatException ex) {
                            fileInputStream.close();
                            intValue = n2;
                            break Label_0218;
                        }
                        finally {
                            fileInputStream.close();
                        }
                    }
                    if ((n3 = n2) != -1) {
                        break;
                    }
                    final FileInputStream fileInputStream2 = new FileInputStream("/proc/cpuinfo");
                    try {
                        final int n4 = parseFileForValue("cpu MHz", fileInputStream2) * 1000;
                        int n5 = n2;
                        if (n4 > n2) {
                            n5 = n4;
                        }
                        return n5;
                    }
                    finally {
                        fileInputStream2.close();
                    }
                }
                ++n;
                n2 = intValue;
            }
        }
        catch (IOException ex2) {
            n3 = -1;
        }
        return n3;
    }
    
    public static int getNumberOfCPUCores() {
        if (Build$VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            return new File("/sys/devices/system/cpu/").listFiles(DeviceInfo.CPU_FILTER).length;
        }
        catch (NullPointerException ex) {
            return -1;
        }
        catch (SecurityException ex2) {
            return -1;
        }
    }
    
    @TargetApi(16)
    public static long getTotalMemory(Context ex) {
        if (Build$VERSION.SDK_INT >= 16) {
            final ActivityManager$MemoryInfo activityManager$MemoryInfo = new ActivityManager$MemoryInfo();
            ((ActivityManager)((Context)ex).getSystemService("activity")).getMemoryInfo(activityManager$MemoryInfo);
            if (activityManager$MemoryInfo != null) {
                return activityManager$MemoryInfo.totalMem;
            }
            return -1L;
        }
        else {
            try {
                ex = (IOException)new FileInputStream("/proc/meminfo");
                try {
                    final long n = parseFileForValue("MemTotal", (FileInputStream)ex) * 1024L;
                    try {
                        return n;
                    }
                    catch (IOException ex) {
                        return n;
                    }
                }
                finally {
                    ((FileInputStream)ex).close();
                }
            }
            catch (IOException ex2) {
                return -1L;
            }
        }
    }
    
    private static int parseFileForValue(final String s, final FileInputStream fileInputStream) {
        while (true) {
            final byte[] array = new byte[1024];
        Label_0020_Outer:
            while (true) {
                int n4 = 0;
            Label_0129:
                while (true) {
                    int read = 0;
                    int n = 0;
                    try {
                        read = fileInputStream.read(array);
                        n = 0;
                        break Label_0087;
                        final int n3;
                        Label_0053: {
                            return extractValue(array, n3);
                        }
                        // iftrue(Label_0073:, n2 != s.length() - 1)
                        final int n5;
                        n4 = n5;
                        // iftrue(Label_0129:, n3 >= read)
                        final int n2 = n3 - n5;
                        // iftrue(Label_0053:, array[n3] == s.charAt(n2))
                        n4 = n5;
                        break Label_0129;
                        Label_0073: {
                            ++n3;
                        }
                        continue;
                    }
                    catch (NumberFormatException ex) {}
                    catch (IOException ex2) {
                        goto Label_0081;
                    }
                    if (n >= read) {
                        goto Label_0081;
                    }
                    if (array[n] == 10 || (n4 = n) == 0) {
                        int n5 = n;
                        if (array[n] == 10) {
                            n5 = n + 1;
                        }
                        final int n3 = n5;
                        continue;
                    }
                    break;
                }
                int n = n4 + 1;
                continue Label_0020_Outer;
            }
        }
    }
}

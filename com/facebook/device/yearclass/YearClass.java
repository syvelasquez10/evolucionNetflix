// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.device.yearclass;

import java.util.Iterator;
import java.util.ArrayList;
import android.util.Log;
import android.content.Context;

public class YearClass
{
    private static volatile Integer mYearCategory;
    
    private static int categorizeByYear(final Context context) {
        Log.v("YearClass", "getClockSpeedYear(): " + getClockSpeedYear());
        Log.v("YearClass", "getNumCoresYear(): " + getNumCoresYear());
        Log.v("YearClass", "getRamYear(): " + getRamYear(context));
        final ArrayList<Integer> list = new ArrayList<Integer>();
        conditionallyAdd(list, getClockSpeedYear());
        conditionallyAdd(list, getRamYear(context));
        if (list.isEmpty()) {
            conditionallyAdd(list, getNumCoresYear());
        }
        final Iterator<Integer> iterator = list.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final Integer n2 = iterator.next();
            if (n2 > -1) {
                n += n2;
            }
        }
        if (n > 0) {
            return n / list.size();
        }
        return -1;
    }
    
    private static void conditionallyAdd(final ArrayList<Integer> list, final int n) {
        if (n != -1) {
            list.add(n);
        }
    }
    
    public static int get(final Context context) {
        Label_0028: {
            if (YearClass.mYearCategory != null) {
                break Label_0028;
            }
            synchronized (YearClass.class) {
                if (YearClass.mYearCategory == null) {
                    YearClass.mYearCategory = categorizeByYear(context);
                }
                // monitorexit(YearClass.class)
                return YearClass.mYearCategory;
            }
        }
    }
    
    private static int getClockSpeedYear() {
        int n = 2014;
        final long n2 = DeviceInfo.getCPUMaxFreqKHz();
        if (n2 == -1L) {
            n = -1;
        }
        else if (DeviceInfo.getNumberOfCPUCores() < 8) {
            if (n2 <= 528000L) {
                return 2008;
            }
            if (n2 <= 620000L) {
                return 2009;
            }
            if (n2 <= 1020000L) {
                return 2010;
            }
            if (n2 <= 1220000L) {
                return 2011;
            }
            if (n2 <= 1520000L) {
                return 2012;
            }
            if (n2 <= 2020000L) {
                return 2013;
            }
            if (n2 > 2200000L) {
                return 2015;
            }
        }
        else if (n2 > 1520000L) {
            return 2015;
        }
        return n;
    }
    
    private static int getNumCoresYear() {
        final int numberOfCPUCores = DeviceInfo.getNumberOfCPUCores();
        if (numberOfCPUCores < 1) {
            return -1;
        }
        if (numberOfCPUCores == 1) {
            return 2008;
        }
        if (numberOfCPUCores <= 3) {
            return 2011;
        }
        if (numberOfCPUCores <= 4) {
            return 2014;
        }
        return 2015;
    }
    
    private static int getRamYear(final Context context) {
        final long totalMemory = DeviceInfo.getTotalMemory(context);
        if (totalMemory <= 0L) {
            return -1;
        }
        if (totalMemory <= 201326592L) {
            return 2008;
        }
        if (totalMemory <= 304087040L) {
            return 2009;
        }
        if (totalMemory <= 536870912L) {
            return 2010;
        }
        if (totalMemory <= 1073741824L) {
            return 2011;
        }
        if (totalMemory <= 1610612736L) {
            return 2012;
        }
        if (totalMemory <= 2147483648L) {
            return 2013;
        }
        return 2015;
    }
}

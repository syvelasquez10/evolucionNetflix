// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.util.DeviceUtils;
import android.util.Log;
import android.content.Context;
import android.util.SparseIntArray;
import android.util.SparseArray;

public class SearchUtils
{
    public static final String TAG = "nf_log_search";
    private static SearchUtils$TestCell currentTest;
    private static final SparseArray<SparseIntArray> numPeopleColumnsTable;
    private static final SparseArray<SparseIntArray> numRelatedColumnsTable;
    private static final SparseArray<SparseIntArray> numVideoColumnsTable;
    
    static {
        SearchUtils.currentTest = SearchUtils$TestCell.ONE;
        numRelatedColumnsTable = new SparseArray(2);
        numPeopleColumnsTable = new SparseArray(2);
        numVideoColumnsTable = new SparseArray(2);
        initColumnsTable();
    }
    
    private static int computeMaxResultsForPeople(final Context context) {
        int n = 8;
        final int numPeopleGridCols = getNumPeopleGridCols(context);
        int n2 = n;
        if (numPeopleGridCols > 0) {
            int n3 = -1;
            while (true) {
                n2 = n;
                if (n3 == 0) {
                    break;
                }
                n3 = ++n % numPeopleGridCols;
            }
        }
        return n2;
    }
    
    private static int computeMaxResultsForVideos(final Context context) {
        final int n = 21;
        final int numVideoGridCols = getNumVideoGridCols(context);
        int n2 = n;
        if (numVideoGridCols > 0) {
            final int n3 = 21 % numVideoGridCols;
            n2 = n;
            if (n3 != 0) {
                n2 = 21 - n3;
            }
        }
        return n2;
    }
    
    public static int getMaxResultsPeople(final Context context) {
        if (context == null) {
            Log.w("nf_log_search", "getMaxResultsPeople, Context is null");
            return 1;
        }
        final int basicScreenOrientation = DeviceUtils.getBasicScreenOrientation(context);
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory(context);
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return computeMaxResultsForPeople(context);
            }
            case 1: {
                if (basicScreenOrientation == 1 && (screenSizeCategory == 3 || screenSizeCategory == 4)) {
                    return 3;
                }
                return computeMaxResultsForPeople(context);
            }
        }
    }
    
    public static int getMaxResultsRelated(final Context context) {
        if (context == null) {
            Log.w("nf_log_search", "getMaxResultsRelated, Context is null");
            return 1;
        }
        final int basicScreenOrientation = DeviceUtils.getBasicScreenOrientation(context);
        final int screenSizeCategory = DeviceUtils.getScreenSizeCategory(context);
        if (basicScreenOrientation == 1 && (screenSizeCategory == 3 || screenSizeCategory == 4)) {
            return 3;
        }
        return 40;
    }
    
    public static int getMaxResultsVideos(final Context context) {
        return computeMaxResultsForVideos(context);
    }
    
    public static int getNumPeopleGridCols(final Context context) {
        if (context == null) {
            Log.w("nf_log_search", "getNumPeopleGridCols, Context is null");
            return 2;
        }
        return ((SparseIntArray)SearchUtils.numPeopleColumnsTable.get(DeviceUtils.getBasicScreenOrientation(context))).get(DeviceUtils.getScreenSizeCategory(context));
    }
    
    public static int getNumRelatedGridCols(final Context context) {
        if (context == null) {
            Log.w("nf_log_search", "getNumRelatedGridCols, Context is null");
            return 2;
        }
        return ((SparseIntArray)SearchUtils.numRelatedColumnsTable.get(DeviceUtils.getBasicScreenOrientation(context))).get(DeviceUtils.getScreenSizeCategory(context));
    }
    
    public static int getNumVideoGridCols(final Context context) {
        if (context == null) {
            Log.w("nf_log_search", "getNumVideoGridCols, Context is null");
            return 2;
        }
        return ((SparseIntArray)SearchUtils.numVideoColumnsTable.get(DeviceUtils.getBasicScreenOrientation(context))).get(DeviceUtils.getScreenSizeCategory(context));
    }
    
    public static double getPeopleImageAspectRatio() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return 1.0;
            }
        }
    }
    
    public static int getSearchFragLayout() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return 2130903176;
            }
            case 1: {
                return 2130903177;
            }
        }
    }
    
    public static int getSearchViewLayoutPeople() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return 2130903173;
            }
            case 1: {
                return 2130903175;
            }
        }
    }
    
    public static int getSearchViewLayoutRelated() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return 2130903174;
            }
            case 1: {
                return 2130903175;
            }
        }
    }
    
    public static double getVideoImageAspectRatio() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return 1.4299999475479126;
            }
            case 1:
            case 2: {
                return 0.5625;
            }
        }
    }
    
    public static boolean handleBackPress() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return false;
            }
            case 1: {
                return true;
            }
        }
    }
    
    private static void initColumnsTable() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                intColumnTableTestONELandscape();
                intColumnTableTestONEPortrait();
            }
            case 2: {
                intColumnTableTestTWOLandscape();
                intColumnTableTestTWOPortrait();
            }
            case 1: {
                intColumnTableTestTHREELandscape();
                intColumnTableTestTHREEPortrait();
            }
        }
    }
    
    private static void intColumnTableTestONELandscape() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 3);
        sparseIntArray2.put(1, 3);
        sparseIntArray3.put(1, 1);
        sparseIntArray.put(2, 4);
        sparseIntArray2.put(2, 4);
        sparseIntArray3.put(2, 1);
        sparseIntArray.put(3, 5);
        sparseIntArray2.put(3, 5);
        sparseIntArray3.put(3, 1);
        sparseIntArray.put(4, 6);
        sparseIntArray2.put(4, 6);
        sparseIntArray3.put(4, 1);
        SearchUtils.numVideoColumnsTable.put(2, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(2, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(2, (Object)sparseIntArray3);
    }
    
    private static void intColumnTableTestONEPortrait() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 2);
        sparseIntArray2.put(1, 2);
        sparseIntArray3.put(1, 1);
        sparseIntArray.put(2, 3);
        sparseIntArray2.put(2, 3);
        sparseIntArray3.put(2, 1);
        sparseIntArray.put(3, 4);
        sparseIntArray2.put(3, 4);
        sparseIntArray3.put(3, 1);
        sparseIntArray.put(4, 4);
        sparseIntArray2.put(4, 4);
        sparseIntArray3.put(4, 1);
        SearchUtils.numVideoColumnsTable.put(1, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(1, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(1, (Object)sparseIntArray3);
    }
    
    private static void intColumnTableTestTHREELandscape() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 2);
        sparseIntArray2.put(1, 4);
        sparseIntArray3.put(1, 1);
        sparseIntArray.put(2, 3);
        sparseIntArray2.put(2, 6);
        sparseIntArray3.put(2, 1);
        sparseIntArray.put(3, 3);
        sparseIntArray2.put(3, 1);
        sparseIntArray3.put(3, 1);
        sparseIntArray.put(4, 3);
        sparseIntArray2.put(4, 1);
        sparseIntArray3.put(4, 1);
        SearchUtils.numVideoColumnsTable.put(2, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(2, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(2, (Object)sparseIntArray3);
    }
    
    private static void intColumnTableTestTHREEPortrait() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 3);
        sparseIntArray2.put(1, 4);
        sparseIntArray3.put(1, 3);
        sparseIntArray.put(2, 2);
        sparseIntArray2.put(2, 4);
        sparseIntArray3.put(2, 3);
        sparseIntArray.put(3, 2);
        sparseIntArray2.put(3, 3);
        sparseIntArray3.put(3, 3);
        sparseIntArray.put(4, 3);
        sparseIntArray2.put(4, 3);
        sparseIntArray3.put(4, 3);
        SearchUtils.numVideoColumnsTable.put(1, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(1, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(1, (Object)sparseIntArray3);
    }
    
    private static void intColumnTableTestTWOLandscape() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 2);
        sparseIntArray2.put(1, 4);
        sparseIntArray3.put(1, 1);
        sparseIntArray.put(2, 3);
        sparseIntArray2.put(2, 6);
        sparseIntArray3.put(2, 1);
        sparseIntArray.put(3, 3);
        sparseIntArray2.put(3, 6);
        sparseIntArray3.put(3, 1);
        sparseIntArray.put(4, 4);
        sparseIntArray2.put(4, 8);
        sparseIntArray3.put(4, 1);
        SearchUtils.numVideoColumnsTable.put(2, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(2, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(2, (Object)sparseIntArray3);
    }
    
    private static void intColumnTableTestTWOPortrait() {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        final SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray.put(1, 2);
        sparseIntArray2.put(1, 4);
        sparseIntArray3.put(1, 1);
        sparseIntArray.put(2, 2);
        sparseIntArray2.put(2, 4);
        sparseIntArray3.put(2, 1);
        sparseIntArray.put(3, 2);
        sparseIntArray2.put(3, 4);
        sparseIntArray3.put(3, 1);
        sparseIntArray.put(4, 3);
        sparseIntArray2.put(4, 6);
        sparseIntArray3.put(4, 1);
        SearchUtils.numVideoColumnsTable.put(1, (Object)sparseIntArray);
        SearchUtils.numPeopleColumnsTable.put(1, (Object)sparseIntArray2);
        SearchUtils.numRelatedColumnsTable.put(1, (Object)sparseIntArray3);
    }
    
    public static String sanitizeQuery(final String s) {
        final String replaceAll = s.replaceAll("\\s+", " ");
        if (Log.isLoggable("nf_log_search", 2)) {
            Log.v("nf_log_search", "Sanitized query from: \"" + s + "\" to \"" + replaceAll + "\"");
        }
        return replaceAll;
    }
    
    public static void setTestCell(final int n) {
        switch (n) {
            default: {
                SearchUtils.currentTest = SearchUtils$TestCell.ONE;
                break;
            }
            case 2: {
                SearchUtils.currentTest = SearchUtils$TestCell.TWO;
                break;
            }
            case 3: {
                SearchUtils.currentTest = SearchUtils$TestCell.THREE;
                break;
            }
        }
        initColumnsTable();
    }
    
    public static boolean shouldOpenNewActivityForRelated() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return true;
            }
            case 1: {
                return false;
            }
        }
    }
    
    public static boolean shouldResetQueryOnTouch() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return true;
            }
            case 3: {
                return false;
            }
        }
    }
    
    public static boolean shouldShowVerticalBoxArt() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return true;
            }
            case 1:
            case 2: {
                return false;
            }
        }
    }
    
    public static boolean shouldUpperCaseTitleLabels() {
        switch (SearchUtils$1.$SwitchMap$com$netflix$mediaclient$ui$search$SearchUtils$TestCell[SearchUtils.currentTest.ordinal()]) {
            default: {
                return true;
            }
            case 1: {
                return false;
            }
        }
    }
}

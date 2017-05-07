// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import android.content.ComponentName;
import java.util.Collections;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Intent;
import android.content.Context;
import java.util.List;
import java.util.Map;
import android.database.DataSetObservable;

public class ActivityChooserModel extends DataSetObservable
{
    private static final String ATTRIBUTE_ACTIVITY = "activity";
    private static final String ATTRIBUTE_TIME = "time";
    private static final String ATTRIBUTE_WEIGHT = "weight";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    private static final String LOG_TAG;
    private static final String TAG_HISTORICAL_RECORD = "historical-record";
    private static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private static final Map<String, ActivityChooserModel> sDataModelRegistry;
    private static final Object sRegistryLock;
    private final List<ActivityChooserModel$ActivityResolveInfo> mActivities;
    private ActivityChooserModel$OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivityChooserModel$ActivitySorter mActivitySorter;
    private boolean mCanReadHistoricalData;
    private final Context mContext;
    private final List<ActivityChooserModel$HistoricalRecord> mHistoricalRecords;
    private boolean mHistoricalRecordsChanged;
    private final String mHistoryFileName;
    private int mHistoryMaxSize;
    private final Object mInstanceLock;
    private Intent mIntent;
    private boolean mReadShareHistoryCalled;
    private boolean mReloadActivities;
    
    static {
        LOG_TAG = ActivityChooserModel.class.getSimpleName();
        sRegistryLock = new Object();
        sDataModelRegistry = new HashMap<String, ActivityChooserModel>();
    }
    
    private ActivityChooserModel(final Context context, final String mHistoryFileName) {
        this.mInstanceLock = new Object();
        this.mActivities = new ArrayList<ActivityChooserModel$ActivityResolveInfo>();
        this.mHistoricalRecords = new ArrayList<ActivityChooserModel$HistoricalRecord>();
        this.mActivitySorter = new ActivityChooserModel$DefaultSorter(this, null);
        this.mHistoryMaxSize = 50;
        this.mCanReadHistoricalData = true;
        this.mReadShareHistoryCalled = false;
        this.mHistoricalRecordsChanged = true;
        this.mReloadActivities = false;
        this.mContext = context.getApplicationContext();
        if (!TextUtils.isEmpty((CharSequence)mHistoryFileName) && !mHistoryFileName.endsWith(".xml")) {
            this.mHistoryFileName = mHistoryFileName + ".xml";
            return;
        }
        this.mHistoryFileName = mHistoryFileName;
    }
    
    private boolean addHisoricalRecord(final ActivityChooserModel$HistoricalRecord activityChooserModel$HistoricalRecord) {
        final boolean add = this.mHistoricalRecords.add(activityChooserModel$HistoricalRecord);
        if (add) {
            this.mHistoricalRecordsChanged = true;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            this.persistHistoricalDataIfNeeded();
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
        return add;
    }
    
    private void ensureConsistentState() {
        final boolean loadActivitiesIfNeeded = this.loadActivitiesIfNeeded();
        final boolean historicalDataIfNeeded = this.readHistoricalDataIfNeeded();
        this.pruneExcessiveHistoricalRecordsIfNeeded();
        if (loadActivitiesIfNeeded | historicalDataIfNeeded) {
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
    }
    
    public static ActivityChooserModel get(final Context context, final String s) {
        synchronized (ActivityChooserModel.sRegistryLock) {
            ActivityChooserModel activityChooserModel;
            if ((activityChooserModel = ActivityChooserModel.sDataModelRegistry.get(s)) == null) {
                activityChooserModel = new ActivityChooserModel(context, s);
                ActivityChooserModel.sDataModelRegistry.put(s, activityChooserModel);
            }
            return activityChooserModel;
        }
    }
    
    private boolean loadActivitiesIfNeeded() {
        boolean b = false;
        if (this.mReloadActivities) {
            b = b;
            if (this.mIntent != null) {
                this.mReloadActivities = false;
                this.mActivities.clear();
                final List queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
                for (int size = queryIntentActivities.size(), i = 0; i < size; ++i) {
                    this.mActivities.add(new ActivityChooserModel$ActivityResolveInfo(this, queryIntentActivities.get(i)));
                }
                b = true;
            }
        }
        return b;
    }
    
    private void persistHistoricalDataIfNeeded() {
        if (!this.mReadShareHistoryCalled) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.mHistoricalRecordsChanged) {
            this.mHistoricalRecordsChanged = false;
            if (!TextUtils.isEmpty((CharSequence)this.mHistoryFileName)) {
                AsyncTaskCompat.executeParallel((android.os.AsyncTask<Object, Object, Object>)new ActivityChooserModel$PersistHistoryAsyncTask(this, null), this.mHistoricalRecords, this.mHistoryFileName);
            }
        }
    }
    
    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        final int n = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if (n > 0) {
            this.mHistoricalRecordsChanged = true;
            for (int i = 0; i < n; ++i) {
                final ActivityChooserModel$HistoricalRecord activityChooserModel$HistoricalRecord = this.mHistoricalRecords.remove(0);
            }
        }
    }
    
    private boolean readHistoricalDataIfNeeded() {
        if (this.mCanReadHistoricalData && this.mHistoricalRecordsChanged && !TextUtils.isEmpty((CharSequence)this.mHistoryFileName)) {
            this.mCanReadHistoricalData = false;
            this.mReadShareHistoryCalled = true;
            this.readHistoricalDataImpl();
            return true;
        }
        return false;
    }
    
    private void readHistoricalDataImpl() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        android/support/v7/internal/widget/ActivityChooserModel.mContext:Landroid/content/Context;
        //     4: aload_0        
        //     5: getfield        android/support/v7/internal/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //     8: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    11: astore_2       
        //    12: invokestatic    android/util/Xml.newPullParser:()Lorg/xmlpull/v1/XmlPullParser;
        //    15: astore_3       
        //    16: aload_3        
        //    17: aload_2        
        //    18: aconst_null    
        //    19: invokeinterface org/xmlpull/v1/XmlPullParser.setInput:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    24: iconst_0       
        //    25: istore_1       
        //    26: iload_1        
        //    27: iconst_1       
        //    28: if_icmpeq       46
        //    31: iload_1        
        //    32: iconst_2       
        //    33: if_icmpeq       46
        //    36: aload_3        
        //    37: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //    42: istore_1       
        //    43: goto            26
        //    46: ldc             "historical-records"
        //    48: aload_3        
        //    49: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //    54: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    57: ifne            112
        //    60: new             Lorg/xmlpull/v1/XmlPullParserException;
        //    63: dup            
        //    64: ldc_w           "Share records file does not start with historical-records tag."
        //    67: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //    70: athrow         
        //    71: astore_3       
        //    72: getstatic       android/support/v7/internal/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //    75: new             Ljava/lang/StringBuilder;
        //    78: dup            
        //    79: invokespecial   java/lang/StringBuilder.<init>:()V
        //    82: ldc_w           "Error reading historical recrod file: "
        //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    88: aload_0        
        //    89: getfield        android/support/v7/internal/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    95: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    98: aload_3        
        //    99: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   102: pop            
        //   103: aload_2        
        //   104: ifnull          111
        //   107: aload_2        
        //   108: invokevirtual   java/io/FileInputStream.close:()V
        //   111: return         
        //   112: aload_0        
        //   113: getfield        android/support/v7/internal/widget/ActivityChooserModel.mHistoricalRecords:Ljava/util/List;
        //   116: astore          4
        //   118: aload           4
        //   120: invokeinterface java/util/List.clear:()V
        //   125: aload_3        
        //   126: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //   131: istore_1       
        //   132: iload_1        
        //   133: iconst_1       
        //   134: if_icmpne       148
        //   137: aload_2        
        //   138: ifnull          111
        //   141: aload_2        
        //   142: invokevirtual   java/io/FileInputStream.close:()V
        //   145: return         
        //   146: astore_2       
        //   147: return         
        //   148: iload_1        
        //   149: iconst_3       
        //   150: if_icmpeq       125
        //   153: iload_1        
        //   154: iconst_4       
        //   155: if_icmpeq       125
        //   158: ldc             "historical-record"
        //   160: aload_3        
        //   161: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //   166: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   169: ifne            226
        //   172: new             Lorg/xmlpull/v1/XmlPullParserException;
        //   175: dup            
        //   176: ldc_w           "Share records file not well-formed."
        //   179: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //   182: athrow         
        //   183: astore_3       
        //   184: getstatic       android/support/v7/internal/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //   187: new             Ljava/lang/StringBuilder;
        //   190: dup            
        //   191: invokespecial   java/lang/StringBuilder.<init>:()V
        //   194: ldc_w           "Error reading historical recrod file: "
        //   197: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   200: aload_0        
        //   201: getfield        android/support/v7/internal/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   207: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   210: aload_3        
        //   211: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   214: pop            
        //   215: aload_2        
        //   216: ifnull          111
        //   219: aload_2        
        //   220: invokevirtual   java/io/FileInputStream.close:()V
        //   223: return         
        //   224: astore_2       
        //   225: return         
        //   226: aload           4
        //   228: new             Landroid/support/v7/internal/widget/ActivityChooserModel$HistoricalRecord;
        //   231: dup            
        //   232: aload_3        
        //   233: aconst_null    
        //   234: ldc             "activity"
        //   236: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   241: aload_3        
        //   242: aconst_null    
        //   243: ldc             "time"
        //   245: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   250: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   253: aload_3        
        //   254: aconst_null    
        //   255: ldc             "weight"
        //   257: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   262: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   265: invokespecial   android/support/v7/internal/widget/ActivityChooserModel$HistoricalRecord.<init>:(Ljava/lang/String;JF)V
        //   268: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   273: pop            
        //   274: goto            125
        //   277: astore_3       
        //   278: aload_2        
        //   279: ifnull          286
        //   282: aload_2        
        //   283: invokevirtual   java/io/FileInputStream.close:()V
        //   286: aload_3        
        //   287: athrow         
        //   288: astore_2       
        //   289: return         
        //   290: astore_2       
        //   291: goto            286
        //   294: astore_2       
        //   295: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  0      12     294    296    Ljava/io/FileNotFoundException;
        //  12     24     71     111    Lorg/xmlpull/v1/XmlPullParserException;
        //  12     24     183    226    Ljava/io/IOException;
        //  12     24     277    288    Any
        //  36     43     71     111    Lorg/xmlpull/v1/XmlPullParserException;
        //  36     43     183    226    Ljava/io/IOException;
        //  36     43     277    288    Any
        //  46     71     71     111    Lorg/xmlpull/v1/XmlPullParserException;
        //  46     71     183    226    Ljava/io/IOException;
        //  46     71     277    288    Any
        //  72     103    277    288    Any
        //  107    111    288    290    Ljava/io/IOException;
        //  112    125    71     111    Lorg/xmlpull/v1/XmlPullParserException;
        //  112    125    183    226    Ljava/io/IOException;
        //  112    125    277    288    Any
        //  125    132    71     111    Lorg/xmlpull/v1/XmlPullParserException;
        //  125    132    183    226    Ljava/io/IOException;
        //  125    132    277    288    Any
        //  141    145    146    148    Ljava/io/IOException;
        //  158    183    71     111    Lorg/xmlpull/v1/XmlPullParserException;
        //  158    183    183    226    Ljava/io/IOException;
        //  158    183    277    288    Any
        //  184    215    277    288    Any
        //  219    223    224    226    Ljava/io/IOException;
        //  226    274    71     111    Lorg/xmlpull/v1/XmlPullParserException;
        //  226    274    183    226    Ljava/io/IOException;
        //  226    274    277    288    Any
        //  282    286    290    294    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 142, Size: 142
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
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
    
    private boolean sortActivitiesIfNeeded() {
        if (this.mActivitySorter != null && this.mIntent != null && !this.mActivities.isEmpty() && !this.mHistoricalRecords.isEmpty()) {
            this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList((List<? extends ActivityChooserModel$HistoricalRecord>)this.mHistoricalRecords));
            return true;
        }
        return false;
    }
    
    public Intent chooseActivity(final int n) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent == null) {
                return null;
            }
            this.ensureConsistentState();
            final ActivityChooserModel$ActivityResolveInfo activityChooserModel$ActivityResolveInfo = this.mActivities.get(n);
            final ComponentName component = new ComponentName(activityChooserModel$ActivityResolveInfo.resolveInfo.activityInfo.packageName, activityChooserModel$ActivityResolveInfo.resolveInfo.activityInfo.name);
            final Intent intent = new Intent(this.mIntent);
            intent.setComponent(component);
            if (this.mActivityChoserModelPolicy != null && this.mActivityChoserModelPolicy.onChooseActivity(this, new Intent(intent))) {
                return null;
            }
            this.addHisoricalRecord(new ActivityChooserModel$HistoricalRecord(component, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }
    
    public ResolveInfo getActivity(final int n) {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            return this.mActivities.get(n).resolveInfo;
        }
    }
    
    public int getActivityCount() {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            return this.mActivities.size();
        }
    }
    
    public int getActivityIndex(final ResolveInfo resolveInfo) {
        while (true) {
            while (true) {
                int n;
                synchronized (this.mInstanceLock) {
                    this.ensureConsistentState();
                    final List<ActivityChooserModel$ActivityResolveInfo> mActivities = this.mActivities;
                    final int size = mActivities.size();
                    n = 0;
                    if (n >= size) {
                        return -1;
                    }
                    if (mActivities.get(n).resolveInfo == resolveInfo) {
                        return n;
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    public ResolveInfo getDefaultActivity() {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            if (!this.mActivities.isEmpty()) {
                return this.mActivities.get(0).resolveInfo;
            }
            return null;
        }
    }
    
    public int getHistoryMaxSize() {
        synchronized (this.mInstanceLock) {
            return this.mHistoryMaxSize;
        }
    }
    
    public int getHistorySize() {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            return this.mHistoricalRecords.size();
        }
    }
    
    public Intent getIntent() {
        synchronized (this.mInstanceLock) {
            return this.mIntent;
        }
    }
    
    public void setActivitySorter(final ActivityChooserModel$ActivitySorter mActivitySorter) {
        synchronized (this.mInstanceLock) {
            if (this.mActivitySorter == mActivitySorter) {
                return;
            }
            this.mActivitySorter = mActivitySorter;
            if (this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }
        }
    }
    
    public void setDefaultActivity(final int n) {
        while (true) {
            while (true) {
                synchronized (this.mInstanceLock) {
                    this.ensureConsistentState();
                    final ActivityChooserModel$ActivityResolveInfo activityChooserModel$ActivityResolveInfo = this.mActivities.get(n);
                    final ActivityChooserModel$ActivityResolveInfo activityChooserModel$ActivityResolveInfo2 = this.mActivities.get(0);
                    if (activityChooserModel$ActivityResolveInfo2 != null) {
                        final float n2 = activityChooserModel$ActivityResolveInfo2.weight - activityChooserModel$ActivityResolveInfo.weight + 5.0f;
                        this.addHisoricalRecord(new ActivityChooserModel$HistoricalRecord(new ComponentName(activityChooserModel$ActivityResolveInfo.resolveInfo.activityInfo.packageName, activityChooserModel$ActivityResolveInfo.resolveInfo.activityInfo.name), System.currentTimeMillis(), n2));
                        return;
                    }
                }
                final float n2 = 1.0f;
                continue;
            }
        }
    }
    
    public void setHistoryMaxSize(final int mHistoryMaxSize) {
        synchronized (this.mInstanceLock) {
            if (this.mHistoryMaxSize == mHistoryMaxSize) {
                return;
            }
            this.mHistoryMaxSize = mHistoryMaxSize;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            if (this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }
        }
    }
    
    public void setIntent(final Intent mIntent) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent == mIntent) {
                return;
            }
            this.mIntent = mIntent;
            this.mReloadActivities = true;
            this.ensureConsistentState();
        }
    }
    
    public void setOnChooseActivityListener(final ActivityChooserModel$OnChooseActivityListener mActivityChoserModelPolicy) {
        synchronized (this.mInstanceLock) {
            this.mActivityChoserModelPolicy = mActivityChoserModelPolicy;
        }
    }
}

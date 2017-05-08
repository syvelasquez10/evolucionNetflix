// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.content.ComponentName;
import java.util.Collections;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import java.util.Collection;
import java.util.ArrayList;
import android.text.TextUtils;
import android.content.pm.ResolveInfo;
import java.util.HashMap;
import android.content.Intent;
import android.content.Context;
import java.util.List;
import java.util.Map;
import android.database.DataSetObservable;

class ActivityChooserModel extends DataSetObservable
{
    static final String LOG_TAG;
    private static final Map<String, ActivityChooserModel> sDataModelRegistry;
    private static final Object sRegistryLock;
    private final List<ActivityChooserModel$ActivityResolveInfo> mActivities;
    private ActivityChooserModel$OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivityChooserModel$ActivitySorter mActivitySorter;
    boolean mCanReadHistoricalData;
    final Context mContext;
    private final List<ActivityChooserModel$HistoricalRecord> mHistoricalRecords;
    private boolean mHistoricalRecordsChanged;
    final String mHistoryFileName;
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
    
    private boolean addHistoricalRecord(final ActivityChooserModel$HistoricalRecord activityChooserModel$HistoricalRecord) {
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
                AsyncTaskCompat.executeParallel((android.os.AsyncTask<Object, Object, Object>)new ActivityChooserModel$PersistHistoryAsyncTask(this), new ArrayList(this.mHistoricalRecords), this.mHistoryFileName);
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
        //     1: getfield        android/support/v7/widget/ActivityChooserModel.mContext:Landroid/content/Context;
        //     4: aload_0        
        //     5: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //     8: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    11: astore_2       
        //    12: invokestatic    android/util/Xml.newPullParser:()Lorg/xmlpull/v1/XmlPullParser;
        //    15: astore_3       
        //    16: aload_3        
        //    17: aload_2        
        //    18: ldc             "UTF-8"
        //    20: invokeinterface org/xmlpull/v1/XmlPullParser.setInput:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    25: iconst_0       
        //    26: istore_1       
        //    27: iload_1        
        //    28: iconst_1       
        //    29: if_icmpeq       47
        //    32: iload_1        
        //    33: iconst_2       
        //    34: if_icmpeq       47
        //    37: aload_3        
        //    38: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //    43: istore_1       
        //    44: goto            27
        //    47: ldc             "historical-records"
        //    49: aload_3        
        //    50: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //    55: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    58: ifne            111
        //    61: new             Lorg/xmlpull/v1/XmlPullParserException;
        //    64: dup            
        //    65: ldc             "Share records file does not start with historical-records tag."
        //    67: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //    70: athrow         
        //    71: astore_3       
        //    72: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //    75: new             Ljava/lang/StringBuilder;
        //    78: dup            
        //    79: invokespecial   java/lang/StringBuilder.<init>:()V
        //    82: ldc             "Error reading historical recrod file: "
        //    84: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    87: aload_0        
        //    88: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //    91: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    94: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    97: aload_3        
        //    98: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   101: pop            
        //   102: aload_2        
        //   103: ifnull          110
        //   106: aload_2        
        //   107: invokevirtual   java/io/FileInputStream.close:()V
        //   110: return         
        //   111: aload_0        
        //   112: getfield        android/support/v7/widget/ActivityChooserModel.mHistoricalRecords:Ljava/util/List;
        //   115: astore          4
        //   117: aload           4
        //   119: invokeinterface java/util/List.clear:()V
        //   124: aload_3        
        //   125: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //   130: istore_1       
        //   131: iload_1        
        //   132: iconst_1       
        //   133: if_icmpne       147
        //   136: aload_2        
        //   137: ifnull          110
        //   140: aload_2        
        //   141: invokevirtual   java/io/FileInputStream.close:()V
        //   144: return         
        //   145: astore_2       
        //   146: return         
        //   147: iload_1        
        //   148: iconst_3       
        //   149: if_icmpeq       124
        //   152: iload_1        
        //   153: iconst_4       
        //   154: if_icmpeq       124
        //   157: ldc             "historical-record"
        //   159: aload_3        
        //   160: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //   165: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   168: ifne            223
        //   171: new             Lorg/xmlpull/v1/XmlPullParserException;
        //   174: dup            
        //   175: ldc             "Share records file not well-formed."
        //   177: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //   180: athrow         
        //   181: astore_3       
        //   182: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //   185: new             Ljava/lang/StringBuilder;
        //   188: dup            
        //   189: invokespecial   java/lang/StringBuilder.<init>:()V
        //   192: ldc             "Error reading historical recrod file: "
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: aload_0        
        //   198: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   207: aload_3        
        //   208: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   211: pop            
        //   212: aload_2        
        //   213: ifnull          110
        //   216: aload_2        
        //   217: invokevirtual   java/io/FileInputStream.close:()V
        //   220: return         
        //   221: astore_2       
        //   222: return         
        //   223: aload           4
        //   225: new             Landroid/support/v7/widget/ActivityChooserModel$HistoricalRecord;
        //   228: dup            
        //   229: aload_3        
        //   230: aconst_null    
        //   231: ldc             "activity"
        //   233: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   238: aload_3        
        //   239: aconst_null    
        //   240: ldc             "time"
        //   242: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   247: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   250: aload_3        
        //   251: aconst_null    
        //   252: ldc             "weight"
        //   254: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   259: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   262: invokespecial   android/support/v7/widget/ActivityChooserModel$HistoricalRecord.<init>:(Ljava/lang/String;JF)V
        //   265: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   270: pop            
        //   271: goto            124
        //   274: astore_3       
        //   275: aload_2        
        //   276: ifnull          283
        //   279: aload_2        
        //   280: invokevirtual   java/io/FileInputStream.close:()V
        //   283: aload_3        
        //   284: athrow         
        //   285: astore_2       
        //   286: return         
        //   287: astore_2       
        //   288: goto            283
        //   291: astore_2       
        //   292: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  0      12     291    293    Ljava/io/FileNotFoundException;
        //  12     25     71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  12     25     181    223    Ljava/io/IOException;
        //  12     25     274    285    Any
        //  37     44     71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  37     44     181    223    Ljava/io/IOException;
        //  37     44     274    285    Any
        //  47     71     71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  47     71     181    223    Ljava/io/IOException;
        //  47     71     274    285    Any
        //  72     102    274    285    Any
        //  106    110    285    287    Ljava/io/IOException;
        //  111    124    71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  111    124    181    223    Ljava/io/IOException;
        //  111    124    274    285    Any
        //  124    131    71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  124    131    181    223    Ljava/io/IOException;
        //  124    131    274    285    Any
        //  140    144    145    147    Ljava/io/IOException;
        //  157    181    71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  157    181    181    223    Ljava/io/IOException;
        //  157    181    274    285    Any
        //  182    212    274    285    Any
        //  216    220    221    223    Ljava/io/IOException;
        //  223    271    71     110    Lorg/xmlpull/v1/XmlPullParserException;
        //  223    271    181    223    Ljava/io/IOException;
        //  223    271    274    285    Any
        //  279    283    287    291    Ljava/io/IOException;
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
            this.addHistoricalRecord(new ActivityChooserModel$HistoricalRecord(component, System.currentTimeMillis(), 1.0f));
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
    
    public void setDefaultActivity(final int n) {
        while (true) {
            while (true) {
                synchronized (this.mInstanceLock) {
                    this.ensureConsistentState();
                    final ActivityChooserModel$ActivityResolveInfo activityChooserModel$ActivityResolveInfo = this.mActivities.get(n);
                    final ActivityChooserModel$ActivityResolveInfo activityChooserModel$ActivityResolveInfo2 = this.mActivities.get(0);
                    if (activityChooserModel$ActivityResolveInfo2 != null) {
                        final float n2 = activityChooserModel$ActivityResolveInfo2.weight - activityChooserModel$ActivityResolveInfo.weight + 5.0f;
                        this.addHistoricalRecord(new ActivityChooserModel$HistoricalRecord(new ComponentName(activityChooserModel$ActivityResolveInfo.resolveInfo.activityInfo.packageName, activityChooserModel$ActivityResolveInfo.resolveInfo.activityInfo.name), System.currentTimeMillis(), n2));
                        return;
                    }
                }
                final float n2 = 1.0f;
                continue;
            }
        }
    }
}

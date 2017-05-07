// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.Log;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.SortedMap;
import android.content.Context;

public class BifManager
{
    private static final int CHUNK_SIZE = 1024;
    private static final int HEADER_SIZE = 64;
    private static final int INDEX_ENTRY_SIZE = 8;
    private static final int MAX_INDEX_SIZE = 28800;
    private static final String SAVED_FILE_NAME = "bif.tmp";
    private static final String TAG = "BifManager";
    private final Context mAppContext;
    private int mBifCount;
    private final SortedMap<Integer, Integer> mBifMap;
    private volatile boolean mBifReady;
    private Thread mDownloadThread;
    private int mHeaderIndexSize;
    private int mInterval;
    private String mSavedFileName;
    private volatile boolean mStopBif;
    private final TrickplayUrl[] mTrickplayUrls;
    private final ArrayList<String> mUrlList;
    private int mVersion;
    
    public BifManager(final Context mAppContext, final String s) {
        this.mBifMap = new TreeMap<Integer, Integer>();
        this.mInterval = 1000;
        this.mUrlList = new ArrayList<String>(4);
        this.mSavedFileName = "bif.tmp";
        this.mTrickplayUrls = null;
        this.mSavedFileName = "mdxbif.tmp";
        this.mAppContext = mAppContext;
        this.mUrlList.add(s);
        if (Log.isLoggable("BifManager", 3)) {
            Log.d("BifManager", "load bif from  " + s);
        }
        this.loadBif();
        Log.d("BifManager", "BifManager end");
    }
    
    public BifManager(final Context mAppContext, final TrickplayUrl[] mTrickplayUrls, final int n) {
        this.mBifMap = new TreeMap<Integer, Integer>();
        this.mInterval = 1000;
        this.mUrlList = new ArrayList<String>(4);
        this.mSavedFileName = "bif.tmp";
        this.mAppContext = mAppContext;
        this.createUrlList(this.mTrickplayUrls = mTrickplayUrls);
        this.loadBif();
        Log.d("BifManager", "BifManager end");
    }
    
    private void createUrlList(final TrickplayUrl[] array) {
        if (Log.isLoggable("BifManager", 3)) {
            Log.d("BifManager", "BifManager start, pick urls..." + array);
        }
        final int n = -1;
        int n2 = 0;
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= this.mTrickplayUrls.length) {
                break;
            }
            final String[] url = this.mTrickplayUrls[n2].getUrl();
            if (this.mTrickplayUrls[n2].getAspect() == 1.0f) {
                for (int i = 0; i < url.length; ++i) {
                    if (Log.isLoggable("BifManager", 3)) {
                        Log.d("BifManager", "preferred url" + i + " == " + url[i]);
                    }
                    this.mUrlList.add(url[i]);
                }
                n3 = n2;
                break;
            }
            ++n2;
        }
        for (int j = 0; j < this.mTrickplayUrls.length; ++j) {
            if (n3 != j) {
                final String[] url2 = this.mTrickplayUrls[j].getUrl();
                for (int k = 0; k < url2.length; ++k) {
                    this.mUrlList.add(url2[k]);
                }
            }
        }
        if (Log.isLoggable("BifManager", 3)) {
            Log.d("BifManager", this.mTrickplayUrls.length + " TrickplayUrls entities with " + this.mUrlList.size() + " urls");
        }
    }
    
    private void dumpBifBuffer(final byte[] array, final int n) {
        Log.d("BifManager", String.format("first foure %02x %02x %02x %2x", array[0], array[1], array[2], array[3]));
        Log.d("BifManager", String.format("last foure %02x %02x %02x %2x", array[n - 4], array[n - 3], array[n - 2], array[n - 1]));
    }
    
    private static void dumpHeader(final byte[] array) {
        for (int i = 0; i < 64; i += 4) {
            Log.d("BifManager", String.format("%02x %02x %02x %2x", array[i], array[i + 1], array[i + 2], array[i + 3]));
        }
    }
    
    private void loadBif() {
        this.mStopBif = false;
        this.mBifReady = false;
        this.mDownloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //     4: invokestatic    com/netflix/mediaclient/media/BifManager.access$000:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/ArrayList;
                //     7: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
                //    10: astore          18
                //    12: aload           18
                //    14: invokeinterface java/util/Iterator.hasNext:()Z
                //    19: ifeq            87
                //    22: aload           18
                //    24: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
                //    29: checkcast       Ljava/lang/String;
                //    32: astore          6
                //    34: ldc             "BifManager"
                //    36: iconst_3       
                //    37: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //    40: ifeq            69
                //    43: ldc             "BifManager"
                //    45: new             Ljava/lang/StringBuilder;
                //    48: dup            
                //    49: invokespecial   java/lang/StringBuilder.<init>:()V
                //    52: ldc             "try url@ "
                //    54: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    57: aload           6
                //    59: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    62: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //    65: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //    68: pop            
                //    69: aload_0        
                //    70: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //    73: invokestatic    com/netflix/mediaclient/media/BifManager.access$100:(Lcom/netflix/mediaclient/media/BifManager;)Z
                //    76: ifeq            88
                //    79: ldc             "BifManager"
                //    81: ldc             "stopped"
                //    83: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //    86: pop            
                //    87: return         
                //    88: aconst_null    
                //    89: astore          10
                //    91: aconst_null    
                //    92: astore          7
                //    94: aconst_null    
                //    95: astore          17
                //    97: aconst_null    
                //    98: astore          16
                //   100: aconst_null    
                //   101: astore          15
                //   103: aconst_null    
                //   104: astore          14
                //   106: aconst_null    
                //   107: astore          11
                //   109: aconst_null    
                //   110: astore          13
                //   112: aconst_null    
                //   113: astore          12
                //   115: aload           17
                //   117: astore          8
                //   119: aload           11
                //   121: astore          9
                //   123: new             Ljava/net/URL;
                //   126: dup            
                //   127: aload           6
                //   129: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
                //   132: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
                //   135: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
                //   138: astore          6
                //   140: aload           17
                //   142: astore          8
                //   144: aload           6
                //   146: astore          7
                //   148: aload           11
                //   150: astore          9
                //   152: aload           6
                //   154: astore          10
                //   156: new             Ljava/io/BufferedInputStream;
                //   159: dup            
                //   160: aload           6
                //   162: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
                //   165: astore          11
                //   167: aload           12
                //   169: astore          8
                //   171: aload           13
                //   173: astore          7
                //   175: bipush          64
                //   177: newarray        B
                //   179: astore          9
                //   181: aload           12
                //   183: astore          8
                //   185: aload           13
                //   187: astore          7
                //   189: aload           11
                //   191: aload           9
                //   193: iconst_0       
                //   194: bipush          64
                //   196: invokevirtual   java/io/BufferedInputStream.read:([BII)I
                //   199: istore_1       
                //   200: aload           12
                //   202: astore          8
                //   204: aload           13
                //   206: astore          7
                //   208: ldc             "BifManager"
                //   210: iconst_3       
                //   211: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   214: ifeq            255
                //   217: aload           12
                //   219: astore          8
                //   221: aload           13
                //   223: astore          7
                //   225: ldc             "BifManager"
                //   227: new             Ljava/lang/StringBuilder;
                //   230: dup            
                //   231: invokespecial   java/lang/StringBuilder.<init>:()V
                //   234: ldc             "read "
                //   236: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   239: iload_1        
                //   240: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   243: ldc             " bytes"
                //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   248: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   251: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   254: pop            
                //   255: iload_1        
                //   256: bipush          64
                //   258: if_icmpge       358
                //   261: aload           12
                //   263: astore          8
                //   265: aload           13
                //   267: astore          7
                //   269: aload           11
                //   271: invokevirtual   java/io/BufferedInputStream.close:()V
                //   274: aload           12
                //   276: astore          8
                //   278: aload           13
                //   280: astore          7
                //   282: aload           6
                //   284: invokevirtual   java/io/InputStream.close:()V
                //   287: iconst_0       
                //   288: ifeq            299
                //   291: new             Ljava/lang/NullPointerException;
                //   294: dup            
                //   295: invokespecial   java/lang/NullPointerException.<init>:()V
                //   298: athrow         
                //   299: iconst_0       
                //   300: ifeq            311
                //   303: new             Ljava/lang/NullPointerException;
                //   306: dup            
                //   307: invokespecial   java/lang/NullPointerException.<init>:()V
                //   310: athrow         
                //   311: iconst_0       
                //   312: ifeq            323
                //   315: new             Ljava/lang/NullPointerException;
                //   318: dup            
                //   319: invokespecial   java/lang/NullPointerException.<init>:()V
                //   322: athrow         
                //   323: ldc             "BifManager"
                //   325: iconst_3       
                //   326: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   329: ifeq            12
                //   332: ldc             "BifManager"
                //   334: ldc             "bif download complete"
                //   336: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   339: pop            
                //   340: goto            12
                //   343: astore          6
                //   345: ldc             "BifManager"
                //   347: ldc             "Failed downlaod"
                //   349: aload           6
                //   351: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //   354: pop            
                //   355: goto            323
                //   358: aload           12
                //   360: astore          8
                //   362: aload           13
                //   364: astore          7
                //   366: aload           9
                //   368: invokestatic    java/nio/ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
                //   371: astore          9
                //   373: aload           12
                //   375: astore          8
                //   377: aload           13
                //   379: astore          7
                //   381: aload           9
                //   383: invokestatic    java/nio/ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
                //   386: invokevirtual   java/nio/ByteBuffer.order:(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
                //   389: pop            
                //   390: aload           12
                //   392: astore          8
                //   394: aload           13
                //   396: astore          7
                //   398: aload_0        
                //   399: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   402: aload           9
                //   404: bipush          8
                //   406: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
                //   409: invokestatic    com/netflix/mediaclient/media/BifManager.access$202:(Lcom/netflix/mediaclient/media/BifManager;I)I
                //   412: pop            
                //   413: aload           12
                //   415: astore          8
                //   417: aload           13
                //   419: astore          7
                //   421: aload_0        
                //   422: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   425: aload           9
                //   427: bipush          12
                //   429: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
                //   432: invokestatic    com/netflix/mediaclient/media/BifManager.access$302:(Lcom/netflix/mediaclient/media/BifManager;I)I
                //   435: pop            
                //   436: aload           12
                //   438: astore          8
                //   440: aload           13
                //   442: astore          7
                //   444: aload_0        
                //   445: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   448: aload           9
                //   450: bipush          16
                //   452: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
                //   455: invokestatic    com/netflix/mediaclient/media/BifManager.access$402:(Lcom/netflix/mediaclient/media/BifManager;I)I
                //   458: pop            
                //   459: aload           12
                //   461: astore          8
                //   463: aload           13
                //   465: astore          7
                //   467: ldc             "BifManager"
                //   469: iconst_3       
                //   470: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   473: ifeq            545
                //   476: aload           12
                //   478: astore          8
                //   480: aload           13
                //   482: astore          7
                //   484: ldc             "BifManager"
                //   486: new             Ljava/lang/StringBuilder;
                //   489: dup            
                //   490: invokespecial   java/lang/StringBuilder.<init>:()V
                //   493: ldc             "mVersion= "
                //   495: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   498: aload_0        
                //   499: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   502: invokestatic    com/netflix/mediaclient/media/BifManager.access$200:(Lcom/netflix/mediaclient/media/BifManager;)I
                //   505: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   508: ldc             ", mBifCount= "
                //   510: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   513: aload_0        
                //   514: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   517: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //   520: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   523: ldc             ",mInterval= "
                //   525: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   528: aload_0        
                //   529: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   532: invokestatic    com/netflix/mediaclient/media/BifManager.access$400:(Lcom/netflix/mediaclient/media/BifManager;)I
                //   535: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   538: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   541: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   544: pop            
                //   545: aload           12
                //   547: astore          8
                //   549: aload           13
                //   551: astore          7
                //   553: aload_0        
                //   554: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   557: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //   560: ifle            1052
                //   563: aload           12
                //   565: astore          8
                //   567: aload           13
                //   569: astore          7
                //   571: aload_0        
                //   572: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   575: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //   578: sipush          28800
                //   581: if_icmpgt       1052
                //   584: aload           12
                //   586: astore          8
                //   588: aload           13
                //   590: astore          7
                //   592: aload_0        
                //   593: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   596: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //   599: iconst_1       
                //   600: iadd           
                //   601: bipush          8
                //   603: imul           
                //   604: istore_3       
                //   605: aload           12
                //   607: astore          8
                //   609: aload           13
                //   611: astore          7
                //   613: aload_0        
                //   614: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   617: iload_3        
                //   618: bipush          64
                //   620: iadd           
                //   621: invokestatic    com/netflix/mediaclient/media/BifManager.access$502:(Lcom/netflix/mediaclient/media/BifManager;I)I
                //   624: pop            
                //   625: aload           12
                //   627: astore          8
                //   629: aload           13
                //   631: astore          7
                //   633: iload_3        
                //   634: newarray        B
                //   636: astore          9
                //   638: iconst_0       
                //   639: istore_2       
                //   640: iload_2        
                //   641: istore_1       
                //   642: aload           12
                //   644: astore          8
                //   646: aload           13
                //   648: astore          7
                //   650: ldc             "BifManager"
                //   652: iconst_3       
                //   653: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   656: ifeq            694
                //   659: aload           12
                //   661: astore          8
                //   663: aload           13
                //   665: astore          7
                //   667: ldc             "BifManager"
                //   669: new             Ljava/lang/StringBuilder;
                //   672: dup            
                //   673: invokespecial   java/lang/StringBuilder.<init>:()V
                //   676: ldc             "try to read index "
                //   678: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   681: iload_3        
                //   682: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   685: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   688: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   691: pop            
                //   692: iload_2        
                //   693: istore_1       
                //   694: iload_1        
                //   695: iload_3        
                //   696: if_icmpge       733
                //   699: aload           12
                //   701: astore          8
                //   703: aload           13
                //   705: astore          7
                //   707: aload_0        
                //   708: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   711: invokestatic    com/netflix/mediaclient/media/BifManager.access$100:(Lcom/netflix/mediaclient/media/BifManager;)Z
                //   714: ifeq            1864
                //   717: aload           12
                //   719: astore          8
                //   721: aload           13
                //   723: astore          7
                //   725: ldc             "BifManager"
                //   727: ldc             "stopped"
                //   729: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   732: pop            
                //   733: aload           12
                //   735: astore          8
                //   737: aload           13
                //   739: astore          7
                //   741: aload           9
                //   743: invokestatic    java/nio/ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
                //   746: astore          9
                //   748: aload           12
                //   750: astore          8
                //   752: aload           13
                //   754: astore          7
                //   756: aload           9
                //   758: invokestatic    java/nio/ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
                //   761: invokevirtual   java/nio/ByteBuffer.order:(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
                //   764: pop            
                //   765: iconst_0       
                //   766: istore_2       
                //   767: iload_2        
                //   768: iload_1        
                //   769: if_icmpge       1114
                //   772: aload           12
                //   774: astore          8
                //   776: aload           13
                //   778: astore          7
                //   780: aload           9
                //   782: invokevirtual   java/nio/ByteBuffer.getInt:()I
                //   785: istore          4
                //   787: aload           12
                //   789: astore          8
                //   791: aload           13
                //   793: astore          7
                //   795: aload           9
                //   797: invokevirtual   java/nio/ByteBuffer.getInt:()I
                //   800: istore          5
                //   802: iload           4
                //   804: istore_3       
                //   805: iload           4
                //   807: iconst_m1      
                //   808: if_icmpne       814
                //   811: ldc             2147483647
                //   813: istore_3       
                //   814: aload           12
                //   816: astore          8
                //   818: aload           13
                //   820: astore          7
                //   822: aload_0        
                //   823: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   826: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
                //   829: iload_3        
                //   830: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
                //   833: iload           5
                //   835: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
                //   838: invokeinterface java/util/SortedMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
                //   843: pop            
                //   844: iload_2        
                //   845: bipush          8
                //   847: iadd           
                //   848: istore_2       
                //   849: goto            767
                //   852: aload           12
                //   854: astore          8
                //   856: aload           13
                //   858: astore          7
                //   860: aload           11
                //   862: aload           9
                //   864: iload_1        
                //   865: iload_2        
                //   866: invokevirtual   java/io/BufferedInputStream.read:([BII)I
                //   869: istore          4
                //   871: iload           4
                //   873: iload_2        
                //   874: if_icmpeq       942
                //   877: aload           12
                //   879: astore          8
                //   881: aload           13
                //   883: astore          7
                //   885: ldc             "BifManager"
                //   887: iconst_3       
                //   888: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   891: ifeq            942
                //   894: aload           12
                //   896: astore          8
                //   898: aload           13
                //   900: astore          7
                //   902: ldc             "BifManager"
                //   904: new             Ljava/lang/StringBuilder;
                //   907: dup            
                //   908: invokespecial   java/lang/StringBuilder.<init>:()V
                //   911: ldc             "attempt to read "
                //   913: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   916: iload_2        
                //   917: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   920: ldc             ", actual "
                //   922: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   925: iload           4
                //   927: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   930: ldc             " bytes"
                //   932: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   935: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   938: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   941: pop            
                //   942: iload           4
                //   944: ifgt            1044
                //   947: aload           12
                //   949: astore          8
                //   951: aload           13
                //   953: astore          7
                //   955: aload           11
                //   957: invokevirtual   java/io/BufferedInputStream.close:()V
                //   960: aload           12
                //   962: astore          8
                //   964: aload           13
                //   966: astore          7
                //   968: aload           6
                //   970: invokevirtual   java/io/InputStream.close:()V
                //   973: iconst_0       
                //   974: ifeq            985
                //   977: new             Ljava/lang/NullPointerException;
                //   980: dup            
                //   981: invokespecial   java/lang/NullPointerException.<init>:()V
                //   984: athrow         
                //   985: iconst_0       
                //   986: ifeq            997
                //   989: new             Ljava/lang/NullPointerException;
                //   992: dup            
                //   993: invokespecial   java/lang/NullPointerException.<init>:()V
                //   996: athrow         
                //   997: iconst_0       
                //   998: ifeq            1009
                //  1001: new             Ljava/lang/NullPointerException;
                //  1004: dup            
                //  1005: invokespecial   java/lang/NullPointerException.<init>:()V
                //  1008: athrow         
                //  1009: ldc             "BifManager"
                //  1011: iconst_3       
                //  1012: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1015: ifeq            12
                //  1018: ldc             "BifManager"
                //  1020: ldc             "bif download complete"
                //  1022: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1025: pop            
                //  1026: goto            12
                //  1029: astore          6
                //  1031: ldc             "BifManager"
                //  1033: ldc             "Failed downlaod"
                //  1035: aload           6
                //  1037: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //  1040: pop            
                //  1041: goto            1009
                //  1044: iload_1        
                //  1045: iload           4
                //  1047: iadd           
                //  1048: istore_1       
                //  1049: goto            694
                //  1052: aload           12
                //  1054: astore          8
                //  1056: aload           13
                //  1058: astore          7
                //  1060: aload_0        
                //  1061: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1064: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //  1067: ifgt            1425
                //  1070: aload           12
                //  1072: astore          8
                //  1074: aload           13
                //  1076: astore          7
                //  1078: ldc             "BifManager"
                //  1080: new             Ljava/lang/StringBuilder;
                //  1083: dup            
                //  1084: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1087: ldc             "Index size is not positive, but "
                //  1089: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1092: aload_0        
                //  1093: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1096: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //  1099: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1102: ldc             ". Try next IRL if exist"
                //  1104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1107: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1110: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
                //  1113: pop            
                //  1114: aload           12
                //  1116: astore          8
                //  1118: aload           14
                //  1120: astore          9
                //  1122: aload           13
                //  1124: astore          7
                //  1126: aload_0        
                //  1127: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1130: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
                //  1133: invokeinterface java/util/SortedMap.isEmpty:()Z
                //  1138: ifne            1667
                //  1141: aload           12
                //  1143: astore          8
                //  1145: aload           13
                //  1147: astore          7
                //  1149: aload_0        
                //  1150: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1153: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
                //  1156: aload_0        
                //  1157: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1160: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
                //  1163: invokeinterface java/util/SortedMap.firstKey:()Ljava/lang/Object;
                //  1168: invokeinterface java/util/SortedMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
                //  1173: checkcast       Ljava/lang/Integer;
                //  1176: invokevirtual   java/lang/Integer.intValue:()I
                //  1179: istore_3       
                //  1180: aload           12
                //  1182: astore          8
                //  1184: aload           13
                //  1186: astore          7
                //  1188: aload_0        
                //  1189: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1192: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
                //  1195: aload_0        
                //  1196: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1199: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
                //  1202: invokeinterface java/util/SortedMap.lastKey:()Ljava/lang/Object;
                //  1207: invokeinterface java/util/SortedMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
                //  1212: checkcast       Ljava/lang/Integer;
                //  1215: invokevirtual   java/lang/Integer.intValue:()I
                //  1218: istore_2       
                //  1219: aload           12
                //  1221: astore          8
                //  1223: aload           13
                //  1225: astore          7
                //  1227: ldc             "BifManager"
                //  1229: iconst_3       
                //  1230: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1233: ifeq            1278
                //  1236: aload           12
                //  1238: astore          8
                //  1240: aload           13
                //  1242: astore          7
                //  1244: ldc             "BifManager"
                //  1246: new             Ljava/lang/StringBuilder;
                //  1249: dup            
                //  1250: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1253: ldc             "first offset "
                //  1255: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1258: iload_3        
                //  1259: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1262: ldc             ", end @"
                //  1264: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1267: iload_2        
                //  1268: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1271: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1274: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1277: pop            
                //  1278: aload           12
                //  1280: astore          8
                //  1282: aload           13
                //  1284: astore          7
                //  1286: iload_3        
                //  1287: aload_0        
                //  1288: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1291: invokestatic    com/netflix/mediaclient/media/BifManager.access$500:(Lcom/netflix/mediaclient/media/BifManager;)I
                //  1294: if_icmple       1321
                //  1297: aload           12
                //  1299: astore          8
                //  1301: aload           13
                //  1303: astore          7
                //  1305: aload           11
                //  1307: iload_3        
                //  1308: aload_0        
                //  1309: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1312: invokestatic    com/netflix/mediaclient/media/BifManager.access$500:(Lcom/netflix/mediaclient/media/BifManager;)I
                //  1315: isub           
                //  1316: i2l            
                //  1317: invokevirtual   java/io/BufferedInputStream.skip:(J)J
                //  1320: pop2           
                //  1321: aload           12
                //  1323: astore          8
                //  1325: aload           13
                //  1327: astore          7
                //  1329: aload_0        
                //  1330: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1333: invokestatic    com/netflix/mediaclient/media/BifManager.access$800:(Lcom/netflix/mediaclient/media/BifManager;)Landroid/content/Context;
                //  1336: aload_0        
                //  1337: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1340: invokestatic    com/netflix/mediaclient/media/BifManager.access$700:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/lang/String;
                //  1343: iconst_0       
                //  1344: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
                //  1347: astore          9
                //  1349: aload           9
                //  1351: astore          8
                //  1353: aload           9
                //  1355: astore          7
                //  1357: sipush          1024
                //  1360: newarray        B
                //  1362: astore          10
                //  1364: iconst_0       
                //  1365: istore_1       
                //  1366: iload_1        
                //  1367: iload_2        
                //  1368: iload_3        
                //  1369: isub           
                //  1370: if_icmpge       1595
                //  1373: aload           9
                //  1375: astore          8
                //  1377: aload           9
                //  1379: astore          7
                //  1381: aload           11
                //  1383: aload           10
                //  1385: iconst_0       
                //  1386: sipush          1024
                //  1389: invokevirtual   java/io/BufferedInputStream.read:([BII)I
                //  1392: istore          4
                //  1394: iload           4
                //  1396: ifle            1595
                //  1399: aload           9
                //  1401: astore          8
                //  1403: aload           9
                //  1405: astore          7
                //  1407: aload           9
                //  1409: aload           10
                //  1411: iconst_0       
                //  1412: iload           4
                //  1414: invokevirtual   java/io/FileOutputStream.write:([BII)V
                //  1417: iload_1        
                //  1418: iload           4
                //  1420: iadd           
                //  1421: istore_1       
                //  1422: goto            1366
                //  1425: aload           12
                //  1427: astore          8
                //  1429: aload           13
                //  1431: astore          7
                //  1433: ldc             "BifManager"
                //  1435: new             Ljava/lang/StringBuilder;
                //  1438: dup            
                //  1439: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1442: ldc             "Index size is higher than maximal positibility "
                //  1444: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1447: aload_0        
                //  1448: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1451: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //  1454: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1457: ldc_w           " > "
                //  1460: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1463: sipush          28800
                //  1466: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1469: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1472: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
                //  1475: pop            
                //  1476: goto            1114
                //  1479: astore          12
                //  1481: aload           11
                //  1483: astore          7
                //  1485: aload           8
                //  1487: astore          10
                //  1489: aload           6
                //  1491: astore          11
                //  1493: aload           7
                //  1495: astore          6
                //  1497: aload           6
                //  1499: astore          8
                //  1501: aload           11
                //  1503: astore          7
                //  1505: aload           10
                //  1507: astore          9
                //  1509: aload_0        
                //  1510: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1513: iconst_0       
                //  1514: invokestatic    com/netflix/mediaclient/media/BifManager.access$902:(Lcom/netflix/mediaclient/media/BifManager;Z)Z
                //  1517: pop            
                //  1518: aload           6
                //  1520: astore          8
                //  1522: aload           11
                //  1524: astore          7
                //  1526: aload           10
                //  1528: astore          9
                //  1530: ldc             "BifManager"
                //  1532: ldc             "Failed downlaod"
                //  1534: aload           12
                //  1536: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //  1539: pop            
                //  1540: aload           10
                //  1542: ifnull          1555
                //  1545: aload           10
                //  1547: invokevirtual   java/io/FileOutputStream.flush:()V
                //  1550: aload           10
                //  1552: invokevirtual   java/io/FileOutputStream.close:()V
                //  1555: aload           11
                //  1557: ifnull          1565
                //  1560: aload           11
                //  1562: invokevirtual   java/io/InputStream.close:()V
                //  1565: aload           6
                //  1567: ifnull          1575
                //  1570: aload           6
                //  1572: invokevirtual   java/io/BufferedInputStream.close:()V
                //  1575: ldc             "BifManager"
                //  1577: iconst_3       
                //  1578: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1581: ifeq            12
                //  1584: ldc             "BifManager"
                //  1586: ldc             "bif download complete"
                //  1588: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1591: pop            
                //  1592: goto            12
                //  1595: aload           9
                //  1597: astore          8
                //  1599: aload           9
                //  1601: astore          7
                //  1603: ldc             "BifManager"
                //  1605: iconst_3       
                //  1606: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1609: ifeq            1650
                //  1612: aload           9
                //  1614: astore          8
                //  1616: aload           9
                //  1618: astore          7
                //  1620: ldc             "BifManager"
                //  1622: new             Ljava/lang/StringBuilder;
                //  1625: dup            
                //  1626: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1629: ldc             "read "
                //  1631: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1634: iload_1        
                //  1635: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1638: ldc             " bytes"
                //  1640: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1643: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1646: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1649: pop            
                //  1650: aload           9
                //  1652: astore          8
                //  1654: aload           9
                //  1656: astore          7
                //  1658: aload_0        
                //  1659: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1662: iconst_1       
                //  1663: invokestatic    com/netflix/mediaclient/media/BifManager.access$902:(Lcom/netflix/mediaclient/media/BifManager;Z)Z
                //  1666: pop            
                //  1667: aload           9
                //  1669: ifnull          1682
                //  1672: aload           9
                //  1674: invokevirtual   java/io/FileOutputStream.flush:()V
                //  1677: aload           9
                //  1679: invokevirtual   java/io/FileOutputStream.close:()V
                //  1682: aload           6
                //  1684: ifnull          1692
                //  1687: aload           6
                //  1689: invokevirtual   java/io/InputStream.close:()V
                //  1692: aload           11
                //  1694: ifnull          1702
                //  1697: aload           11
                //  1699: invokevirtual   java/io/BufferedInputStream.close:()V
                //  1702: ldc             "BifManager"
                //  1704: iconst_3       
                //  1705: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1708: ifeq            87
                //  1711: ldc             "BifManager"
                //  1713: ldc             "bif download complete"
                //  1715: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1718: pop            
                //  1719: return         
                //  1720: astore          6
                //  1722: ldc             "BifManager"
                //  1724: ldc             "Failed downlaod"
                //  1726: aload           6
                //  1728: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //  1731: pop            
                //  1732: goto            1702
                //  1735: astore          6
                //  1737: ldc             "BifManager"
                //  1739: ldc             "Failed downlaod"
                //  1741: aload           6
                //  1743: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //  1746: pop            
                //  1747: goto            1575
                //  1750: astore          6
                //  1752: aload           7
                //  1754: astore          10
                //  1756: aload           9
                //  1758: ifnull          1771
                //  1761: aload           9
                //  1763: invokevirtual   java/io/FileOutputStream.flush:()V
                //  1766: aload           9
                //  1768: invokevirtual   java/io/FileOutputStream.close:()V
                //  1771: aload           10
                //  1773: ifnull          1781
                //  1776: aload           10
                //  1778: invokevirtual   java/io/InputStream.close:()V
                //  1781: aload           8
                //  1783: ifnull          1791
                //  1786: aload           8
                //  1788: invokevirtual   java/io/BufferedInputStream.close:()V
                //  1791: ldc             "BifManager"
                //  1793: iconst_3       
                //  1794: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1797: ifeq            1808
                //  1800: ldc             "BifManager"
                //  1802: ldc             "bif download complete"
                //  1804: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1807: pop            
                //  1808: aload           6
                //  1810: athrow         
                //  1811: astore          7
                //  1813: ldc             "BifManager"
                //  1815: ldc             "Failed downlaod"
                //  1817: aload           7
                //  1819: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //  1822: pop            
                //  1823: goto            1791
                //  1826: astore          12
                //  1828: aload           11
                //  1830: astore          8
                //  1832: aload           6
                //  1834: astore          10
                //  1836: aload           7
                //  1838: astore          9
                //  1840: aload           12
                //  1842: astore          6
                //  1844: goto            1756
                //  1847: astore          12
                //  1849: aload           16
                //  1851: astore          6
                //  1853: aload           10
                //  1855: astore          11
                //  1857: aload           15
                //  1859: astore          10
                //  1861: goto            1497
                //  1864: sipush          1024
                //  1867: istore_2       
                //  1868: iload_3        
                //  1869: iload_1        
                //  1870: isub           
                //  1871: sipush          1024
                //  1874: if_icmpge       852
                //  1877: iload_3        
                //  1878: iload_1        
                //  1879: isub           
                //  1880: istore_2       
                //  1881: goto            852
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  123    140    1847   1864   Ljava/io/IOException;
                //  123    140    1750   1756   Any
                //  156    167    1847   1864   Ljava/io/IOException;
                //  156    167    1750   1756   Any
                //  175    181    1479   1497   Ljava/io/IOException;
                //  175    181    1826   1847   Any
                //  189    200    1479   1497   Ljava/io/IOException;
                //  189    200    1826   1847   Any
                //  208    217    1479   1497   Ljava/io/IOException;
                //  208    217    1826   1847   Any
                //  225    255    1479   1497   Ljava/io/IOException;
                //  225    255    1826   1847   Any
                //  269    274    1479   1497   Ljava/io/IOException;
                //  269    274    1826   1847   Any
                //  282    287    1479   1497   Ljava/io/IOException;
                //  282    287    1826   1847   Any
                //  291    299    343    358    Ljava/io/IOException;
                //  303    311    343    358    Ljava/io/IOException;
                //  315    323    343    358    Ljava/io/IOException;
                //  366    373    1479   1497   Ljava/io/IOException;
                //  366    373    1826   1847   Any
                //  381    390    1479   1497   Ljava/io/IOException;
                //  381    390    1826   1847   Any
                //  398    413    1479   1497   Ljava/io/IOException;
                //  398    413    1826   1847   Any
                //  421    436    1479   1497   Ljava/io/IOException;
                //  421    436    1826   1847   Any
                //  444    459    1479   1497   Ljava/io/IOException;
                //  444    459    1826   1847   Any
                //  467    476    1479   1497   Ljava/io/IOException;
                //  467    476    1826   1847   Any
                //  484    545    1479   1497   Ljava/io/IOException;
                //  484    545    1826   1847   Any
                //  553    563    1479   1497   Ljava/io/IOException;
                //  553    563    1826   1847   Any
                //  571    584    1479   1497   Ljava/io/IOException;
                //  571    584    1826   1847   Any
                //  592    605    1479   1497   Ljava/io/IOException;
                //  592    605    1826   1847   Any
                //  613    625    1479   1497   Ljava/io/IOException;
                //  613    625    1826   1847   Any
                //  633    638    1479   1497   Ljava/io/IOException;
                //  633    638    1826   1847   Any
                //  650    659    1479   1497   Ljava/io/IOException;
                //  650    659    1826   1847   Any
                //  667    692    1479   1497   Ljava/io/IOException;
                //  667    692    1826   1847   Any
                //  707    717    1479   1497   Ljava/io/IOException;
                //  707    717    1826   1847   Any
                //  725    733    1479   1497   Ljava/io/IOException;
                //  725    733    1826   1847   Any
                //  741    748    1479   1497   Ljava/io/IOException;
                //  741    748    1826   1847   Any
                //  756    765    1479   1497   Ljava/io/IOException;
                //  756    765    1826   1847   Any
                //  780    787    1479   1497   Ljava/io/IOException;
                //  780    787    1826   1847   Any
                //  795    802    1479   1497   Ljava/io/IOException;
                //  795    802    1826   1847   Any
                //  822    844    1479   1497   Ljava/io/IOException;
                //  822    844    1826   1847   Any
                //  860    871    1479   1497   Ljava/io/IOException;
                //  860    871    1826   1847   Any
                //  885    894    1479   1497   Ljava/io/IOException;
                //  885    894    1826   1847   Any
                //  902    942    1479   1497   Ljava/io/IOException;
                //  902    942    1826   1847   Any
                //  955    960    1479   1497   Ljava/io/IOException;
                //  955    960    1826   1847   Any
                //  968    973    1479   1497   Ljava/io/IOException;
                //  968    973    1826   1847   Any
                //  977    985    1029   1044   Ljava/io/IOException;
                //  989    997    1029   1044   Ljava/io/IOException;
                //  1001   1009   1029   1044   Ljava/io/IOException;
                //  1060   1070   1479   1497   Ljava/io/IOException;
                //  1060   1070   1826   1847   Any
                //  1078   1114   1479   1497   Ljava/io/IOException;
                //  1078   1114   1826   1847   Any
                //  1126   1141   1479   1497   Ljava/io/IOException;
                //  1126   1141   1826   1847   Any
                //  1149   1180   1479   1497   Ljava/io/IOException;
                //  1149   1180   1826   1847   Any
                //  1188   1219   1479   1497   Ljava/io/IOException;
                //  1188   1219   1826   1847   Any
                //  1227   1236   1479   1497   Ljava/io/IOException;
                //  1227   1236   1826   1847   Any
                //  1244   1278   1479   1497   Ljava/io/IOException;
                //  1244   1278   1826   1847   Any
                //  1286   1297   1479   1497   Ljava/io/IOException;
                //  1286   1297   1826   1847   Any
                //  1305   1321   1479   1497   Ljava/io/IOException;
                //  1305   1321   1826   1847   Any
                //  1329   1349   1479   1497   Ljava/io/IOException;
                //  1329   1349   1826   1847   Any
                //  1357   1364   1479   1497   Ljava/io/IOException;
                //  1357   1364   1826   1847   Any
                //  1381   1394   1479   1497   Ljava/io/IOException;
                //  1381   1394   1826   1847   Any
                //  1407   1417   1479   1497   Ljava/io/IOException;
                //  1407   1417   1826   1847   Any
                //  1433   1476   1479   1497   Ljava/io/IOException;
                //  1433   1476   1826   1847   Any
                //  1509   1518   1750   1756   Any
                //  1530   1540   1750   1756   Any
                //  1545   1555   1735   1750   Ljava/io/IOException;
                //  1560   1565   1735   1750   Ljava/io/IOException;
                //  1570   1575   1735   1750   Ljava/io/IOException;
                //  1603   1612   1479   1497   Ljava/io/IOException;
                //  1603   1612   1826   1847   Any
                //  1620   1650   1479   1497   Ljava/io/IOException;
                //  1620   1650   1826   1847   Any
                //  1658   1667   1479   1497   Ljava/io/IOException;
                //  1658   1667   1826   1847   Any
                //  1672   1682   1720   1735   Ljava/io/IOException;
                //  1687   1692   1720   1735   Ljava/io/IOException;
                //  1697   1702   1720   1735   Ljava/io/IOException;
                //  1761   1771   1811   1826   Ljava/io/IOException;
                //  1776   1781   1811   1826   Ljava/io/IOException;
                //  1786   1791   1811   1826   Ljava/io/IOException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_1771:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        }, "BifManagerThread");
        if (this.mDownloadThread != null) {
            this.mDownloadThread.start();
        }
    }
    
    public ByteBuffer getIndexFrame(int intValue) {
        if (Log.isLoggable("BifManager", 3)) {
            Log.d("BifManager", "get " + intValue);
        }
        if (!this.mBifReady) {
            return null;
        }
        intValue = (this.mInterval + intValue - 1) / this.mInterval;
        final SortedMap<Integer, Integer> headMap = this.mBifMap.headMap(intValue);
        final SortedMap<Integer, Integer> tailMap = this.mBifMap.tailMap(intValue);
        if (!headMap.isEmpty() && !tailMap.isEmpty()) {
            intValue = (int)headMap.get(headMap.lastKey());
            final int n = tailMap.get(tailMap.firstKey()) - intValue;
            final int mHeaderIndexSize = this.mHeaderIndexSize;
            final byte[] array = new byte[n];
            try {
                final FileInputStream openFileInput = this.mAppContext.openFileInput(this.mSavedFileName);
                openFileInput.skip(intValue - mHeaderIndexSize);
                final int read = openFileInput.read(array, 0, n);
                if (Log.isLoggable("BifManager", 3)) {
                    Log.d("BifManager", "return @" + intValue + ", with size " + n + ", read " + read);
                }
                final ByteBuffer wrap = ByteBuffer.wrap(array, 0, n);
                wrap.position(0);
                wrap.limit(n);
                openFileInput.close();
                return wrap;
            }
            catch (Exception ex) {
                Log.e("BifManager", "Failed reading bif ", ex);
            }
        }
        return null;
    }
    
    public boolean isBifReady() {
        return this.mBifReady;
    }
    
    public void release() {
        this.mStopBif = true;
        this.mAppContext.deleteFile(this.mSavedFileName);
    }
    
    public static class Utils
    {
        public static void showBifInView(final BifManager bifManager, final int n, final ImageView imageView) {
            showBifInView(bifManager.getIndexFrame(n), imageView);
        }
        
        public static void showBifInView(final ByteBuffer byteBuffer, final ImageView imageView) {
            if (imageView == null) {
                Log.w("BifManager", "View is null");
                return;
            }
            if (byteBuffer == null) {
                Log.v("BifManager", "ByteBuffer is null");
                return;
            }
            if (imageView.getVisibility() != 0) {
                imageView.setVisibility(0);
            }
            final Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
            if (decodeByteArray != null) {
                imageView.setImageBitmap(decodeByteArray);
                return;
            }
            Log.w("BifManager", "decoded bif bitmap is null");
        }
    }
}

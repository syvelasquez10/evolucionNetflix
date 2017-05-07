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
                //   110: astore          12
                //   112: aconst_null    
                //   113: astore          13
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
                //   167: aload           13
                //   169: astore          8
                //   171: aload           12
                //   173: astore          7
                //   175: bipush          64
                //   177: newarray        B
                //   179: astore          9
                //   181: aload           13
                //   183: astore          8
                //   185: aload           12
                //   187: astore          7
                //   189: aload           11
                //   191: aload           9
                //   193: iconst_0       
                //   194: bipush          64
                //   196: invokevirtual   java/io/BufferedInputStream.read:([BII)I
                //   199: istore_1       
                //   200: aload           13
                //   202: astore          8
                //   204: aload           12
                //   206: astore          7
                //   208: ldc             "BifManager"
                //   210: iconst_3       
                //   211: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   214: ifeq            255
                //   217: aload           13
                //   219: astore          8
                //   221: aload           12
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
                //   261: aload           13
                //   263: astore          8
                //   265: aload           12
                //   267: astore          7
                //   269: aload           11
                //   271: invokevirtual   java/io/BufferedInputStream.close:()V
                //   274: aload           13
                //   276: astore          8
                //   278: aload           12
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
                //   358: aload           13
                //   360: astore          8
                //   362: aload           12
                //   364: astore          7
                //   366: aload           9
                //   368: invokestatic    java/nio/ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
                //   371: astore          9
                //   373: aload           13
                //   375: astore          8
                //   377: aload           12
                //   379: astore          7
                //   381: aload           9
                //   383: invokestatic    java/nio/ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
                //   386: invokevirtual   java/nio/ByteBuffer.order:(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
                //   389: pop            
                //   390: aload           13
                //   392: astore          8
                //   394: aload           12
                //   396: astore          7
                //   398: aload_0        
                //   399: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   402: aload           9
                //   404: bipush          8
                //   406: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
                //   409: invokestatic    com/netflix/mediaclient/media/BifManager.access$202:(Lcom/netflix/mediaclient/media/BifManager;I)I
                //   412: pop            
                //   413: aload           13
                //   415: astore          8
                //   417: aload           12
                //   419: astore          7
                //   421: aload_0        
                //   422: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   425: aload           9
                //   427: bipush          12
                //   429: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
                //   432: invokestatic    com/netflix/mediaclient/media/BifManager.access$302:(Lcom/netflix/mediaclient/media/BifManager;I)I
                //   435: pop            
                //   436: aload           13
                //   438: astore          8
                //   440: aload           12
                //   442: astore          7
                //   444: aload_0        
                //   445: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   448: aload           9
                //   450: bipush          16
                //   452: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
                //   455: invokestatic    com/netflix/mediaclient/media/BifManager.access$402:(Lcom/netflix/mediaclient/media/BifManager;I)I
                //   458: pop            
                //   459: aload           13
                //   461: astore          8
                //   463: aload           12
                //   465: astore          7
                //   467: ldc             "BifManager"
                //   469: iconst_3       
                //   470: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   473: ifeq            545
                //   476: aload           13
                //   478: astore          8
                //   480: aload           12
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
                //   545: aload           13
                //   547: astore          8
                //   549: aload           12
                //   551: astore          7
                //   553: aload_0        
                //   554: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   557: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //   560: ifle            1052
                //   563: aload           13
                //   565: astore          8
                //   567: aload           12
                //   569: astore          7
                //   571: aload_0        
                //   572: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   575: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //   578: sipush          28800
                //   581: if_icmpgt       1052
                //   584: aload           13
                //   586: astore          8
                //   588: aload           12
                //   590: astore          7
                //   592: aload_0        
                //   593: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   596: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //   599: iconst_1       
                //   600: iadd           
                //   601: bipush          8
                //   603: imul           
                //   604: istore_3       
                //   605: aload           13
                //   607: astore          8
                //   609: aload           12
                //   611: astore          7
                //   613: aload_0        
                //   614: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   617: iload_3        
                //   618: bipush          64
                //   620: iadd           
                //   621: invokestatic    com/netflix/mediaclient/media/BifManager.access$502:(Lcom/netflix/mediaclient/media/BifManager;I)I
                //   624: pop            
                //   625: aload           13
                //   627: astore          8
                //   629: aload           12
                //   631: astore          7
                //   633: iload_3        
                //   634: newarray        B
                //   636: astore          9
                //   638: iconst_0       
                //   639: istore_2       
                //   640: iload_2        
                //   641: istore_1       
                //   642: aload           13
                //   644: astore          8
                //   646: aload           12
                //   648: astore          7
                //   650: ldc             "BifManager"
                //   652: iconst_3       
                //   653: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   656: ifeq            694
                //   659: aload           13
                //   661: astore          8
                //   663: aload           12
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
                //   699: aload           13
                //   701: astore          8
                //   703: aload           12
                //   705: astore          7
                //   707: aload_0        
                //   708: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //   711: invokestatic    com/netflix/mediaclient/media/BifManager.access$100:(Lcom/netflix/mediaclient/media/BifManager;)Z
                //   714: ifeq            1856
                //   717: aload           13
                //   719: astore          8
                //   721: aload           12
                //   723: astore          7
                //   725: ldc             "BifManager"
                //   727: ldc             "stopped"
                //   729: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   732: pop            
                //   733: aload           13
                //   735: astore          8
                //   737: aload           12
                //   739: astore          7
                //   741: aload           9
                //   743: invokestatic    java/nio/ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
                //   746: astore          9
                //   748: aload           13
                //   750: astore          8
                //   752: aload           12
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
                //   772: aload           13
                //   774: astore          8
                //   776: aload           12
                //   778: astore          7
                //   780: aload           9
                //   782: invokevirtual   java/nio/ByteBuffer.getInt:()I
                //   785: istore          4
                //   787: aload           13
                //   789: astore          8
                //   791: aload           12
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
                //   814: aload           13
                //   816: astore          8
                //   818: aload           12
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
                //   852: aload           13
                //   854: astore          8
                //   856: aload           12
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
                //   877: aload           13
                //   879: astore          8
                //   881: aload           12
                //   883: astore          7
                //   885: ldc             "BifManager"
                //   887: iconst_3       
                //   888: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   891: ifeq            942
                //   894: aload           13
                //   896: astore          8
                //   898: aload           12
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
                //   947: aload           13
                //   949: astore          8
                //   951: aload           12
                //   953: astore          7
                //   955: aload           11
                //   957: invokevirtual   java/io/BufferedInputStream.close:()V
                //   960: aload           13
                //   962: astore          8
                //   964: aload           12
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
                //  1052: aload           13
                //  1054: astore          8
                //  1056: aload           12
                //  1058: astore          7
                //  1060: aload_0        
                //  1061: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1064: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
                //  1067: ifgt            1425
                //  1070: aload           13
                //  1072: astore          8
                //  1074: aload           12
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
                //  1114: aload           13
                //  1116: astore          8
                //  1118: aload           14
                //  1120: astore          9
                //  1122: aload           12
                //  1124: astore          7
                //  1126: aload_0        
                //  1127: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1130: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
                //  1133: invokeinterface java/util/SortedMap.isEmpty:()Z
                //  1138: ifne            1655
                //  1141: aload           13
                //  1143: astore          8
                //  1145: aload           12
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
                //  1179: istore_2       
                //  1180: aload           13
                //  1182: astore          8
                //  1184: aload           12
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
                //  1218: istore_3       
                //  1219: aload           13
                //  1221: astore          8
                //  1223: aload           12
                //  1225: astore          7
                //  1227: ldc             "BifManager"
                //  1229: iconst_3       
                //  1230: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1233: ifeq            1278
                //  1236: aload           13
                //  1238: astore          8
                //  1240: aload           12
                //  1242: astore          7
                //  1244: ldc             "BifManager"
                //  1246: new             Ljava/lang/StringBuilder;
                //  1249: dup            
                //  1250: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1253: ldc             "first offset "
                //  1255: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1258: iload_2        
                //  1259: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1262: ldc             ", end @"
                //  1264: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1267: iload_3        
                //  1268: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1271: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1274: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1277: pop            
                //  1278: aload           13
                //  1280: astore          8
                //  1282: aload           12
                //  1284: astore          7
                //  1286: iload_2        
                //  1287: aload_0        
                //  1288: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1291: invokestatic    com/netflix/mediaclient/media/BifManager.access$500:(Lcom/netflix/mediaclient/media/BifManager;)I
                //  1294: if_icmple       1321
                //  1297: aload           13
                //  1299: astore          8
                //  1301: aload           12
                //  1303: astore          7
                //  1305: aload           11
                //  1307: iload_2        
                //  1308: aload_0        
                //  1309: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1312: invokestatic    com/netflix/mediaclient/media/BifManager.access$500:(Lcom/netflix/mediaclient/media/BifManager;)I
                //  1315: isub           
                //  1316: i2l            
                //  1317: invokevirtual   java/io/BufferedInputStream.skip:(J)J
                //  1320: pop2           
                //  1321: aload           13
                //  1323: astore          8
                //  1325: aload           12
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
                //  1367: iload_3        
                //  1368: iload_2        
                //  1369: isub           
                //  1370: if_icmpge       1583
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
                //  1396: ifle            1583
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
                //  1425: aload           13
                //  1427: astore          8
                //  1429: aload           12
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
                //  1481: aload           8
                //  1483: astore          10
                //  1485: aload           11
                //  1487: astore          8
                //  1489: aload           6
                //  1491: astore          7
                //  1493: aload           10
                //  1495: astore          9
                //  1497: aload_0        
                //  1498: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1501: iconst_0       
                //  1502: invokestatic    com/netflix/mediaclient/media/BifManager.access$902:(Lcom/netflix/mediaclient/media/BifManager;Z)Z
                //  1505: pop            
                //  1506: aload           11
                //  1508: astore          8
                //  1510: aload           6
                //  1512: astore          7
                //  1514: aload           10
                //  1516: astore          9
                //  1518: ldc             "BifManager"
                //  1520: ldc             "Failed downlaod"
                //  1522: aload           12
                //  1524: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //  1527: pop            
                //  1528: aload           10
                //  1530: ifnull          1543
                //  1533: aload           10
                //  1535: invokevirtual   java/io/FileOutputStream.flush:()V
                //  1538: aload           10
                //  1540: invokevirtual   java/io/FileOutputStream.close:()V
                //  1543: aload           6
                //  1545: ifnull          1553
                //  1548: aload           6
                //  1550: invokevirtual   java/io/InputStream.close:()V
                //  1553: aload           11
                //  1555: ifnull          1563
                //  1558: aload           11
                //  1560: invokevirtual   java/io/BufferedInputStream.close:()V
                //  1563: ldc             "BifManager"
                //  1565: iconst_3       
                //  1566: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1569: ifeq            12
                //  1572: ldc             "BifManager"
                //  1574: ldc             "bif download complete"
                //  1576: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1579: pop            
                //  1580: goto            12
                //  1583: aload           9
                //  1585: astore          8
                //  1587: aload           9
                //  1589: astore          7
                //  1591: ldc             "BifManager"
                //  1593: iconst_3       
                //  1594: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1597: ifeq            1638
                //  1600: aload           9
                //  1602: astore          8
                //  1604: aload           9
                //  1606: astore          7
                //  1608: ldc             "BifManager"
                //  1610: new             Ljava/lang/StringBuilder;
                //  1613: dup            
                //  1614: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1617: ldc             "read "
                //  1619: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1622: iload_1        
                //  1623: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1626: ldc             " bytes"
                //  1628: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1631: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1634: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1637: pop            
                //  1638: aload           9
                //  1640: astore          8
                //  1642: aload           9
                //  1644: astore          7
                //  1646: aload_0        
                //  1647: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
                //  1650: iconst_1       
                //  1651: invokestatic    com/netflix/mediaclient/media/BifManager.access$902:(Lcom/netflix/mediaclient/media/BifManager;Z)Z
                //  1654: pop            
                //  1655: aload           9
                //  1657: ifnull          1670
                //  1660: aload           9
                //  1662: invokevirtual   java/io/FileOutputStream.flush:()V
                //  1665: aload           9
                //  1667: invokevirtual   java/io/FileOutputStream.close:()V
                //  1670: aload           6
                //  1672: ifnull          1680
                //  1675: aload           6
                //  1677: invokevirtual   java/io/InputStream.close:()V
                //  1680: aload           11
                //  1682: ifnull          1690
                //  1685: aload           11
                //  1687: invokevirtual   java/io/BufferedInputStream.close:()V
                //  1690: ldc             "BifManager"
                //  1692: iconst_3       
                //  1693: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1696: ifeq            87
                //  1699: ldc             "BifManager"
                //  1701: ldc             "bif download complete"
                //  1703: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1706: pop            
                //  1707: return         
                //  1708: astore          6
                //  1710: ldc             "BifManager"
                //  1712: ldc             "Failed downlaod"
                //  1714: aload           6
                //  1716: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //  1719: pop            
                //  1720: goto            1690
                //  1723: astore          6
                //  1725: ldc             "BifManager"
                //  1727: ldc             "Failed downlaod"
                //  1729: aload           6
                //  1731: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //  1734: pop            
                //  1735: goto            1563
                //  1738: astore          6
                //  1740: aload           9
                //  1742: astore          10
                //  1744: aload           7
                //  1746: astore          9
                //  1748: aload           10
                //  1750: ifnull          1763
                //  1753: aload           10
                //  1755: invokevirtual   java/io/FileOutputStream.flush:()V
                //  1758: aload           10
                //  1760: invokevirtual   java/io/FileOutputStream.close:()V
                //  1763: aload           9
                //  1765: ifnull          1773
                //  1768: aload           9
                //  1770: invokevirtual   java/io/InputStream.close:()V
                //  1773: aload           8
                //  1775: ifnull          1783
                //  1778: aload           8
                //  1780: invokevirtual   java/io/BufferedInputStream.close:()V
                //  1783: ldc             "BifManager"
                //  1785: iconst_3       
                //  1786: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1789: ifeq            1800
                //  1792: ldc             "BifManager"
                //  1794: ldc             "bif download complete"
                //  1796: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1799: pop            
                //  1800: aload           6
                //  1802: athrow         
                //  1803: astore          7
                //  1805: ldc             "BifManager"
                //  1807: ldc             "Failed downlaod"
                //  1809: aload           7
                //  1811: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
                //  1814: pop            
                //  1815: goto            1783
                //  1818: astore          12
                //  1820: aload           11
                //  1822: astore          8
                //  1824: aload           6
                //  1826: astore          9
                //  1828: aload           7
                //  1830: astore          10
                //  1832: aload           12
                //  1834: astore          6
                //  1836: goto            1748
                //  1839: astore          12
                //  1841: aload           16
                //  1843: astore          11
                //  1845: aload           10
                //  1847: astore          6
                //  1849: aload           15
                //  1851: astore          10
                //  1853: goto            1485
                //  1856: sipush          1024
                //  1859: istore_2       
                //  1860: iload_3        
                //  1861: iload_1        
                //  1862: isub           
                //  1863: sipush          1024
                //  1866: if_icmpge       852
                //  1869: iload_3        
                //  1870: iload_1        
                //  1871: isub           
                //  1872: istore_2       
                //  1873: goto            852
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  123    140    1839   1856   Ljava/io/IOException;
                //  123    140    1738   1748   Any
                //  156    167    1839   1856   Ljava/io/IOException;
                //  156    167    1738   1748   Any
                //  175    181    1479   1485   Ljava/io/IOException;
                //  175    181    1818   1839   Any
                //  189    200    1479   1485   Ljava/io/IOException;
                //  189    200    1818   1839   Any
                //  208    217    1479   1485   Ljava/io/IOException;
                //  208    217    1818   1839   Any
                //  225    255    1479   1485   Ljava/io/IOException;
                //  225    255    1818   1839   Any
                //  269    274    1479   1485   Ljava/io/IOException;
                //  269    274    1818   1839   Any
                //  282    287    1479   1485   Ljava/io/IOException;
                //  282    287    1818   1839   Any
                //  291    299    343    358    Ljava/io/IOException;
                //  303    311    343    358    Ljava/io/IOException;
                //  315    323    343    358    Ljava/io/IOException;
                //  366    373    1479   1485   Ljava/io/IOException;
                //  366    373    1818   1839   Any
                //  381    390    1479   1485   Ljava/io/IOException;
                //  381    390    1818   1839   Any
                //  398    413    1479   1485   Ljava/io/IOException;
                //  398    413    1818   1839   Any
                //  421    436    1479   1485   Ljava/io/IOException;
                //  421    436    1818   1839   Any
                //  444    459    1479   1485   Ljava/io/IOException;
                //  444    459    1818   1839   Any
                //  467    476    1479   1485   Ljava/io/IOException;
                //  467    476    1818   1839   Any
                //  484    545    1479   1485   Ljava/io/IOException;
                //  484    545    1818   1839   Any
                //  553    563    1479   1485   Ljava/io/IOException;
                //  553    563    1818   1839   Any
                //  571    584    1479   1485   Ljava/io/IOException;
                //  571    584    1818   1839   Any
                //  592    605    1479   1485   Ljava/io/IOException;
                //  592    605    1818   1839   Any
                //  613    625    1479   1485   Ljava/io/IOException;
                //  613    625    1818   1839   Any
                //  633    638    1479   1485   Ljava/io/IOException;
                //  633    638    1818   1839   Any
                //  650    659    1479   1485   Ljava/io/IOException;
                //  650    659    1818   1839   Any
                //  667    692    1479   1485   Ljava/io/IOException;
                //  667    692    1818   1839   Any
                //  707    717    1479   1485   Ljava/io/IOException;
                //  707    717    1818   1839   Any
                //  725    733    1479   1485   Ljava/io/IOException;
                //  725    733    1818   1839   Any
                //  741    748    1479   1485   Ljava/io/IOException;
                //  741    748    1818   1839   Any
                //  756    765    1479   1485   Ljava/io/IOException;
                //  756    765    1818   1839   Any
                //  780    787    1479   1485   Ljava/io/IOException;
                //  780    787    1818   1839   Any
                //  795    802    1479   1485   Ljava/io/IOException;
                //  795    802    1818   1839   Any
                //  822    844    1479   1485   Ljava/io/IOException;
                //  822    844    1818   1839   Any
                //  860    871    1479   1485   Ljava/io/IOException;
                //  860    871    1818   1839   Any
                //  885    894    1479   1485   Ljava/io/IOException;
                //  885    894    1818   1839   Any
                //  902    942    1479   1485   Ljava/io/IOException;
                //  902    942    1818   1839   Any
                //  955    960    1479   1485   Ljava/io/IOException;
                //  955    960    1818   1839   Any
                //  968    973    1479   1485   Ljava/io/IOException;
                //  968    973    1818   1839   Any
                //  977    985    1029   1044   Ljava/io/IOException;
                //  989    997    1029   1044   Ljava/io/IOException;
                //  1001   1009   1029   1044   Ljava/io/IOException;
                //  1060   1070   1479   1485   Ljava/io/IOException;
                //  1060   1070   1818   1839   Any
                //  1078   1114   1479   1485   Ljava/io/IOException;
                //  1078   1114   1818   1839   Any
                //  1126   1141   1479   1485   Ljava/io/IOException;
                //  1126   1141   1818   1839   Any
                //  1149   1180   1479   1485   Ljava/io/IOException;
                //  1149   1180   1818   1839   Any
                //  1188   1219   1479   1485   Ljava/io/IOException;
                //  1188   1219   1818   1839   Any
                //  1227   1236   1479   1485   Ljava/io/IOException;
                //  1227   1236   1818   1839   Any
                //  1244   1278   1479   1485   Ljava/io/IOException;
                //  1244   1278   1818   1839   Any
                //  1286   1297   1479   1485   Ljava/io/IOException;
                //  1286   1297   1818   1839   Any
                //  1305   1321   1479   1485   Ljava/io/IOException;
                //  1305   1321   1818   1839   Any
                //  1329   1349   1479   1485   Ljava/io/IOException;
                //  1329   1349   1818   1839   Any
                //  1357   1364   1479   1485   Ljava/io/IOException;
                //  1357   1364   1818   1839   Any
                //  1381   1394   1479   1485   Ljava/io/IOException;
                //  1381   1394   1818   1839   Any
                //  1407   1417   1479   1485   Ljava/io/IOException;
                //  1407   1417   1818   1839   Any
                //  1433   1476   1479   1485   Ljava/io/IOException;
                //  1433   1476   1818   1839   Any
                //  1497   1506   1738   1748   Any
                //  1518   1528   1738   1748   Any
                //  1533   1543   1723   1738   Ljava/io/IOException;
                //  1548   1553   1723   1738   Ljava/io/IOException;
                //  1558   1563   1723   1738   Ljava/io/IOException;
                //  1591   1600   1479   1485   Ljava/io/IOException;
                //  1591   1600   1818   1839   Any
                //  1608   1638   1479   1485   Ljava/io/IOException;
                //  1608   1638   1818   1839   Any
                //  1646   1655   1479   1485   Ljava/io/IOException;
                //  1646   1655   1818   1839   Any
                //  1660   1670   1708   1723   Ljava/io/IOException;
                //  1675   1680   1708   1723   Ljava/io/IOException;
                //  1685   1690   1708   1723   Ljava/io/IOException;
                //  1753   1763   1803   1818   Ljava/io/IOException;
                //  1768   1773   1803   1818   Ljava/io/IOException;
                //  1778   1783   1803   1818   Ljava/io/IOException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_1763:
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

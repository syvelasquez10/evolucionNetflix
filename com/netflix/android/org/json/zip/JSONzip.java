// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json.zip;

public abstract class JSONzip implements None, PostMortem
{
    public static final byte[] bcd;
    public static final int end = 256;
    public static final int endOfNumber;
    public static final long int14 = 16384L;
    public static final long int4 = 16L;
    public static final long int7 = 128L;
    public static final int maxSubstringLength = 10;
    public static final int minSubstringLength = 3;
    public static final boolean probe = false;
    public static final int substringLimit = 40;
    public static final int[] twos;
    public static final int zipArrayString = 6;
    public static final int zipArrayValue = 7;
    public static final int zipEmptyArray = 1;
    public static final int zipEmptyObject = 0;
    public static final int zipFalse = 3;
    public static final int zipNull = 4;
    public static final int zipObject = 5;
    public static final int zipTrue = 2;
    protected final Huff namehuff;
    protected final MapKeep namekeep;
    protected final MapKeep stringkeep;
    protected final Huff substringhuff;
    protected final TrieKeep substringkeep;
    protected final MapKeep values;
    
    static {
        twos = new int[] { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536 };
        bcd = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 46, 45, 43, 69 };
        endOfNumber = JSONzip.bcd.length;
    }
    
    protected JSONzip() {
        this.namehuff = new Huff(257);
        this.namekeep = new MapKeep(9);
        this.stringkeep = new MapKeep(11);
        this.substringhuff = new Huff(257);
        this.substringkeep = new TrieKeep(12);
        this.values = new MapKeep(10);
        this.namehuff.tick(32, 125);
        this.namehuff.tick(97, 122);
        this.namehuff.tick(256);
        this.namehuff.tick(256);
        this.substringhuff.tick(32, 125);
        this.substringhuff.tick(97, 122);
        this.substringhuff.tick(256);
        this.substringhuff.tick(256);
    }
    
    static void log() {
        log("\n");
    }
    
    static void log(final int n) {
        log(n + " ");
    }
    
    static void log(final int n, final int n2) {
        log(n + ":" + n2 + " ");
    }
    
    static void log(final String s) {
        System.out.print(s);
    }
    
    static void logchar(final int n, final int n2) {
        if (n > 32 && n <= 125) {
            log("'" + (char)n + "':" + n2 + " ");
            return;
        }
        log(n, n2);
    }
    
    protected void begin() {
        this.namehuff.generate();
        this.substringhuff.generate();
    }
    
    @Override
    public boolean postMortem(final PostMortem postMortem) {
        final JSONzip jsoNzip = (JSONzip)postMortem;
        return this.namehuff.postMortem(jsoNzip.namehuff) && this.namekeep.postMortem(jsoNzip.namekeep) && this.stringkeep.postMortem(jsoNzip.stringkeep) && this.substringhuff.postMortem(jsoNzip.substringhuff) && this.substringkeep.postMortem(jsoNzip.substringkeep) && this.values.postMortem(jsoNzip.values);
    }
}

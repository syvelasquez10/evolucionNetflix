// 
// Decompiled by Procyon v0.5.30
// 

package test.netflix.model;

import java.util.List;
import junit.framework.Assert;
import java.util.Collection;
import java.util.HashSet;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.model.leafs.Video;
import com.netflix.falkor.PQL;
import com.netflix.falkor.Ref;
import com.netflix.model.branches.SummarizedList;
import com.netflix.falkor.BranchMap;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.falkor.ServiceProvider;
import junit.framework.TestCase;

public class CachedModelProxyTest extends TestCase
{
    private static final ServiceProvider nullServiceProvider;
    
    static {
        nullServiceProvider = new ServiceProvider() {
            @Override
            public NetflixService getService() {
                return null;
            }
        };
    }
    
    private ModelProxy<?> createModelProxyWithBasicLolomos() {
        final Root root = new Root();
        final CachedModelProxy cachedModelProxy = new CachedModelProxy<Object>(CachedModelProxyTest.nullServiceProvider, root, null);
        final BranchMap branchMap = (BranchMap)root.getOrCreate("lolomos");
        final SummarizedList list = (SummarizedList)((BranchMap)root.getOrCreate("lists")).getOrCreate("23432");
        final BranchMap branchMap2 = (BranchMap)root.getOrCreate("videos");
        ((Ref)branchMap.getOrCreate("0")).setRefPath(PQL.create("lists", "23432"));
        ((Ref)list.getOrCreate("0")).setRefPath(PQL.create("videos", "2143"));
        ((Ref)list.getOrCreate("1")).setRefPath(PQL.create("videos", "7890"));
        ((Video.Summary)((FalkorVideo)branchMap2.getOrCreate("2143")).getOrCreate("summary")).title = "Dawn of the Dead";
        return cachedModelProxy;
    }
    
    public void testGetForEmptyCache() {
        final CachedModelProxy cachedModelProxy = new CachedModelProxy(CachedModelProxyTest.nullServiceProvider, (T)new Root(), null);
        final HashSet<PQL> set = new HashSet<PQL>();
        final PQL create = PQL.create("lolomos", PQL.range(19));
        set.add(create);
        final PQL create2 = PQL.create("videos", "47374", PQL.array("detail", "summary"));
        set.add(create2);
        final List<PQL> missingPqls = cachedModelProxy.get(set).missingPqls;
        Assert.assertEquals(2, missingPqls.size());
        Assert.assertTrue(missingPqls.contains(create));
        Assert.assertTrue(missingPqls.contains(create2));
    }
    
    public void testGetForMissingPqls() {
        final ModelProxy<?> modelProxyWithBasicLolomos = this.createModelProxyWithBasicLolomos();
        final PQL create = PQL.create("lolomos", 0, 0, "summary");
        Assert.assertEquals("Dawn of the Dead", ((Video.Summary)modelProxyWithBasicLolomos.getValue(create)).title);
        Assert.assertEquals((Object)PQL.create("videos", "7890"), (Object)((Ref)modelProxyWithBasicLolomos.getValue(PQL.create("lolomos", 0, 1))).getRefPath());
        final PQL create2 = PQL.create("lolomos", 0, 1, "summary");
        Assert.assertNull((Object)modelProxyWithBasicLolomos.getValue(create2));
        final HashSet<PQL> set = new HashSet<PQL>();
        set.add(create);
        set.add(create2);
        final CachedModelProxy.GetResult value = modelProxyWithBasicLolomos.get(set);
        final List<PQL> missingPqls = value.missingPqls;
        final List<PQL> foundPqls = value.foundPqls;
        Assert.assertEquals(1, missingPqls.size());
        Assert.assertTrue(missingPqls.contains(PQL.create("videos", "7890", "summary")));
        Assert.assertEquals(1, foundPqls.size());
        Assert.assertTrue(foundPqls.contains(PQL.create("videos", "2143", "summary")));
    }
    
    public void testGetValue() {
        final Root root = new Root();
        final CachedModelProxy cachedModelProxy = new CachedModelProxy(CachedModelProxyTest.nullServiceProvider, root, null);
        final Video.Summary summary = (Video.Summary)((FalkorVideo)((BranchMap)root.getOrCreate("videos")).getOrCreate("23")).getOrCreate("summary");
        summary.boxartUrl = "test_url";
        final Video.Summary summary2 = (Video.Summary)cachedModelProxy.getValue(PQL.create("videos", "23", "summary"));
        Assert.assertEquals((Object)summary, (Object)summary2);
        Assert.assertEquals(summary2.boxartUrl, "test_url");
    }
    
    public void testGetValueUsingReference() {
        final Root root = new Root();
        final CachedModelProxy cachedModelProxy = new CachedModelProxy(CachedModelProxyTest.nullServiceProvider, root, null);
        final BranchMap branchMap = (BranchMap)root.getOrCreate("videos");
        final Ref ref = (Ref)((SummarizedList)((BranchMap)root.getOrCreate("lists")).getOrCreate("213")).getOrCreate("0");
        ref.setRefPath(PQL.create("videos", "23"));
        final FalkorVideo falkorVideo = (FalkorVideo)branchMap.getOrCreate("23");
        final Video.Summary summary = (Video.Summary)falkorVideo.getOrCreate("summary");
        summary.boxartUrl = "test_url";
        final Video.Summary summary2 = (Video.Summary)cachedModelProxy.getValue(PQL.create("videos", "23", "summary"));
        final Video.Summary summary3 = (Video.Summary)cachedModelProxy.getValue(PQL.create("lists", "213", "0", "summary"));
        Assert.assertEquals((Object)summary3, (Object)summary2);
        Assert.assertTrue(ref.getHardValue() != null);
        cachedModelProxy.getValue(PQL.create("lists", "213", "0", "summary"));
        final List<Ref> list = falkorVideo.getReferences().toList();
        Assert.assertTrue(list.contains(ref));
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(cachedModelProxy.getValue(PQL.create("lists", "213", "0")), (Object)ref);
        Assert.assertEquals((Object)summary, (Object)summary2);
        Assert.assertEquals("test_url", summary2.boxartUrl);
        Assert.assertEquals((Object)summary, (Object)summary3);
        Assert.assertEquals("test_url", summary3.boxartUrl);
    }
    
    public void testGetWithRangesForMissingPqls() {
        final ModelProxy<?> modelProxyWithBasicLolomos = this.createModelProxyWithBasicLolomos();
        Assert.assertEquals("Dawn of the Dead", ((Video.Summary)modelProxyWithBasicLolomos.getValue(PQL.create("lolomos", 0, 0, "summary"))).title);
        Assert.assertEquals((Object)PQL.create("videos", "7890"), (Object)((Ref)modelProxyWithBasicLolomos.getValue(PQL.create("lolomos", 0, 1))).getRefPath());
        Assert.assertNull((Object)modelProxyWithBasicLolomos.getValue(PQL.create("lolomos", 0, 1, "summary")));
        final HashSet<PQL> set = new HashSet<PQL>();
        set.add(PQL.create("lolomos", 0, PQL.range(1), "summary"));
        final List<PQL> missingPqls = modelProxyWithBasicLolomos.get(set).missingPqls;
        Assert.assertEquals(1, missingPqls.size());
        Assert.assertTrue(missingPqls.contains(PQL.create("videos", "7890", "summary")));
    }
}

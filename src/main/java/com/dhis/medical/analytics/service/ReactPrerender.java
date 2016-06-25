package com.dhis.medical.analytics.service;

import com.dhis.medical.analytics.model.HtmlString;
import com.dhis.medical.analytics.model.PerformanceData;
import de.cronn.jsxtransformer.CachedJsxTransformer;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import sun.misc.PerformanceLogger;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by dehan on 6/18/16.
 */
@Service
public class ReactPrerender {
    CachedJsxTransformer assetCache;
    ScriptEngine scriptEngine;


    public ReactPrerender() throws IOException, ScriptException, FileNotFoundException{
        scriptEngine = (new ScriptEngineManager()).getEngineByName("nashorn");
        //JSX Transformations

        scriptEngine.eval(read("static/libs/nashorn-polyfill.js"));
        scriptEngine.eval(read("static/libs/react.js"));
        scriptEngine.eval(read("static/libs/react-dom-server.js"));
        scriptEngine.eval(read("static/libs/showdown.js"));
        CachedJsxTransformer assetCache   = new CachedJsxTransformer(false, false);
        final CachedJsxTransformer.AssetEntry assetEntry = assetCache.get("uilogic.js", (key) -> IOUtils
                .toString(read("static/jsx/uilogic.js")));
        scriptEngine.eval(assetEntry.content);

    }


    public HtmlString getHospitalPerformancePage(List<PerformanceData> performanceDatas) throws ScriptException, NoSuchMethodException {

        Invocable inv = (Invocable) scriptEngine;
        String retValue = (String) inv.invokeFunction("renderInServer",performanceDatas);
        return new HtmlString(retValue);

    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }

}

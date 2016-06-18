package com.dhis.medical.analytics.service;

import de.cronn.jsxtransformer.CachedJsxTransformer;
import org.apache.commons.io.IOUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by dehan on 6/18/16.
 */
public class ReactPrerender {
    CachedJsxTransformer assetCache;
    ScriptEngine scriptEngine;
    public ReactPrerender() throws IOException, ScriptException, FileNotFoundException{
        scriptEngine = (new ScriptEngineManager()).getEngineByName("nashorn");
        //JSX Transformations

        scriptEngine.eval(new FileReader("src/main/resources/libs/nashorn-polyfill.js"));
        scriptEngine.eval(new FileReader("src/main/resources/libs/react.js"));
        scriptEngine.eval(new FileReader("src/main/resources/libs/react-dom-server.js"));
        scriptEngine.eval(new FileReader("src/main/resources/libs/showdown.js"));
        CachedJsxTransformer assetCache   = new CachedJsxTransformer(false, false);
        final CachedJsxTransformer.AssetEntry assetEntry = assetCache.get("commentBox.js", (key) -> IOUtils
                .toString(new FileInputStream("src/main/resources/jsx/commentBox.js"), StandardCharsets.UTF_8));
            System.out.println(assetEntry.content);

        scriptEngine.eval(assetEntry.content);

    }


}

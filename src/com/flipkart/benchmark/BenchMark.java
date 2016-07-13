package com.flipkart.benchmark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jitendra.k on 13/07/16.
 */
public abstract class BenchMark {

    protected static final String STAGE_URL = "http://ekart-flash-stage-0002.stage.nm.flipkart.com:21004/";
    protected static final String PROD_URL = "https://api.ekartflash.com/";

    protected final ExecutorService threadpool = Executors.newFixedThreadPool(50);

    public abstract void getReport();
}

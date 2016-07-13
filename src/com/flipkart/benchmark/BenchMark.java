package com.flipkart.benchmark;

import com.flipkart.task.PincodeData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by jitendra.k on 13/07/16.
 */
public abstract class BenchMark {

    protected long qps;
    protected List<Future> tasks = new ArrayList<Future>();
    protected long totalTime;

    protected long CALL_COUNT;
    protected static long RUNTIME = 3;
    protected long failures;


    protected final ExecutorService threadpool = Executors.newFixedThreadPool(50);

    public long getFailures() {
        return failures;
    }

    public double getAverageTime() {
        return totalTime / (double) (tasks.size());
    }

    public void getReport() {
        System.out.println("Class : " + this.getClass() );
        System.out.println("QPS : " + qps );
        System.out.println("Average Time : " + getAverageTime() );
        System.out.println("Total : " + tasks.size() + "  Failures : " + getFailures() );
    }

}

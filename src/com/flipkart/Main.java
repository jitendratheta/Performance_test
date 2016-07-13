package com.flipkart;

import com.flipkart.benchmark.PincodeDataBM;
import com.flipkart.task.PincodeData;

import java.util.Date;
import java.util.concurrent.*;

public class Main {

    private static final String STAGE_URL = "http://ekart-flash-stage-0002.stage.nm.flipkart.com:21004/";
    private static final String PROD_URL = "https://api.ekartflash.com/";

//    public static void main(String[] args) {
//        Main main = new Main();
//        main.testOnVariableQPS(10, 1000, 10);
//    }


    private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

    public static void main(String args[]) throws InterruptedException, ExecutionException {
        Data.initialize();
        PincodeData pincodeData = new PincodeData();
        PincodeDataBM pincodeDataBM = new PincodeDataBM(10, pincodeData);
        pincodeDataBM.run();
        pincodeDataBM.getReport();
        return;
    }

    void testOnVariableQPS(int min, int max, int inr) {

        String url = STAGE_URL + "api/v1/serviceability/560034/pickup";

        for (int i = min; i <= max; i += inr) {
            try {
                //Thread.sleep(1000/i);
                HttpHelper.sendGet(url);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Response res = new Response();
            System.out.println(res.getResponseTime());
        }

    }
}

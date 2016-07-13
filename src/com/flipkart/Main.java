package com.flipkart;

import com.flipkart.benchmark.PincodeDataBM;
import com.flipkart.benchmark.ServiceabilityDataBM;
import com.flipkart.task.PincodeData;
import com.flipkart.task.ServiceabilityData;

import java.util.Date;
import java.util.concurrent.*;

public class Main {

    private static final String STAGE_URL = "http://ekart-flash-stage-0002.stage.nm.flipkart.com:21004/";
    private static final String PROD_URL = "https://api.ekartflash.com/";

//    public static void main(String[] args) {
//        Main main = new Main();
//        main.testOnVariableQPS(10, 1000, 10);
//    }


    public static void main(String args[]) throws InterruptedException, ExecutionException {
        Data.initialize();
        PincodeData pincodeData = new PincodeData();

//        pincodeDataBM = new PincodeDataBM(10, pincodeData);
//        pincodeDataBM.run();
//        pincodeDataBM.getReport();
//        pincodeDataBM = new PincodeDataBM(20, pincodeData);
//        pincodeDataBM.run();
//        pincodeDataBM.getReport();
//        pincodeDataBM = new PincodeDataBM(50, pincodeData);
//        pincodeDataBM.run();
//        pincodeDataBM.getReport();

//        PincodeDataBM pincodeDataBM = new PincodeDataBM(2, pincodeData);
//        pincodeDataBM.run();
//        pincodeDataBM.getReport();
//        pincodeDataBM = new PincodeDataBM(100, pincodeData);
//        pincodeDataBM.run();
//        pincodeDataBM.getReport();
//        pincodeDataBM = new PincodeDataBM(200, pincodeData);
//        pincodeDataBM.run();
//        pincodeDataBM.getReport();
//        PincodeDataBM pincodeDataBM = new PincodeDataBM(500, pincodeData);
//        pincodeDataBM.run();
//        pincodeDataBM.getReport();
//        PincodeDataBM pincodeDataBM = new PincodeDataBM(1000, pincodeData);
//        pincodeDataBM.run();
//        pincodeDataBM.getReport();

        ServiceabilityData serviceabilityData = new ServiceabilityData();
        ServiceabilityDataBM serviceabilityDataBM  = new ServiceabilityDataBM(10, serviceabilityData);
        serviceabilityDataBM.run();
        serviceabilityDataBM.getReport();

        serviceabilityDataBM  = new ServiceabilityDataBM(1000, serviceabilityData);
        serviceabilityDataBM.run();
        serviceabilityDataBM.getReport();

        serviceabilityDataBM  = new ServiceabilityDataBM(20, serviceabilityData);
        serviceabilityDataBM.run();
        serviceabilityDataBM.getReport();

        serviceabilityDataBM  = new ServiceabilityDataBM(50, serviceabilityData);
        serviceabilityDataBM.run();
        serviceabilityDataBM.getReport();

        serviceabilityDataBM  = new ServiceabilityDataBM(100, serviceabilityData);
        serviceabilityDataBM.run();
        serviceabilityDataBM.getReport();

        serviceabilityDataBM  = new ServiceabilityDataBM(200, serviceabilityData);
        serviceabilityDataBM.run();
        serviceabilityDataBM.getReport();

        serviceabilityDataBM  = new ServiceabilityDataBM(500, serviceabilityData);
        serviceabilityDataBM.run();
        serviceabilityDataBM.getReport();

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

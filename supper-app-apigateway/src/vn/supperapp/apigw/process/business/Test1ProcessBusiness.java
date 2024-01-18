package vn.supperapp.apigw.process.business;

import vn.supperapp.apigw.process.confgis.ThreadProcessConfigInfo;
import org.hibernate.Session;

public class Test1ProcessBusiness extends BaseProcessBusiness {

    public Test1ProcessBusiness(ThreadProcessConfigInfo config) {
        super(config);
    }

    @Override
    protected void process() {
        logger.info("Thread is running (isRunning = " + isRunning + " )");
        if (!isRunning) {
            logger.info("#Stop thread");
            this.stop();
            return;
        }

        logger.info("# is running: " + System.currentTimeMillis());
        Session session = null;
        try {
            logger.info("# - TEST1: " + System.currentTimeMillis());

            //Sleep for 1 second
            logger.info("Thread sleep in " + (this.sleepTime * 1000L) + " ms");
            Thread.sleep(this.sleepTime * 1000L);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#process - ERROR: ", ex);
        }
    }

    //region - Common configs
    @Override
    public void stop() {
        isRunning = false;
        super.stop();
        logger.info("STOP");
    }

    @Override
    public void start() {
        isRunning = true;
        super.start();
        logger.info("START");
    }

    @Override
    public void restart() {
        this.stop();
        this.start();
    }
}

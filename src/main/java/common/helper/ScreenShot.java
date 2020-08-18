package common.helper;

import java.io.File;
import java.io.IOException;


import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//responsible for taking screen shoots of any error
public class ScreenShot {

    private static Logger log = LogManager.getLogger(ScreenShot.class.getName());

    public String getScreenShotPath(String methodName, WebDriver driver) throws IOException {

        TakesScreenshot screen = (TakesScreenshot) driver;
        File source = screen.getScreenshotAs(OutputType.FILE);
        String destinationPath = System.getProperty("user.dir") + "/reports/" + methodName + ".png";// path to save
        FileUtils.copyFile(source, new File(destinationPath));
        log.info("Taking error screen shot and saving it to "+destinationPath);

        return destinationPath;
    }
}

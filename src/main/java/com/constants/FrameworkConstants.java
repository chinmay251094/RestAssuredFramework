package com.constants;

import com.utils.DateTimeUtils;
import com.utils.FrameworkConfig;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

public class FrameworkConstants {
    /**
     * If variable/method is non-static then have @Getters at class level else at field level
     */
    public static final String BOLD_START = "<b>";
    public static final String BOLD_END = "</b>";
    public static final String ICON_AUTHOR = "&#9998;";
    public static final String ICON_CATEGORY = "&#128193;";
    public static final String ICON_MANAGER = "<i class='fa fa-users' style='font-size:18px'></i>";
    public static final String ICON_TEAM = "<i class='fa fa-user-circle' style='font-size:18px'></i>";
    public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
    public static final String ICON_SMILEY_SKIP = "<i class='fas fa-frown-open'></i>";
    public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";
    public static final String ICON_BUG = "<i class='fa fa-bug' style='font-size:18px'></i>";
    private static @Getter
    final String requestJsonFilePath = System.getProperty("user.dir") + "/src/test/resources/json/";
    private static @Getter
    final String responseJsonFilePath = System.getProperty("user.dir") + "/output/";
    private static @Getter
    final String configPropertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
    private static String extentReportFilePath = "";
    private static final FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

    public static String getExtentReportFilePath() throws Exception {
        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    /**
     * Creates the path for the Extent report file. If the framework configuration specifies not to
     * override existing reports, the path will include a timestamp in the file name.
     *
     * @return the path to the Extent report file
     * @throws Exception if an error occurs while creating the report file
     */
    private static String createReportPath() throws Exception {
        if (frameworkConfig.overridereports().equalsIgnoreCase("no")) {
            return EXTENTREPORTFOLDERPATH + DateTimeUtils.generateDateTime() + " Test Report.html";
        } else {
            return EXTENTREPORTFOLDERPATH + "/index.html";
        }
    }
}

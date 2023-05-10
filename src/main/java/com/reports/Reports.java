package com.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.constants.FrameworkConstants;
import com.enums.Author;
import com.enums.Category;

import java.awt.*;
import java.io.File;
import java.util.Objects;

import static com.reports.ReportsManager.getReport;

public class Reports {
    static ExtentReports extentReports;

    private Reports() {
    }

    public static void initReports() throws Exception {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
            extentReports.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("RestAssured Reports");
            spark.config().setReportName("Standard Test Reports");
        }
    }

    public static void flushReports() throws Exception {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
        ReportsManager.unLoad();
        Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
    }

    public static void createTests(String testcasename) {
        ReportsManager.setReport(extentReports.createTest(testcasename));
    }

    public static void assignTestAttributes(String description, Author[] authors, Category[] categories) {
        if (description != null && !description.isEmpty()) {
            createTests(description);
            for (Author author : authors) {
                getReport().assignAuthor(author.name());
            }
            for (Category category : categories) {
                getReport().assignCategory(category.name());
            }
        } else {
            throw new IllegalArgumentException("Test Description must not be null or empty");
        }
    }

    /**
     * Returns a comma-separated list of author names from the given array of Author objects.
     *
     * @param authors An array of Author objects representing the authors of a test.
     * @return A String representing the comma-separated list of author names.
     */
    public static String getAuthorList(Author[] authors) {
        StringBuilder authorList = new StringBuilder();
        for (Author author : authors) {
            authorList.append(author.name()).append(", ");
        }
        if (authorList.length() > 2) {
            return authorList.toString().substring(0, authorList.length() - 2);
        } else {
            return "";
        }
    }

    /**
     * Returns a comma-separated list of category names from the given array of Category objects.
     *
     * @param categories An array of Category objects representing the categories of a test.
     * @return A String representing the comma-separated list of category names.
     */
    public static String getCategoryList(Category[] categories) {
        StringBuilder categoryList = new StringBuilder();
        for (Category category : categories) {
            categoryList.append(category.name()).append(", ");
        }
        return categoryList.toString().substring(0, categoryList.length() - 2);
    }
}
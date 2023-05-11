package com.listeners;

import com.annotations.TestDescription;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.enums.Author;
import com.enums.Category;
import com.reports.Reports;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

import static com.constants.FrameworkConstants.*;
import static com.reports.Logger.*;
import static com.reports.Reports.*;

public class TestListener implements ITestListener, ISuiteListener {
    private static final String errorNoDescription = "Test Description must not be null or empty";

    @Override
    @SneakyThrows
    public void onStart(ISuite suite) {
        Reports.initReports();
    }

    @Override
    @SneakyThrows
    public void onFinish(ISuite suite) {
        Reports.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Author[] authors = new Author[0];
        Category[] categories = new Category[0];
        // Get the test description from the TestDescription annotation if present
        String description;
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            authors = testDescription.author();
            categories = testDescription.category();
            assignTestAttributes(description, authors, categories);
        }

        info(ICON_AUTHOR + " Author(s): " + "<b>" + getAuthorList(authors) + "</b>");
        info(ICON_CATEGORY + " Category: " + "<b>" + getCategoryList(categories) + "</b>");
    }

    @Override
    @SneakyThrows
    public void onTestSuccess(ITestResult result) {
        String description = null;
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            if (description != null && !description.isEmpty()) {
                String logText = "<b>" + description + " is passed.</b>" + "  " + ICON_SMILEY_PASS;
                Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
                pass(markup_message);
            } else {
                throw new IllegalArgumentException(errorNoDescription);
            }
        }
    }

    @Override
    @SneakyThrows
    public void onTestFailure(ITestResult result) {
        String description = null;
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            if (description != null && !description.isEmpty()) {
                try {
                    // Log failure message with stack trace
                    fail(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
                    String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
                    String message = "<details><summary><b><font color=red> Exception occured, click to see details: "
                            + ICON_SMILEY_FAIL + " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
                            + "</details> \n";

                    fail(message);
                    String logText = BOLD_START + description + " has failed." + BOLD_END + "  " + ICON_SMILEY_FAIL;
                    Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.RED);
                    fail(markup_message);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException(errorNoDescription);
            }
        }
    }

    @Override
    @SneakyThrows
    public void onTestSkipped(ITestResult result) {
        String description = null;
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            if (description != null && !description.isEmpty()) {
                skip(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
                skip(description + " has been skipped.");
                String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>" + "  " + ICON_SMILEY_SKIP;
                Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
                skip(markup_message);
            } else {
                throw new IllegalArgumentException(errorNoDescription);
            }
        }
    }
}

package com.reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import static com.reports.ReportsManager.getReport;

public class Logger {
    private Logger() {
    }

    public static void pass(String message) {
        getReport().pass(message);
    }

    public static void pass(Markup message) {
        getReport().pass(message);
    }

    public static void fail(String message) {
        getReport().fail(message);
    }

    public static void fail(Markup message) {
        getReport().fail(message);
    }

    public static void skip(String message) {
        getReport().skip(message);
    }

    public static void skip(Markup message) {
        getReport().skip(message);
    }

    public static void info(Markup message) {
        getReport().info(message);
    }

    public static void info(String message) {
        getReport().info(message);
    }

    public static void logResponse(String message) {
        getReport().pass(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
    }
}

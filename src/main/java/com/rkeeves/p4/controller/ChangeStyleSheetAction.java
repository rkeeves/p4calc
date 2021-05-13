package com.rkeeves.p4.controller;

import lombok.RequiredArgsConstructor;
import org.tinylog.Logger;

import java.util.List;

@RequiredArgsConstructor
public class ChangeStyleSheetAction implements Runnable {

    private final StyleSheetService styleSheetService;

    private final List<String> styleSheets;

    @Override
    public void run() {
        Logger.debug("Setting theme to " + styleSheets.toString());
        styleSheetService.setStyleSheets(styleSheets);
    }
}

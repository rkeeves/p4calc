package com.rkeeves.p4.controller;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.BiFunction;

public class FileChooserController {

    public Optional<File> chooseJSONFileForOpen(){
        return chooseJSONFile("Open", FileChooser::showOpenDialog);
    }

    public Optional<File> chooseJSONFileForSave(){
        return chooseJSONFile("Save", FileChooser::showSaveDialog);
    }

    private Optional<File> chooseJSONFile(String pickerTitle , BiFunction<FileChooser, Window, File> showFunction){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(pickerTitle);
        fileChooser.setInitialDirectory(getCurrentDir());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
        return Optional.ofNullable(showFunction.apply(fileChooser,null));
    }

    private File getCurrentDir(){
        return new File(Path.of("").toAbsolutePath().toString());
    }
}

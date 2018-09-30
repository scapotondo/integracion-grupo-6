package com.integracion.grupo6.batch.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;

@Component
public class FileUtils {

    public String[] listCsvFiles(String directory) {
        return new File(directory).list(buildCsvFilter());
    }

    private FilenameFilter buildCsvFilter() {
        return (file, s) -> s.endsWith(".csv");
    }

    public void deleteCsv(String path) {
        new File(path).delete();
    }

}

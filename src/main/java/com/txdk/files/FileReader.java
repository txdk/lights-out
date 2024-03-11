package com.txdk.files;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

public class FileReader {
    
    private final Charset CHARSET = Charsets.UTF_8;

    public String readTextFromFile(String path)
    {
        String text;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        try 
        {
            text = CharStreams.toString(new InputStreamReader(inputStream, CHARSET));
        }
        catch (IOException ioException) {text = "Error reading file";}
        return text;
    }
}

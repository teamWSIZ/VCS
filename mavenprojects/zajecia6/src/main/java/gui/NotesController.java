package gui;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;

public class NotesController {

    public void doSomething() throws Exception {
        System.out.println("Sure, I'm doing it!");

        //zapisywanie
        String doZapisu = "Trump claims he took himself out of the running for Time's 'Person of the Year'";
        File file = new File("test.txt");
        Files.write(doZapisu, file, Charsets.UTF_8);

        //wczytywanie
        String result = Files.toString(file, Charsets.UTF_8);


    }
}

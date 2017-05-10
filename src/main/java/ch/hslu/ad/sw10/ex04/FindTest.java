package ch.hslu.ad.sw10.ex04;

import java.io.File;
import java.util.Optional;

public class FindTest {
    public static void main(String[] args) {
        final String directory = "/home/paedubucher/workspace/ad_exercises/";
        final String regex = ".*FindTask.*";
        FindTask task = new FindTask(new File(directory), regex);
        Optional<File> opt = task.invoke();
        if (opt.isPresent()) {
            System.out.println(opt.get().toString());
        } else {
            System.out.println("nothing found");
        }
    }
}

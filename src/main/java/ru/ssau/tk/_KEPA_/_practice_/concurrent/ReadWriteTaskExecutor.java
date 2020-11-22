package ru.ssau.tk._KEPA_._practice_.concurrent;

import java.util.*;

import ru.ssau.tk._KEPA_._practice_.functions.*;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction listFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        List<Thread> myThread = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            myThread.add(new Thread(new ReadWriteTask(listFunction)));
        }
        for (Thread i : myThread) {
            i.start();
            Thread.sleep(2_000);
            System.out.println(listFunction.toString());
        }
    }

}

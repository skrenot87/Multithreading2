package ru.geekbrains.race;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore signal;
    public Tunnel(Semaphore signal) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.signal = signal;
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                signal.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                signal.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

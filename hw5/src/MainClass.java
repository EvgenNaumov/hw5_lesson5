
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass {

    public static final int CARS_COUNT = 4;
    public static List<Car> placeCar;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        CyclicBarrier cyclicBarrier = new CyclicBarrier(cars.length);
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(cars.length);

        ThreadGroup threadGroup = new ThreadGroup("groupCar");

        new Thread(()->{
            try{

                for (int i = 0; i < cars.length; i++) {
                    final int w = i;
                    cars[w] = new Car(race, 20 + (int) (Math.random() * 10));
                    new Thread(cars[w]).start();
                }
                cyclicBarrier.await();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        for (int i = 0; i < cars.length; i++) {
            final int w = i;
            ListIterator<Stage> itrStage = race.getStages().listIterator();
            while (itrStage.hasNext()) {
                itrStage.next().go(cars[w]);

            }
        }



    }

}



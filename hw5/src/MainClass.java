
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

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));

            final int w = i;

            new Thread(()->{
                try{
                    new Thread(cars[w]).start();
                    cyclicBarrier.await();

//                    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
/*
                    if (Thread.currentThread().getName().){
                        placeCar.add(cars[w]);
                    }

                    if (placeCar.size()==CARS_COUNT){
                        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                    }
*/
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();



//            ListIterator<Stage> itrStage = race.getStages().listIterator();
//            while (itrStage.hasNext()){
//                itrStage.next().go(cars[w]);
//
//            }

        }




    }

}



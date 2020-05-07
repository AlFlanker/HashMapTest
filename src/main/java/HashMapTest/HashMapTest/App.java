package HashMapTest.HashMapTest;

import java.util.HashMap;

/**
 * Тест мапы. Кейс не совсем корректного использвоания мапы - вызов put в лямбде привдит к дублированию ключей
 * в баги JDK уже фигурирует. Исправленно для Java 9+
 *
 */
public class App 
{
    static HashMap<Long, String> TEST = new HashMap<>();
    public static void main( String[] args ) {
        test(1L);
        test(2L);
        test(3L);
        test(4L);
        test(22L);
        test(2L);
        test(6L);
        System.out.printf("size of map: %d, real size: %d \n", TEST.size(), TEST.entrySet().stream()
                .distinct().count());
        System.out.println( "Мапа: " + TEST);
    }

    public static void test(Long l) {
        TEST.computeIfAbsent(l, t -> {
            TEST.put(l, t.toString()+"+");
            return l.toString() + "*";
        });
    }
}

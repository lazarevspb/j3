package lesson7;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Homework for lesson #7
 *
 * @author Valeriy Lazarev
 * @since 12.12.2020
 */
public class PerformingTests {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        start(ClassUnderTest.class);
    }

    public static void start(Class<?> aClass) throws InvocationTargetException, IllegalAccessException {
        Map<Integer, Method> map = new TreeMap<>();
        Method beforeM = null;
        Method afterM = null;
        Method[] methods = aClass.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeM == null) {
                    beforeM = m;
                } else throw new RuntimeException("More than one annotated method @BeforeSuite");

            } else if (m.isAnnotationPresent(Test.class)) {
                int p = m.getAnnotation(Test.class).priority();
                map.put(p, m);

            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (afterM == null) {
                    afterM = m;
                } else throw new RuntimeException("More than one annotated method @AfterSuite");
            }
        }

        if (beforeM != null) beforeM.invoke(null);
        invokeMethodReverseMap(map);
        if (afterM != null) afterM.invoke(null);
    }

    private static void invokeMethodReverseMap(Map<Integer, Method> map) throws IllegalAccessException, InvocationTargetException {
        List<Integer> keys = new ArrayList<>(map.keySet());
        for (int i = keys.size() - 1; i >= 0; i--) {
            map.get(keys.get(i)).invoke(null);
        }
    }
}

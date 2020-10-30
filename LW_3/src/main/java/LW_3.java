import java.util.function.Function;
import java.util.function.Predicate;
import java.util.ArrayList;

public class LW_3 {

    public static <F, T> Iterable<T> transform(Iterable<F> iterObject, Function<? super F, ? extends T> func)
    {
        ArrayList<T> result = new ArrayList<>();
        for (F temp: iterObject)
            result.add(func.apply(temp));

        return result;
    }

    public static <T> Iterable<T> filter(Iterable<T> iterObject, Predicate<? super T> condition) {
        ArrayList<T> result = new ArrayList<>();
        for (T temp: iterObject)
            if (condition.test(temp))
                result.add(temp);

        return result;
    }
}

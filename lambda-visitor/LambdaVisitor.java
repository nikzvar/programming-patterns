import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class LambdaVisitor<T, R> implements Function<T, R> {
    
    private final Map<Class<?>, Function<Object, R>> handlers = new HashMap<>();

    public <SubT extends T> Acceptor<SubT, T, R> on(Class<SubT> clazz) {
        return new Acceptor<>(this, clazz);
    }

    @Override
    public R apply(T targetObject) {
        Function<Object, R> function = handlers.get(targetObject.getClass());
        if (function == null) {
            throw new IllegalArgumentException("Нет обработчика для класса: " + targetObject.getClass());
        }
        return function.apply(targetObject);
    }

    public static class Acceptor<SubT, T, R> {
        private final LambdaVisitor<T, R> visitor;
        private final Class<SubT> clazz;

        public Acceptor(LambdaVisitor<T, R> visitor, Class<SubT> clazz) {
            this.visitor = visitor;
            this.clazz = clazz;
        }

        public LambdaVisitor<T, R> execute(Function<SubT, R> function) {
            visitor.handlers.put(clazz, (Object obj) -> function.apply(clazz.cast(obj)));
            return visitor;
        }
    }
}

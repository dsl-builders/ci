package com.agorapulse.ci;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface CI {

    Repository getRepository();
    String getCommit();

    Optional<String> getBuildNumber();
    Optional<String> getBranch();
    Optional<String> getTag();

    default <T extends CI> void is(Class<T> type, Consumer<T> code) {
        if (type.isInstance(this)) {
            code.accept(type.cast(this));
        }
    }

    default <T extends CI, R> Optional<R> is(Class<T> type, Function<T, R> code) {
        if (type.isInstance(this)) {
            return Optional.ofNullable(code.apply(type.cast(this)));
        }
        return Optional.empty();
    }

    static Optional<CI> getCurrent() {
        return Factory.create();
    }

}

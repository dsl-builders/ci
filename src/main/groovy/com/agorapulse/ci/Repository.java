package com.agorapulse.ci;

import java.util.Optional;

public class Repository {

    public static Repository parse(String slug) {
        String[] parts = slug.split("/");
        if (parts.length == 2) {
            return new Repository(parts[0], parts[1]);
        }
        return new Repository(null, slug);
    }

    private final String owner;
    private final String name;

    public Repository(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public Optional<String> getOwner() {
        return Optional.ofNullable(owner);
    }

    public String getName() {
        return name;
    }
}

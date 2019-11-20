package com.agorapulse.ci;

import java.util.Objects;
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

    @Override
    public String toString() {
        return "Repository{owner='" + owner + "', name='" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(owner, that.owner) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, name);
    }
}

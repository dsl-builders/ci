package com.agorapulse.ci;

import java.util.Optional;

public enum TravisCI implements CI {

    INSTANCE;

    @Override
    public Repository getRepository() {
        return Repository.parse(System.getenv("TRAVIS_REPO_SLUG"));
    }

    @Override
    public String getCommit() {
        return System.getenv("TRAVIS_COMMIT");
    }

    @Override
    public Optional<String> getBuildNumber() {
        return Optional.of(System.getenv("TRAVIS_BUILD_NUMBER"));
    }

    @Override
    public Optional<String> getBranch() {
        return Optional.ofNullable(System.getenv("TRAVIS_BRANCH"));
    }

    @Override
    public Optional<String> getTag() {
        return Optional.ofNullable(System.getenv("TRAVIS_TAG"));
    }

}

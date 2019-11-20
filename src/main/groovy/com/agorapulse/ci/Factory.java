package com.agorapulse.ci;

import java.util.Optional;

class Factory {

    static Optional<CI> create() {
        // TODO: service providers
        if ("true".equals(System.getenv("GITHUB_ACTIONS"))) {
            return Optional.of(GithubActions.INSTANCE);
        }
        return Optional.empty();
    }

}

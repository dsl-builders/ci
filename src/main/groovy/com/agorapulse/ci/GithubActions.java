package com.agorapulse.ci;

import java.util.Optional;

public enum GithubActions implements CI {

    INSTANCE;

    @Override
    public Repository getRepository() {
        return Repository.parse(System.getenv("GITHUB_REPOSITORY"));
    }

    @Override
    public String getCommit() {
        return System.getenv("GITHUB_SHA");
    }

    @Override
    public Optional<String> getBuildNumber() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getBranch() {
        return Optional.ofNullable(extractFromRef("refs/heads/"));
    }

    @Override
    public Optional<String> getTag() {
        return Optional.ofNullable(extractFromRef("refs/tags/"));
    }

    /**
     * @return The name of the workflow
     */
    public String getWorkflow() {
        return System.getenv("GITHUB_WORKFLOW");
    }

    /**
     * @return The unique identifier (id) of the action
     */
    public String getAction() {
        return System.getenv("GITHUB_ACTION");
    }

    /**
     * @return The name of the person or app that initiated the workflow. For example, octocat
     */
    public String getActor() {
        return System.getenv("GITHUB_ACTOR");
    }

    /**
     * @return The name of the webhook event that triggered the workflow
     */
    public String getEventName() {
        return System.getenv("GITHUB_EVENT_NAME");
    }

    /**
     * @return The path of the file with the complete webhook event payload. For example, /github/workflow/event.json
     */
    public String getEventPath() {
        return System.getenv("GITHUB_EVENT_PATH");
    }

    /**
     * @return The GitHub workspace directory path. The workspace directory contains a subdirectory with a copy of your
     * repository if your workflow uses the actions/checkout action. If you don't use the actions/checkout action,
     * the directory will be empty. For example, /home/runner/work/my-repo-name/my-repo-name
     */
    public String getWorkspace() {
        return System.getenv("GITHUB_WORKSPACE");
    }

    private String extractFromRef(String prefix) {
        String ref = System.getenv("GITHUB_REF");
        if (ref.startsWith(prefix)) {
            return ref.substring(prefix.length());
        }
        return null;
    }

}

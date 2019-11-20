package com.agorapulse.ci

import com.agorapulse.testing.fixt.Fixt
import org.junit.Rule
import org.junit.contrib.java.lang.system.EnvironmentVariables
import org.yaml.snakeyaml.Yaml
import spock.lang.Specification

class GithubActionsSpec extends Specification {

    @Rule EnvironmentVariables env = new EnvironmentVariables()
    @Rule Fixt fixt = Fixt.create(GithubActionsSpec)

    void setup() {
        Set<String> variableNames = new LinkedHashSet<>(System.getenv().keySet())
        variableNames.each { env.set(it, null)}
    }

    void 'master branch build'() {
        given:
            loadEnv 'master.yml'
        expect:
            CI.getCurrent().present

        when:
            CI ci = CI.getCurrent().get()

        then:
            verifyAll {
                ci instanceof GithubActions

                ci.commit == '8eee24aac46ace5cd156d351062dfce68e57e49e'
                ci.repository == new Repository('agorapulse', 'ci')

                ci.branch.present
                ci.branch.get() == 'master'

                !ci.buildNumber.present
                !ci.tag.present
            }

        when:
            GithubActions actions = ci as GithubActions

        then:
            verifyAll {
                actions.action == 'run'
                actions.actor == 'musketyr'
                actions.eventName == 'push'
                actions.eventPath == '/home/runner/work/_temp/_github_workflow/event.json'
                actions.workflow == 'Java CI'
                actions.workspace == '/home/runner/work/ci/ci'
            }
    }

    void loadEnv(String filename) {
        Yaml yaml = new Yaml()

        InputStream stream = fixt.readStream(filename)

        assert stream

        yaml.load(stream).each {
            env.set(it.key, it.value?.toString())
        }
    }
}

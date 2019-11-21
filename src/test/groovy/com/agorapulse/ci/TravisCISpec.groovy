package com.agorapulse.ci

class TravisCISpec extends CISpec {

    void 'master branch build'() {
        given:
            loadEnv 'branch.yml'
        expect:
            CI.getCurrent().present

        when:
            CI ci = CI.getCurrent().get()

        then:
            verifyAll {
                ci instanceof TravisCI

                ci.commit == '807d24b230d3f33832a7c472b84150a0299e6365'
                ci.repository == new Repository('agorapulse', 'ci')

                ci.branch.present
                ci.branch.get() == 'master'

                ci.buildNumber.present
                ci.buildNumber.get() == '1'

                !ci.tag.present
            }

        when:
            TravisCI actions = ci as TravisCI

        then:
            verifyAll {
                actions
            }
    }

}

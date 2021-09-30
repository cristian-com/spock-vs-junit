package spock

import cristian.com.spock.interactions.GatewayA
import cristian.com.spock.interactions.GatewayB
import cristian.com.spock.interactions.InteractionUseCase
import spock.lang.Specification

class InteractionsSpec extends Specification {

    GatewayA gatewayA = Mock()
    GatewayB gatewayB = Mock()
    InteractionUseCase useCase = new InteractionUseCase(gatewayA, gatewayB)

    def 'In order' () {
        when:
        useCase.send()
        then:
        1 * gatewayA.send(_)
        then:
        1 * gatewayB.send(_)
    }

    def "Never calls" () {
        when:
        useCase.send(3)
        then:
        0 * gatewayA.send(_)
    }

    def "Exactly one call" () {
        when:
        useCase.send(3)
        then:
        1 * gatewayB.send(_)
    }

    def "Between number of calls" () {
        when:
        useCase.sendNumberOfTimes(3)
        then:
        (1..4) * gatewayA.send(_)
    }

    def "At least number of calls" () {
        when:
        useCase.sendNumberOfTimes(2)
        then:
        (1.._) * gatewayA.send(_)
    }

    def "At most number of calls" () {
        when:
        useCase.sendNumberOfTimes(2)
        then:
        (_..4) * gatewayA.send(_)
    }

    def "Exact parameters matching" () {
        given:
        def value = "value"
        when:
        useCase.sendString(value)
        then:
        1 * gatewayA.send("value")
    }

    def "Parameters different of" () {
        given:
        def value = "val"
        when:
        useCase.sendString(value)
        then:
        1 * gatewayA.send(!"value")
    }

    def "Method call any single argument" () {
        given:
        def value = "val"
        when:
        useCase.sendString(value)
        then:
        1 * gatewayA.send(_)
    }

    def "Method call any list argument" () {
        given:
        def value = "val"
        when:
        useCase.sendString(value)
        then:
        1 * gatewayA.send(*_)
    }
}

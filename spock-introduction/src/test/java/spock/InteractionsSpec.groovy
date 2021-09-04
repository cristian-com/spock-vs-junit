package spock

import cristian.com.spock.interactions.GatewayA
import cristian.com.spock.interactions.GatewayB
import cristian.com.spock.interactions.InOrderUseCase
import spock.lang.Specification

class InteractionsSpec extends Specification {

    GatewayA gatewayA = Mock()
    GatewayB gatewayB = Mock()
    InOrderUseCase useCase = new InOrderUseCase(gatewayA, gatewayB)

    def 'In order' () {
        when:
        useCase.send()

        then:
        1 * gatewayA.send(_)

        and:
        1 * gatewayB.send(_)
    }

}

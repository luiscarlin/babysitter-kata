import spock.lang.Specification

class BabySitterCalculatorSpec extends Specification {

    def "when scheduled to start before 17 calculator returns exception"() {
        setup:
            BabySitterCalculator calc = new BabySitterCalculator()

        when:
            calc.setTime(16)

        then:
            def exception = thrown(GroovyRuntimeException)
            exception.message == 'Invalid Start Time'
    }
}

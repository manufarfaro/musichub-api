
import grails.util.BuildSettings
import grails.util.Environment


// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

root(ERROR, ['STDOUT'])

if(Environment.current == Environment.DEVELOPMENT) {
    def targetDir = BuildSettings.TARGET_DIR
    if(targetDir) {

        appender("FULL_STACKTRACE", FileAppender) {

            file = "${targetDir}/stacktrace.log"
            append = true
            encoder(PatternLayoutEncoder) {
                pattern = "%level %logger - %msg%n"
            }
        }
        logger("StackTrace", INFO, ['FULL_STACKTRACE'], true )
		logger('org.springframework.boot.autoconfigure.security', INFO)
//		logger('org.hibernate.type', TRACE)
//		logger('org.hibernate.SQL', DEBUG)
		logger('org.springframework.security', INFO)
		logger('org.springframework.security', DEBUG)
		logger('com.google.api', INFO)
		logger('com.google.api', DEBUG)
		logger('net.sf.jmimemagic', DEBUG)
		logger('com.musichub', INFO)
		logger('com.musichub', DEBUG)
    }
}

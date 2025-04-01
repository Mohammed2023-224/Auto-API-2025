package engine.gui.reporter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class CustomLogger {
        private static final String path="src/main/resources/Properties/log4j2.xml";

        static {
            Configurator.initialize(null,path);
        }

        public static Logger logger=LogManager.getLogger("mainLogger");
}

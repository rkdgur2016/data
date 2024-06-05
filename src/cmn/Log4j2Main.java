package cmn;
import org.apache.logging.log4j.LogManager;
import org.apache .logging.log4j.Logger;


public class Log4j2Main {

	static final Logger Log = LogManager.getLogger(Log4j2Main.class);

	public static void main(String[] args) {
		
		//fatal > error > info > debug > trace
		Log.debug("debug");
		Log.info("info");
		Log.warn("warn");
		Log.error("error");
		Log.fatal("fatal");

	}

}

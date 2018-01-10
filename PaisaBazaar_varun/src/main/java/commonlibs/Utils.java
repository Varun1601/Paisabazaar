package commonlibs;



	import java.io.FileInputStream;
	import java.io.InputStream;
	import java.util.Properties;

	public class Utils {



	public static Properties getProperties(String sPropertiesFile){
			
			try {
				InputStream oFileReader;
				Properties oProperty;
				
				oFileReader = new FileInputStream(sPropertiesFile);
				oProperty = new Properties();
				
				oProperty.load(oFileReader);
				
				return oProperty;
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
				return null;
			}
			
			
		}

	}





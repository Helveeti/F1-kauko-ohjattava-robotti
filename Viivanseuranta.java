import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/*
 * 
 *  NOT FINISHED
 * 
 */

public class Viivanseuranta {
	
	private Väri väri = new Väri(0.0f, 0.0f, 0.0f);
	private Motors motor;

	Port port = LocalEV3.get().getPort("S3");
	SensorModes sensor = new EV3ColorSensor(port);
	SampleProvider colorProvider = ((EV3ColorSensor)sensor).getRGBMode();
	
	float[] sample = new float[colorProvider.sampleSize()];
	
	public Viivanseuranta(Motors motor) {
		this.motor = motor;
	}
	
	public void takaisinLähtöön() {
		
		colorProvider.fetchSample(sample, 0);
		väri = new Väri(sample[0], sample[1], sample[2]);
		
		if(väri.getVäri().equals("Maaliviiva")) {
			motor.pysäytys();
		}else if(väri.getVäri().equals("Viiva")) {
			motor.ajaViivalla();
		}else if(väri.getVäri().equals("Lattia")) {
			
			motor.pysäytys();
			motor.ajaViivallaLeft();
			motor.pysäytys();
			
			colorProvider.fetchSample(sample, 0);
			väri = new Väri(sample[0], sample[1], sample[2]);
			
			if(väri.getVäri().equals("Lattia")) {
				motor.ajaViivallaRight();
				motor.ajaViivallaRight();
				
				motor.pysäytys();
				
				colorProvider.fetchSample(sample, 0);
				väri = new Väri(sample[0], sample[1], sample[2]);
				
				if(väri.getVäri().equals("Lattia")) {
					motor.ajaViivallaJyrkkäLeft();
					
					motor.pysäytys();
				}
				
			}
		}
		
		//motor.pysäytys();
		
	}
	
	public void viivojenKalibrointi() {
		
		try {
			
			float[] teippiSample = null, maaliteippiSample = null, lattiaSample = null;
			
			System.out.println("Kalibroi seurattava teippi painamalla keskinäppäintä");
			
			while(!(Button.ENTER.isDown())) {
				teippiSample = new float[colorProvider.sampleSize()];
				colorProvider.fetchSample(teippiSample, 0);
				väri.setKalibrointiViiva(teippiSample[0], teippiSample[1], teippiSample[2]);
			}
			
			System.out.println(" ... ");
			
			Delay.msDelay(1000);
			
			System.out.println("Kalibroi maaliviivan teippi painamalla keskinäppäintä");
			
			while(!(Button.ENTER.isDown())) {
				maaliteippiSample = new float[colorProvider.sampleSize()];
				colorProvider.fetchSample(maaliteippiSample, 0);
				väri.setKalibrointiMaaliviiva(maaliteippiSample[0], maaliteippiSample[1], maaliteippiSample[2]);
			}
			
			System.out.println(" ... ");
			
			Delay.msDelay(1000);
			
			System.out.println("\nKalibroi lattia painamalla keskinäppäintä");
			
			while(!(Button.ENTER.isDown())) {
				lattiaSample = new float[colorProvider.sampleSize()];
				colorProvider.fetchSample(lattiaSample, 0);
				väri.setKalibrointiLattia(lattiaSample[0], lattiaSample[1], lattiaSample[2]);
			}
			
			System.out.println(" ... ");
			
			Delay.msDelay(1000);
			
			System.out.println(" ---- Kalibrointi onnistunut. ---- ");
		
		}catch(Exception e) {
			System.out.println(" ---- Kalibrointi epäonnistui. ---- ");
		}
		
	}
	
	

}

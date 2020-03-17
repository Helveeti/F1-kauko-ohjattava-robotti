		import lejos.hardware.Button;
		import lejos.hardware.device.PSPNXController;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
		
public class Main extends Thread implements Runnable {

	public static void main(String[] args) {
		// luodaan moottori
		Motors motor = new Motors();
		
		// luodaan TrueFalse olio
		TrueFalse palautus = new TrueFalse();
		
		// luodaan nappi ja moottori komennot
		ButtonCommands buttonKomennot = new ButtonCommands(motor, palautus);
		MotorCommands motorKomennot = new MotorCommands(motor, palautus);
		
		// Aloitetaan säikeet
		
		buttonKomennot.start();
		motorKomennot.start();
		
		}			
			
	}



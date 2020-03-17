import java.util.Timer;
import java.util.TimerTask;

public class MotorCommands extends Thread implements Runnable{
	
	// Olioiden luominen
	private TimerTask task;
	
	// konstruktori joka alustaa palautus ja moottori olion
	public MotorCommands(Motors motor, TrueFalse palautus) {

		// Herättää säikeen tuhannes sekunnin välein
		task = new TimerTask(){
			// Säikeen suoritus 
			public void run() {
				
				// MOTOR A TURN
				if(palautus.l3Turn().equals("right")) { // Ohjaimen käännökset analogista sauvaa käyttämällä
					motor.ajaRight();
				}else if(palautus.l3Turn().equals("left")) {
					motor.ajaLeft();
				}else { // Renkaiden automaattinen suoristus
					motor.turnReset();
				}
				
			}
		};
		
		// Säikeen suorituksen ajanmäärittely
		Timer timer = new Timer();
		timer.schedule(task, 0l, 1l);
		
	}
}

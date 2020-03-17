import java.util.Timer;
import java.util.TimerTask;

public class ButtonCommands extends Thread implements Runnable {
	
	// Olioiden luominen
	private Motors motor;
	private TrueFalse palautus;
	private TimerTask task;
	private Viivanseuranta viivanseuranta;
	
	// konstruktori 
	public ButtonCommands(Motors motor, TrueFalse palautus) {
		
	// Luodaan viivanseuranta olio
		viivanseuranta = new Viivanseuranta(motor);
		viivanseuranta.viivojenKalibrointi();
	
	// Herättää säikeen tuhannes sekunnin välein
		task = new TimerTask(){
			
			// Säikeen suoritus 		
			public void run() {
				
				if(palautus.ympyraPressed()) {// ympyrä napista robotti pysähtyy
					
					motor.pysäytys();
				}
				else if(palautus.xPressed()) {// x napista robotti ajaa eteenpäin
					
					while(palautus.xPressed()) {
						
						motor.aja();
						
					}
					
					motor.pysäytys(); // robotti pysähtyy jos x nappi ei ole pohjassa
					
				}else if(palautus.nelioPressed()) { // neliö nappia painamalla robotti peruuttaa niin kauan kuin se on pohjassa
					
					while(palautus.nelioPressed()) {
						
						motor.peruuta();
						
					}
					
					motor.pysäytys();
					
				}else if(palautus.startPressed()){
					
					while(palautus.startPressed()){ // start nappia painamalla robotti seuraa viivaa takaisin maaliviivalle
						viivanseuranta.takaisinLähtöön();
					}
					
				}else if(palautus.r2l2Pressed()){
					
					viivanseuranta.viivojenKalibrointi();
					
				}else if(palautus.selectPressed()) { // select nappia painamalla robotti sulkee ohjelman
					System.exit(1);
				}
				
			}
		};
		
		// Säikeen suorituksen ajanmäärittely
		Timer timer = new Timer();
		timer.schedule(task, 0l, 1l);

	}
}

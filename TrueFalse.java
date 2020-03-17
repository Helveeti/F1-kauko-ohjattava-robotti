import lejos.hardware.device.PSPNXController;
import lejos.hardware.port.SensorPort;

public class TrueFalse extends Thread{
	// Luodaan kaukoohjain ja alustetaan se porttiin 1
	private PSPNXController pspc = new PSPNXController(SensorPort.S1);
	// Luodaan taulukko jossa on kaikki ohjaimen napit tallennettu
	private int[] buttons;
	// Muuttuja
	private int l3;
	
	public TrueFalse() {}
	// Boolean tarkistus onko x nappia painettu
	public boolean xPressed() {
		buttons = pspc.getButtons();
		boolean palautus = false;
		
		if(buttons[9] == 1) {
			palautus = true;
		}
		
		return palautus;
	}
	// Boolean tarkistus onko ympyrää painettu
	public boolean ympyraPressed() {
		buttons = pspc.getButtons();
		boolean palautus = false;
		
		if(buttons[10] == 1) {
			palautus = true;
		}
		
		return palautus;
	}
	// Boolean tarkistus onko neliö nappia painettu
	public boolean nelioPressed() {
		buttons = pspc.getButtons();
		boolean palautus = false;
		
		if(buttons[8] == 1) {
			palautus = true;
		}
		
		return palautus;
	}
	// Boolean tarkistus onko select nappia painettu
	public boolean selectPressed() {
		buttons = pspc.getButtons();
		boolean palautus = false;
		
		if(buttons[7] == 1) {
			palautus = true;
		}
		
		return palautus;
	}
	// Boolean tarkistus onko start nappia painettu
	public boolean startPressed() {
		buttons = pspc.getButtons();
		boolean palautus = false;
		
		if(buttons[4] == 1) {
			palautus = true;
		}
		
		return palautus;
	}
	// Boolean tarkistus onko r2 nappia painettu
	public boolean r2l2Pressed() {
		buttons = pspc.getButtons();
		boolean palautus = false;
		
		
		if(buttons[14] == 1 && buttons[15] == 1) {
			palautus = true;
		}
		
		return palautus;
	}
	// tarkistus onko analogista sauvaa käännetty vasemmalle
	public String l3Turn() {
		l3 = pspc.getLeftX();
		
		if(l3 >= 20) {
			return "right";
		}else if(l3 <= -20) {
			return "left";
		}
		
		return "forward";
	}

}

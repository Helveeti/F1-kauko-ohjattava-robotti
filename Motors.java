import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Motors {
	
	// Moottorien muuttujat
	private static RegulatedMotor mB;
	private static RegulatedMotor mC;
	private static RegulatedMotor mA;
	// Konstruktori joka luo moottorit ja alustaa ne oikeisiin portteihin
	public Motors() {
		
		mB = new EV3LargeRegulatedMotor(MotorPort.B);
		mC = new EV3LargeRegulatedMotor(MotorPort.C);
		mA = new EV3LargeRegulatedMotor(MotorPort.A);
		mB.synchronizeWith(new RegulatedMotor[] {mC});
		
	}
	// peruutus nopeuksien asettaminen ja itse peruutus komennot
	public void peruuta() {
		
		mB.setSpeed(10000);
		mC.setSpeed(10000);
		
		mB.startSynchronization();
		mB.forward();
		mC.forward();
		mB.endSynchronization();
		
		
	}
	// eteenpäin ajamis nopeuksien asettelu ja eteenpäin liikkumis komennot
	public void aja() {
		
		mB.setSpeed(10000);
		mC.setSpeed(10000);
		
		mB.startSynchronization();
		mB.backward();
		mC.backward();
		mB.endSynchronization();

	}
	
	// Vasen käännös komento
	public void ajaLeft() {
		
		mA.rotate(50);

	}
	// oikea käännös komento
	public void ajaRight() {
		
		mA.rotate(-50);

	}
	// Renkaiden suoristus käännöksen jälkeen
	public void turnReset() {
		
		mA.rotateTo(0);
		
	}
	// Robotin pysäytys
	public void pysäytys() {
		
		mB.startSynchronization();
		mB.stop();
		mC.stop();
		mB.endSynchronization();
		
	}
	
	// Viivanseuranta metodit --->
	
	public void ajaViivalla() {
		
		mB.setSpeed(100);
		mC.setSpeed(100);
		
		mB.startSynchronization();
		mB.backward();
		mC.backward();
		mB.endSynchronization();

	}
	
	public void ajaViivallaLeft() {
		
		mB.setSpeed(100);
		mC.setSpeed(100);
		
		mB.startSynchronization();
		mB.forward();
		mC.backward();
		mB.endSynchronization();
		
		mA.rotate(50);

	}
	
	public void ajaViivallaRight() {
		
		mB.setSpeed(100);
		mC.setSpeed(100);
		
		mB.startSynchronization();
		mB.backward();
		mC.backward();
		mB.endSynchronization();
		
		mA.rotate(-50);

	}
	
	public void ajaViivallaJyrkkäRight() {
		
		mB.setSpeed(100);
		mC.setSpeed(100);
		
		mB.startSynchronization();
		mB.backward();
		mC.backward();
		mB.endSynchronization();
		
		mA.rotate(100);

	}
	
	public void ajaViivallaJyrkkäLeft() {
		
		mB.setSpeed(100);
		mC.setSpeed(100);
		
		mB.startSynchronization();
		mB.backward();
		mC.backward();
		mB.endSynchronization();
		
		mA.rotate(100);

	}
}

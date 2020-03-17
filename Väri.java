
public class Väri {
	private int red, green, blue;
	private static int redK;
	private static int greenK;
	private static int blueK;
	private static int[] viiva = new int[5];
	private static int[] lattia = new int[5];
	private static int[] maaliviiva = new int[5];
	private static int[] aVärit = new int[5];
	private static int[][] lVärit = new int[5][5];
	

	public Väri(float r, float g, float b) {
		red = Math.round(r * 765);
		green = Math.round(g * 765);
		blue = Math.round(b * 765);
		
		aVärit[0] = red;
		aVärit[1] = green;
		aVärit[2] = blue;
		
	}
	
	public void setKalibrointiViiva(float r, float g, float b) {
		
		redK = Math.round(r * 765);
		greenK = Math.round(g * 765);
		blueK = Math.round(b * 765);
		
		viiva[0] = redK;
		viiva[1] = greenK;
		viiva[2] = blueK;
		
	}
	
	public void setKalibrointiMaaliviiva(float r, float g, float b) {
		
		redK = Math.round(r * 765);
		greenK = Math.round(g * 765);
		blueK = Math.round(b * 765);
		
		maaliviiva[0] = redK;
		maaliviiva[1] = greenK;
		maaliviiva[2] = blueK;
		
	}
	
	public void setKalibrointiLattia(float r, float g, float b) {
		
		redK = Math.round(r * 765);
		greenK = Math.round(g * 765);
		blueK = Math.round(b * 765);
		
		lattia[0] = redK;
		lattia[1] = greenK;
		lattia[2] = blueK;
		
	}
	
	public String getVäri() {
		
		// Viiva etäisyys
		
		for(int i = 0; i < 3; i++) {
			lVärit[0][i] = aVärit[i] - viiva[i];
		}
		
		// Lattia etäisyys
		
		for(int j = 0; j < 3; j++) {
			lVärit[1][j] = aVärit[j] - lattia[j];
		}
		
		// Maaliviiva etäisyys
		
		for(int l = 0; l < 3; l++) {
			lVärit[2][l] = aVärit[l] - maaliviiva[l];
		}
		
		// Potenssiin kaksi
		
		for(int n = 0; n < 3; n++) {
			for(int m = 0; m < 3; m++) {
				lVärit[n][m] = lVärit[n][m] * lVärit[n][m];
			}
		}
		
		
		// Lopputulos
		
		double viivaResult = Math.sqrt(lVärit[0][0] + lVärit[0][1] + lVärit[0][2]);
		viivaResult = Math.round(viivaResult * 10) / 10.0;
		//System.out.println("Väri luokka palauttaa : viivaResult " + viivaResult);
		
		double lattiaResult = Math.sqrt(lVärit[1][0] + lVärit[1][1] + lVärit[1][2]);
		lattiaResult = Math.round(lattiaResult * 10) / 10.0;
		//System.out.println("Väri luokka palauttaa : lattiaResult " + lattiaResult);
		
		double maaliviivaResult = Math.sqrt(lVärit[2][0] + lVärit[2][1] + lVärit[2][2]);
		maaliviivaResult = Math.round(maaliviivaResult * 10) / 10.0;
		//System.out.println("Väri luokka palauttaa : maaliviivaResult " + maaliviivaResult);
		
		
		// Vertailu - pienin arvo
		
		if(maaliviivaResult < lattiaResult && maaliviivaResult < viivaResult) {
			return "Maaliviiva";
		}else if(viivaResult < lattiaResult && viivaResult < maaliviivaResult) {
			return "Viiva";
		}else if(lattiaResult < viivaResult && lattiaResult < maaliviivaResult) {
			return "Lattia";
		}
		
		return "Jotain meni vikaan. ";
	}
}

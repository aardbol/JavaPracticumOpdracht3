package db;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import model.Adres;
import model.Klant;

public class KlantOpslag {

	public static void opslaan()
	{
		try {
			PrintWriter OpslaanInBestand = new PrintWriter("klanten.txt");

			for (int n = 0; n < KlantenRegister.getKlanten().size(); n++) {
				OpslaanInBestand.println(KlantenRegister.getKlanten().get(n));
			}
			OpslaanInBestand.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void lezen()
	{
		File file = new File("klanten.txt");
		Scanner scanner = null;

		if (!file.exists()) {
			return;
		}

		try {
			scanner = new Scanner(file);
			String[] klantData;

			while (scanner.hasNext()) {
				String lijn = scanner.nextLine();

				// Leeg bestand
				if (lijn.isEmpty()) {
					break;
				}

				klantData = lijn.split(" \\| ");
				
				Adres adres = new Adres();
				adres.setEmail(klantData[2]);
				adres.setStraat(klantData[5]);
				adres.setNummer(Integer.parseInt(klantData[6]));
				adres.setPostcode(Integer.parseInt(klantData[7]));
				adres.setGemeente(klantData[8]);
				
				// Wordt ook autom. toegevoegd aan het register in de klasse zelf
				new Klant(Integer.parseInt(klantData[1]), klantData[3], klantData[4], adres);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}

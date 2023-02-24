import java.util.ArrayList;
import java.util.HashMap;

// Make a class called Verkkokauppaa to be same as Verkkokauppa but to use hashmap

public class Verkkokauppa {
    private HashMap<String, Asiakas> asiakkaat;
    private HashMap<String, Tuote> tuotteet;
    private HashMap<String, Myyja> myyjat;
    private ArrayList<Ostotapahtuma> tapahtumat;

    public Verkkokauppa() {
        asiakkaat = new HashMap<>();
        tuotteet = new HashMap<>();
        myyjat = new HashMap<>();
        tapahtumat = new ArrayList<>();
    }

    public void lisaaAsiakas(Asiakas asiakas) {
        asiakkaat.put(asiakas.getAsiakasNumero(), asiakas);
    }

    public void lisaaTuote(Tuote tuote) {
        tuotteet.put(tuote.getNimi(), tuote);
    }

    public void lisaaMyyja(Myyja myyja) {
        myyjat.put(myyja.getTunniste(), myyja);
    }

    public void lisaaTapahtuma(Ostotapahtuma tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    /**
     * Yrittää poistaa annetun asiakkaan asiakalistasta.
     *
     * @param asiakas asiakas, jonka poistoa yritetään
     * @return true, jos asiakas löytyi ja poistettiin, muuten false
     */
    public boolean poistaAsiakas(String asiakasnumero) {
        if (asiakkaat.containsKey(asiakasnumero)) {
            asiakkaat.remove(asiakasnumero);
            return true;
        }
        return false;
    }

    /**
     * Yrittää poistaa annetun tuotteen tuotelistasta.
     *
     * @param tuote tuote, jonka poistoa yritetään
     * @return true, jos tuote löytyi ja poistettiin, muuten false
     */
    public boolean poistaTuote(String nimi) {
        if (tuotteet.containsKey(nimi)) {
            tuotteet.remove(nimi);
            return true;
        }
        return false;
    }

    /**
     * Yrittää poistaa annetun myyjän myyjalistasta.
     *
     * @param myyja myyja, jonka poistoa yritetään
     * @return true, jos myyjä löytyi ja poistettiin, muuten false
     */
    public boolean poistaMyyja(String tunniste) {
        if (myyjat.containsKey(tunniste)) {
            myyjat.remove(tunniste);
            return true;
        }
        return false;
    }

    /**
     * Yrittää poistaa annetun tapahtuman ostotapahtumalistasta.
     *
     * @param tapahtuma ostotapahtuma, jonka poistoa yritetään
     * @return true, jos ostotapahtuma löytyi ja poistettiin, muuten false
     */
    public boolean poistaOstotapahtuma(Ostotapahtuma tapahtuma) {

        if (tapahtumat.contains(tapahtuma)) {
            tapahtumat.remove(tapahtuma);
            return true;
        }
        return false;
    }

    /**
     * Palauttaa annetun asiakasnumeron mukaisen asiakkaan tai
     * <code>null</code>, jos asiakasta ei löydy
     *
     * @param asiakasnumero halutun asiakkaan asiakasnumero
     * @return pyydetyn asiakkaan tai null, jos asiakasta ei löydy
     */
    public Asiakas annaAsiakas(String asiakasnumero) {
        return asiakkaat.get(asiakasnumero);
    }

    /**
     * Palauttaa annetun nimen mukaisen tuotteen tai
     * <code>null</code>, jos tuotetta ei löydy
     *
     * @param nimi halutun tuotteen nimi
     * @return pyydetyn tuotteen tai null, jos tuotetta ei löydy
     */
    public Tuote annaTuote(String nimi) {
        return tuotteet.get(nimi);
    }

    /**
     * Palauttaa annetun tunnisteen mukaisen myyjän tai
     * <code>null</code>, jos myyjää ei löydy
     *
     * @param tunniste halutun myyjän tunniste
     * @return pyydetyn myyjän tai null, jos myyjää ei löydy
     */
    public Myyja annaMyyja(String tunniste) {
        return myyjat.get(tunniste);
    }

    /**
     * Palauttaa annetun indeksin mukaisen ostotapahtuman listasta.
     * Huomaa, että indeksointi alkaa nollasta, mutta tapahtumien listaamisessa
     * indeksit on ilmoitettu alkaen ykkösestä.
     *
     * @param indeksi indeksi jonka kohdalta tapahtuma palautetaan
     * @return annetun indeksin mukaisen ostotapahtuman
     */
    public Ostotapahtuma annaTapahtuma(int indeksi) {
        if (tapahtumat.size() > indeksi) {
            return tapahtumat.get(indeksi);
        }
        return null;
    }

    /**
     * Palauttaa kaikki asiakkaat yhtenä merkkijonona.
     *
     * @return asiakalistan merkkijonona
     */

    public String listaaAsiakkaat() {
        // Tekee otsikkorivin
        // \t on tabulaattori, joka tasaa tulosteen
        // sarakkeisiin; \n tekee rivinvaihdon
        StringBuilder s = new StringBuilder("Asiakasnumero\tNimi\t\t\tOstoja\tKanta-asiakas\n");
        for (Asiakas asiakas : asiakkaat.values()) {
            s.append(asiakas.getAsiakasNumero() + "\t\t\t");
            s.append(asiakas.getNimi() + "\t");
            s.append(asiakas.getOstojaTehty() + "\t\t");
            if (asiakas instanceof KantaAsiakas) {
                s.append("On\n");
            } else {
                s.append("Ei\n");
            }
        }
        return s.toString();
    }

    /**
     * Palauttaa kaikki myyjät yhtenä merkkijonona
     *
     * @return myyjät merkkijonona
     */

    public String listaaMyyjat() {
        // Tekee otsikkorivin
        // \t on tabulaattori, joka tasaa tulosteen
        // sarakkeisiin; \n tekee rivinvaihdon
        StringBuilder s = new StringBuilder("Tunniste\tNimi\t\t\tProvisiot\n");
        for (Myyja myyja : myyjat.values()) {
            s.append(myyja.getTunniste() + "\t\t");
            s.append(myyja.getNimi() + "\t\t");
            s.append(myyja.getProvisiot() + "\n");
        }
        return s.toString();
    }

    /**
     * Palautta kaikki tuotteet yhtenä merkkijonona.
     *
     * @return tuotteet merkkijonona
     */
    public String listaaTuotteet() {
        // Tekee otsikkorivin
        // \t on tabulaattori, joka tasaa tulosteen
        // sarakkeisiin; \n tekee rivinvaihdon
        StringBuilder s = new StringBuilder("Nimi\tSaldo\t\t\tHinta\tVirtuaalinen\n");
        for (Tuote tuote : tuotteet.values()) {
            s.append(tuote.getNimi() + "\t");
            s.append(tuote.getSaldo() + "\t\t");
            s.append(tuote.getHinta() + "\t");
            if (tuote instanceof VirtuaalinenTuote) {
                s.append("Kyllä\n");
            } else {
                s.append("Ei\n");
            }
        }
        return s.toString();
    }

    /**
     * Palauttaa kaikki tapahtumat yhtenä merkkijonona.
     *
     * @return tapahtumat merkkijonona
     */
    public String listaaTapahtumat() {
        StringBuilder s = new StringBuilder("Numero\tTuote\tAsiakas\tMyyjä\tKpl\tHinta\n");
        int n = 1;
        for (Ostotapahtuma tapahtuma : tapahtumat) {
            s.append(n++ + "\t");
            s.append(tapahtuma.getTuote().getNimi() + "\t");
            s.append(tapahtuma.getAsiakas().getNimi() + "\t");
            s.append(tapahtuma.getMyyja().getNimi() + "\t");
            s.append(tapahtuma.getMaara() + "\t");
            s.append(tapahtuma.getHinta() + "\n");
        }
        return s.toString();

    }
}

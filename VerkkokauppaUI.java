import java.util.Scanner;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

public class VerkkokauppaUI {
    private Verkkokauppa verkkokauppa;
    private Scanner lukija;

    public static void main(String[] args) {
        VerkkokauppaUI ui = new VerkkokauppaUI();
        ui.aloita();
    }

    public VerkkokauppaUI() {
        verkkokauppa = new Verkkokauppa();
        lukija = new Scanner(System.in);
    }

    public void aloita() {
        int valinta = -1; // joku muu kuin 0
        while (valinta != 0) {
            tulostaMenu();
            valinta = lueKokonaisluku(0, 4, "Anna valinta");
            if (valinta == 1) {
                asiakasMenu();
            } else if (valinta == 2) {
                tuoteMenu();
            } else if (valinta == 3) {
                myyjaMenu();
            } else if (valinta == 4) {
                ostotapahtumaMenu(); // ei vielä toteutettu...
            }
        }
    }

    private void asiakasMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println(verkkokauppa.listaaAsiakkaat());
            System.out.println();
            System.out.println("1. Lisää asiakas");
            System.out.println("2. Poista asiakas");
            System.out.println("3. Muuta kanta-asiakkaaksi");
            System.out.println("0. Poistu");

            valinta = lueKokonaisluku(0, 3, "Anna valinta");
            if (valinta == 1) {
                String asnro = lueMerkkijono("Asiakasnumero");
                String nimi = lueMerkkijono("Nimi");
                verkkokauppa.lisaaAsiakas(new Asiakas(asnro, nimi, 0));
                System.out.println("Asiakas lisätty!");
            } else if (valinta == 2) {
                String asnro = lueMerkkijono("Asiakasnumero");
                Asiakas as = verkkokauppa.annaAsiakas(asnro);
                if (as != null) {
                    // fixme: poista asiakas
                    verkkokauppa.poistaAsiakas(asnro);
                    System.out.println("Asiakas poistettu!");
                } else {
                    System.out.println("Asiakasta ei löytynyt.");
                }
            } else if (valinta == 3) {
                String asnro = lueMerkkijono("Asiakasnumero");
                Asiakas as = verkkokauppa.annaAsiakas(asnro);
                if (as != null) {
                    KantaAsiakas ka = new KantaAsiakas(as.getAsiakasNumero(),
                            as.getNimi(), as.getOstojaTehty(), 0);
                    verkkokauppa.poistaAsiakas(asnro);
                    verkkokauppa.lisaaAsiakas(ka);
                    System.out.println("Muutettu!");
                } else {
                    System.out.println("Asiakasta ei löytynyt.");
                }
            }
        }
    }

    private void tuoteMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println(verkkokauppa.listaaTuotteet());
            System.out.println();
            System.out.println("1. Lisää tuote");
            System.out.println("2. Poista tuote");
            System.out.println("3. Muuta saldoa");
            System.out.println("4. Muuta hintaa");
            System.out.println("0. Poistu");

            valinta = lueKokonaisluku(0, 4, "Anna valinta");
            if (valinta == 1) {
                String nimi = lueMerkkijono("Nimi");
                double hinta = lueDesimaaliluku(0, Tuote.MAKSIMIHINTA, "Anna hinta");
                String virt = lueMerkkijono("Onko virtuaalinen tuote kyllä / ei");
                if (virt.equals("kyllä")) {
                    verkkokauppa.lisaaTuote(new VirtuaalinenTuote(nimi, hinta));
                } else {
                    int saldo = lueKokonaisluku(0, Tuote.MAKSIMISALDO, "Anna saldo");
                    verkkokauppa.lisaaTuote(new Tuote(nimi, saldo, hinta));
                }

                System.out.println("Tuote lisätty!");
            } else if (valinta == 2) {
                String nimi = lueMerkkijono("Nimi");
                Tuote tuote = verkkokauppa.annaTuote(nimi);
                if (tuote != null) {
                    verkkokauppa.poistaTuote(nimi);
                    System.out.println("Tuote poistettu!");
                } else {
                    System.out.println("Tuotetta ei löytynyt.");
                }
            } else if (valinta == 3) {
                String nimi = lueMerkkijono("Nimi");
                Tuote tuote = verkkokauppa.annaTuote(nimi);
                if (tuote != null) {
                    int saldo = lueKokonaisluku(0, Tuote.MAKSIMISALDO, "Anna uusi saldo");
                    tuote.setSaldo(saldo);
                } else {
                    System.out.println("Tuotetta ei löytynyt.");
                }
            } else if (valinta == 4) {
                String nimi = lueMerkkijono("Nimi");
                Tuote tuote = verkkokauppa.annaTuote(nimi);
                if (tuote != null) {
                    double hinta = lueDesimaaliluku(0, Tuote.MAKSIMIHINTA, "Anna uusi hinta");
                    tuote.setHinta(hinta);
                } else {
                    System.out.println("Tuotetta ei löytynyt.");
                }
            }
        }
    }

    private void myyjaMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println(verkkokauppa.listaaMyyjat());
            System.out.println();
            System.out.println("1. Lisää myyjä");
            System.out.println("2. Poista myyjä");
            System.out.println("0. Poistu");

            valinta = lueKokonaisluku(0, 2, "Anna valinta");
            if (valinta == 1) {
                String tunniste = lueMerkkijono("Tunniste");
                String nimi = lueMerkkijono("Nimi");
                verkkokauppa.lisaaMyyja(new Myyja(tunniste, nimi, 0));
                System.out.println("Myyjä lisätty!");
            } else if (valinta == 2) {
                String tunniste = lueMerkkijono("Tunniste");
                Myyja m = verkkokauppa.annaMyyja(tunniste);
                if (m != null) {
                    verkkokauppa.poistaMyyja(tunniste);
                    System.out.println("Myyjä poistettu!");
                } else {
                    System.out.println("Myyjää ei löytynyt.");
                }
            }
        }
    }

    /**
     * TODO: Toteuta tämä demojen 2. tehtävänä - katso demosta tarkemmat ohjeet
     */

    /**
     * Käyttöliittymäluokasta VerkkokauppaUI puuttuu kokonaan toiminnallisuus
     * ostotapahtumien käsittelyyn. Toteuta siis metodi ostotapahtumaMenu ja kaikki
     * sen mahdollisesti tarvitsemat apumetodit.
     * 
     * Ostotapahtumamenu pitää näyttää käyttäjälle lista ostotapahtumista ja
     * valikko, josta käyttäjä voi joko lisätä uuden tapahtuman, poistaa jonkin
     * nykyisistä tai palata päävalikkoon.
     * 
     * Ostotapahtuma lisätään niin, että aluksi näytetään lista myyjistä ja
     * pyydetään käyttäjää valitsemaan näistä yksi. Sen jälkeen sama tehdään
     * tuotteille ja lopuksi asiakkaille.
     * 
     * Tämän jälkeen ohjelma vielä pyytää käyttäjää syöttämään myytävien tuotteiden
     * kappalemäärän. Tuotetta ei tietenkään voi ostaa enempää kuin mitä sitä
     * varastossa on.
     * 
     * Ohjelman tulee laskea ostotapahtumalle oikea hinta huomioiden käyttäjän
     * mahdollisen alennusprosentin (muista mahdolliset kanta-asiakkaat)! Tämän
     * jälkeen ostotapahtuma tallennetaan tapahtumalistan loppuun.
     * 
     * Tuotesaldoa tulee pienentää myydyn määrän verran. Ostotapahtumassa mukana
     * oleva myyjä saa provisiota 10 % yhteishinnasta. Huomaa myös, että asiakkaan
     * ostot kasvavat yhteissummalla.
     * 
     * Ostotapahtumaa poistettaessa annetaan poistettavan tapahtuman järjestysluku.
     * Tätä varten järjestysluvut listataan käyttäjälle käyttöliittymässä. Huomaa,
     * että käyttäjälle näytettävät luvut alkavat indeksistä 1.
     * 
     */

    private void ostotapahtumaMenu() {
        int valinta = -1;
        while (valinta != 0) {
            System.out.println(verkkokauppa.listaaTapahtumat());
            System.out.println();
            System.out.println("1. Lisää ostotapahtuma");
            System.out.println("2. Poista ostotapahtuma");
            System.out.println("0. Poistu");

            valinta = lueKokonaisluku(0, 2, "Anna valinta");
            if (valinta == 1) {
                System.out.println(verkkokauppa.listaaMyyjat());
                String tunniste = lueMerkkijono("Anna myyjän tunniste");
                Myyja m = verkkokauppa.annaMyyja(tunniste);
                if (m != null) {
                    System.out.println(verkkokauppa.listaaTuotteet());
                    String nimi = lueMerkkijono("Anna tuotteen nimi");
                    Tuote t = verkkokauppa.annaTuote(nimi);
                    if (t != null) {
                        System.out.println(verkkokauppa.listaaAsiakkaat());
                        String asiakasTunniste = lueMerkkijono("Anna asiakkaan tunniste");
                        Asiakas a = verkkokauppa.annaAsiakas(asiakasTunniste);
                        if (a != null) {
                            int maara = lueKokonaisluku(1, t.getSaldo(), "Anna tuotteen määrä");
                            verkkokauppa.lisaaTapahtuma(new Ostotapahtuma(a, m, t, maara));
                            System.out.println("Ostotapahtuma lisätty!");
                        } else {
                            System.out.println("Asiakasta ei löytynyt.");
                        }
                    } else {
                        System.out.println("Tuotetta ei löytynyt.");
                    }
                } else {
                    System.out.println("Myyjää ei löytynyt.");
                }
            }
            if (valinta == 2) {
                String tunniste = lueMerkkijono("Anna poistettavan tapahtuman numero");
                Ostotapahtuma tapahtuma = verkkokauppa.annaTapahtuma(Integer.parseInt(tunniste));
                if (tapahtuma != null) {
                    verkkokauppa.poistaOstotapahtuma(tapahtuma);
                    System.out.println("Tapahtuma poistettu!");
                } else {
                    System.out.println("Tapahtumaa ei löytynyt.");
                }
            }

        }
    }

    private void tulostaMenu() {
        System.out.println("1. Asiakkaat");
        System.out.println("2. Tuotteet");
        System.out.println("3. Myyjät");
        System.out.println("4. Ostotapahtumat");
        System.out.println("0. Poistu");
    }

    /**
     * Lukee käyttäjältä syötteen ja palauttaa sen kokonaislukuna.
     * Kokonaisluvun tulee olla annetun minimi ja maksimiarvon välissä.
     *
     * @param minimi  pienin sallittu arvo
     * @param maksimi suurin sallittu arvo
     * @param kehote  käyttäjälle näytetty kehote
     * @return käyttäjän syötteen kokonaislukuna
     */
    private int lueKokonaisluku(int minimi, int maksimi, String kehote) {
        while (true) {
            System.out.print(kehote + ": ");
            try {
                int arvo = Integer.parseInt(lukija.nextLine());
                if (arvo >= minimi && arvo <= maksimi) {
                    return arvo;
                }
                System.out.println("Arvon pitää olla väliltä " +
                        minimi + " - " + maksimi);
            } catch (NumberFormatException nfe) {
                System.out.println("Anna arvo numerona!");
            }
        }
    }

    /**
     * Lukee käyttäjältä syötteen ja palauttaa sen liukulukuna.
     * Luvun tulee olla annetun minimi ja maksimiarvon välissä.
     *
     * @param minimi  pienin sallittu arvo
     * @param maksimi suurin sallittu arvo
     * @param kehote  käyttäjälle näytetty kehote
     * @return käyttäjän syötteen liukulukuna
     */
    private double lueDesimaaliluku(double minimi, double maksimi, String kehote) {
        while (true) {
            System.out.print(kehote + ": ");
            try {
                double arvo = Double.parseDouble(lukija.nextLine());
                if (arvo >= minimi && arvo <= maksimi) {
                    return arvo;
                }
                System.out.println("Luvun pitää olla väliltä " +
                        minimi + " - " + maksimi);
            } catch (NumberFormatException nfe) {
                System.out.println("Anna luku numerona!");
            }
        }
    }

    /**
     * Apumetodi, joka näyttää käyttäjälle annetun kehotteen ja lukee
     * sitten tältä merkkijonon. Annettu jono ei voi olla tyhjä.
     *
     * @param kehote käyttäjälle näytettävä kehote
     * @return käyttäjän syöttämän ei-tyhjän merkkijonon
     */
    private String lueMerkkijono(String kehote) {
        while (true) {
            System.out.print(kehote + ": ");
            String arvo = lukija.nextLine();
            if (!arvo.equals("")) {
                return arvo;
            }
        }
    }

}

package javaapplication3;

import java.util.ArrayList;

/**
 * Created by Dorota on 2016-12-25.
 */
/* CHANGELOG */

/* 16.05
   Dodałem algorytm obliczający nowe overalle dla graczy dla danych pozycjach
   Dodałem algorytm wyszukujący najlepszego ustawienia dla danej drużyny
*/
/* 18.05
   Proces wyszukiwania najlepszej formacji może być ukryty lub widoczny
   Obliczanie ratingów ofensywnych i defensywnych
   */

/* ZROBIĆ NASTĘPNYM RAZEM
   Funkcja zamieniająca graczy pozycjami
   Ustawienie defensywne/ofensywne używając Player.task
   Dodanie nowych zawodników do drużyn, np jakiś algorytm generujący
 */

/* NA PRZYSZŁOŚĆ
   Dodanie możliwości robienia zmian w trakcie meczu
   Dodać klasę manager która będzie używała funkcji managerskich
   Stworzenie GUI!!!!!!!!!!!
 */

/* OPCJONALNIE
   Poprawić funkcję losującą
 */
/* NAPRAWDĘ OPCJONALNIE
   Napisać nowy algorytm rozgrywania meczów
 */

public class LigaMistrzow {

    public static void main(String[] args) {

        Position GK = new Position("GK","C","D");
        Position CB = new Position("B","C","D");
        Position RB = new Position("B","R","D");
        Position LB = new Position("B","L","D");
        Position CM = new Position("M","C","E");
        Position CDM = new Position("M","C","D");
        Position CAM = new Position("M","C","O");
        Position ST = new Position("A","C","O");
        Position RW = new Position("A","R","O");
        Position LW = new Position("A","L","O");
        Position LWB = new Position("B","L","E");
        Position RWB = new Position("B","R","E");
        Position RM = new Position("M","R","E");
        Position LM = new Position("M","L","E");
        

        ArrayList <Formation> formations= new ArrayList<>();
        formations.add(new Formation("4-4-2",GK,LB,CB,CB,RB,LM,CM,CM,RM,ST,ST));
        formations.add(new Formation("4-3-3",GK,LB,CB,CB,RB,LM,CAM,RM,LW,RW,ST));
        formations.add(new Formation("4-2-3-1",GK,LB,CB,CB,RB,CM,CM,RM,LM,CAM,ST));
        formations.add(new Formation("4-3-2-1",GK,LB,CB,CB,RB,CM,CM,CM,RW,LW,ST));
        formations.add(new Formation("4-5-1",GK,LB,CB,CB,RB,LM,CM,CM,RM,CM,ST));
        formations.add(new Formation("3-4-3",GK,CB,CB,CB,CM,LM,CM,RW,RM,LW,ST));
        formations.add(new Formation("3-5-2",GK,CB,CB,CB,RM,CM,CM,CAM,LM,ST,ST));
        formations.add(new Formation("5-4-1",GK,LB,CB,CB,RB,CB,CM,CM,RM,LM,ST));
        formations.add(new Formation("5-3-2",GK,LB,CB,CB,RB,CB,CM,CM,CM,ST,ST));
        formations.add(new Formation("5-2-2-1",GK,LWB,CB,CB,RWB,CB,CM,CM,RW,LW,ST));
        


        // tworzenie zawodników
        Player zero= new Player ("","",GK,0,0);
        Player KNavas=new Player("Keylor", "Navas", GK ,1, 84);
        Player Marcelo=new Player("Marcelo", " ", LB, 3, 84);
        Player SRamos=new Player("Sergio", "Ramos", CB, 4, 89);
        Player Pepe=new Player("Pepe", " ", CB, 5, 84);
        Player DCarvajal=new Player("Dani", "Carvajal", RB, 63, 83);
        Player TKroos=new Player("Toni", "Kroos", CM,6, 86);
        Player LModric=new Player("Luca", "Modric", CM,18, 90);
        Player JRodriguez=new Player("James", "Rodriguez", CAM, 10, 84);
        Player CRonaldo=new Player("Cristiano", "Ronaldo", ST, 7, 96);
        Player GBale=new Player("Gareth", "Bale", RW,11, 92);
        Player AMorata=new Player("Alvaro", "Morata", ST, 19, 85);
        Player KBenzema = new Player("Karim","Benzema",ST,9,87);
        Player Isco = new Player("Isco","",CAM,22,86);
        Player Casemiro = new Player("Casemiro","",CM,21,82);
        Player MAsensio= new Player("Marco","Asensio",CAM,20,83);
        Player LVasquez= new Player("Lucas","Vasquez",LW,18,82);
        Player RVarane= new Player("Raphael", "Varane",CB, 14,84);
        Player KCas = new Player ("Kiko","Casilla",GK, 93,78);

        Player TStegen=new Player("Ter", "Stegen", GK, 1, 80);
        Player JAlba=new Player("Jordi", "Alba", LB,2, 83);
        Player GPique=new Player("Gerrard", "Pique", CB, 4, 84);
        Player JMascherano=new Player("Javier", "Mascherano", CB,5, 83);
        Player SRoberto=new Player("Sergi", "Roberto", RB, 3, 80);
        Player SBusquets=new Player("Sergio", "Busquets", CDM, 6, 86);
        Player IRakitic=new Player("Ivan", "Rakitic", CM, 8, 85);
        Player AIniesta=new Player("Andres", "Iniesta", CAM, 12, 88);
        Player LMessi=new Player("Lionel", "Messi", ST,10, 95);
        Player LSuarez=new Player("Luis", "Suarez", ST, 9, 92);
        Player Neymar=new Player("Neymar", "Jr", ST, 11, 93);

        Player SUmtiti= new Player("Samuel","Umtitti",CB,13,83);
        Player AGomes= new Player("Andre","Gomes",CM,14,79);
        Player PAlcacer = new Player("Paco","Alcacer",ST,15,82);

        Player CBravo=new Player("Claudio", "Bravo", GK, 1, 82);
        Player GClichy=new Player("Gael", "Clichy", LB, 22, 82);
        Player PZabaleta=new Player("Pablo", "Zabaleta", RB, 5, 85);
        Player JStones=new Player("John", "Stones", CB,11,81 );
        Player NOtamendi=new Player("Nicolas", "Otamendi", CB,30, 84);
        Player DSilva=new Player("David", "Silva", CAM,21, 88);
        Player Fernandinho=new Player("", "Fernandinho", CDM,22, 85);
        Player Fernando=new Player("Fernando", "", CDM, 6, 83);
        Player KDBruyne=new Player("Kevin", "De Bruyne", CAM, 17, 90);
        Player RSterling=new Player("Raheem", "Sterling", ST, 7, 87);
        Player SAguero=new Player("Sergio", "Aguero", ST, 9, 90);
        Player WCaballero= new Player("Willy","Caballero",GK,12,82);
        Player VKompany = new Player("Vincent","Kompany",CB,4,84);
        Player LSane = new Player("Leroy","Sane",RW,19,85);
        Player GJesus= new Player("Gabriel", "Jesus",ST,33,85);
        Player Nolito= new Player("Nolito","",LW,28,80);
        Player BSagna= new Player("Bacary","Sagna",RB,2,79);
        Player AKolarov= new Player("Aleksandar","Kolarov",LB,30,79);

        Player PCech=new Player("Petr", "Čech", GK, 33, 88);
        Player NMonreal=new Player("Nacho", "Monreal", LB, 18, 81);
        Player LKoscielny=new Player("Laurent", "Koscielny", CB, 6, 85);
        Player GPaulista=new Player("Gabriel", "Paulista", CB, 5, 84);
        Player HBellerín=new Player("Hector", "Bellerín	", RB, 24, 85);
        Player MÖzil=new Player("Mesut", "Özil", CAM, 11, 89);
        Player FCoquelin=new Player("Francis", "Coquelin", CDM, 34, 84);
        Player GXhaka=new Player("Granit", "Xhaka", CM, 29, 83);
        Player TWalcott=new Player("Theo", "Walcott", ST, 14, 86);
        Player ASánchez=new Player("Alexis", "Sánchez", ST, 7, 90);
        Player AIwobi=new Player("Alex", "Iwobi", CM, 17, 83);
        Player DOspina = new Player("David","Ospina",GK,12,82);
        Player ARamsey = new Player("Aaron","Ramsey",CM,8,83);
        Player OGiroud=  new Player("Oliver","Giroud",ST,9,84);
        Player Mustafi= new Player("","Mustafi",CB,15,83);
        Player AOChamberlain= new Player("Alex","Oxlade-Chamberlain",RW,20,82);
        Player LPerez= new Player("Lucas","Perez",ST,40,84);
        Player KGibbs=new Player("Keiran","Gibbs",RB,16,79);

        Player TCourtois=new Player("Thibout", "Courtois", GK, 13, 89);
        Player CAzpilicueta=new Player("Cesar", "Azpilicueta", CB, 28, 85);
        Player GCahill=new Player("Gary", "Cahill", CB,24, 83);
        Player DLuiz=new Player("David", "Luiz", CB, 30, 84);
        Player VMoses=new Player("Victor", "Moses", LB, 20, 83);
        Player MAlonso=new Player("Marcos", "Alonso", RB,3, 83);
        Player NKante=new Player("N'golo", "Kante", CDM, 7, 86);
        Player NMatic=new Player("Nemanja", "Matic", CDM, 6, 85);
        Player PRodriguez=new Player("Pedro", "Rodriguez", RW, 11, 86);
        Player EHazard=new Player("Eden", "Hazard", LW,10, 90);
        Player DCosta=new Player("Diego", "Costa", ST, 9, 88);


        Player MNeuer=new Player("Manuel", "Neuer", GK, 1, 89);
        Player DAlaba=new Player("David", "Alaba", LB, 2, 84);
        Player MHummels=new Player("Mats", "Hummels", CB, 3, 86);
        Player JBoateng=new Player("Jerome", "Boateng", CB, 17, 86);
        Player JKimmich=new Player("Joshua", "Kimmich", RB, 4, 83);
        Player XAlonso=new Player("Xabi", "Alonso", CDM, 5, 83);
        Player AVidal=new Player("Arturo", "Vidal", CM, 6, 86);
        Player TMuller=new Player("Thomas", "Muller", CAM, 10, 85);
        Player DGCosta=new Player("Douglas", "Costa", LW, 7, 84);
        Player ARobben=new Player("Arjen", "Robben", RW, 11, 85);
        Player RLewandowski=new Player("Robert", "Lewandowski", ST, 9, 90);

        Player PReina = new Player("Pepe","Reina",GK,12,79);
        Player FRibery = new Player("Franck","Ribery",LW,18,85);
        Player KComan= new Player("Kingsley","Coman",RW,19,83);
        Player JMartinez= new Player("Javi","Martinez",CDM,24,82);
        Player RSanches= new Player("Renato","Sanches",CM,20,82);


        Player Trapp=new Player("Trapp", "", GK, 1, 85);
        Player TSilva=new Player("Thiago", "Silva", CB, 2, 88);
        Player Marquinios=new Player("Marquinios", "", CB,5, 83);
        Player Meunier=new Player("Meunier", "Thoms", LB,12, 82);
        Player Kurzawa=new Player("Kurzawa", "", RB, 20, 83);
        Player BMatuidi=new Player("Blaise", "Matuidi", CDM,14, 86);
        Player GKrychowiak=new Player("Grzegorz", "Krychowiak", CDM, 8, 85);
        Player MVeratti=new Player("Marco", "Veratti", CAM, 6, 86);
        Player LMoura=new Player("Lucas", "Moura", RW, 7, 85);
        Player ECavani=new Player("Edinson", "Cavani", ST, 9, 88);
        Player CNkunku=new Player("Cristian", "Nkunku", CAM, 6, 86);
        Player DSubasic=new Player("Danjel","Subasic",GK,1,82);
        Player ARaggi=new Player("Andrea","Raggi ",CB,5,82);
        Player KGlik=new Player("Kamil","Glik",CB,4,86);
        Player Jemerson=new Player("Jemerson","",RB,2,81);
        Player YCissokho=new Player("Yarouba","Cissokho",LB,3,77);
        Player Bakayoko=new Player("","Bakayoko",CDM,10,85);
        Player JMoutinho=new Player("Joao","Moutinho",CM,8,84);
        Player TLemar=new Player("Thomas","Lemar",CM,6,83);
        Player BSilva=new Player("Bernardo","Silva",CAM,7,84);
        Player KMbappe=new Player("Kyllian","Mbappe",ST,11,86);
        Player RFalcao=new Player("Radamel","Falcao",ST,9,86);
        Player HLloris=new Player("Hugo","Lloris",GK,1,88);
        Player TAlderweireld=new Player("Toby","Alderweireld",CB,4,85);
        Player JVertonghen=new Player("Jan","Vertonghen",CB,5,83);
        Player KWalker=new Player("Kyle","Walker",RB,3,81);
        Player DRose=new Player("Danny","Rose",LB,2,83);
        Player MDembele=new Player("Moussa","Dembele",CM,7,85);
        Player VWanyama=new Player("Victor","Wanyama",CM,8,86);
        Player DAlli=new Player("Delle","Alli",CAM,11,84);
        Player SHueng=new Player("Son","Hueng",ST,10,83);
        Player HKane=new Player("Harry","Kane",ST,9,85);
        Player CEriksen=new Player("Cristian","Eriksen",CAM,10,84);
        Player Burki=new Player("Burki","",GK,1,81);
        Player SPapasdopulos=new Player("Sokratis","Papasdopulos",CB,4,84);
        Player LPiszczek=new Player("Lukasz","Piszczek",RB,3,82);
        Player MBartra=new Player("Marc","Bartra",CB,5,81);
        Player CPulisic=new Player("Christian","Pulisic",LB,2,80);
        Player SKagawa=new Player("Shinji","Kagawa",CM,8,83);
        Player MGoetze=new Player("Mario","Goetze",CAM,7,84);
        Player ASchurrle=new Player("Andre","Schurrle",CAM,8,81);
        Player ODembele=new Player("Osama","Dembele",RW,10,81);
        Player Reus=new Player("Marco","Reus",LW,11,88);
        Player PAubameyang=new Player("Pierre-Emerick","Aubameyang",ST,9,87);
        Player JOblak=new Player("Jan","Oblak",GK,1,87);
        Player DGodin=new Player("Diego","Godin",CB,3,88);
        Player FLuis=new Player("Felipe","Luis",LB,4,85);
        Player Savic=new Player("Savic","",CB,5,83);
        Player Juanfran=new Player("Juanfran","",RB,2,83);
        Player MGaitan=new Player("Marcos","Gaitan",CM,7,85);
        Player YCarrasco=new Player("Yannick","Carrasco",CM,8,82);
        Player Gabi=new Player("Gabi","",CM,6,82);
        Player Koke=new Player("Koke","",CM,10,84);
        Player AGriezmann=new Player("Antoine","Griezmann",ST,11,88);
        Player FTorres=new Player("Fernando","Torres",ST,9,81);
        Player TShinhan=new Player("Tien","Shinhan ",GK,1,82);
        Player Yamcha=new Player("Yamcha"," ",CB,5,81);
        Player Krillin=new Player("Krillin"," ",CB,4,82);
        Player Roshi=new Player("Master","Roshi ",LB,3,81);
        Player Whis=new Player("Whis"," ",RB,2,99);
        Player Piccolo=new Player("Piccolo"," ",CM,6,84);
        Player Trunks=new Player("Trunks"," ",CAM,7,83);
        Player Goten=new Player("Goten"," ",CDM,8,83);
        Player Vegeta=new Player("Vegeta"," ",LW,9,95);
        Player Goku=new Player("Goku"," ",ST,10,94);
        Player Beerus=new Player("Beerus"," ",RW,11,97);
        Player Jamemba=new Player("Jamemba"," ",GK,1,89);
        Player Turles=new Player("Turles"," ",CB,2,70);
        Player Brolly=new Player("Brolly"," ",CB,3,93);
        Player Nappa=new Player("Nappa"," ",CB,4,75);
        Player Cell=new Player("Cell"," ",RB,5,89);
        Player Buu=new Player("Buu"," ",LB,6,93);
        Player Frieza=new Player("Frieza"," ",CM,7,87);
        Player Android=new Player("Android","17 ",CM,17,87);
        Player Black=new Player("Black","",CAM,9,94);
        Player Zamasu=new Player("Zamasu"," ",CAM,11,89);
        Player Ginyu=new Player("Ginyu"," ",ST,10,80);
        //Superstarr
        Player HBajda=new Player("Henryk","Bajda",GK,1,84);
        Player SJach=new Player("Stanisław 'Stajku'","Jach",CB,2,90);
        Player APastuszek=new Player("Adam 'Chomik'","Pastuszek",CB,4,90);
        Player HMinus=new Player("Herkules","Minus",CB,3,83);
        Player BBolek=new Player("Bolek","",LB,5,83);
        Player FGerula=new Player("Florian","Gerula",RB,6,86);
        Player MGerula=new Player("Marian","Gerula",CM,7,86);
        Player CGerula=new Player("Czesław","Gerula",CM,8,86);
        Player JSzpytman=new Player("Jarosław 'Pitas'","Szpytman",CM,23,89);
        Player KWojtowicz=new Player("Kamil 'Kombajn'","Wojtowicz",ST,10,87);
        Player ŁJach=new Player("Łukasz Kryminał","Jach",ST,9,87);
        // Huragan
        Player MDaraz=new Player("Maciej", "Daraz", GK, 12, 87);
        Player MZatawrdnicki=new Player("Maciej", "Zatawrdnicki", CB, 2, 83);
        Player BZatwardnicki=new Player("Bartosz", "Zatwardnicki", CB, 4, 90);
        Player DMazur=new Player("Damian", "Mazur", LB, 3, 89);
        Player KLewiarz=new Player("Krzysztof", "Lewiarz", RB, 11, 88);
        Player DFudali=new Player("Daniel", "Fudali", CDM,6, 90);
        Player BFrosztega=new Player("Bartosz", "Frosztega", CM,13, 83);
        Player LPawlowski=new Player("Łukasz", "Pawłowski", RW, 19, 90);
        Player DPawlowski=new Player("Daniel", "Pawłowski", CAM, 10, 90);
        Player BDzimira=new Player("Bogumił", "Dzimira", ST,9, 91);
        Player APawlowski=new Player("Arkadiusz", "Pawłowski", LW, 7, 94);
        // Legends
        Player GBednarczyk=new Player("Grzegorz","Bednarczyk",GK,12,87);
        Player MGłuszko=new Player("Mieczysław","Głuszko",LB,3,86);
        Player MPawłowski=new Player("Marian","Pawłowski",CB,2,85);
        Player PDzidek=new Player("Paweł","Dzidek",CB,3,87);
        Player ZPawłowski=new Player("Zenon","Pawłowski",RB,5,84);
        Player EFudali=new Player("Edward","Fudali",CDM,7,84);
        Player DSzpytman=new Player("Dawid","Szpytman",CDM,8,85);
        Player STomaszewski=new Player("Szymon ","Tomaszewski",CAM,10,94);
        Player LChorbowy=new Player("Leon","Chorbowy",CAM,4,88);
        Player MSzpytman=new Player("Marek","Szpytman",CAM,88,85);
        Player WBajda=new Player("Wiesław","Bajda",ST,11,87);
        Player LDaraż=new Player("Łukasz","Daraż",CB,70,86);
        Player DGłowacz=new Player("Dawid","Głowacz",RM,8,85);
        Player AWanat= new Player("Arkadiusz","Wanat",CM,96,84);
        Player MPawlowski= new Player("Marcin","Pawłowski",LB,25,87);
        Player MBus= new Player("Mateusz","Buś",CB,74,85);
        Player JKwasny=new Player("Józef","Kwaśny",GK,1,87);
        Player KPastuszek= new Player("Kacper","Pastuszek",ST,69,84);
        Player BBajda=new Player("Bartosz","Bajda",CB,22,79);
        Player JGerula=new Player("Jacek","Gerula",ST,9,86);
        Player PGerula=new Player("Piotr","Gerula",ST,19,88);
        Player JBajda=new Player("Jarosław","Bajda",CB,26,84);
        Player DBochenek= new Player("Damian","Bochenek",CB,14,89);
        Player PMikita= new Player("Piotr","Mikita",CDM,30,79);
        Player EKowalinski= new Player("Eugeniusz","Kowaliński",RB,32,80);
        Player JGluszko=new Player("Jarosław","Głuszko",CM,18,87);
        Player KZabawski= new Player("Krystian","Zabawski",CM,42,87);
        Player DZabawski= new Player("Damian","Zabawski",CB,41,80);
        Player SFednar= new Player("Sylwester","Fednar",RM,21,80);
        Player MFednar= new Player("Marcin","Fednar",CM,17,82);
        Player DSwist= new Player("Dominik","Swist",RW,27,85);
        Player MSwist= new Player("Mateusz","Swist",LW,28,86);
        Player ABegovic = new Player("Asmir","Begovic",GK,1,81);
        Player KZouma = new Player("Kurt","Zouma",CB,4,80);
        Player CFabregas = new Player("Cesc","Fabregas",CM,4,83);
        Player Willian = new Player("Willian","",RW,22,85);
        Player NAke = new Player("Nathan","Ake",RB,12,77);
        Player MBatshuai = new Player("Michy","Batshuai",ST,19,81);
        Player RLCheek = new Player("Ruben","Loftus-Cheek",CM,20,79);


        // stworzenie tablicy zespołów i przypisanie do niej zespołów (pozycja [16] jest pozycją buforową aby w późniejszym etapie możliwe były zmiany pozycji zespołów w tablicy)
        Team ChampionsLeagueCompetitors[]=new Team[17];
        ChampionsLeagueCompetitors[0]=new Team("Real Madrid      ","Spain", formations.get(0),KNavas,	Marcelo,SRamos,Pepe,DCarvajal,TKroos,LModric,JRodriguez,CRonaldo,GBale,AMorata);
        ChampionsLeagueCompetitors[1]=new Team( "FC Barcelona     ", "Spain",formations.get(0), TStegen,JAlba,GPique,JMascherano,SRoberto,SBusquets,IRakitic,AIniesta,LMessi,LSuarez,Neymar);
        ChampionsLeagueCompetitors[2]=new Team( "Huragan Reczpol  ", "Poland",formations.get(2),  MDaraz,MZatawrdnicki,	BZatwardnicki,DMazur,KLewiarz,DFudali,BFrosztega,LPawlowski,DPawlowski,BDzimira,APawlowski);
        ChampionsLeagueCompetitors[3]=new Team( "Manchester City  ", "England",formations.get(0),  CBravo,	GClichy,PZabaleta,JStones,NOtamendi,DSilva,Fernandinho,Fernando,KDBruyne,RSterling,SAguero);
        ChampionsLeagueCompetitors[4]=new Team( "Arsenal FC       ", "England",formations.get(0),  PCech,NMonreal,LKoscielny,GPaulista,	HBellerín,FCoquelin,GXhaka,TWalcott,ASánchez,AIwobi,MÖzil);
        ChampionsLeagueCompetitors[5]=new Team( "Chelsea FC       ", "England",formations.get(0),  TCourtois,CAzpilicueta,GCahill,DLuiz,VMoses,MAlonso,NKante,NMatic,PRodriguez,EHazard,DCosta);
        ChampionsLeagueCompetitors[6]=new Team("Bayern Munich    ", "Germany",formations.get(0), MNeuer,	DAlaba,	MHummels,JBoateng,JKimmich,XAlonso,	AVidal,TMuller,DGCosta,ARobben,RLewandowski);
        ChampionsLeagueCompetitors[7]=new Team("PSG              ","France",formations.get(0), Trapp,	TSilva,	Marquinios,Meunier,Kurzawa,BMatuidi,GKrychowiak,MVeratti,LMoura,	ECavani,CNkunku);
        ChampionsLeagueCompetitors[8]=new Team("AS Monaco        ","France",formations.get(0),DSubasic,ARaggi,KGlik,Jemerson,YCissokho,Bakayoko,JMoutinho,TLemar,BSilva,	KMbappe,RFalcao);
        ChampionsLeagueCompetitors[9]=new Team("Tottenham Hotspur","England",formations.get(0), HLloris,TAlderweireld,JVertonghen,KWalker,DRose,MDembele,VWanyama,DAlli,SHueng,	HKane,CEriksen);
        ChampionsLeagueCompetitors[10]=new Team("Borussia Dortmund","Germany",formations.get(0), Burki,SPapasdopulos,LPiszczek,	MBartra,CPulisic,SKagawa,MGoetze,ASchurrle,ODembele,Reus,PAubameyang);
        ChampionsLeagueCompetitors[11]=new Team("Atletico Madrid  ", "Spain",formations.get(0), JOblak,	DGodin,	FLuis,Savic,Juanfran,	MGaitan,YCarrasco,Gabi,Koke,AGriezmann,FTorres) ;
        ChampionsLeagueCompetitors[12]=new Team("DragunboluGut    ","Japan",formations.get(0), TShinhan,Yamcha,Krillin,Roshi,Whis,Piccolo,Trunks,Goten,Vegeta,Goku,Beerus);
        ChampionsLeagueCompetitors[13]=new Team("DragunboluBat    ","Japan",formations.get(0),Jamemba,Turles,Brolly,Nappa,Cell,Buu,Frieza,Android,Black,Zamasu,Ginyu);
        ChampionsLeagueCompetitors[14]=new Team("Reczpol Superstar","Poland",formations.get(0), HBajda,SJach,APastuszek,HMinus,BBolek,FGerula,MGerula,CGerula,JSzpytman,KWojtowicz,ŁJach);
        ChampionsLeagueCompetitors[15]=new Team("Reczpol Legends  ","Poland",formations.get(0),GBednarczyk,MGłuszko,MPawłowski,PDzidek,ZPawłowski,	EFudali,DSzpytman,STomaszewski,LChorbowy,MSzpytman,WBajda);

        ChampionsLeagueCompetitors[0].addPlayer(KCas);
        ChampionsLeagueCompetitors[0].addPlayer(Isco);
        ChampionsLeagueCompetitors[0].addPlayer(Casemiro);
        ChampionsLeagueCompetitors[0].addPlayer(KBenzema);
        ChampionsLeagueCompetitors[0].addPlayer(MAsensio);
        ChampionsLeagueCompetitors[0].addPlayer(RVarane);
        ChampionsLeagueCompetitors[0].addPlayer(LVasquez);

        ChampionsLeagueCompetitors[3].addPlayer(WCaballero);
        ChampionsLeagueCompetitors[3].addPlayer(BSagna);
        ChampionsLeagueCompetitors[3].addPlayer(AKolarov);
        ChampionsLeagueCompetitors[3].addPlayer(LSane);
        ChampionsLeagueCompetitors[3].addPlayer(Nolito);
        ChampionsLeagueCompetitors[3].addPlayer(VKompany);
        ChampionsLeagueCompetitors[3].addPlayer(GJesus);

        ChampionsLeagueCompetitors[4].addPlayer(DOspina);
        ChampionsLeagueCompetitors[4].addPlayer(KGibbs);
        ChampionsLeagueCompetitors[4].addPlayer(AOChamberlain);
        ChampionsLeagueCompetitors[4].addPlayer(LPerez);
        ChampionsLeagueCompetitors[4].addPlayer(OGiroud);
        ChampionsLeagueCompetitors[4].addPlayer(Mustafi);
        ChampionsLeagueCompetitors[4].addPlayer(ARamsey);


        ChampionsLeagueCompetitors[5].addPlayer(ABegovic);
        ChampionsLeagueCompetitors[5].addPlayer(Willian);
        ChampionsLeagueCompetitors[5].addPlayer(NAke);
        ChampionsLeagueCompetitors[5].addPlayer(RLCheek);
        ChampionsLeagueCompetitors[5].addPlayer(CFabregas);
        ChampionsLeagueCompetitors[5].addPlayer(KZouma);
        ChampionsLeagueCompetitors[5].addPlayer(MBatshuai);

        ChampionsLeagueCompetitors[2].addPlayer(LDaraż);
        ChampionsLeagueCompetitors[2].addPlayer(DGłowacz);
        ChampionsLeagueCompetitors[2].addPlayer(AWanat);
        ChampionsLeagueCompetitors[2].addPlayer(MPawlowski);
        ChampionsLeagueCompetitors[2].addPlayer(MBus);
        ChampionsLeagueCompetitors[2].addPlayer(JKwasny);
        ChampionsLeagueCompetitors[2].addPlayer(KPastuszek);

        ChampionsLeagueCompetitors[14].addPlayer(BBajda);
        ChampionsLeagueCompetitors[14].addPlayer(JGerula);
        ChampionsLeagueCompetitors[14].addPlayer(PGerula);
        ChampionsLeagueCompetitors[14].addPlayer(JBajda);
        ChampionsLeagueCompetitors[14].addPlayer(DBochenek);
        ChampionsLeagueCompetitors[14].addPlayer(PMikita);
        ChampionsLeagueCompetitors[14].addPlayer(EKowalinski);

        ChampionsLeagueCompetitors[15].addPlayer(JGluszko);
        ChampionsLeagueCompetitors[15].addPlayer(KZabawski);
        ChampionsLeagueCompetitors[15].addPlayer(DZabawski);
        ChampionsLeagueCompetitors[15].addPlayer(SFednar);
        ChampionsLeagueCompetitors[15].addPlayer(MFednar);
        ChampionsLeagueCompetitors[15].addPlayer(DSwist);
        ChampionsLeagueCompetitors[15].addPlayer(MSwist);


        //ustawienie najlepszych formacji dla każdej z drużyn
       for(int i=0; i<ChampionsLeagueCompetitors.length-1; i++) ChampionsLeagueCompetitors[i].bestSetUp(formations,false);
        // stworzenie grupy aby można było wykonać operację losowania drużyn
        Group GroupsA = new Group("A",ChampionsLeagueCompetitors[0],ChampionsLeagueCompetitors[0],ChampionsLeagueCompetitors[0],ChampionsLeagueCompetitors[0]);
        GroupsA.Randomo(ChampionsLeagueCompetitors);
        // stworzenie grup w których rozgrywane będą mecze (program działa tak, aby w jednej grupie nie spotkały się dwie drużyny z tego samego państwa)
        Group GroupA = new Group("A",ChampionsLeagueCompetitors[0],ChampionsLeagueCompetitors[4],ChampionsLeagueCompetitors[10],ChampionsLeagueCompetitors[14]);
        Group GroupB = new Group("B",ChampionsLeagueCompetitors[1],ChampionsLeagueCompetitors[8],ChampionsLeagueCompetitors[13],ChampionsLeagueCompetitors[6]);
        Group GroupC = new Group("C",ChampionsLeagueCompetitors[2],ChampionsLeagueCompetitors[7],ChampionsLeagueCompetitors[15],ChampionsLeagueCompetitors[11]);
        Group GroupD = new Group("D",ChampionsLeagueCompetitors[3],ChampionsLeagueCompetitors[9],ChampionsLeagueCompetitors[12],ChampionsLeagueCompetitors[5]);
        //rozegranie fazy grupowej
       /* GroupA.GroupStage();
        GroupB.GroupStage();
        GroupC.GroupStage();
        GroupD.GroupStage();
        */
        //pobranie dwóch pierwszych drużyn z każdej grupy i ustawienie ich w odpowiednich komórkach tablicy drużyn F
        Team BracketStageCompetitors[]= new Team[9];

        BracketStageCompetitors[0]=GroupA.getGroupTeams(0);
        BracketStageCompetitors[4]=GroupA.getGroupTeams(1);
        BracketStageCompetitors[1]=GroupB.getGroupTeams(0);
        BracketStageCompetitors[5]=GroupB.getGroupTeams(1);
        BracketStageCompetitors[2]=GroupC.getGroupTeams(0);
        BracketStageCompetitors[6]=GroupC.getGroupTeams(1);
        BracketStageCompetitors[3]=GroupD.getGroupTeams(0);
        BracketStageCompetitors[7]=GroupD.getGroupTeams(1);
        // rozegranie fazy pucharowej
        ChampionsLeagueCompetitors[10].BracketStage(BracketStageCompetitors);
    }
}
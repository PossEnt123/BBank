import java.util.Scanner;
public class GioDadBank 
{
    private static BankAccount conto;
    private static boolean controllaPwd;
    
    public static void main (String [] args)
    {
        welcome();
        Scanner input = new Scanner(System.in);
        boolean continua = true;
        while (continua)
        {
            menu();
            String scelta = input.nextLine();
            if (scelta.equals("0"))
            {continua = false;}
            else if (scelta.equals("1"))
            {creaConto(input);}
            else if (scelta.equals("2"))
            {mostraConto(input);}
            else if (scelta.equals("3"))
            {inserisciSoldi(input);}
            else if (scelta.equals("4"))
            {prelevaSoldi(input);}
            else if (scelta.equals("5"))
            {
                altreOperazioni(input);
                scelta = input.nextLine();
                if (scelta.equals("0"))
                {
                    System.out.println("Ritorno al menu' principale...");
                }
                else if (scelta.equals("1"))
                {versamentoTotale();}
                else if (scelta.equals("2"))
                {prelievoTotale();}
                else if (scelta.equals("3"))
                {calcolaInteressi(input);}
            }
        }
        input.close();
    }

    private static void menu()
    {
        System.out.println(); 
        System.out.println("*******************************************************");
        System.out.println("* Menu:                                               *");
        System.out.println("* 1: Crea un nuovo conto. (prima azione da fare)      *");
        System.out.println("* 2: Visualizza il conto bancario.                    *");
        System.out.println("* 3: Deposita denaro sul conto bancario.              *");
        System.out.println("* 4: Prelieva denaro dal conto bancario.              *");
        System.out.println("* 5: Altre operazioni.                                *");
        System.out.println("* 0: termina programma                                *");
        System.out.println("*******************************************************");
        System.out.println();  
        System.out.println("Quale operazione desideri effettare?");
        System.out.println("(Digita il numero corrispondente all'operazione desiderata.)");
    }

    private static void welcome()
    {
        System.out.println(); 
        System.out.println("*******************************************************");
        System.out.println("*                                                     *");
        System.out.println("* Benvenuto in B&B, la banca creata da Giorgio Bozza! *");
        System.out.println("*                                                     *");
        System.out.println("*******************************************************");  
    }

    private static void creaConto(Scanner input)
    {
        System.out.print("Per creare un conto bancario, devi prima scegliergli il nome: ");
        conto = new BankAccount(input.nextLine());
        System.out.println("Bene, hai creato un account di nome "+ conto.getName() + ", vuoi assegnargli una password? (S/N)");
        controllaPwd = false;
        String risposta = input.nextLine();
        if("S".equalsIgnoreCase(risposta))
        {
            System.out.print("Bene, scegli la tua password: ");
            risposta = input.nextLine();
            System.out.println("La password che hai scelto e' " + risposta);
            conto.setPassword(risposta);
            controllaPwd = true;
        }
        System.out.println("Hai creato il tuo primo conto bancario con successo.");
        System.out.println("Ritorno alla schermata principale...");
    }

    private static boolean controllaP(Scanner input)
    {
        boolean risultato = false;
        if(conto == null)
        {
            System.out.print("Non puoi fare questa operazione senza aver prima creato un conto.");
            return risultato;
        }
        if (controllaPwd)
        {
            System.out.println("Prima di fare la prima operazione sul conto, devi inserire la password. "+
            "Tranquillo, ti verra' chiesta solo la prima volta.");
            boolean check = false;
            while (!check)
            {
                System.out.print("Inserisci la password: ");
                String risposta = input.nextLine();
                check = conto.verifyPassword(risposta);
                if(!check)
                {
                    System.out.println("La password e' errata. Vuoi riprovare? (S/N).");
                    risposta = input.nextLine();
                    if (!"S".equalsIgnoreCase(risposta))
                    {break;}
                }
                else
                {
                    System.out.println("Password corretta.");
                    System.out.println();
                    risultato = true;
                    controllaPwd = false;
                }
            }         
        }
        else
        {
            risultato = true;
        }
        return risultato;
    }

    private static void mostraConto(Scanner input)
    {
        if(controllaP(input))
        {
            System.out.println("Il tuo conto attualmente ammonta a: "+ conto.getBalance()+" euro");
        }
    }

    private static void inserisciSoldi(Scanner input)
    {
        if(controllaP(input))
        {
            System.out.println("Quanti soldi vuoi depositare sul conto "+ conto.getName()+"?");
            double versamento = input.nextDouble();
            conto.deposit(versamento);
        }
    }

    private static void prelevaSoldi(Scanner input)
    {
        if(controllaP(input))
        {
            System.out.println("Quanti soldi vuoi prelevare dal conto "+ conto.getName()+"?");
            double prelievo = input.nextDouble();
            conto.withdrow(prelievo);
        }
    }

    private static void altreOperazioni(Scanner input)
    {
        System.out.println(); 
        System.out.println("*******************************************************");
        System.out.println("* Altre operazioni:                                   *");
        System.out.println("* 1: Calcola deposito totale.                         *");
        System.out.println("* 2: Calcola prelievo totale.                         *");
        System.out.println("* 3: Calcola interesse.                               *");
        System.out.println("* 0: Torna indietro.                                  *");
        System.out.println("*******************************************************");
        System.out.println();  
        System.out.println("Quale operazione desideri effettare?");
        System.out.println("(Digita il numero corrispondente all'operazione desiderata.)");  
    }
    
    private static void versamentoTotale()
    {
        System.out.println("Sul tuo conto hai depositato un totale di "+ conto.getTotalDeposit()+ " euro da quando lo hai aperto.");
    }

    private static void prelievoTotale()
    {
        System.out.println("Dal tuo conto hai prelevato un totale di "+ conto.getTotalWithdrow()+ " euro da quando lo hai aperto.");
    }

    private static void calcolaInteressi(Scanner input)
    {
        System.out.println("Per calcolare un interesse sul conto "+ conto.getName()+" devi stabilire:");
        System.out.print("Il tasso di interesse come numero intero (in %): ");
        String risposta = input.nextLine();
        conto.setRate(Double.parseDouble(risposta));
        System.out.print("Gli anni che vuoi far trascorrere: ");
        risposta = input.nextLine();
        conto.setYears(Integer.parseInt(risposta));
        conto.calculateTotalInterest();
        System.out.println("Calcolo dell'interesse in corso...");
        System.out.println("Dopo "+ conto.getYears()+" anni hai guadagnato "+conto.getTotalInterest()+ " euro grazie ad un interesse del "+conto.getRate()+"% annuo.");
        
        System.out.println("Vuoi aggiungere tale valore nel tuo conto bancario? (S/N)");
        risposta = input.nextLine();
        if("S".equalsIgnoreCase(risposta));
        {
            conto.deposit(conto.getTotalInterest());
        }

    }




}

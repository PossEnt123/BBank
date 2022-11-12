public class BankAccount
{
    /**
     * Versa una quantita' di denaro nel conto bancario.
     * 
     * @param balance la qauntita' di denaro nel conto bancario.    
     * @param debito la quantita' di denaro che il proprietario del conto puo' contrarre come debito
     * @param maxBalance la quantita' massima che si puo' prelevare in un dato momento, contraendo un debito
     */
    private double balance;
    private double debito = 1000;
    private String name;
    private String password;
    private double totalDeposit;
    private double totalWithdrow;
    private double rate;
    private int years;
    private double totalInterest;
    // Crea un conto bancario con valore iniziale 
    public BankAccount(String nomeAccount)
    {
        balance = 0;
        name = nomeAccount;
    }
    // Crea un conto bancario con valore iniziale iBalance
    public BankAccount(String nomeAccount, double iBalance)
    {
        balance = iBalance;
        name = nomeAccount;
    }
    /* 
    Deposita nel conto bancario un valore amount
    @param amount la quantita' da depositare
    */ 
    public void deposit(double amount) {
        if (amount >= 0) 
        {
            balance = balance + amount;
            totalDeposit += amount;
            System.out.println("Trasfermimento in corso. Attendere.");
            System.out.println("Hai effettuato correttamente la trasazione. Hai agigunto " + amount
            + " euro sul tuo conto bancario.");
        } 
        else
            System.out.println("Non puoi depositare un valore negativo di denaro!");
    }

    /*
     * Ritira dal conto bancario un valore amount
     * 
     * @param amount la quantita' di denaro da ritirare
     */
    public void withdrow(double amount)
    {
        if (balance - amount >= -debito)
        {    
            balance = balance - amount;
            totalWithdrow += amount;
            System.out.println("Trasfermimento in corso. Attendere.");
            System.out.println("Hai effettuato correttamente la trasazione. Hai ritirato "+ amount + " euro dal tuo conto bancario.");
        }
        else
           System.out.println("Non puoi indebitarti piu' di "+ debito+ "euro");
    }
    /*
     * Ottieni il conto attuale dell'account
     */
    public double getBalance()
    {
        return balance;
    }
    /*
     * Stampa a video la frase preindicata
     */
    public String toString()
    {
        return "BankAccount: saldo attuale pari a " + balance;
    }

    public String getName()
    {
        return name;
    }

    public void setPassword(String newPassword)
    {
        password = newPassword;
    }

    public boolean verifyPassword(String challenge)
    {
        if (password != null)
        {
            if(password.equals(challenge))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        return true;
    }

    public double getTotalDeposit()
    {
        return totalDeposit;
    }

    public double getTotalWithdrow()
    {
        return totalWithdrow;
    }

    public void setRate(double newRate)
    {
        rate = newRate;
    }

    public double getRate()
    {
        return rate;
    }

    public void setYears(int newYears)
    {
        years = newYears;
    }

    public double getYears()
    {
        return years;
    }

    public void calculateTotalInterest()
    {
        double iBalance = balance;
        for (int i=1; i <= years; i++ )
        {
            double INTEREST = iBalance*rate/100;
            iBalance += INTEREST;
        }
        totalInterest = iBalance - balance;
    }

    public double getTotalInterest()
    {
        return totalInterest;
    }
    
}
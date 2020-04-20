package testApp;

public class TransactionResponse {

    private String unique_id;
    private String status;
    private String usage;
    private double amount;
    private String transaction_time;
    private String message;

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setUsage(String usage){
        this.usage = usage;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public void setTransaction_time(String transaction_time){
        this.transaction_time = transaction_time;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getUnique_id(){
        return this.unique_id;
    }

    public String getStatus(){
        return this.status;
    }

    public String getUsage(){
        return this.usage;
    }

    public Double getAmount(){
        return this.amount;
    }

    public String getTransaction_time(){
        return this.transaction_time;
    }

    public String getMessage(){
        return this.message;
    }
}

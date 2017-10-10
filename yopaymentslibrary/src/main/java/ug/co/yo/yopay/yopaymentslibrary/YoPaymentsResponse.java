package ug.co.yo.yopay.yopaymentslibrary;

/**
 * Created by AZIZ on 10/10/2017.
 */

public class YoPaymentsResponse {
    private String Status;
    private String StatusCode;
    private String StatusMessage;
    private String TransactionStatus;
    private String ErrorMessageCode;
    private String ErrorMessage;
    private String TransactionReference;
    private String MNOTransactionReferenceId;
    private String IssuedReceiptNumber;
    private String amount;
    private String formattedAmount;
    private String currencyCode;
    private String TransactionInitiationDate;
    private String TransactionCompletionDate;


    public YoPaymentsResponse(){}

    public String getStatus(){return this.Status;}
    public void setStatus(String status){this.Status = status;}

    public String getStatusCode(){return this.StatusCode;}
    public void setStatusCode(String statusCode){this.StatusCode = statusCode;}

    public String getStatusMessage(){return this.StatusMessage;}
    public void setStatusMessage(String statusMessage){this.StatusMessage = statusMessage;}

    public String getTransactionStatus(){return this.TransactionStatus;}
    public void setTransactionStatus(String transactionStatus){this.TransactionStatus = transactionStatus;}

    public String getErrorMessageCode(){return this.ErrorMessageCode;}
    public void setErrorMessageCode(String errorMessageCode){this.ErrorMessageCode = errorMessageCode;}

    public String getErrorMessage(){return this.ErrorMessage;}
    public void setErrorMessage(String errorMessage){this.ErrorMessage = errorMessage;}

    public String getTransactionReference(){return this.TransactionReference;}
    public void setTransactionReference(String transactionReference){this.TransactionReference = transactionReference;}

    public String getMNOTransactionReferenceId(){return this.MNOTransactionReferenceId;}
    public void setMNOTransactionReferenceId(String mnoTransactionReferenceId){this.MNOTransactionReferenceId = mnoTransactionReferenceId;}

    public String getIssuedReceiptNumber(){return this.IssuedReceiptNumber;}
    public void setIssuedReceiptNumber(String issuedReceiptNumber){this.IssuedReceiptNumber = issuedReceiptNumber;}

    public String getAmount(){return this.amount;}
    public void setAmount(String amount){this.amount = amount;}

    public String getFormattedAmount(){return this.formattedAmount;}
    public void setFormattedAmount(String formattedAmount){this.formattedAmount = formattedAmount;}

    public String getCurrencyCode(){return this.currencyCode;}
    public void setCurrencyCode(String currencyCode){this.currencyCode = currencyCode;}

    public String getTransactionInitiationDate(){return this.TransactionInitiationDate;}
    public void setTransactionInitiationDate(String transactionInitiationDate){this.TransactionInitiationDate = transactionInitiationDate;}

    public String getTransactionCompletionDate(){return this.TransactionCompletionDate;}
    public void setTransactionCompletionDate(String transactionCompletionDate){this.TransactionCompletionDate = transactionCompletionDate;}


    @Override
    public String toString() {
        return "RESPONSE = {"+
                "Status=" + Status +
                ", StatusCode='" + StatusCode + '\'' +
                ", StatusMessage='" + StatusMessage + '\'' +
                ", TransactionStatus='" + TransactionStatus + '\'' +
                ", ErrorMessageCode='" + ErrorMessageCode + '\'' +
                ", TransactionReference='" + TransactionReference + '\'' +
                ", amount='" + amount + '\'' +
                ", formattedAmount='" + formattedAmount + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", TransactionInitiationDate='" + TransactionInitiationDate + '\'' +
                ", TransactionCompletionDate='" + TransactionCompletionDate + '\'' +
                '}';
    }
}

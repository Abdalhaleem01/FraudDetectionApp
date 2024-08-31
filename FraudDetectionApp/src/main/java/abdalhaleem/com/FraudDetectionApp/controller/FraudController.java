package abdalhaleem.com.FraudDetectionApp.controller;

import abdalhaleem.com.FraudDetectionApp.model.TransactionModel;
import abdalhaleem.com.FraudDetectionApp.service.FraudDetectionService;
import abdalhaleem.com.FraudDetectionApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FraudController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private FraudDetectionService fraudDetectionService;

    @GetMapping("transactions")
    public List<TransactionModel> getAllTransactions(){

        return transactionService.getAllTransactions();
    }

    @GetMapping("transaction/{transactionId}")
    public TransactionModel getTransaction(@PathVariable("transactionId") String transactionId){

        return transactionService.getTransaction(transactionId);
    }

    @PostMapping("transaction")
    public TransactionModel doTransaction(@RequestBody TransactionModel transaction) {
        transactionService.addTransaction(transaction);

        return transaction;
    }

    @PutMapping("transaction")
    public TransactionModel updateTransaction(@RequestBody TransactionModel transaction) {
        transactionService.updateTransaction(transaction);
        return transaction;
    }

    @DeleteMapping("transaction/{transactionID}")
    public String deleteTransaction(@PathVariable("transactionID") String transactionID){

        transactionService.deleteTransaction(transactionID);
        return  "The transaction has been deleted successfully";
    }

}

package abdalhaleem.com.FraudDetectionApp.service;

import abdalhaleem.com.FraudDetectionApp.model.TransactionModel;
import abdalhaleem.com.FraudDetectionApp.repo.TransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    FraudDetectionService fraudDetectionService;

    public void addTransaction(TransactionModel transaction) {
        log.info("in add transaction");
        fraudDetectionService.fraudDetectionScore(transaction);

        if (transaction.getFraudScore() > 60) {
            transaction.setFraudFlag(1);
        }

        transactionRepo.save(transaction);
    }

    public void updateTransaction(TransactionModel transaction) {
        transactionRepo.save(transaction);
    }

    public void deleteTransaction(String transactionID) {
        transactionRepo.deleteById(transactionID);
    }

    public List<TransactionModel> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public TransactionModel getTransaction(String transactionId) {
        return transactionRepo.findById(transactionId).orElse(new TransactionModel());
    }
}

package abdalhaleem.com.FraudDetectionApp.service;

import abdalhaleem.com.FraudDetectionApp.model.TransactionModel;
import abdalhaleem.com.FraudDetectionApp.repo.FraudDetectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FraudDetectionService {
    public int fraudScore;

    @Autowired
    FraudDetectionRepo fraudDetectionRepo;

    public void fraudDetectionScore(TransactionModel transaction) {
        fraudScore = 0;

        isAmountSuspicious(transaction);
        isFrequencySuspicious(transaction);
        isGeolocationSuspicious(transaction);
        isUserNameSuspicious(transaction);
        isCurrencySuspicious(transaction);
        isIpAddressSuspicious(transaction);
        isDeviceTypeSuspicious(transaction);
        isBrowserSuspicious(transaction);

        transaction.setFraudScore(fraudScore);

    }

    private void isAmountSuspicious(TransactionModel transaction) {
        BigDecimal threshold = BigDecimal.valueOf(10000);
        if (transaction.getAmount().compareTo(threshold) > 0) {
            fraudScore += 10;
        }
    }

    private void isFrequencySuspicious(TransactionModel transaction) {
        if (fraudDetectionRepo.isFrequencySuspicious(transaction.getCardNumber())) {
            fraudScore += 35;
        }
    }

    private void isGeolocationSuspicious(TransactionModel transaction) {
        if (fraudDetectionRepo.isCountrySuspicious(transaction.getCountry(), transaction.getCardNumber())) {
            fraudScore += 25;
        }

        if (fraudDetectionRepo.isLocationSuspicious(transaction.getLocation(), transaction.getCardNumber())) {
            fraudScore += 10;
        }

    }

    private void isUserNameSuspicious(TransactionModel transaction) {
        if (fraudDetectionRepo.isUserNameSuspicious(transaction.getUserName(), transaction.getCardNumber())) {
            fraudScore += 30;
        }
    }

    private void isCurrencySuspicious(TransactionModel transaction) {
        if (fraudDetectionRepo.isCurrencySuspicious(transaction.getCurrency(), transaction.getCardNumber())) {
            fraudScore += 10;
        }
    }

    private void isIpAddressSuspicious(TransactionModel transaction) {
        if (fraudDetectionRepo.isIpAddressSuspicious(transaction.getIpAddress(), transaction.getCardNumber())) {
            fraudScore += 20;
        }
    }

    private void isDeviceTypeSuspicious(TransactionModel transaction) {
        if (fraudDetectionRepo.isDeviceTypeSuspicious(transaction.getDeviceType(), transaction.getCardNumber())) {
            fraudScore += 15;
        }
    }

    private void isBrowserSuspicious(TransactionModel transaction) {
        if (fraudDetectionRepo.isBrowserSuspicious(transaction.getBrowser(), transaction.getCardNumber())) {
            fraudScore += 10;
        }
    }


}

package com.solvd.booking.reservation;

import com.solvd.booking.main.PastDateException;

import java.time.LocalDate;
import java.time.YearMonth;

public class CreditCard{
    private String holderFullName;
    private CardType cardType;
    private String number;
    private YearMonth expiry;
    private String verificationCode;


    public CreditCard() {}
    public CreditCard(String holderFullName, CardType cardType, String number,
                      YearMonth expiry, String verificationCode) {
        this.holderFullName = holderFullName;
        this.cardType = cardType;
        this.number = number;
        this.expiry = expiry;
        this.verificationCode = verificationCode;
    }


    public void validate() throws InvalidCardNumberException, PastDateException {
        if (! cardType.isValid(number)) {
            throw new InvalidCardNumberException();
        } else if (expiry.compareTo(YearMonth.from(LocalDate.now())) <= 0){
            throw new PastDateException();
        }
    }


    public String getHolderFullName() {
        return holderFullName;
    }
    public void setHolderFullName(String holderFullName) {
        this.holderFullName = holderFullName;
    }

    public CardType getCardType() {
        return cardType;
    }
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public YearMonth getExpiry() {
        return expiry;
    }
    public void setExpiry(YearMonth expiry) {
        this.expiry = expiry;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}

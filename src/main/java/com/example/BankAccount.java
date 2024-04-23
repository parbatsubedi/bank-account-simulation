package com.example;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class BankAccount extends AbstractActor {
    private double balance;

    public BankAccount() {
        this.balance = 100.0; // Initialize balance to £100
    }

    static Props props() {
        return Props.create(BankAccount.class, BankAccount::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Deposit.class, this::handleDeposit)
                .match(Withdrawal.class, this::handleWithdrawal)
                .build();
    }

    private void handleDeposit(Deposit deposit) {
        balance += deposit.getAmount();
        System.out.println("Deposited: £" + deposit.getAmount() + " New Balance: £" + balance);
    }

    private void handleWithdrawal(Withdrawal withdrawal) {
        balance -= withdrawal.getAmount();
        System.out.println("Withdrawn: £" + withdrawal.getAmount() + " New Balance: £" + balance);
    }
}

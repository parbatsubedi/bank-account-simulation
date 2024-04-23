package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("BankSystem");

        // Create BankAccount actor
        ActorRef bankAccount = system.actorOf(BankAccount.props(), "bankAccount");

        // Print initial balance
        bankAccount.tell(new Deposit(0), ActorRef.noSender());

        // Generate and process 10 transactions
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            double amount = random.nextDouble() * 2000 - 1000; // Random value between -1000 to 1000
            if (amount > 0) {
                bankAccount.tell(new Deposit(amount), ActorRef.noSender());
            } else {
                bankAccount.tell(new Withdrawal(-amount), ActorRef.noSender());
            }
        }

        // Terminate the system
        system.terminate();
    }
}

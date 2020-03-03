package com.lby.thread;

public class TestUnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "基金");
        Drawing boy = new Drawing(account, 50, "boy");
        Drawing girl = new Drawing(account, 100, "girl");
        boy.start();
        girl.start();
    }
}

//账户
class Account {
    int money;//余额
    String IDCard;//卡号

    public Account(int money, String IDCard) {
        this.money = money;
        this.IDCard = IDCard;
    }
}

class Drawing extends Thread {
    Account account;//账户
    int drawingMoney;//取了多少钱
    int nowMoney;//现在手头上的钱

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "余额不足");
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //卡内余额=余额-取出的钱
            account.money = account.money - drawingMoney;
            //手里的钱
            nowMoney += drawingMoney;
            System.out.println(account.IDCard + "余额为" + account.money);
            System.out.println(this.getName() + "手里的钱为" + nowMoney);
        }
    }
}
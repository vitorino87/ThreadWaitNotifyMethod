/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadwaitnotifymethod;

/**
 *
 * @author tiago.lucas
 */
public class NewThread implements Runnable{

    String name; //name of thread
    Thread t;
    boolean suspendFlag;

    public NewThread(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New Thread: "+t);
        suspendFlag = false;
        t.start(); //start the thread
    }     
    
    @Override
    public void run() {
        try {
            for(int i =15;i>0;i--){
                System.out.println(name+": "+i);
                Thread.sleep(200);
                synchronized(this)
                {
                    while(suspendFlag)
                    {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name+ " interrupted");
        }
        System.out.println(name + " exiting");
    }
    void mySuspend(){
        suspendFlag = true;
    }
    synchronized void myResume(){
        suspendFlag = false;
        notify();
    }
}

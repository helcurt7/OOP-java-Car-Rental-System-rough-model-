package src.Contoller;
/*
 *  
 * This method uses to meke fake  loading time  to create the illusion that the system is processing.
 */
public class WaitingTime {
    
    public WaitingTime(){
        processWaitingTime();
    }

public void processWaitingTime() {
    System.out.print("Loading");
    
    for (int i = 0; i < 4; ++i) {
        System.out.print("."); 
        System.out.flush(); // Force the buffer to be written immediately
        
        // Better way to create a delay
        try {
            Thread.sleep(500); // Wait for 500 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    System.out.println("\n"); // Add newlines after loading is complete
}
}

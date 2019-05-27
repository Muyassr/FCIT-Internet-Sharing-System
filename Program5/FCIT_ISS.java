
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/*
Moyasr Tamim
1539152
moytam.uni@gamil.com
 */

public class FCIT_ISS {

    public static void main(String[] args) throws FileNotFoundException {
        
           File inputFile = new File("FCIT-ISS.in");
        File outputFile = new File("FCIT-ISS.out");
        PrintWriter write = new PrintWriter(outputFile);
        if (!inputFile.exists()) {
            System.out.println("Input file, " + inputFile + ", does not exist.");
            write.flush();
            write.close();
            System.exit(0);

        }

        Scanner donga = new Scanner(inputFile);
        
        FCIT_ISSbst stdTree = new FCIT_ISSbst();
        
        int seed = donga.nextInt();
        Random randomNumGen = new Random(seed);
        
        int sizeX = donga.nextInt();
        int sizeY = donga.nextInt();
        
        // number of ittrations
        int k = donga.nextInt();
        donga.nextLine();
        
        String command;
        for (int i = 0; i < k; i++) {
           
            command = donga.next();
            
            //System.out.println("\ncomm: "+command+" i:"+i+"\n");
            
            if(command.equals("JOIN")) {
                
                JOIN(stdTree, donga);
                
            } 
             else if(command.equals("FINDMAC")) {
                 
                 FINDMAC(donga, stdTree);
                
            }
             else if(command.equals("LINK")) {
                 
                 LINK(donga,stdTree );
                 
            }
             else if(command.equals("UNLINK")) {
                 
                 
                 int mac1 = donga.nextInt();
                 int mac2 = donga.nextInt();
                UNLINK(  mac1,  mac2, stdTree, command);
                
            }
             else if(command.equals("QUIT")) {
                
                QUIT(donga, stdTree, command);
                 
            }
             else if(command.equals("SHOWCONNECTIONS")) {
                
                 SHOWCONNECTIONA(stdTree, donga);
                 
            }
             else if(command.equals("PRINTMEMBERS")) {
                 
                 PRINTMEMBERS(stdTree);
               
            }
             else if(command.equals("MOVEDEVICES")) {
                
                if (!stdTree.isEmpty()) {

                    INORDERMOVEDEVICES(stdTree.getRoot(), sizeX, sizeY, randomNumGen);
                    System.out.println("All devices successfully moved.");
                } else {
                    System.out.println("Cannot Perform MOVEDEVICES Command:\n\t"
                            + "There are currently no participants in the FCIT-ISS system.");

                }
                
            }
           
            
            
        }
        
        

    }
    
  public static void JOIN(FCIT_ISSbst stdTree, Scanner donga) {
      
            
                int MACnumber = donga.nextInt();
                 FCITstudent newStd = new FCITstudent(MACnumber);
                 String firstName = donga.next();
                 String lastName = donga.next();
                 int broadcastRange = donga.nextInt();
                int x = donga.nextInt();
                int y = donga.nextInt();
                //check if the student is already in the database
                if (!stdTree.search(MACnumber)) {
                    
                    FCIT_ISSlinkedDevices devList = new FCIT_ISSlinkedDevices();
                    newStd.setFirstName(firstName);
                    newStd.setLastName(lastName);
                    newStd.setMACnumber(MACnumber);
                    newStd.setAllowedMACs(devList);
                    newStd.setBroadcastRange(broadcastRange);
                    newStd.setX(x); newStd.setY(y);
                    
                 //add to the tree
                    stdTree.insert(newStd);
                    System.out.println(firstName+" "+lastName+", MAC "+MACnumber
                    +", joined the FCIT-ISS system.");
                    
                } 
                else {
                    
                    System.out.println("Cannot Perform JOIN Command:\n"
                    +"\tMAC "+MACnumber+", "+firstName+" "+ lastName
                    +" - already a participant in the FCIT-ISS system.");
                }
               
      
  }
  public static void FINDMAC(Scanner donga, FCIT_ISSbst stdTree) {
      
                int MACnumber = donga.nextInt();
                 FCITstudent temp = stdTree.findNode(MACnumber);
                 //student is in the database ? 
                 if( temp != null) {
                     
                     int link = temp.getNumLinks();
                     
                     String l = " Link";
                     if(link != 1 ) l+="s";
                         
                     System.out.println("Found:  MAC "+MACnumber+", "
                             +temp.getFirstName()+" "+temp.getLastName()
                             +", currently at position ("+temp.getX()+", "+temp.getY()+"), "+temp.getNumLinks()+l);
                 } 
                 else {
                     System.out.println("MAC "+MACnumber
                             +" not found in the FCIT-ISS system.");
                 }
      
  }
  public static void LINK(Scanner donga, FCIT_ISSbst stdTree ) {
      
                 int mac1 = donga.nextInt();
                 int mac2 = donga.nextInt();
                 
                  FCITstudent temp1 = stdTree.findNode(mac1);
                  FCITstudent temp2 = stdTree.findNode(mac2);
                    
                  // make sure both students are already in the database
                if (temp1 != null && temp2 != null) {
                    // we wanna know if they are not linked so we link them toghether
                    if(!isAllLinked(temp1,temp2)) {
                        
                        // link both sides with eachother
                        temp1.getAllowedMACs().insert(temp2.getMACnumber()); 
                        temp2.getAllowedMACs().insert(temp1.getMACnumber());
                        System.out.println("MAC "+mac1+" and MAC "+mac2+" are now linked.");
                        
                        //increase numLinks
                        temp1.setNumLinks(temp1.getNumLinks()+1);
                        temp2.setNumLinks(temp2.getNumLinks()+1);
                    }
                    else {
                        System.out.println("Cannot Perform LINK Command:\n"
                                + "\tMAC "+mac1+" and MAC "+mac2+" are already linked.");
                    }
                   
                }
                else {
                    
                    System.out.println("Cannot Perform LINK Command:");
                    if(temp1 == null) 
                        System.out.println("\tMAC "+mac1+" - This MAC Address is not in the FCIT-ISS system.");
                    
                    if(temp2 == null)
                        System.out.println("\tMAC "+mac2+" - This MAC Address is not in the FCIT-ISS system.");
                    
                            
                }
               
      
  }
  public static boolean isAllLinked(FCITstudent p1, FCITstudent p2) {
      // its just to see if students are linked in both sides
      if(p1.getAllowedMACs().search(p2.getMACnumber()) && p2.getAllowedMACs().search(p1.getMACnumber()))
          return true;
      
      return false;
      
  }
  public static void UNLINK(int mac1, int mac2, FCIT_ISSbst stdTree, String command) {
      
                 
                  FCITstudent temp1 = stdTree.findNode(mac1);
                  FCITstudent temp2 = stdTree.findNode(mac2);
        // CHECK IF THEY ARE EXCSTING      
                if (temp1 != null && temp2 != null) {
                //check if they are not linked
                    if(isAllLinked(temp1,temp2)) {
                        
                        //delete eachother's MACs
                        temp1.getAllowedMACs().delete(temp2.getMACnumber());
                        temp2.getAllowedMACs().delete(temp1.getMACnumber());
                        
                        if(command.equals("UNLINK"))
                        System.out.println("MAC "+mac1+" and MAC "+mac2+" are no longer linked.");
                        
                        // decrement numLinks
                        temp1.setNumLinks(temp1.getNumLinks()-1);
                        temp2.setNumLinks(temp2.getNumLinks()-1);
                    }
                    else {
                        System.out.println("Cannot Perform UNLINK Command:\n"
                                + "\tMAC "+mac1+" and MAC "+mac2+" are not currently linked.");
                    }
                   
                }
                else {
                    System.out.println("Cannot Perform UNLINK Command:");
                    if(temp1 == null) 
                        System.out.println("\tMAC "+mac1+" - This MAC Address is not in the FCIT-ISS system.");
                    
                    if(temp2 == null)
                        System.out.println("\tMAC "+mac2+" - This MAC Address is not in the FCIT-ISS system.");
                    
                            
                }
               
      
  }
  public static void QUIT(Scanner donga, FCIT_ISSbst stdTree, String command) {
      
          int mac = donga.nextInt();
                 
                 FCITstudent temp = stdTree.findNode(mac);
                 // if student is in the database ? 
                 if( temp != null ) {
                     
               //treverse AllowedMACs linkedlist using help pointer
                     FCIT_ISSdevice hp = temp.getAllowedMACs().getHead();
                     while(hp != null) {
                         UNLINK(mac, hp.getMACnumber(), stdTree, command);
                         hp = hp.getNext();
                     }
                     
                     stdTree.delete(mac);
                     System.out.println("MAC "+mac+" has been removed from the FCIT-ISS system.");
                     
                 }
                 else {
                     
                     System.out.println("Cannot Perform QUIT Command:\n\t"
                             + "MAC "+mac+" not found in the FCIT-ISS system.");
                     
                 }
      
  }
  public static int isInRange(FCITstudent std, FCITstudent temp) {

      // this method to calculate Range and return the result
      
            int qx = std.getX();
            int qy = std.getY();
            int px = temp.getX();
            int py = temp.getY();

            int result = (int) Math.sqrt( Math.pow((px - qx), 2) + Math.pow((py - qy), 2) );

          return result;  
    }
  public static void SHOWCONNECTIONA(FCIT_ISSbst stdTree, Scanner donga) {
      
                 int mac = donga.nextInt();
                 FCITstudent std = stdTree.findNode(mac);
               //is student in the System?
                 if( std != null ) {
                    //is his list not empty?  
                     if (std.getNumLinks() > 0) {
                         
                         System.out.println("Connections for MAC "+std.getMACnumber()
                                 +", "+std.getFirstName()+" "+std.getLastName()+", currently at position "
                                 + "("+std.getX()+", "+std.getY()+"):");
                         System.out.println("\tThere are a total of "+std.getNumLinks()+" link(s).");
                         
                        // now we count the active and non-active connections with Conditions:
            //(1) found in the linked-list of allowed laptops   (2) within the broadcasting range
                      
                         int numAct = 0; int numUnAct = 0;
                    //loop to count active and nonactive MACs
                         FCIT_ISSdevice hpDevice = std.getAllowedMACs().getHead();
                         
                         while (hpDevice != null) {
                             
                             FCITstudent temp = stdTree.findNode(hpDevice.getMACnumber());
                             
                             int result = isInRange(std,temp);
                             if( std.getBroadcastRange() >= result )
                                 numAct++;
                             else 
                                 numUnAct++;
                             
                             hpDevice = hpDevice.getNext();
                         }// end loop
                         
                         
                       
                         if(numUnAct == std.getNumLinks()){ 
                             System.out.println("\tThere are NO active links within the broadcast range of "
                                     +std.getBroadcastRange()+".");
                         }
                         else if(numAct <= std.getNumLinks() ){
                             
                             System.out.println("\tThere are "+numAct
                                     +" active link(s) within the broadcast range of "+
                                     std.getBroadcastRange()+":");
                                //loop to print only ACTIVE MACs 
                                 FCIT_ISSdevice hpDevice2 = std.getAllowedMACs().getHead();
                                 while (hpDevice2 != null) { 
                                    FCITstudent temp = stdTree.findNode(hpDevice2.getMACnumber());
                                    
                                     int result = isInRange(std,temp);
                                     
                                    if(std.getBroadcastRange() >= result ) {
                                         System.out.println("\t\tMAC "+temp.getMACnumber()+", "
                                                 +temp.getFirstName()+" "+ temp.getLastName()
                                                 +", currently at position ("+temp.getX()+", "+temp.getY()+")"); 
                                    }

                                    hpDevice2 = hpDevice2.getNext();
                                 }//end loop
                             
                         }
                         
                     
                         
                     }
                     else  {
                          System.out.println("MAC "+std.getMACnumber()+" has no links.");
                     }
                 } 
                 else {
                     System.out.println("Cannot Perform SHOWCONNECTIONS Command:\n\t"
                             + "MAC "+mac+" - This MAC Address is not in the FCIT-ISS system.");
                 }
                    
                 
                 
  }
  public static void PRINTMEMBERS(FCIT_ISSbst stdTree) {
      // print as long as the tree is not empty
       if(!stdTree.isEmpty()){
           System.out.println("Members of FCIT Internet Sharing System:");
                 stdTree.inorderMembers();
       }
                else
                    System.out.println("Cannot Perform PRINTMEMBERS Command:\n\tThere are currently no participants in the FCIT-ISS system.");
                 
  }
  public static void INORDERMOVEDEVICES(FCITstudent p, int sizeX, int sizeY, Random randomNumGen ) {
      
      // treverse in inorder treverse and change x and y in every node
      
		if (p != null) {
			INORDERMOVEDEVICES(p.getLeft(),sizeX, sizeY, randomNumGen);
                    int x = randomNumGen.nextInt(sizeX);
                    int y = randomNumGen.nextInt(sizeY);
                       p.setX(x);
                       p.setY(y);
                
			INORDERMOVEDEVICES(p.getRight(), sizeX, sizeY, randomNumGen);
		}
	}
  
    
}

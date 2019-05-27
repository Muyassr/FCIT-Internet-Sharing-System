/*
Moyasr Tamim
1539152
moytam.uni@gamil.com
 */

public class FCITstudent {
    
    private String firstName;
    private String lastName;
    private int MACnumber; 
    private int broadcastRange;
    private int x;
    private int y;
    private int numLinks;
    private FCIT_ISSlinkedDevices allowedMACs;
    private FCITstudent right;
    private FCITstudent left;
    
    
    public FCITstudent() {
       
    }
    
    
    public FCITstudent(int t) {
        this.MACnumber = t;
    }
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMACnumber() {
        return MACnumber;
    }

    public void setMACnumber(int MACnumber) {
        this.MACnumber = MACnumber;
    }

    public int getBroadcastRange() {
        return broadcastRange;
    }

    public void setBroadcastRange(int broadcastRange) {
        this.broadcastRange = broadcastRange;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumLinks() {
        return numLinks;
    }

    public void setNumLinks(int numLinks) {
        this.numLinks = numLinks;
    }

    public FCIT_ISSlinkedDevices getAllowedMACs() {
        return allowedMACs;
    }

    public void setAllowedMACs(FCIT_ISSlinkedDevices allowedMACs) {
        this.allowedMACs = allowedMACs;
    }

    public FCITstudent getRight() {
        return right;
    }

    public void setRight(FCITstudent right) {
        this.right = right;
    }

    public FCITstudent getLeft() {
        return left;
    }

    public void setLeft(FCITstudent left) {
        this.left = left;
    }
    
    
    
    
    
}

/*
Moyasr Tamim
1539152
moytam.uni@gamil.com
 */

public class FCIT_ISSdevice {
    
    private int MACnumber;
    private FCIT_ISSdevice next;

    
    public FCIT_ISSdevice(int d,FCIT_ISSdevice s) {
        this.MACnumber = d;
        this.next = s;
    }
    
     public FCIT_ISSdevice(int d) {
        this.MACnumber = d;
        this.next = null;
    }
    
     public FCIT_ISSdevice() {
        
    }
    
    public int getMACnumber() {
        return MACnumber;
    }

    public void setMACnumber(int MACnumber) {
        this.MACnumber = MACnumber;
    }

    public FCIT_ISSdevice getNext() {
        return next;
    }

    public void setNext(FCIT_ISSdevice next) {
        this.next = next;
    }
    
    
    
    
}

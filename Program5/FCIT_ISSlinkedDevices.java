/*
Moyasr Tamim
1539152
moytam.uni@gamil.com
 */

public class FCIT_ISSlinkedDevices {
    private FCIT_ISSdevice head;
    

	public boolean isEmpty() {
		return head == null;
	}
	
        
        public FCIT_ISSdevice getHead() {
       
            return head;
        }
	
	
	public boolean search(int data) {
		return search(head, data);
	}
	
	private boolean search(FCIT_ISSdevice p, int data) {
		FCIT_ISSdevice helpPtr = p;
		while (helpPtr != null) {
			if (helpPtr.getMACnumber() == data)
				return true;
			helpPtr = helpPtr.getNext();			
		}
		return false;
	}


	
	public FCIT_ISSdevice findNode(int data) {
		return findNode(head, data);
	}
	
        
	private FCIT_ISSdevice findNode(FCIT_ISSdevice p, int data) {
		FCIT_ISSdevice helpPtr = p;
		while (helpPtr != null) {
			if (helpPtr.getMACnumber() == data)
				return helpPtr;
			helpPtr = helpPtr.getNext();			
		}
		return null;
	}
	
	
	
	public void PrintList() {
		PrintList(head);
	}
	private void PrintList(FCIT_ISSdevice head) {
		// We need to traverse...so we need a help ptr
		FCIT_ISSdevice helpPtr = head;
		// Traverse to correct insertion point
		while (helpPtr != null) {
			// Print the data value of the node
			System.out.print(helpPtr.getMACnumber() + ", ");
			// Step one node over
			helpPtr = helpPtr.getNext();
		}
		System.out.println();
	}
	
	

    
	public void insert(int data) {
		head = insert(head, data);
	}
	
	private FCIT_ISSdevice insert(FCIT_ISSdevice head, int data) {
		// IF there is no list, newNode will be the first node, so just return it
		if (head == null || head.getMACnumber() > data) {
			head = new FCIT_ISSdevice(data, head);
			return head;
		}
		
		// ELSE, we have a list. Insert the new node at the correct location
		else {
			// We need to traverse to the correct insertion location...so we need a help ptr
			FCIT_ISSdevice helpPtr = head;
			// Traverse to correct insertion point
			while (helpPtr.getNext() != null) {
				if (helpPtr.getNext().getMACnumber() > data)
					break; // we found our spot and should break out of the while loop
				helpPtr = helpPtr.getNext();
			}
			// Now make the new node. Set its next to point to the successor node.
			// And then make the predecessor node point to the new node
			FCIT_ISSdevice newNode = new FCIT_ISSdevice(data, helpPtr.getNext());
			helpPtr.setNext(newNode);
		}
		// Return head
		return head;
	}
	
	
	public void delete(int data) {
		head = delete(head, data);
	}
	private FCIT_ISSdevice delete(FCIT_ISSdevice head, int data) {
		// We can only delete if the list has nodes (is not empty)
		if (!isEmpty()) {
			// IF the first node (at the head) has the data value we are wanting to delete
			// we found it. Delete by skipping the node and making head point to the next node.
			if (head.getMACnumber() == data) {
				head = head.getNext();
			}
			// ELSE, the data is perhaps somewhere else in the list...so we must traverse and look for it
			else {
				// We need to traverse to find the data we want to delete...so we need a help ptr
				FCIT_ISSdevice helpPtr = head;
				// Traverse to correct deletion point
				while (helpPtr.getNext() != null) {
					if (helpPtr.getNext().getMACnumber() == data) {
						helpPtr.setNext(helpPtr.getNext().getNext());
						break; // we deleted the value and should break out of the while loop
					}
					helpPtr = helpPtr.getNext();
				}
			}
			// return the possibly updated head of the list
			return head;
		}
		return head;
	}

}
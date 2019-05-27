/*
Moyasr Tamim
1539152
moyuni@gamil.com
 */

public class FCIT_ISSbst {

    private FCITstudent root;

    public boolean isEmpty() {
        return root == null;
    }

    public FCITstudent getRoot() {
        return root;
    }

    //
    // boolean | search(int)
    //
    public boolean search(int MACnumber) {
        return search(root, MACnumber);
    }
   

    private boolean search(FCITstudent p, int MACnumber) {
        if (p == null) {
            return false;
        } else {
            // if the data we are searching for is found at p (at the current root)
            if (MACnumber == p.getMACnumber()) {
                return true;
            } else if (MACnumber < p.getMACnumber()) {
                return search(p.getLeft(), MACnumber);
            } else {
                return search(p.getRight(), MACnumber);
            }
        }
    }

   
    public void insert(FCITstudent newStd) {
        root = insert(root, newStd);
    }
   

    private FCITstudent insert(FCITstudent r, FCITstudent newStd) {
        // IF there is no tree, newNode will be the root, so just return it
        if (r == null) {
            return newStd;
        } // ELSE, we have a tree. Insert to the right or the left
        else {
            // Insert to the RIGHT of root
            if (newStd.getMACnumber() > r.getMACnumber()) {
                // Recursively insert into the right subtree
                // The result of insertion is an updated root of the right subtree
                FCITstudent temp = insert(r.getRight(), newStd);
                // Save this newly updated root of right subtree into p.right
                r.setRight(temp);
            } // Insert to the LEFT of root
            else {
                // Recursively insert into the left subtree
                // The result of insertion is an updated root of the left subtree
                FCITstudent temp = insert(r.getLeft(), newStd);
                // Save this newly updated root of left subtree into p.left
                r.setLeft(temp);
            }
        }
        // Return root of updated tree
        return r;
    }

  
    public void inorderMembers() {
        inorderMembers(root);
    }
  

    private void inorderMembers(FCITstudent p) {
        if (p != null) {

            inorderMembers(p.getLeft());
            String l = " Link";
            if (p.getNumLinks() != 1) {
                l += "s";
            }

            System.out.println("\tMAC " + p.getMACnumber()
                    + ", " + p.getFirstName() + " " + p.getLastName()
                    + ", currently at position (" + p.getX() + ", " + p.getY() + "), "
                    + p.getNumLinks() + l);

            inorderMembers(p.getRight());
        }
    }

    public void inorderMOVEDEVICE(int sizeX, int sizeY) {
        inorderMOVEDEVICE(root, sizeX, sizeY);
    }
    //
    // void | inorder(FCITstudent)
    //

    private void inorderMOVEDEVICE(FCITstudent p, int sizeX, int sizeY) {
        if (p != null) {
            inorderMOVEDEVICE(p.getLeft(), sizeX, sizeY);

            p.setX(sizeX);
            p.setY(sizeY);

            inorderMOVEDEVICE(p.getRight(), sizeX, sizeY);
        }
    }

    public void DeleteMac(int mac) {
        DeleteMac(root, mac);
    }
    //
    // void | inorder(FCITstudent)
    //

    private void DeleteMac(FCITstudent p, int mac) {
        if (p != null) {
            DeleteMac(p.getLeft(), mac);
            //System.out.print(p.getMACnumber() + ", ");
            if (p.getMACnumber() != mac && p.getAllowedMACs().search(mac)) {
                p.getAllowedMACs().delete(mac);
            }
            DeleteMac(p.getRight(), mac);
        }
    }

    //
    // int | sumNodes()
    //
    public int sumNodes() {
        return sumNodes(root);
    }
    //
    // int | sumNodes(FCITstudent)
    //

    private int sumNodes(FCITstudent p) {
        if (p != null) {
            return p.getMACnumber() + sumNodes(p.getLeft()) + sumNodes(p.getRight());
        } else {
            return 0;
        }
    }

    //
    // void | delete(int)
    //
    public void delete(int data) {
        root = delete(root, data);
    }
    //
    // FCITstudent | delete(FCITstudent, int)
    //

    private FCITstudent delete(FCITstudent p, int data) {
        FCITstudent node2delete, newnode2delete, node2save, parent;
        int saveValue;

        // Step 1: Find the node we want to delete
        node2delete = findNode(p, data);
        // If node is not found (does not exist in tree), we clearly cannot delete it.
        if (node2delete == null) {
            return root;
        }

        // Step 2: Find the parent of the node we want to delete
        parent = parent(p, node2delete);

        // Step 3: Perform Deletion based on three possibilities
        // **1** :  node2delete is a leaf node
        if (isLeaf(node2delete)) {
            // if parent is null, this means that node2delete is the ONLY node in the tree
            if (parent == null) {
                return null; // we return null as the updated root of the tree
            }
            // Delete node if it is a left child
            if (data < parent.getMACnumber()) {
                parent.setLeft(null);
            } // Delete node if it is a right child
            else {
                parent.setRight(null);
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **2** : node2delete has only a left child
        if (hasOnlyLeftChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getLeft();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some node
            // IF it is the left child of some node
            if (data < parent.getMACnumber()) {
                parent.setLeft(parent.getLeft().getLeft());
            } // ELSE it is the right child of some node
            else {
                parent.setRight(parent.getRight().getLeft());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **3** :  node2delete has only a right child
        if (hasOnlyRightChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getRight();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some node
            // IF it is the left child of some node
            if (data < parent.getMACnumber()) {
                parent.setLeft(parent.getLeft().getRight());
            } // ELSE it is the right child of some node
            else {
                parent.setRight(parent.getRight().getRight());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **4** :  node2delete has TWO children.
        // Remember, we have two choices: the minVal from the right subtree (of the deleted node)
        // or the maxVal from the left subtree (of the deleted node)
        // We choose to find the minVal from the right subtree.
        newnode2delete = minNode(node2delete.getRight());
        // Now we need to temporarily save the data value(s) from this node
        saveValue = newnode2delete.getMACnumber();

        // Now, we recursively call our delete method to actually delete this node that we just copied the data from
        p = delete(p, saveValue);

        // Now, put the saved data (in saveValue) into the correct place (the original node we wanted to delete)
        node2delete.setMACnumber(saveValue);
        node2delete.setAllowedMACs(newnode2delete.getAllowedMACs());
        node2delete.setBroadcastRange(newnode2delete.getBroadcastRange());
        node2delete.setFirstName(newnode2delete.getFirstName());
        node2delete.setLastName(newnode2delete.getLastName());
        node2delete.setNumLinks(newnode2delete.getNumLinks());
        node2delete.setX(newnode2delete.getX());
        node2delete.setY(newnode2delete.getY());
        // Finally, return the root of the tree (in case the root got updated)
        return p;
    }

    //
    // FCITstudent | minNode(FCITstudent)
    //
    public FCITstudent minNode(FCITstudent root) {
        if (root == null) {
            return null;
        } else {
            if (root.getLeft() == null) {
                return root;
            } else {
                return minNode(root.getLeft());
            }
        }
    }

    //
    // FCITstudent | maxNode(FCITstudent)
    //
    public FCITstudent maxNode(FCITstudent root) {
        if (root == null) {
            return null;
        } else {
            if (root.getRight() == null) {
                return root;
            } else {
                return maxNode(root.getRight());
            }
        }
    }

    //
    // FCITstudent | findNode(int)
    //
    public FCITstudent findNode(int data) {
        return findNode(root, data);
    }
    //
    // FCITstudent | findNode(FCITstudent, int)
    //

    private FCITstudent findNode(FCITstudent p, int data) {
        if (p == null) {
            return null;
        } else {
            // if the data we are searching for is found at p (at the current root)
            if (data == p.getMACnumber()) {
                return p;
            } else if (data < p.getMACnumber()) {
                return findNode(p.getLeft(), data);
            } else {
                return findNode(p.getRight(), data);
            }
        }
    }

    //
    // FCITstudent | parent(FCITstudent)
    //
    public FCITstudent parent(FCITstudent p) {
        return parent(root, p);
    }
    //
    // FCITstudent | parent(FCITstudent, FCITstudent)
    //

    private FCITstudent parent(FCITstudent root, FCITstudent p) {
        // Take care of NULL cases
        if (root == null || root == p) {
            return null; // because there is on parent
        }
        // If root is the actual parent of node p
        if (root.getLeft() == p || root.getRight() == p) {
            return root; // because root is the parent of p
        }
        // Look for p's parent in the left side of root
        if (p.getMACnumber() < root.getMACnumber()) {
            return parent(root.getLeft(), p);
        } // Look for p's parent in the right side of root
        else if (p.getMACnumber() > root.getMACnumber()) {
            return parent(root.getRight(), p);
        } // Take care of any other cases
        else {
            return null;
        }
    }

    //
    // boolean | isLeaf(FCITstudent)
    //
    public boolean isLeaf(FCITstudent p) {
        return (p.getLeft() == null && p.getRight() == null);
    }

    //
    // boolean | hasOnlyLeftChild(FCITstudent)
    //
    public boolean hasOnlyLeftChild(FCITstudent p) {
        return (p.getLeft() != null && p.getRight() == null);
    }

    //
    // boolean | hasOnlyRightChild(FCITstudent)
    //
    public boolean hasOnlyRightChild(FCITstudent p) {
        return (p.getLeft() == null && p.getRight() != null);
    }

}

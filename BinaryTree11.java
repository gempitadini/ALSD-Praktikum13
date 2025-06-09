public class BinaryTree11 {
    Node11 root;

    public BinaryTree11() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

        public void add(Mahasiswa11 mahasiswa) {
        Node11 newNode = new Node11(mahasiswa);
        if (root == null) {
            root = newNode;
        } else {
            Node11 current = root;
            Node11 parent;
            while (true) {
                parent = current;
                if (mahasiswa.ipk < current.mahasiswa.ipk) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean find(double ipk) {
        Node11 current = root;
        while (current != null) {
            if (current.mahasiswa.ipk == ipk) {
                return true;
            } else if (ipk < current.mahasiswa.ipk) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public void traversePreOrder(Node11 node) {
        if (node != null) {
            node.mahasiswa.tampilInformasi();
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    public void traverseInOrder(Node11 node) {
        if (node != null) {
            traverseInOrder(node.left);
            node.mahasiswa.tampilInformasi();
            traverseInOrder(node.right);
        }
    }

    public void traversePostOrder(Node11 node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            node.mahasiswa.tampilInformasi();
        }
    }

    public boolean delete(double ipk) {
        Node11 current = root;
        Node11 parent = root;
        boolean isLeftChild = true;

        // Cari node
        while (current != null && current.mahasiswa.ipk != ipk) {
            parent = current;
            if (ipk < current.mahasiswa.ipk) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
        }

        if (current == null) {
            System.out.println("Mahasiswa dengan IPK " + ipk + " tidak ditemukan.");
            return false;
        }

        // 1. Node tidak punya anak
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }

        // 2. Node hanya punya anak kanan
        else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }

        // 3. Node hanya punya anak kiri
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }

        // 4. Node punya dua anak
        else {
            Node11 successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }

        return true;
    }

    private Node11 getSuccessor(Node11 del) {
        Node11 successorParent = del;
        Node11 successor = del;
        Node11 current = del.right;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        if (successor != del.right) {
            successorParent.left = successor.right;
            successor.right = del.right;
        }

        return successor;
    }

    public void addRekursif(Mahasiswa11 mahasiswa) {
        root = addRekursif(root, mahasiswa);
    }

    private Node11 addRekursif(Node11 current, Mahasiswa11 mahasiswa) {
        if (current == null) {
            return new Node11(mahasiswa);
        }

        if (mahasiswa.ipk < current.mahasiswa.ipk) {
            current.left = addRekursif(current.left, mahasiswa);
        } else {
            current.right = addRekursif(current.right, mahasiswa);
        }

        return current;
    }

    public void cariMinIPK() {
        if (root == null) {
            System.out.println("Tree kosong");
            return;
        }
        Node11 current = root;
        while (current.left != null) {
            current = current.left;
        }
        System.out.print("Data Mahasiswa dengan IPK terkecil: ");
        current.mahasiswa.tampilInformasi();
    }

    public void cariMaxIPK() {
        if (root == null) {
            System.out.println("Tree kosong");
            return;
        }
        Node11 current = root;
        while (current.right != null) {
            current = current.right;
        }
        System.out.print("Data Mahasiswa dengan IPK terbesar: ");
        current.mahasiswa.tampilInformasi();
    }

    public void tampilMahasiswaIPKdiAtas(double ipkBatas) {
        tampilMahasiswaIPKdiAtas(root, ipkBatas);
    }

    private void tampilMahasiswaIPKdiAtas(Node11 node, double ipkBatas) {
        if (node != null) {
            tampilMahasiswaIPKdiAtas(node.left, ipkBatas);
            if (node.mahasiswa.ipk > ipkBatas) {
                node.mahasiswa.tampilInformasi();
            }
            tampilMahasiswaIPKdiAtas(node.right, ipkBatas);
        }
    }
}


public class BinaryTreeArray11 {
    Mahasiswa11[] dataMahasiswa;
    int idxLast;

    public BinaryTreeArray11() {
        this.dataMahasiswa = new Mahasiswa11[10];
    }

    void populateData(Mahasiswa11 dataMhs[], int idxLast) {
        this.dataMahasiswa = dataMhs;
        this.idxLast = idxLast;
    }

    void traverseInOrder(int idxStart) {
        if (idxStart <= idxLast) {
            if (dataMahasiswa[idxStart] != null) {
                traverseInOrder(2 * idxStart + 1);
                dataMahasiswa[idxStart].tampilInformasi();
                traverseInOrder(2 * idxStart + 2);
            }
        }
    }

    public void add(Mahasiswa11 data) {
        if (dataMahasiswa[0] == null) {
            dataMahasiswa[0] = data;
            idxLast = 0;
        } else {
            int index = 0;
            while (index < dataMahasiswa.length) {
                if (dataMahasiswa[index] == null) {
                    dataMahasiswa[index] = data;
                    idxLast = Math.max(idxLast, index);
                    return;
                } 
                if (data.ipk < dataMahasiswa[index].ipk) {
                    index = 2 * index + 1;
                } else {
                    index = 2 * index + 2;
                }
                if (index >= dataMahasiswa.length) {
                    System.out.println("Tree penuh, tidak bisa menambahkan data baru.");
                    return;
                }
            }
        }
    }

    void traversePreOrder(int idxStart) {
        if (idxStart <= idxLast && dataMahasiswa[idxStart] != null) {
            dataMahasiswa[idxStart].tampilInformasi();
            traversePreOrder(2 * idxStart + 1);
            traversePreOrder(2 * idxStart + 2);
        }
    }
}

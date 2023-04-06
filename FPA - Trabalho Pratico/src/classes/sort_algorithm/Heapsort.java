package classes.sort_algorithm;

public class Heapsort {

  long contComparacoes = 0;
  long contItensModificados = 0;

  public int[] sort(int arr[]) {
    int n = arr.length;

    // Build max heap
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(arr, n, i);
    }

    // Heap sort
    for (int i = n - 1; i >= 0; i--) {
      int temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;

      // Heapify root element
      heapify(arr, i, 0);
    }
    return arr;
  }

  private void heapify(int arr[], int n, int i) {
    // Find largest among root, left child and right child
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    contComparacoes++;

    if (l < n && arr[l] > arr[largest])
      largest = l;

    if (r < n && arr[r] > arr[largest])
      largest = r;

    // Swap and continue heapifying if root is not largest
    if (largest != i) {
      contItensModificados++;
      int swap = arr[i];
      arr[i] = arr[largest];
      arr[largest] = swap;

      heapify(arr, n, largest);
    }
  }

  public void showComparations() {
    System.out.println("Número de comparações: " + contComparacoes);
    System.out.println("Número de modificações: " + contItensModificados);
  }
}

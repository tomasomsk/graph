package findshortestpath;

public class JQueue {

    private int[] array;//массив для хранения элементов
    private int size;//количество элементов в очереди
    private int count;//текущее количество элементов
    private int front;//элемент, который находится в начале очереди
    private int rear;//элемент, который находится в конце очереди

    public JQueue(int queueSize) {
        size = queueSize;
        array = new int[size];
        front = 0;
        rear = -1;
        count = 0;//элементов в очереди пока нет

    }

    //вставка элемента в конец очереди
    public void insert(int value) {
        if (rear == size - 1)
            rear = -1;
        array[++rear] = value;
        count++;
    }

    //извлечение элемента из начала очереди
    public int pop() {
        int temp = array[front++];
        if (front == size)
            front = 0;
        count--;
        return temp;
    }

    public int size() {
        return count;
    }

    //проверка, пуста ли очередь (а вот это в реальной жизни вряд ли пригодилось)
    public boolean isEmpty() {
        return (count == 0);
    }
}

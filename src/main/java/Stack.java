public class Stack {

    private final int maxSize = 100;//Размер массива
    private final int[] stackArray;
    private int top;//Вершина стека

    public Stack() {
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int node) {
        stackArray[++top] = node;//Увеличение top, вставка элемента
    }

    public int pop() {
        return stackArray[top--];//Извлечение элемента, уменьшение top
    }

    //Чтение элемента с вершины стека
    public int peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        //True, если стек пуст
        return (top == -1);
    }

    public boolean isFull() {
        //True, если стек полон
        return (top == maxSize - 1);
    }

}

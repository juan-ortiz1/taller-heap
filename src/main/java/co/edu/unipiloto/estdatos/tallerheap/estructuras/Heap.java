/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unipiloto.estdatos.tallerheap.estructuras;

/**
 *
 * @author Juan David
 * @param <Key>
 */
public class Heap<Key extends Comparable<Key>> implements IHeap<Key> {

    private Node<Key> root;
    private int size;

    public Heap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(Key elemento) {

        Node<Key> nuevoNodo = new Node<Key>(elemento);
        if (root == null) {
            root = nuevoNodo;
        } else {
            Node<Key> padre = getNode((size + 1) / 2);
            if (size % 2 == 0) {
                padre.right = nuevoNodo;
            } else {
                padre.left = nuevoNodo;
            }
            nuevoNodo.father = padre;
        }
        size++;

        siftUp();

    }

    public Node<Key> getNode(int index) {
        if (index < 1 || index > size) {
            return null;
        }

        Node<Key> node = root;
        int path = index;

        int depth = 0;
        while (path > 1) {
            path /= 2;
            depth++;
        }

        path = index;

        for (int i = depth - 1; i >= 0; i--) {
            if (node == null) {
                return null;
            }

            boolean isLeft = ((path >> i) & 1) == 0;
            node = isLeft ? node.left : node.right;
        }

        return node;
    }

    public Key getNodeKey(int index) {
        Node<Key> node = getNode(index);
        if (node != null) {
            return node.getKey();
        }
        return null;
    }

    @Override
    public Key peek() {
        if (root != null) {
            return root.getKey();
        }
        return null;
    }

    @Override
    public Key poll() {
        if (root == null) {
            return null;
        }
        Key result = root.getKey();
        if (size == 1) {
            root = null;
        } else {
            Node<Key> lastNode = getNode(size);
            exch(root, lastNode);
            if (lastNode.father.right == lastNode) {
                lastNode.father.right = null;
            } else {
                lastNode.father.left = null;
            }

        }
        if (root != null) {
            siftDown();
        }
        size--;
        return result;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void siftUp() {
        Node<Key> node = getNode(size);
        while (node.father != null && less(node.father, node)) {
            exch(node, node.father);
            node = node.father;
        }
    }

    private boolean less(Node<Key> a, Node<Key> b) {
        return a.getKey().compareTo(b.getKey()) < 0;
    }

    private void exch(Node<Key> a, Node<Key> b) {
        Key temp = a.getKey();
        a.setKey(b.getKey());
        b.setKey(temp);
    }

    @Override
    public void siftDown() {
        Node<Key> node = root;
        while (node.left != null) {
            Node<Key> maxChild = (node.right != null && less(node.left, node.right)) ? node.right : node.left;
            if (!less(node, maxChild)) {
                break;
            }
            exch(node, maxChild);
            node = maxChild;
        }
    }

    private class Node<Key extends Comparable<Key>> {

        private Key key;
        private Node<Key> left, right, father;

        public Node(Key key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.father = null;
        }

        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 1; i <= size; i++) {
            Node<Key> node = getNode(i);
            if (node != null) {
                sb.append(node.getKey());
                if (i < size) {
                    sb.append(", ");
                }
            }
        }

        sb.append("]");
        return sb.toString();
    }
}

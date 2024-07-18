class AVL {
    private NodoAVL raiz;

    public AVL() {
        this.raiz = null;
    }

    // Método para inserir um valor na árvore AVL
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private NodoAVL inserirRecursivo(NodoAVL nodo, int valor) {
        if (nodo == null) {
            return new NodoAVL(valor);
        }

        if (valor < nodo.valor) {
            nodo.esquerda = inserirRecursivo(nodo.esquerda, valor);
        } else if (valor > nodo.valor) {
            nodo.direita = inserirRecursivo(nodo.direita, valor);
        } else {
            return nodo;  // valores duplicados não são permitidos
        }

        nodo.altura = 1 + Math.max(altura(nodo.esquerda), altura(nodo.direita));

        int balanceamento = getBalanceamento(nodo);

        // Verificar os casos de balanceamento
        // RSD - Rotação Simples à Direita
        if (balanceamento > 1 && valor < nodo.esquerda.valor) {
            System.out.println("RSD - Rotação Simples à Direita");
            return rotacaoDireita(nodo);
        }

        // RSE - Rotação Simples à Esquerda
        if (balanceamento < -1 && valor > nodo.direita.valor) {
            System.out.println("RSE - Rotação Simples à Esquerda");
            return rotacaoEsquerda(nodo);
        }

        // RDD - Rotação Dupla à Direita
        if (balanceamento > 1 && valor > nodo.esquerda.valor) {
            System.out.println("RDD - Rotação Dupla à Direita");
            nodo.esquerda = rotacaoEsquerda(nodo.esquerda);
            return rotacaoDireita(nodo);
        }

        // RDE - Rotação Dupla à Esquerda
        if (balanceamento < -1 && valor < nodo.direita.valor) {
            System.out.println("RDE - Rotação Dupla à Esquerda");
            nodo.direita = rotacaoDireita(nodo.direita);
            return rotacaoEsquerda(nodo);
        }

        return nodo;
    }

    // Métodos de rotação
    private NodoAVL rotacaoDireita(NodoAVL y) {
        NodoAVL x = y.esquerda;
        NodoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    private NodoAVL rotacaoEsquerda(NodoAVL x) {
        NodoAVL y = x.direita;
        NodoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    // Método para obter a altura de um nodo
    private int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    // Método para obter o fator de balanceamento de um nodo
    private int getBalanceamento(NodoAVL nodo) {
        return nodo == null ? 0 : altura(nodo.esquerda) - altura(nodo.direita);
    }

    // Método para buscar um valor na árvore
    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(NodoAVL nodo, int valor) {
        if (nodo == null) {
            return false;
        }

        if (valor == nodo.valor) {
            return true;
        }

        return valor < nodo.valor ? buscarRecursivo(nodo.esquerda, valor) : buscarRecursivo(nodo.direita, valor);
    }

    // Método para imprimir a árvore (ordem simétrica)
    public void imprimir() {
        imprimirRecursivo(raiz);
    }

    private void imprimirRecursivo(NodoAVL nodo) {
        if (nodo != null) {
            imprimirRecursivo(nodo.esquerda);
            System.out.print(nodo.valor + " ");
            imprimirRecursivo(nodo.direita);
        }
    }

    public static void main(String[] args) {
        AVL arvore = new AVL();

        int[] valores = {51, 57, 98, 19, 11, 45, 79};

        for (int valor : valores) {
            arvore.inserir(valor);
        }

        System.out.println("Impressão da árvore em ordem:");
        arvore.imprimir();
    }
}

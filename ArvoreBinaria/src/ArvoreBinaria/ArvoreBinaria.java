package ArvoreBinaria;

	import java.util.LinkedList;
	import java.util.Queue;
	
	public class ArvoreBinaria {
		
		private class Nodo{
			private int chave;
			private Nodo dir, esq;
			
			public Nodo(int item) {
				this.chave = item;
				dir = esq = null;
			}
		}
		
		Nodo raiz = null;
		
		
		
		public void inserir(int chave) {
			raiz = inserirDado(raiz, chave);
		}
		
		private Nodo inserirDado(Nodo raiz, int chave) {
			if (raiz == null) {
				raiz = new Nodo(chave);
				return raiz;
			}
			
			if(chave < raiz.chave)
				raiz.esq = inserirDado(raiz.esq, chave);
			else if(chave > raiz.chave)
				raiz.dir = inserirDado(raiz.dir, chave);
			return raiz;
		}
		
		public void mostrarEmOrdem() {
			mostrarCrescente(raiz);
		}
		
		private void mostrarCrescente(Nodo raiz) {
			if(raiz != null) {
				mostrarCrescente(raiz.esq);
				System.out.println(raiz.chave);
				mostrarCrescente(raiz.dir);
			}
		}
		
		public void mostrarEmOrdemDescrescente() {
			mostrarDecrescente(raiz);
		}
		
		private void mostrarDecrescente(Nodo raiz) {
			if(raiz != null) {
				mostrarDecrescente(raiz.dir);
				System.out.println(raiz.chave);
				mostrarDecrescente(raiz.esq);
			}
		}
		
		public void mostrarPorNivel() {
			if(raiz == null) {
				System.out.println("Árvore vazia!");
				return;
			}
			Queue<Nodo> fila = new LinkedList<>();
			fila.add(raiz);
			
			while(!fila.isEmpty()) {
				int tamanhoNivel = fila.size();
				for(int i = 0; i < tamanhoNivel; i++) {
					Nodo nodoAtual = fila.poll();
					if(nodoAtual != null) {
						System.out.print(nodoAtual.chave + " ");
						fila.add(nodoAtual.esq);
						fila.add(nodoAtual.dir);
					} else {
						System.out.print("- ");
					}
				}
				System.out.println();
			}
		}
		
		
		public void remover(int chave) {
			raiz = removerItem(raiz, chave);
		}
		
		private Nodo removerItem(Nodo raiz, int chave) {
			if(raiz == null) {       // Nó não encontrado, não faz nada
				return null;
			}
			if(chave < raiz.chave) {
				raiz.esq = removerItem(raiz.esq, chave);    // chave a ser removida está à esquerda
			} else if(chave > raiz.chave) {
				raiz.dir = removerItem(raiz.dir, chave);    // chave a ser removida está à direita
			} else {             // encontramos o nó a ser removido
				if(raiz.esq == null) {       // Caso em que o nó não possui filho a esquerda
					return raiz.dir;
				} else if(raiz.dir == null){    // Caso em que o nó não possui filho a direita
					return raiz.esq;
				} else {          // Caso em que o nó possui ambos os filhos, sucessor será o menor da subarvore a direita
					Nodo sucessor = encontrarSucessor(raiz.dir);
					raiz.chave = sucessor.chave;     // substituimos o valor do nó a ser removido pelo valor do sucessor
					raiz.dir = removerItem(raiz.dir, sucessor.chave);
				}
			}
			return raiz;
		}
		
		private Nodo encontrarSucessor(Nodo nodo) {
			while(nodo.esq != null) {
				nodo = nodo.esq;
			}
			return nodo;
		}
			
		private int maiorElemento(Nodo raiz) {           // Item a)
		    if (raiz == null) {
		        // Retorna o menor valor possível caso a árvore esteja vazia
		        return 0;
		    }
	
		    int valorMaximoAtual = raiz.chave;
		    int valorMaximoEsquerda = maiorElemento(raiz.esq);
		    int valorMaximoDireita = maiorElemento(raiz.dir);
	
		    // Se o valor máximo da esquerda é maior que o valor máximo atual, atualiza o valor máximo atual
		    if (valorMaximoEsquerda > valorMaximoAtual) {
		        valorMaximoAtual = valorMaximoEsquerda;
		    }
	
		    // Se o valor máximo da direita é maior que o valor máximo atual, atualiza o valor máximo atual
		    if (valorMaximoDireita > valorMaximoAtual) {
		        valorMaximoAtual = valorMaximoDireita;
		    }
	
		    return valorMaximoAtual;
		}
		
		public void mostrarMaior() {                  // Item a)
		    int maior = maiorElemento(raiz);
		    System.out.println("O maior valor na árvore binária é: " + maior);
		}
		 
		private int menorElemento(Nodo raiz) {         // Item b)
		    if (raiz == null) {
		        // Retorna o maior valor possível caso a árvore esteja vazia
		        return 0;
		    }
	
		    int valorMinimoAtual = raiz.chave;
		    int valorMinimoEsquerda = menorElemento(raiz.esq);
		    int valorMinimoDireita = menorElemento(raiz.dir);
	
		    // Se o valor mínimo da esquerda é menor que o valor mínimo atual, atualiza o valor mínimo atual
		    if (valorMinimoEsquerda < valorMinimoAtual) {
		        valorMinimoAtual = valorMinimoEsquerda;
		    }
	
		    // Se o valor mínimo da direita é menor que o valor mínimo atual, atualiza o valor mínimo atual
		    if (valorMinimoDireita < valorMinimoAtual) {
		        valorMinimoAtual = valorMinimoDireita;
		    }
	
		    return valorMinimoAtual;
		}
		
		public void mostrarMenor() {               // Item b)
		    int menor = menorElemento(raiz);
		    System.out.println("O menor valor na árvore binária é: " + menor);
		}
	
		
		private void mostrarNosFolhas(Nodo raiz) {         // Item c)
		    if (raiz != null) {
		        // Se o nó atual é um nó folha, imprima-o
		        if (raiz.esq == null && raiz.dir == null) {
		            System.out.println(raiz.chave);
		        } else {
		            // Se não for um nó folha, continue a percorrer a árvore
		            mostrarNosFolhas(raiz.esq);
		            mostrarNosFolhas(raiz.dir);
		        }
		    }
		}
		
		public void mostrarNosFolhas() {            // Item c)
		    System.out.println("Os nós folha são:"); 
		    mostrarNosFolhas(raiz);
		}
		
		
		private boolean mostrarNosAncestrais(Nodo nodo, int chave) {              // Item d)
		    // Retorna false se a árvore está vazia
		    if (nodo == null) {
		        return false;
		    }
		    // Retorna true se a chave está na raiz ou em um dos ancestrais
		    if (nodo.chave == chave) {
		        return true;
		    }
		    // Se a chave está presente em uma das subárvores, então o nó é um ancestral
		    if (mostrarNosAncestrais(nodo.esq, chave) || mostrarNosAncestrais(nodo.dir, chave)) {
		        System.out.print(nodo.chave + " ");
		        return true;
		    }
		    // Se a chave não está presente em nenhuma subárvore, retorne false
		    return false;
		}
	
		
		public void mostrarNosAncestrais(int chave) {                 // Item d)
		    System.out.println("Os ancestrais do nó " + chave + " são:");
		    mostrarNosAncestrais(raiz, chave);
		    System.out.println();
		}
	
	
		private void mostrarNosDescendentes(Nodo nodo) {            // Item e)
		    // Se o nodo é nulo, retorne
		    if (nodo == null) {
		        return;
		    }
	
		    // Imprime a chave do nodo
		    System.out.print(nodo.chave + " ");
	
		    // Chama recursivamente o método para os filhos esquerdo e direito
		    mostrarNosDescendentes(nodo.esq);
		    mostrarNosDescendentes(nodo.dir);
		}
	
		public void mostrarNosDescendentes(int chave) {             // Item e)
		    // Encontra o nó com a chave desejada
		    Nodo nodo = buscarNodo(raiz, chave);
	
		    // Se o nó foi encontrado, imprime seus descendentes
		    if (nodo != null) {
		        System.out.println("Os descendentes do nó " + chave + " são:");
		        mostrarNosDescendentes(nodo);
		        System.out.println();
		    } else {
		        System.out.println("Nó não encontrado na árvore.");
		    }
		}
	
		private Nodo buscarNodo(Nodo nodo, int chave) {                     // Item e,f,g)
		    // Se o nodo é nulo ou a chave é encontrada, retorna o nodo
		    if (nodo == null || nodo.chave == chave) {
		        return nodo;
		    }
		    // A chave é menor que a chave do nodo, procura na subárvore esquerda
		    if (chave < nodo.chave) {
		        return buscarNodo(nodo.esq, chave);
		    }
		    // A chave é maior que a chave do nodo, procura na subárvore direita
		    return buscarNodo(nodo.dir, chave);
		}
		
		
		private void mostrarSubArvoreDireita(Nodo nodo) {         // Item f)
		    // Se o nodo é nulo, retorne
		    if(nodo == null) {
		        return;
		    }
	
		    // Imprime a chave do nodo
		    System.out.print(nodo.chave + " ");
	
		    // Chama recursivamente o método para o filho direito
		    mostrarSubArvoreDireita(nodo.dir);
		}
	
		public void mostrarSubArvoreDireita(int chave) {             // Item f)
		    // Encontra o nó com a chave desejada
		    Nodo nodo = buscarNodo(raiz, chave);
	
		    // Se o nó foi encontrado, imprime a subárvore direita
		    if(nodo != null) {
		        System.out.println("A subárvore direita do nó " + chave + " é:");
		        mostrarSubArvoreDireita(nodo.dir);
		        System.out.println();
		    } else {
		        System.out.println("Nó não encontrado na árvore.");
		    }
		}
		
		private void mostrarSubArvoreEsquerda(Nodo nodo) {          // Item g)
		    // Se o nodo é nulo, retorne
		    if(nodo == null) {
		        return;
		    }
	
		    // Imprime a chave do nodo
		    System.out.print(nodo.chave + " ");
	
		    // Chama recursivamente o método para o filho esquerdo
		    mostrarSubArvoreEsquerda(nodo.esq);
		}
	
		public void mostrarSubArvoreEsquerda(int chave) {          // Item g)
		    // Encontra o nó com a chave desejada
		    Nodo nodo = buscarNodo(raiz, chave);
	
		    // Se o nó foi encontrado, imprime a subárvore esquerda
		    if(nodo != null) {
		        System.out.println("A subárvore esquerda do nó " + chave + " é:");
		        mostrarSubArvoreEsquerda(nodo.esq);
		        System.out.println();
		    } else {
		        System.out.println("Nó não encontrado na árvore.");
		    }
		}
		
		public class NodoLista {             // Item h)
		    int chave;
		    NodoLista proximo;
	
		    NodoLista(int chave) {
		        this.chave = chave;
		        this.proximo = null;
		    }
		}
	
		NodoLista cabecaLista; // cabeça da lista encadeada
		NodoLista atualLista; // nodo atual na lista encadeada
	
		public void transformarEmLista() {                   // Item h)
		    cabecaLista = atualLista = null;
		    transformarEmLista(raiz);
		    raiz = null;
		}
	
		private void transformarEmLista(Nodo nodo) {         // Item h)
		    // Caso base: se o nodo é nulo, retorne
		    if(nodo == null) {
		        return;
		    }
	
		    // Primeiro, transforma a subárvore esquerda
		    transformarEmLista(nodo.esq);
	
		    // Agora, visita o nodo atual
		    // Se a lista está vazia, define o nodo atual como a cabeça da lista
		    // Senão, adiciona o nodo atual ao final da lista
		    if(cabecaLista == null) {
		        cabecaLista = atualLista = new NodoLista(nodo.chave);
		    } else {
		        atualLista.proximo = new NodoLista(nodo.chave);
		        atualLista = atualLista.proximo;
		    }
	
		    // Finalmente, transforma a subárvore direita
		    transformarEmLista(nodo.dir);
		}
		
		public void imprimirLista() {                // Item h)
		    NodoLista nodoAtual = cabecaLista;
		    System.out.println("Lista: ");
		    while(nodoAtual != null) {
		        System.out.println(nodoAtual.chave);
		        nodoAtual = nodoAtual.proximo;
		    }
		}
	
		public void mostrarPares() {             // Item i)
		    mostrarPares(raiz);
		}
	
		private void mostrarPares(Nodo raiz) {   // Item i)
		    if (raiz == null) {
		        return;
		    }
		    
		    if (raiz.chave % 2 == 0) {
		        System.out.println(raiz.chave);
		    }
		    
		    if (raiz.esq != null) {
		        mostrarPares(raiz.esq);
		    }
		    
		    if (raiz.dir != null) {
		        mostrarPares(raiz.dir);
		    }
		}

		private int nivelDoNodo(Nodo nodo, int chave, int nivel) {          // Item j)
		    // Se a árvore está vazia, retorna 0
		    if (nodo == null) {
		        return 0;
		    }

		    // Se a chave está na raiz, retorna o nível
		    if (nodo.chave == chave) {
		        return nivel;
		    }

		    // Se a chave está em uma das subárvores, retorna o nível
		    int nivelDaSubarvore = nivelDoNodo(nodo.esq, chave, nivel + 1);
		    if (nivelDaSubarvore != 0) {
		        return nivelDaSubarvore;
		    }

		    nivelDaSubarvore = nivelDoNodo(nodo.dir, chave, nivel + 1);
		    return nivelDaSubarvore;
		}

		public void mostrarNivelDoNodo(int chave) {           // Item j)
		    int nivel = nivelDoNodo(raiz, chave, 1);
		    if (nivel != 0) {
		        System.out.println("O nível do nó " + chave + " é: " + nivel);
		    } else {
		        System.out.println("Nó não encontrado na árvore.");
		    }
		}
		
	    public int altura(Nodo nodo) {               // Item k)
	        if (nodo == null) {
	            return 0;
	        } else {
	            int alturaEsq = altura(nodo.esq);
	            int alturaDir = altura(nodo.dir);
	            return Math.max(alturaEsq, alturaDir) + 1;
	        }
	    }
	    
	    public void mostrarAltura() {                         // Item k)
	        int altura = altura(raiz); 
	        System.out.println("A altura da árvore é: " + altura);
	    }
	    
	    
	    public int tamanho(Nodo nodo) {                 // Item l)
	        if (nodo == null) {
	            return 0;
	        } else {
	            int tamEsq = tamanho(nodo.esq);
	            int tamDir = tamanho(nodo.dir);
	            return tamEsq + tamDir + 1;
	        }
	    }
	    

	    public void mostrarTamanho() {              // Item l)
	        int tamanho = tamanho(raiz);
	        System.out.println("O tamanho da árvore é: " + tamanho);
	    }
	
	    public void inserir2(int chave) {            // Item m)
	        Nodo novoNodo = new Nodo(chave);

	        if (raiz == null) {
	            raiz = novoNodo;
	            return;
	        }

	        Nodo atual = raiz;
	        Nodo pai = null;

	        while (true) {
	            pai = atual;

	            if (chave < atual.chave) {
	                atual = atual.esq;
	                if (atual == null) {
	                    pai.esq = novoNodo;
	                    return;
	                }
	            } else {
	                atual = atual.dir;
	                if (atual == null) {
	                    pai.dir = novoNodo;
	                    return;
	                }
	            }
	        }
	    }
}

package ArvoreBinaria;

public class TesteArvore {

	public static void main(String[] args) {
		
		ArvoreBinaria arvore = new ArvoreBinaria();
		
		ArvoreBinaria arvore2 = new ArvoreBinaria();
		
		arvore.inserir(30);
		arvore.inserir(25);
		arvore.inserir(20);
		arvore.inserir(22);
		arvore.inserir(40);
		arvore.inserir(27);
		arvore.inserir(45);
		
			
		System.out.println("Mostrar por nível");
		arvore.mostrarPorNivel();
	
	  
	/*	System.out.println();
		arvore.mostrarMaior();
				
		System.out.println();
		arvore.mostrarMenor();
		
		System.out.println();
		arvore.mostrarNosFolhas();
		
		System.out.println();
		arvore.mostrarNosAncestrais(45);
		
		System.out.println();
		arvore.mostrarNosDescendentes(25); 
		
		System.out.println();
		arvore.mostrarSubArvoreDireita(30);
		
		System.out.println();
		arvore.mostrarSubArvoreEsquerda(30);   
		
		System.out.println();
		arvore.transformarEmLista(); 
		arvore.imprimirLista();           */
		
		System.out.println("Pares: ");
		arvore.mostrarPares();             
		
		int chave = 27;
	    arvore.mostrarNivelDoNodo(chave);   
	    
	    arvore.mostrarAltura();
	    
	    arvore.mostrarTamanho();
	    
	    System.out.println();
	    
		arvore2.inserir2(30);
		arvore2.inserir2(25);
		arvore2.inserir2(20);
		arvore2.inserir2(22);
		arvore2.inserir2(40);
		arvore2.inserir2(27);
		arvore2.inserir2(45);
        System.out.println("Mostrar por nível");
		arvore2.mostrarPorNivel();
	}

}

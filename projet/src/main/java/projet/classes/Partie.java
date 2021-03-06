package projet.classes;

// Classe Partie

import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
	
    private Joueur j1;
    private Joueur j2;
    private Echiquier echiquierPartie;

    	
    
    // Constructeur
    public Partie(Joueur j1, Joueur j2, Echiquier echiquierPartie) {
    	this.j1 = j1;
    	this.j2 = j2;
    	this.echiquierPartie = echiquierPartie;	
    }

    // Getter du Joueur 1 de la Partie
    public Joueur getJ1() {
        return this.j1;
    }

    // Setter du Joueur 1 de la Partie
    public void setJ1(Joueur j1) {
        this.j1 = j1;
    }

    // Getter du Joueur 2 de la Partie
    public Joueur getJ2() {
        return this.j2;
    }

    // Setter du Joueur 1 de la Partie
    public void setJ2(Joueur j2) {
        this.j2 = j2;
    }

    // Getter de l'Echiquier de la Partie
    public Echiquier getEchiquierPartie() {
        return this.echiquierPartie;
    }

    // Setter de l'Echiquier de la Partie
    public void setEchequierPartie(Echiquier echiquierPartie) {
        this.echiquierPartie = echiquierPartie;
    }
    
    // Méthode pour lancer la Partie
   
    /* public void lancerPartie(){
    	boolean finDePartie=false;
    	while ( finDePartie==false)
		{
    		System.out.println("Choisir une case avec une pièce"); // Prendre ca en compte les execptions ici
    		//Essai sur une case avec un pion
    		deplacerPiece((echiquierPartie.getPlateau())[56], man.getJ());
    		man.incrementerTour();
    		if (man.getJ()==j1)
    			man.associerJoueur(j2);
    		else
    			man.associerJoueur(j1);
    		finDePartie=true;
		}
    }*/
	
    // Méthode pour déplacer une pièce
    public ArrayList<Integer> deplacementPossible(Piece p,Joueur blanc){	
    	// On regarde tous les déplacements possibles (concerne toutes les pièces)
    	ArrayList<Integer> deplacementPossible = new ArrayList<Integer>();
    	ArrayList<Integer> nbsoldir=p.getNbsoldir();
    	ArrayList<Integer> deplacement=p.getDeplacement();
    	int somme=0;
    	Case c=echiquierPartie.getPlateau()[p.isCase()];
    	for (int i=0;i<nbsoldir.size();i++)
    	{
    		boolean nopresent=true;
    		int pos=0;
    		if(nbsoldir.get(i)!=0){
    		do{
    			    			// Si le déplacement sort en-dehors de l'échiquier
    			if (c.getNumCase()+deplacement.get(pos+somme)<0 || c.getNumCase()+deplacement.get(pos+somme)>=64) {
    				nopresent=false;
    			}
    			
    			else {
    				if (echiquierPartie.getPlateau()[c.getNumCase()+deplacement.get(pos+somme)].isEtatCase()==true) {
    					for (int pos2=0;pos2<16;pos2++) {
    						// Si le déplacement est sur une pièce nous appartient
    						if (echiquierPartie.getPlateau()[c.getNumCase()+deplacement.get(pos+somme)].getNumCase()==blanc.getPiece()[pos2].isCase()) {
    							System.out.println(blanc.getPiece()[pos2].isCase());
    							nopresent=false;
    						}
    					}
    					
    					// Si le déplacement est sur une pièce adverse, on peut la capturer mais on ne peut pas aller plus loin
    					if (nopresent==true) {
    						nopresent=false;
    						deplacementPossible.add(deplacement.get(pos+somme));    				
    					}
    				}
    			}
    			
    			// Si le déplacement n'est sur aucune pièce, on peut aller plus loin
    			if(nopresent==true) {
    				deplacementPossible.add(deplacement.get(pos+somme));
    			}
    			
    			pos++;
    			
    		}
    		while(nbsoldir.get(i)>pos);}
    		somme=somme+nbsoldir.get(i);
    		
    	}
    	return deplacementPossible;
    }
    	// On affiche les différents déplacements possibles
    	//rmeplacer ca par les case en surbrillance et les listener de deplacements
    	
    public int ChoixDuDeplacement(ArrayList<Integer> deplacementPossible,Piece p){
    	for (int pos1=0;pos1<deplacementPossible.size();pos1++){
    		System.out.println("Choix "+pos1+" : ");
    		System.out.println(p.isCase()+deplacementPossible.get(pos1));
    	}
    	if (deplacementPossible.size()==0){return 0;}
    	Scanner sc=new Scanner(System.in);//mettre un listener ici pour le deplacement
		return sc.nextInt();
    }
    
    public void deplacementChoisi(int deplacementChoisi,Piece p){	
    	Case c=echiquierPartie.getPlateau()[p.isCase()];
		
		if (echiquierPartie.getPlateau()[deplacementChoisi].isEtatCase()==false) {
			echiquierPartie.getPlateau()[deplacementChoisi].setEtatCase(true);
			echiquierPartie.getPlateau()[deplacementChoisi].setP(c.getP());
			echiquierPartie.getPlateau()[c.getNumCase()].getP().setCase(deplacementChoisi);
			echiquierPartie.getPlateau()[c.getNumCase()].setP(null);
			echiquierPartie.getPlateau()[c.getNumCase()].setEtatCase(false);
		}
		else
		{
			echiquierPartie.getPlateau()[deplacementChoisi].getP().setEtat(false);
			echiquierPartie.getPlateau()[deplacementChoisi].getP().setCase(65);
			echiquierPartie.getPlateau()[deplacementChoisi].setEtatCase(true);
			echiquierPartie.getPlateau()[deplacementChoisi].setP(c.getP());
			echiquierPartie.getPlateau()[deplacementChoisi].getP().setCase(deplacementChoisi);
			echiquierPartie.getPlateau()[c.getNumCase()].setP(null);
			echiquierPartie.getPlateau()[c.getNumCase()].setEtatCase(false);
		}
		
		p.setCase(deplacementChoisi);
    			
    }

    // Méthode pour connaître si le roi est en échec
 	public boolean estEnEchec(Joueur noir, int direction) {
 		if ((noir.getPiece()[3].isCase()<8 && direction== -8) || (noir.getPiece()[3].isCase()>55 && direction == 8)){return false;}
 		if (noir.getPiece()[3].isCase()%8==0 && (direction==-9 || direction == 7 || direction == -1)){return false;}
 		if (noir.getPiece()[3].isCase()%8==7 && (direction==9 || direction == -7 || direction == 1)){return false;}
 		boolean estpasechec=false;
 		int pos=1;
 		
 		// On regarde sur la hauteur descendante
 		while(pos*8+noir.getPiece()[3].isCase()+direction<=63 && estpasechec==false) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos*8].isEtatCase()==true) {
 				for (int k=0;k<16;k++) {
 					// Si on a une pièce allié en face, pas d'échec
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos*8].getNumCase()==noir.getPiece()[k].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos*8].getP().getClass().getName()!="projet.classes.Roi") {
 						estpasechec=true;
 					}
 				}
 				// Si on a une reine ou une tour adverse en face, échec
 				//System.out.println(echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos*8].getP().getClass().getName());
 				if (estpasechec==false && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos*8].getP().getClass().getName()=="projet.classes.Reine"  ||  echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos*8].getP().getClass().getName()=="projet.classes.Tour" )) {
 					
 					return true;
 				}
 				estpasechec=true;
 			}
 			pos++;
 		}
 		//System.out.println(estpasechec);
 		pos=1;
 		estpasechec=false;
 		
 		// On regarde sur la hauteur montante
 		while(-pos*8+noir.getPiece()[3].isCase()+direction>=0 && estpasechec==false) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos*8].isEtatCase()==true) {
 				for (int k=0;k<16;k++) {
 					// Si on a une pièce allié en face, pas d'échec
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos*8].getNumCase()==noir.getPiece()[k].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos*8].getP().getClass().getName()!="projet.classes.Roi") {
 						estpasechec=true;
 					}
 				}
 				// Si on a une reine ou une tour adverse en face, échec
 				if (estpasechec==false && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos*8].getP().getClass().getName()=="projet.classes.Reine"  ||  echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos*8].getP().getClass().getName()=="projet.classes.Tour" )) {
 					return true;
 				}
 				estpasechec=true;
 			}
 			pos++;
 		}
 		//System.out.println(estpasechec);
 		pos=1;
 		estpasechec=false;
 		
 		// On regarde sur le côté droit
 		while((pos+noir.getPiece()[3].isCase()+direction)%8!=0 && estpasechec==false && noir.getPiece()[3].isCase()+direction>=0 && noir.getPiece()[3].isCase()+direction<64) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos].isEtatCase()==true) {
 				for (int k=0;k<16;k++){
 					// Si on a une pièce allié à droite, pas d'échec
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos].getNumCase()==noir.getPiece()[k].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos].getP().getClass().getName()!="projet.classes.Roi") {
 						estpasechec=true;
 					}
 				}
 				// Si on a une reine ou une tour adverse à droite, échec
 				if (estpasechec==false && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos].getP().getClass().getName()=="projet.classes.Reine"  ||  echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos].getP().getClass().getName()=="projet.classes.Tour" )) {
 					return true;
 				}
 				estpasechec=true;
 			}
 			pos++;
 		}
 		//System.out.println(estpasechec);
 		pos=1;
 		estpasechec=false;
 		
 		// On regarde sur le côté gauche
 		while((-pos+noir.getPiece()[3].isCase()+direction)%8!=7 && estpasechec==false  && -pos+noir.getPiece()[3].isCase()+direction>=0 && -pos+noir.getPiece()[3].isCase()+direction<64) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos].isEtatCase()==true) {
 				for (int k=0;k<16;k++) {
 					// Si on a une pièce allié à gauche, pas d'échec
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos].getNumCase()==noir.getPiece()[k].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos].getP().getClass().getName()!="projet.classes.Roi") {
 						estpasechec=true;
 					}
 				}
 				// Si on a une reine ou une tour adverse à gauche, échec
 				if (estpasechec==false && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos].getP().getClass().getName()=="projet.classes.Reine"  ||  echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos].getP().getClass().getName()=="projet.classes.Tour" )) {
 					return true;
 				}
 				estpasechec=true;
 			}
 			pos++;
 		}
 		//System.out.println(estpasechec);
 		pos=1;
 		estpasechec=false;
 		
 		// On regarde sur la diagonale descendante droite
 		while(9*pos+noir.getPiece()[3].isCase()+direction<=63 && (9*pos+noir.getPiece()[3].isCase()+direction)%8!=0 && estpasechec==false) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+9*pos].isEtatCase()==true) {
 				for (int k=0;k<16;k++) {
 					// Si on a une pièce allié dans la diagonale, pas d'échec
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+9*pos].getNumCase()==noir.getPiece()[k].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos*9].getP().getClass().getName()!="projet.classes.Roi") {
 						estpasechec=true;
 					}
 				}
 				// Si on a une reine ou un fou adverse dans la diagonale, échec
 				if (estpasechec==false && ( echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+9*pos].getP().getClass().getName()=="projet.classes.Reine"  ||  echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+9*pos].getP().getClass().getName()=="projet.classes.Fou" )) {
 					return true;
 				}
 				estpasechec=true;
 			}
 			pos++;
 		}
 		//System.out.println(estpasechec);
 		pos=1;
 		estpasechec=false;
 		
 		// On regarde sur la diagonale descendante gauche
 		while((7*pos+noir.getPiece()[3].isCase()+direction<=63 && (7*pos+noir.getPiece()[3].isCase()+direction)%8!=7) && estpasechec==false) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+7*pos].isEtatCase()==true) {
 				for (int k=0;k<16;k++) {
 					// Si on a une pièce allié dans la diagonale, pas d'échec
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+7*pos].getNumCase()==noir.getPiece()[k].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+pos*7].getP().getClass().getName()!="projet.classes.Roi") {
 						estpasechec=true;
 					}
 					
 				}
 				// Si on a une reine ou un fou adverse dans la diagonale, échec la il y as une erreur d'apres le debueguer
 				if (estpasechec==false && ( echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+7*pos].getP().getClass().getName()=="projet.classes.Reine"  ||  echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+7*pos].getP().getClass().getName()=="projet.classes.Fou" )) {
 					return true;
 				}
 				estpasechec=true;
 			}
 			pos++;
 		}
 		//System.out.println(estpasechec);
 		pos=1;
 		estpasechec=false;
 		
 		// On regarde sur la diagonale montante gauche
 		while((-7*pos+noir.getPiece()[3].isCase()+direction>=0 && (-7*pos+noir.getPiece()[3].isCase()+direction)%8!=7) && estpasechec==false && (-7*pos+noir.getPiece()[3].isCase()+direction)%8!=-1) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-7*pos].isEtatCase()==true) {
 				for (int k=0;k<16;k++) {
 					// Si on a une pièce allié dans la diagonale, pas d'échec
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-7*pos].getNumCase()==noir.getPiece()[k].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos*7].getP().getClass().getName()!="projet.classes.Roi") {
 						estpasechec=true;
 					}
 				}
 				// Si on a une reine ou un fou adverse dans la diagonale, échec
 				if (estpasechec==false && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-7*pos].getP().getClass().getName()=="projet.classes.Reine"  ||  echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-7*pos].getP().getClass().getName()=="projet.classes.Fou" )) {
 					return true;
 				}
 				estpasechec=true;
 			}
 			pos++;
 		}
 		//System.out.println(estpasechec);
 		pos=1;
 		estpasechec=false;
 		
 		// On regarde sur la diagonale montante droite
 		while((-9*pos+noir.getPiece()[3].isCase()+direction>=0 && (-9*pos+noir.getPiece()[3].isCase()+direction)%8!=0) && estpasechec==false) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-9*pos].isEtatCase()==true) {
 				for (int k=0;k<16;k++) {
 					// Si on a une pièce allié dans la diagonale, pas d'échec
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-9*pos].getNumCase()==noir.getPiece()[k].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-pos*9].getP().getClass().getName()!="projet.classes.Roi") {
 						estpasechec=true;
 					}
 				}
 				// Si on a une reine ou un fou adverse dans la diagonale, échec
 				if (estpasechec==false && ( echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-9*pos].getP().getClass().getName()=="projet.classes.Reine"  ||  echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-9*pos].getP().getClass().getName()=="projet.classes.Fou" )) {
 					return true;
 				}
 				estpasechec=true;
 			}
 			pos++;
 		}
 		//System.out.println(estpasechec);
 		pos=1;
 		estpasechec=false;
 		
 		// On regarde le cas des pions
 		if (noir==j1) {
 			// On regarde dans la diagonale montante gauche s'il y a un pion
 			if (noir.getPiece()[3].isCase()+direction-9>=0  && (noir.getPiece()[3].isCase()+direction-9)%8!=7) {
 				if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-9].isEtatCase()==true) {
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-9].getP().getClass().getName()=="projet.classes.Pion") {
 						for (int w=8;w<15;w++) {
 							// Si on a une pièce allié dans la diagonale à côté, pas d'échec
 							if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-9].getNumCase()==noir.getPiece()[w].isCase()) {
 								estpasechec=true;
 							}
 						}
 						// Sinon, c'est un pion adverse, donc échec
 						if (estpasechec==false) {
 							return true;
 						}
 					}
 				}
 			}
 			
 			// On regarde dans la diagonale montante droite s'il y a un pion
 			if (noir.getPiece()[3].isCase()+direction-7>=0  && (noir.getPiece()[3].isCase()+direction-7)%8!=0) {
 				if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-7].isEtatCase()==true) {
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-7].getP().getClass().getName()=="projet.classes.Pion") {
 						for (int w=8;w<15;w++) {
 							// Si on a une pièce allié dans la diagonale à côté, pas d'échec
 							if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-7].getNumCase()==noir.getPiece()[w].isCase() ) {
 								estpasechec=true;
 							}
 						}
 						// Sinon, c'est un pion adverse, donc échec
 						if (estpasechec==false) {
 							return true;
 						}
 					}
 				}
 			}
 		}
 		
 		else {
 			// On regarde dans la diagonale descendante droite s'il y a un pion
 			if (noir.getPiece()[3].isCase()+direction+9<=63 && (noir.getPiece()[3].isCase()+direction+9)%8!=0) {
 				if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+9].isEtatCase()==true) {
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+9].getP().getClass().getName()=="projet.classes.Pion") {
 						for (int w=8;w<15;w++) {
 							// Si on a une pièce allié dans la diagonale à côté, pas d'échec
 							if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+9].getNumCase()==noir.getPiece()[w].isCase()) {
 								estpasechec=true;
 							}
 						}
 						// Sinon, c'est un pion adverse, donc échec
 						if (estpasechec==false) {
 							return true;
 						}
 					}
 				}
 			}			
 			// On regarde dans la diagonale descendante gauche s'il y a un pion
 			if (noir.getPiece()[3].isCase()+direction+7<=63 && (noir.getPiece()[3].isCase()+direction+7)%8!=7) {
 				if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+7].isEtatCase()==true) {
 					if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+7].getP().getClass().getName()=="projet.classes.Pion") {
 						for (int w=8;w<15;w++) {
 							// Si on a une pièce allié dans la diagonale à côté, pas d'échec
 							if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+7].getNumCase()==noir.getPiece()[w].isCase()) {
 								estpasechec=true;
 							}
 						}
 						// Sinon, c'est un pion adverse, donc échec
 						if (estpasechec==false) {
 							return true;
 						}
 					}
 				}
 			}
 		}
 		//System.out.println(estpasechec);
 		pos=1;
 		estpasechec=false;
 		
 		// On regarde le cas des cavaliers
 		
 		if(noir.getPiece()[3].isCase()+direction+6<=63 && (noir.getPiece()[3].isCase()+direction-2)%8!=7) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+6].isEtatCase()==true && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+6].getP().getClass().getName()=="projet.classes.Cavalier" && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+6].getNumCase()!=noir.getPiece()[1].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+6].getNumCase()!=noir.getPiece()[6].isCase())) {
 				//System.out.println("cav1");
 				return true;
 			}
 		}
 		if(noir.getPiece()[3].isCase()+direction-6>=0 && (noir.getPiece()[3].isCase()+direction+2)%8!=0) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-6].isEtatCase()==true && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-6].getP().getClass().getName()=="projet.classes.Cavalier" && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-6].getNumCase()!=noir.getPiece()[1].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-6].getNumCase()!=noir.getPiece()[6].isCase())) {
 				//System.out.println("cav2");
 				return true;
 			}
 		}
 		if(noir.getPiece()[3].isCase()+direction+10<=63 && (noir.getPiece()[3].isCase()+direction+2)%8!=0) {
 			//System.out.println(echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+10].getP().getClass().getName());
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+10].isEtatCase()==true && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+10].getP().getClass().getName()=="projet.classes.Cavalier") && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+10].getNumCase()!=noir.getPiece()[1].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+10].getNumCase()!=noir.getPiece()[6].isCase())) {
 				//System.out.println("cav3");
 				return true;
 			}
 		}
 		if(noir.getPiece()[3].isCase()+direction-10>=0 && (noir.getPiece()[3].isCase()+direction-2)%8!=7) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-10].isEtatCase()==true && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-10].getP().getClass().getName()=="projet.classes.Cavalier" && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-10].getNumCase()!=noir.getPiece()[1].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-10].getP().isCase()!=noir.getPiece()[6].isCase())) {
 				//System.out.println("cav4");
 				return true;
 			}
 		}
 		if(noir.getPiece()[3].isCase()+direction+15<=63 && (noir.getPiece()[3].isCase()+direction-1)%8!=7) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+15].isEtatCase()==true && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+15].getP().getClass().getName()=="projet.classes.Cavalier" && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+15].getNumCase()!=noir.getPiece()[1].isCase() &&echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+15].getNumCase()!=noir.getPiece()[6].isCase())) {
 				//System.out.println("cav5");
 				return true;
 			}
 		}
 		if(noir.getPiece()[3].isCase()+direction-15>=0 && (noir.getPiece()[3].isCase()+direction+1)%8!=0) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-15].isEtatCase()==true && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-15].getP().getClass().getName()=="projet.classes.Cavalier" && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-15].getNumCase()!=noir.getPiece()[1].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-15].getNumCase()!=noir.getPiece()[6].isCase())) {
 				//System.out.println("cav6");
 				return true;
 			}
 		}
 		if(noir.getPiece()[3].isCase()+direction+17<=63 && (noir.getPiece()[3].isCase()+direction+1)%8!=0) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+17].isEtatCase()==true && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+17].getP().getClass().getName()=="projet.classes.Cavalier" && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+17].getNumCase()!=noir.getPiece()[1].isCase() && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction+17].getNumCase()!=noir.getPiece()[6].isCase())) {
 				//System.out.println("cav7");
 				return true;
 			}
 		}
 		if(noir.getPiece()[3].isCase()+direction-17>=0 && (noir.getPiece()[3].isCase()+direction-1)%8!=7) {
 			if (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-17].isEtatCase()==true && echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-17].getP().getClass().getName()=="projet.classes.Cavalier" && (echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-17].getNumCase()!=noir.getPiece()[1].isCase() || echiquierPartie.getPlateau()[noir.getPiece()[3].isCase()+direction-17].getNumCase()!=noir.getPiece()[6].isCase())) {
 				//System.out.println("cav8");
 				return true;
 			}
 		}
 		return false;
 	}
 	
 	
}
  
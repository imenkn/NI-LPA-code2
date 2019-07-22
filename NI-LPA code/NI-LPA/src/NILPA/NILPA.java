/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NILPA;

import edu.uci.ics.jung.graph.Graph;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


/**
 *
 * @author Imen
 */
public class NILPA {

   public static Graph g;
   static Vector nodeOverlap;
   static Vector table= new Vector();
   
    double temps1=java.lang.System.currentTimeMillis();
   

    public NILPA(){
       
        
        Vector vv=new Vector();
try{
    
        /* Resultat is the output  file which contains the partitions given by our code.
           The format is like this: write the labels of the nodes belonging to the same cluster in the same line.
        */
	//PrintWriter fichout= new PrintWriter(new FileWriter("C:\\Users\\imen\\Desktop\\Restest.dat")); 
	PrintWriter fichout= new PrintWriter(new FileWriter("Result\\Restest.dat")); 
        for(int tt=0;tt<table.size();tt++)
	{
		vv=(Vector)table.elementAt(tt);
		String comm="";
		for (int ttt=0;ttt<vv.size();ttt++)
		{
			int s=new Integer((Integer) vv.elementAt(ttt)).intValue();
			comm=comm+s+" ";
		}
		fichout.println(comm);
	}
	fichout.close();
}
catch(IOException exp){ System.out.println("un probleme de telechargement de fichier "+ exp);
}
nodeOverlap();
System.out.println("c'est fini");
double temps2= (double)java.lang.System.currentTimeMillis();
double tempsdexe= (double) temps2-temps1;
System.out.println("le temps d'execution: " +tempsdexe);

    };
    void nodeOverlap(){
	 nodeOverlap=new Vector();
	 for(int ii=0;ii<table.size();ii++)
	 {
	  	Vector Collect1=(Vector)table.elementAt(ii);
	 	int intermax=0;
	 	int Max=-1;
	 	Vector comm=new Vector(table.size());
	 	for(int j=ii+1;j<table.size();j++)
	 	{
	 	Vector Collect2=(Vector)table.elementAt(j);
	 		for(int jj=0;jj<Collect1.size();jj++)
	 		{
	 			for(int jjj=0;jjj<Collect2.size();jjj++)
	 			{
	 				if((Collect1.elementAt(jj).equals(Collect2.elementAt(jjj)))&&!nodeOverlap.contains(Collect1.elementAt(jj)))
	 						{nodeOverlap.add(Collect1.elementAt(jj));
	 					     	}
	 			}
	 		}
	 	}
	 }
 try{
     
       // Nodeoverlap is the output file which contains overlapping nodes
       
			//PrintWriter fichout= new PrintWriter(new FileWriter("C:\\Users\\imen\\Desktop\\Nodeoverlap.txt"));
                        PrintWriter fichout= new PrintWriter(new FileWriter("Result\\Nodeoverlap.dat"));
				for (int ttt=0;ttt<nodeOverlap.size();ttt++)
				{
					int s=new Integer((Integer) nodeOverlap.elementAt(ttt)).intValue();
					fichout.println(s);
				}
			fichout.close();
		}
		catch(IOException exp){System.out.println("Un probleme dans le telechargement de fichier  "+ exp);
		}
	 }

    
       // Calculate the importances of nodes
          private static float MNode_importance( Object v) {
		float coef;
		float comp=0;
		float deg = (float) g.degree(v);
		Collection F = g.getNeighbors(v);
		     for(Object d : F){
                        for(Object f: F) {
                         
                            if(f!=d ){
                            comp=comp+g.findEdgeSet(d, f).size();
                            }
                        }
		     }
                     
		if(deg>1){
                    coef=(2*(comp/2)/(deg*(deg-1)));
		          }
		else {coef=0;}
		
		return coef;
          }   


	//   return the node with the maximum value of importance
	private static Object Max(Vector  V){
	
            Object max=V.elementAt(0);
            float mnimpMax=MNode_importance(max);
            Object element;
            int size=V.size() ;
            
            for (int i = 1; i < size ; i++){
                element = V.elementAt(i);
                if(mnimpMax< MNode_importance(element)){
                    max=element;
                    mnimpMax=MNode_importance(max);	}
            }
	return max;
	}
       
        //   return the node with the minimum value of importance
        private static Object inverseMax(Vector  V){
            
            Object min=V.elementAt(0);
            float minimpMax=MNode_importance(min);
            Object element;
            int size=V.size() ;
            
            for (int i = 1; i < size ; i++){
                element = V.elementAt(i);
                if(minimpMax> MNode_importance(element)){
                    min=element;
                    minimpMax=MNode_importance(min);	}
	}
	return min;
	}
        
        
         //   return the node with the minimum value of degree
            private static Object minDeg(Vector V){
            
            Object min =V.elementAt(0);
            int obj = g.degree(min);
            Object element = null ;
            
            	for (int i = 1; i < V.size() ; i++){
                  
                        element = V.elementAt(i);
        
                        if(obj > g.degree(element)){
                                min=element;
                        }
                }
            
            
            return min;
            }
         
             //   return the node with the maximum value of importance
            private static Object maxDeg(Vector V){
            
            Object max =V.elementAt(0);
            int obj = g.degree(max);
            Object element = null ;
            
            	for (int i = 1; i < V.size() ; i++){
                        element = V.elementAt(i);
        
                        if(obj < g.degree(element)){
                                max=element;
                        }
                }
            
            
            return max;
            }
            
            
        // this method calculates for a node its importance value.
        private static HashMap init (Object b)throws ArrayIndexOutOfBoundsException{
      
                 Vector v= new Vector();
                 HashMap<Object,Float> initL = new HashMap<>();    
                 
                 int n=g.degree(b);  
                 v.addAll(g.getNeighbors(b));
                       
                 for (int i1=0 ;i1 < v.size();i1++){
                           Object neghb= v.elementAt(i1); 
                    
                           float c= MNode_importance(neghb)* g.degree(neghb);

                           initL.put(neghb, c);
              
                           if (c==0f){
                                    initL.remove(neghb);
                           }
                 } 
            return (initL);    
        }
        
        
        
        /* this method initializes all the nodes of graph with their importance values 
        ( call to method called unit for each node)*/
        private static HashMap Principalinit (){
            
            Vector v= new Vector();
            HashMap<Object, Float> label=new HashMap();
            HashMap<Object, HashMap> labelf=new HashMap();
            Object b=null;
            
            v.addAll(g.getVertices());
            
            for(int i=0;i<v.size();i++){
                    b=v.elementAt(i);
                    label = init(b);
                    labelf.put(b, label);
            }
        return labelf;
        }
        
       
        
         
    
        
        //filter labels list of a node compared to a threshold
        
         private static HashMap filtrage(HashMap map , Float seuil){
            
            Vector vc=new Vector();
            Vector vv=new Vector();
            HashMap<Object,Float> labelc=new HashMap();
            Object cle ,o = null;
            float valeur=0.0f;
          
        
            for (Iterator it1 = map.entrySet().iterator(); it1.hasNext();) {
           Map.Entry<Object, Float> entry1 = (Map.Entry<Object, Float>) it1.next();
           cle = entry1.getKey();
           valeur = entry1.getValue();
          vc.add(cle);
           vv.add(valeur);
              }
           
            
           for(int i=0;i<vv.size();i++){
             
                    if ((float)vv.elementAt(i)>=seuil){
        
                        labelc.put(vc.elementAt(i), (Float) vv.elementAt(i));
                    }
           }
              
           return(labelc);
        
}
         
               
         // filter labels list of all the graph nodes 
         
          private static HashMap Principalfiltrage (HashMap map, Float seuil){
              
              HashMap<Object,HashMap> labelf=new HashMap();
              Vector vc=new Vector();
              Vector vv=new Vector();
              Vector vvf=new Vector();
              HashMap<Object, Float> valeur = null;
              Object cle = null;
              HashMap h = null;
          
          
              for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<Object, HashMap> entry = (Map.Entry<Object, HashMap>) it.next();
                        cle = entry.getKey();
                        valeur = (HashMap) entry.getValue();
                        vc.add(cle);
                        vv.add(valeur);
              }
       
              for(int i=0;i<vv.size();i++){
                        h = filtrage((HashMap) vv.elementAt(i),seuil);
                        vvf.add(h);
              }
           
              for(int i=0;i<vc.size();i++){
                        labelf.put(vc.elementAt(i), (HashMap) vvf.elementAt(i));
              }
             
          return (labelf);
          }
          
             
          
          // eliminate labels with low coefficient value after propagation step
       private static HashMap filtragemax(HashMap map ) throws ConcurrentModificationException{
        
           Object index = 0 ;
           Vector v= new Vector();
           Vector vv= new Vector();
           HashMap<Object,Float> labelf=new HashMap();
            
           
           for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
                    Map.Entry<Object, Float> entry = (Map.Entry<Object, Float>) it.next();
                    Object cle = entry.getKey();
                    float valeur = entry.getValue();
                    v.add(cle);
                    vv.add(valeur);
           }
                    
       int j=2;  
       float fin = 0;
       while ( j!=0){
       float max=0f;
       
           for(int i=0;i<vv.size();i++){
                    float x= (float) vv.elementAt(i);
         
                    if (x > max){
                            max =x;}
           }
                                
        vv.remove(max); 
        j--; 
        fin=max;
       }
       
       for(int ii=0;ii<v.size();ii++){
                    if ((float)map.get(v.elementAt(ii)) <fin){
                            map.remove(v.elementAt(ii));}
                    }
 
                    return(map);      
      }
              
             
       // the propagation phase at a node
    private static HashMap prop1(Object i, HashMap map, HashMap map1){
                    
            HashMap<Object,Float> h= new  HashMap<Object,Float>();
            Vector vv=new Vector();
            h=(HashMap<Object, Float>) map.get(i);
          
            for (Iterator it1 = h.entrySet().iterator(); it1.hasNext();) {
                        Map.Entry<Object, Float> entry1 = (Map.Entry<Object, Float>) it1.next();
                        Object cle = entry1.getKey();
                        vv.add(cle);}
              
           int ii=0;
           
           while (ii<vv.size()){
                    Float w = null;
 
                    if(map1.containsKey(vv.elementAt(ii))){
                            w=(Float)map1.get(vv.elementAt(ii))+h.get(vv.elementAt(ii));
                            map1.replace(vv.elementAt(ii), w); }
                    else{
                            w=h.get(vv.elementAt(ii));
                            map1.put(vv.elementAt(ii), w);}
            
            ii++;
            }
                  
       return map1;
       }
              
        // the propagation step      
        private static HashMap propagatef (Object n , HashMap map){
                      
            Vector v=new Vector();
            HashMap<Object,Float> map1= new  HashMap<Object,Float>();
            HashMap<Object,Float> map2= new  HashMap<Object,Float>();
                      
                        
            v.addAll(g.getNeighbors(n));
            map1=(HashMap<Object, Float>) map.get(n);
            
            for(int i=0;i<v.size();i++){
                        map2=prop1(v.elementAt(i),map,map1);
            }
                     
            HashMap map4=filtragemax(map2);
          
            HashMap  map5 =normalize1(map4);

            map.replace(n, map1, map5);
       
        return map;
        }
         
         // normalization of label lists        
       private static HashMap normalize1(HashMap<Object,Float> map){
           
            Vector v=new Vector();
            float sum=0.0f;
                
            for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
                       Map.Entry<Object, Float> entry1 = (Map.Entry<Object, Float>) it.next();
                       Object cle = entry1.getKey();
                       float valeur = entry1.getValue();
                       sum+=valeur;
                       v.add(cle);
            }
              
            for(int i=0;i<v.size();i++){
                       float  valeur1=map.get(v.elementAt(i))/sum;
                       map.replace(v.elementAt(i),valeur1);
            }
            
       return map;
       }
          
       
          
           // step of labels extraction
          
          private static Vector readComm1(HashMap map){
              
            Object cle = null,cle1 = null;
            HashMap valeur = null;
            Float valeur1 = null;
            Vector v=new Vector();
         
            for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<Object, HashMap>  entry = (Map.Entry<Object, HashMap>) it.next();
                        cle = entry.getKey();
                        valeur = (HashMap) entry.getValue();
        
                  
              
                for (Iterator it1 = valeur.entrySet().iterator(); it1.hasNext();) {
                        Map.Entry<Object, Float> entry1 = (Map.Entry<Object, Float>) it1.next();
                        cle1 = entry1.getKey();
                        valeur1 = entry1.getValue();
                        
                        if(!v.contains(cle1)){
                                v.add(cle1);}
                }}
           
                 System.out.println("les labels : "+v);
                 System.out.println("**********");
           
       return v;
       }
          
        // extraction of the nodes which contain label w
    private static Vector readComm2(HashMap map, Object w){
                
            Vector vf=new Vector();
             
            for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
                    Map.Entry<Object, HashMap>  entry = (Map.Entry<Object, HashMap>) it.next();
                    Object cle = entry.getKey();
                    HashMap valeur = (HashMap) entry.getValue();
        
                for (Iterator it1 = valeur.entrySet().iterator(); it1.hasNext();) {
                      Map.Entry<Object, Float> entry1 = (Map.Entry<Object, Float>) it1.next();
                      Object cle1 = entry1.getKey();
                      Float valeur1 = entry1.getValue();
     
              
                if (w.equals(cle1)){
                      Object a=entry.getKey();
                      vf.add(a);}
                }  
            } 
       return vf;
    }
             // step of communities extraction (call to readComm1 and readComm2 methods)  
    private static Vector principalreadComm(HashMap map){
        
            Vector v=new Vector();
            Vector vf=new Vector();
            Vector vv=new Vector();
                
            v=readComm1(map);
            
            for(int i=0;i<v.size();i++){
                    Object w = v.elementAt(i);
                    vf=readComm2(map,w);
                    vv.addElement(vf); 
            }
       return vv;
    } 
         
            
                   
        
         /*****************************************************************/
   /*************************** Main class ***************/
  /*****************************************************************/
    public static void main(String[] args) {
        
        System.out.println("Debut");
former f= new former();
int NBitérations=35;
float seuil=0.4f;

/************************* input: path of graph file **************************/
g= f.lecutreFichier3("test\\network1.dat");


System.out.println("le nombre des noeuds  " +g.getVertexCount() );
System.out.println("le nombre des aretes  " +g.getEdgeCount() );

HashMap<Object,HashMap> init=new HashMap();
HashMap<Object,HashMap> initf=new HashMap();
HashMap<Object,HashMap> m=new HashMap();
Vector tab =new Vector();
Vector vec =new Vector();

Vector VV =new Vector();
VV.addAll(g.getVertices());

vec.addAll(VV);

        //initialization step
        init = Principalinit ();
        System.out.println("init" +init); 
        tab.add(init); 

        // propagation step
        for(int i=1;i<=NBitérations;i++){
    
        //System.out.println( "*********************  iteration num°" +i );
        while(vec.size()!=0 ) { 
   
        //Object choixN=Max(vec);  
        //Object choixN=inverseMax(vec);
        //Object choixN=maxDeg(vec);
        Object choixN=minDeg(vec);
        
        m=propagatef(choixN, (HashMap) tab.elementAt(i-1));
        vec.remove(choixN);
        }

 
        vec.addAll(VV);
        tab.addElement(m);
        }  

        //filtering step
        initf=Principalfiltrage(m,seuil); 

        System.out.println("initf   " +initf);
        
        //extraction communities step
        Vector v =new Vector();

        v=readComm1(initf);
        table=principalreadComm(initf);
        
        for(int ii=0;ii<table.size();ii++){
                    Vector vvv=(Vector)table.elementAt(ii);
                    System.out.println("La comunaute C" +ii+ "  de label  "+v.elementAt(ii)+ " est : " + vvv);
        }
    
    new NILPA();
    }
    }
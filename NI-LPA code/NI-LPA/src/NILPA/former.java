/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NILPA;

/**
 *
 * @author Imen
 */

import java.io.*;
import java.util.*;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import java.util.Map;
import java.util.Vector;
import java.awt.Paint;
import org.apache.commons.collections15.*;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Dimension;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.*;


public class former {
public int id=0;
public int nbv=0;
public int nbe=0;

   public former()
    {
    }

public Graph lecutreFichier1(String s){
String ligne;
int n1 = 0, n2;
Graph <Integer, String> g = null ;
try
{
g = new UndirectedSparseGraph<Integer, String>();
InputStream ips = new FileInputStream(s);
InputStreamReader ipsr = new InputStreamReader(ips);
BufferedReader br = new BufferedReader(ipsr);
while ((ligne = br.readLine()) != null)
{
StringTokenizer val = new StringTokenizer(ligne," ");
String mot=String.valueOf(val.nextToken());
if(mot.equals((String)"id")){
nbv=Integer.parseInt(String.valueOf(val.nextToken()));
g.addVertex(nbv);
}
if(mot.equals((String)"source")){
mot=String.valueOf(val.nextToken());
n1=Integer.parseInt(String.valueOf(mot));
}
if(mot.equals((String)"target")){
mot=String.valueOf(val.nextToken());
n2=Integer.parseInt(String.valueOf(mot));
nbe++;
g.addEdge(String.valueOf(nbe), n1, n2);
}
}
br.close();
   }
catch (Exception e) {
System.out.println(e.toString());
}
return g;
}

public Graph lecutreFichier2(String s){
String ligne;
int n1 = 0, n2;
Graph <Integer, String> g = null ;
try
{
g = new UndirectedSparseGraph<Integer, String>();
InputStream ips = new FileInputStream(s);
InputStreamReader ipsr = new InputStreamReader(ips);
BufferedReader br = new BufferedReader(ipsr);
nbv=1;

while ((ligne = br.readLine()) != null)
{
StringTokenizer val = new StringTokenizer(ligne," ");
String mot=String.valueOf(val.nextToken());

if(mot.equals((String)"node")){
g.addVertex(nbv);
nbv++;
}
if(mot.equals((String)"edge")){
mot=String.valueOf(val.nextToken());
n1=Integer.parseInt(String.valueOf(mot));

mot=String.valueOf(val.nextToken());
n2=Integer.parseInt(String.valueOf(mot));
nbe++;
g.addEdge(String.valueOf(nbe), n1, n2);
}
}
br.close();
   }
catch (Exception e) {
System.out.println(e.toString());
}
return g;
}

public Graph lecutreFichier3(String s){
String ligne;
int n1 = 0, n2=0;
Graph <Integer, String> g = null ;
try
{
g = new UndirectedSparseGraph<Integer, String>();
InputStream ips = new FileInputStream(s); 
InputStreamReader ipsr = new InputStreamReader(ips);
BufferedReader br = new BufferedReader(ipsr);
nbv=0;
nbe=0;
while ((ligne = br.readLine()) != null)
{
StringTokenizer val = new StringTokenizer(ligne,"\t");
String mot=String.valueOf(val.nextToken());
n1=Integer.parseInt(String.valueOf(mot));
mot=String.valueOf(val.nextToken());
n2=Integer.parseInt(String.valueOf(mot));
if(!g.containsVertex(n1))
g.addVertex(n1);
if(!g.containsVertex(n2))
g.addVertex(n2);
nbe++;
g.addEdge(String.valueOf(nbe), n1, n2);
}
br.close();
}
catch (Exception e) {
System.out.println(e.toString());
}
return g;
}

public Graph lecutreFichier4(String s){
String ligne;
int n1= 0, n2;
Graph <Integer, String> g = null ;
try
{
g = new UndirectedSparseGraph<Integer, String>();
InputStream ips = new FileInputStream(s);
InputStreamReader ipsr = new InputStreamReader(ips);
BufferedReader br = new BufferedReader(ipsr);
nbv=1;
while ((ligne = br.readLine()) != null)
{
StringTokenizer val = new StringTokenizer(ligne,"   ");
String mot=String.valueOf(val.nextToken());
n1=Integer.parseInt(String.valueOf(mot));
mot=String.valueOf(val.nextToken());
n2=Integer.parseInt(String.valueOf(mot));
if(n1!=n2){
nbe++;
g.addEdge(String.valueOf(nbe), n1, n2);
}
}
br.close();
}
catch (Exception e) {
System.out.println(e.toString());
}
return g;
}
}





import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class prog_ALD extends PApplet {

Table covidData = new Table();
PImage png;
Table data;

public void setup() {
  
  background(200);
  png = loadImage("baggrund.png");
  textAlign(CENTER, CENTER);
  textSize( 30 );
  fill( 0 );
  tf = new Textfield(this, width/2-125,height/2+125,250,50, "              Choose month");
  
  
  
  data = loadTable("owid-covid-data.csv"); 
  covidData.addRow(data.getRow(0));
  for (int i = 0; i < data.getRowCount(); ++i) {

    if (data.getString(i, 2).equalsIgnoreCase("Australia")) {
      covidData.addRow(data.getRow(i));
    }

    
  }
  g = new graf(covidData,width/2-125,170,250,100,7);
  
}

public void draw(){
       if(g.g == false){
        g.colorh -=2;
        
        if(g.colorh ==2){
        g.g = true;
        }
  }
  
  clear();
  background(255);
    imageMode(CENTER);
  image(png, 245, 250,523, 505);
  text("Total Deaths From:\n" + g.table.getString(g.grafstart,3),width/2,145);
  tf.drawTf();
  g.drawgraf();
  println(tf.input);
}

public void mouseClicked(){
  tf.clickedCheck(mouseX,mouseY);
}

public void keyTyped(){
  tf.keyinput(key);
  
  g.setGraf(tf.input);
 
}
Textfield tf;
graf g;
 class Textfield {
  //variabler
    float positionX, positionY, sizeX, sizeY;
    float mouseX, mouseY;
    String textFieldNavn;
    String input = ""; //dit navn består af en masse bogstaver <3

    boolean klikket = false;
    boolean acceptLetters = true;

    PApplet p;
    

 Textfield(PApplet papp, int posX, int posY, int sizeX, int sizeY, String text ){
        p = papp;
        positionX = posX;
        positionY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.textFieldNavn = text;
    }
    
    public void clickedCheck(float pmouseX, float pmouseY) {
     
        if (pmouseX > positionX &&
                pmouseX < positionX + sizeX &&
                pmouseY > positionY &&
                pmouseY < positionY + sizeY) {
            klikket = !klikket;
        } else {
            klikket = false;
        }


    }

    public void drawTf() {
        stroke(1, 46, 74, 100);
        if(klikket){
            p.fill(227, 225, 252, 250);
        } else {
            p.fill(200);
        }


        p.rect(positionX, positionY, sizeX, sizeY);

        p.fill(0);
        p.textSize(16);  
       
        
        p.text(input,positionX+(textWidth(input))/2, positionY + (sizeY/2));
       p.text(textFieldNavn,positionX + (textWidth(textFieldNavn))/2  , positionY-32);
    }

    public void setAcceptLetter(boolean accept) {
        acceptLetters = accept;
    }

    public void keyinput(char key) {

        if(klikket ){
            if(key == BACKSPACE && input.length() > 0){

                input =input.substring(0,input.length()-1);
            } else {
                if ((!acceptLetters && key >= '0' && key <= '9') || acceptLetters)
                input = input + key;
            }

        }
    
    }





}
class graf{
Table data;
    PApplet p; int posX; int posY; int xSize; int ySize;
    int maxY = Integer.MIN_VALUE;
    float xInt;
    float yInt;
    int colon;
    int graphStart = 0;
    Table table = new Table(); 
    int maxList = 0;
    int grafstart =1;
    boolean g = true;
    int colorh = 2;
    String[] dateS = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    
  graf(Table data,int posX, int posY, int xSize, int ySize, int colon){
    this.data = data;

        this.colon = colon;
        this.xSize = xSize;
        this.ySize = ySize;
        this.posX = posX;
        this.posY = posY;
        table = data;
             for (int j = 0; j < covidData.getRowCount(); ++j) {
                if (covidData.getInt(j,colon) > maxList) {
                    maxList = covidData.getInt(j,colon);
                    maxY = Math.max(maxList, maxY);
                }
            }
  }
  
  public void drawgraf(){

     int x2;
     
        for (int i=grafstart; i<covidData.getRowCount(); ++i) {

            
       
            
            xInt = covidData.getRowCount()/xSize;
            yInt = ySize*covidData.getInt(i -1,colon)/maxY;
            x2 = (int) (posX + xInt*(i-grafstart+1) -graphStart)+10;
            fill(colorh, colorh,colorh, colorh );
         
            //denne linje driller nogle gange
            rect(x2,height-(posY + posY/4),xInt,-yInt);
            fill(0 );
        }
       
    
    }
    
      public void setGraf(String date){
     grafstart =1;
    for(int i = 1; i <table.getRowCount();i++){
     
      
     String [] words = table.getString(i,3).split("-", 3); 
     println(words[0]);
     int j = 0;
      j=Integer.parseInt(words[1]);
     
      if(dateS[j-1].equalsIgnoreCase(date)){
          grafstart =i;
      }
    }
    colorh = 255;
    g = false;
   
  }
  

  }
  
  public void settings() {  size(500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "prog_ALD" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

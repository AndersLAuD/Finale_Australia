Table covidData = new Table();
PImage png;
Table data;

void setup() {
  size(500,500);
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

void draw(){
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

void mouseClicked(){
  tf.clickedCheck(mouseX,mouseY);
}

void keyTyped(){
  tf.keyinput(key);
  
  g.setGraf(tf.input);
 
}

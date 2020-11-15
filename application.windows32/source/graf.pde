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
  
  void drawgraf(){

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
    
      void setGraf(String date){
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
  

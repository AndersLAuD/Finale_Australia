Textfield tf;
graf g;
 class Textfield {
  //variabler
    float positionX, positionY, sizeX, sizeY;
    float mouseX, mouseY;
    String textFieldNavn;
    String input = ""; 

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
    
    void clickedCheck(float pmouseX, float pmouseY) {
     
        if (pmouseX > positionX &&
                pmouseX < positionX + sizeX &&
                pmouseY > positionY &&
                pmouseY < positionY + sizeY) {
            klikket = !klikket;
        } else {
            klikket = false;
        }


    }

    void drawTf() {
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

    void setAcceptLetter(boolean accept) {
        acceptLetters = accept;
    }

    void keyinput(char key) {

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

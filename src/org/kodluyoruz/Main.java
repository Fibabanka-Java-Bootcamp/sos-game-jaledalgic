package org.kodluyoruz;

import java.util.Random;
import java.util.Scanner;


public class Main {
    static int n=0;
    static String move_pc;
    static String move_player;
    static int pcScore =0;
    static int playerScore =0;

    public static void main(String[] args) {
        int cont=0;

        while(cont>=0) {
            System.out.println("Lütfen oyun için bir boyut giriniz(3-7 aralığında olmalı) : ");
            Scanner input = new Scanner(System.in);
            n = input.nextInt();
            String blank_mat[][]=new String[n][n];
            if(n<=7 && n>=3){
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        blank_mat[i][j]="-";
                    }
                }printMatris(blank_mat);
                cont=-1;
                playerChoose(blank_mat);
            }else{
                cont++;
            }
        }

    }
    public static void playerChoose(String blank_mat[][]){
        Random random=new Random();
        int player=random.nextInt(2);
        int rand_move=random.nextInt(2);

        if(player==1) {
            if(rand_move==1){
                move_pc="S";
                move_player="O";
            }else if(rand_move==0){
                move_pc="O";
                move_player="S";
            }
            startPlayer(blank_mat);
        }else if(player==0) {
            if(rand_move==1){
                move_pc="S";
                move_player="O";
            }else if(rand_move==0){
                move_pc="O";
                move_player="S";
            }
            startPc(blank_mat);
        }


    }
    public static void startPlayer(String matris[][]){
        Scanner key=new Scanner(System.in);
        int pos_x=key.nextInt();
        int pos_y=key.nextInt();
        if(pos_x<n && pos_x>=0 && pos_y<n && pos_y>=0) {
            moveControl(pos_x, pos_y, matris, move_player);
        }else{
            System.out.println("Lütfen oyun dışına çıkmayın:");
            startPlayer(matris);
        }
    }
    public static void startPc(String matris[][]){
        Random r=new Random();
        int pc_x=r.nextInt(n);
        int pc_y=r.nextInt(n);
        moveControl(pc_x,pc_y,matris,move_pc);

    }
    public static void moveControl(int x, int y, String matris[][],String move){

        if (matris[x][y].equals("-")) {
            matris[x][y] = move;
            printMatris(matris);
            if (move.equals(move_player) ) {
                int point_player=0;
                point_player=scoreCalculate(x,y,matris,move);
                playerScore +=point_player;
                System.out.println("Oyuncu puanı:" + playerScore);
                System.out.println("Bilgisayar puanı:" + pcScore);
                if(point_player>=1 && isEmpty(matris)) {
                    startPlayer(matris);
                }
                else if(isEmpty(matris)){
                    startPc(matris);}
                else if(!(isEmpty(matris))){
                    win();
                }
            }else if (move.equals(move_pc)) {
                int point_pc=0;
                point_pc=scoreCalculate(x,y,matris,move);
                pcScore +=point_pc;
                System.out.println("Oyuncu puanı:" + playerScore);
                System.out.println("Bilgisayar puanı:" + pcScore);
                if( isEmpty(matris)) {
                    if(point_pc>=1) {
                        startPc(matris);
                    }
                    else {
                        startPlayer(matris);
                    }
                }else{
                    win();
                }

            }
        } else if (move.equals(move_player)) {

            System.out.println("Lütfen tekrar konum girin:");
            startPlayer(matris);
        } else if (move.equals(move_pc)) {
            //System.out.println("Geçersiz konum girildi. Tekrar konum bekleniyor:");

            startPc(matris);
        }



    }
    public static boolean isEmpty(String m[][]){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(m[i][j]=="-"){
                    return true;
                }
            }
        }
        System.out.println("GAME OVER");

        return false;
    }
    public static void printMatris(String matCheck[][]){

        int size = matCheck.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matCheck[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }
    public static int scoreCalculate(int x, int y, String mat[][], String move){
        int score=0;
        System.out.println(move_player);
        System.out.println(move_pc);

        if(move.equals("S")){
            if(y<=n-3 && x<=n-1){
                if((((mat[x][y+1])+(mat[x][y+2])).equals("OS"))){  //sağa yatay
                    score+=1;
                }}
            if(x<=n-3 && y<=n-1) {
                if((((mat[x+1][y])+(mat[x+2][y])).equals("OS"))){ //üstten dikey
                    score+=1;
                }}
            if(y>=2 && x<=n-1 ){
                if((((mat[x][y-1])+(mat[x][y-2])).equals("OS"))){ //sola yatay
                    score+=1;
                }}
            if(x>=2 && y<=n-1 ){
                if((((mat[x-1][y])+(mat[x-2][y])).equals("OS"))){ //alttan dikey
                    score+=1;
                }}
            if(x<=n-3 && y<=n-3){
                if((((mat[x+1][y+1])+(mat[x+2][y+2])).equals("OS"))) { //sağ alta çapraz
                    score+=1;
                }}
            if(x<=n-3 && y>=2){
                if((((mat[x+1][y-1])+(mat[x+2][y-2])).equals("OS"))){  //sol alta çapraz
                    score+=1;
                }}
            if(x>=2 && y>=2){
                if((((mat[x-1][y-1])+(mat[x-2][y-2])).equals("OS"))){  //sol üste çapraz
                    score+=1;
                }}
            if(x>=2 && y<=n-3){
                if((((mat[x-1][y+1])+(mat[x-2][y+2])).equals("OS"))){  //sağ üste çapraz
                    score+=1;
                }}

        }
        else if(move.equals("O")){
            if(x<=n-1 && y>=1 && y<=n-2 && ((mat[x][y-1]).equals("S")) && ((mat[x][y+1]).equals("S"))){
                score+=1;
            }
            if(x<n-1 && x!=0 && y<=n-1 && ((mat[x-1][y]).equals("S")) && ((mat[x+1][y]).equals("S"))){
                score+=1;
            }
            if(x!=0 && x<=n-2 && y!=0 && y<=n-2){
                if(((mat[x-1][y-1]).equals("S")) && ((mat[x+1][y+1]).equals("S"))){
                    score+=1;
                }
                if(((mat[x-1][y+1]).equals("S")) && ((mat[x+1][y-1]).equals("S"))){
                    score+=1;
                }
            }
        }
        return score;

    }
    public static void win(){
        if(playerScore > pcScore){
            System.out.println("Oyuncu kazandı.");
        }else if(pcScore > playerScore){
            System.out.println("Bilgisayar kazandı.");
        }else if(playerScore == pcScore){
            System.out.println("Berabere bitti.");
        }
    }
}


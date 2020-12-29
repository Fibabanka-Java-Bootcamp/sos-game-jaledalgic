package org.kodluyoruz;

import java.util.Random;
import java.util.Scanner;


public class Main {
    static int n;
    static int score=0;
    static String move_pc;
    static String move_player;
    static int score_pc=0;
    static int score_player=0;
    static int count=0;
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
                move_pc="0";
                move_player="S";
            }
            startPlayer(blank_mat,move_player);
        }else if(player==0) {
            if(rand_move==1){
                move_pc="S";
                move_player="O";
            }else if(rand_move==0){
                move_pc="0";
                move_player="S";
            }
            startPc(blank_mat);
        }


    }
    public static void startPlayer(String matris[][],String move){
        Scanner key=new Scanner(System.in);
        int x=key.nextInt();
        int y=key.nextInt();
        if(x<n && x>=0 && y<n && y>=0) {
            moveControl(x, y, matris, move);
        }else{
            System.out.println("Lütfen oyun dışına çıkmayın:");
            startPlayer(matris,move_player);
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
                score_player+=scoreCalculate(x,y,matris,move);
                System.out.println("Oyuncu puanı:" + score_player);
                System.out.println("Bilgisayar puanı:" + score_pc);
                if(isEmpty(matris)){
                    startPc(matris);}
            } else if (move.equals(move_pc)) {
                score_pc+=scoreCalculate(x,y,matris,move);
                System.out.println("Oyuncu puanı:" + score_player);
                System.out.println("Bilgisayar puanı:" + score_pc);
                if (isEmpty(matris)){
                    startPlayer(matris,move_player);}
            }
        } else if (move.equals(move_player)) {

            System.out.println("Lütfen tekrar konum girin:");
            startPlayer(matris,move_player);
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
        win();
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
        if(move.equals("S")){
            if(y<=n-3 && x<=n-1 && (mat[x][y+1]+mat[x][y+2]).equals("OS")){  //sağa yatay
                System.out.println(mat[x][y+1]+mat[x][y+2]);
                score+=1;
            }if(x<=n-3 && y<=n-1 && (mat[x+1][y]+mat[x+2][y]).equals("OS")){ //üstten dikey
                System.out.println(mat[x+1][y]+mat[x+2][y]);
                score+=1;
            }if(y>=2 && x<=n-1 && (mat[x][y-1]+mat[x][y-2]).equals("OS")){ //sola yatay
                System.out.println(mat[x][y-1]+mat[x][y-2]);
                score+=1;
            }if(x>=2 && y<=n-1 && (mat[x-1][y]+mat[x-2][y]).equals("OS")){ //alttan dikey
                score+=1;
            }if(x<=n-3 && y<=n-3 && (mat[x+1][y+1]+mat[x+2][y+2]).equals("OS")) { //sağ alta çapraz
                score+=1;

            }if(x<=n-3 && y>=2 && (mat[x+1][y-1]+mat[x+2][y-2]).equals("OS")){  //sol alta çapraz
                score+=1;

            }if(x>=2 && y>=2 && (mat[x-1][y-1]+mat[x-2][y-2]).equals("OS")){  //sol üste çapraz
                System.out.println(mat[x-1][y-1]+mat[x-2][y-2]);
                score+=1;

            }if(x>=2 && y<=n-3 && (mat[x-1][y+1]+mat[x-2][y+2]).equals("OS")){  //sağ üste çapraz
                score+=1;

            }
            System.out.println(score);

        }
        System.out.println(score);
        return score;

    }
    public static void win(){
        if(score_player>score_pc){
            System.out.println("Oyuncu kazandı.");
        }else if(score_pc>score_player){
            System.out.println("Bilgisayar kazandı.");
        }else if(score_player==score_pc){
            System.out.println("Berabere bitti.");
        }
    }
}

